package com.tbse.algebro;

import android.util.Log;
import android.view.VelocityTracker;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.util.modifier.IModifier;
import org.anddev.andengine.util.modifier.IModifier.IModifierListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Expression {

    public boolean isConstant;
    public int value;
    public ArrayList<Symbol> symbols;
    public VelocityTracker velocityTracker;
    Vector2 touchToCorner;
    private MainGameActivity main;
    // private int touchDownSymbolCode;
    private int padding; // for small phone, moves icon out of under thumb
    private UUID uid;
    private long downtime;

    public void insertExpression(Expression beingMoved, int index) {

        // make this the parent of each of moving expression's symbols.
        for (Symbol s : beingMoved.getExpressionSymbols()) {
            s.parentExpression = this;
        }

        // remove it from the list of on-screen expressions
        getMain().removeExpressionFromScreen(beingMoved);

        // add symbols to internal list
        Log.d("ab", "adding moved symbols to list");
        Log.d("ab", "start: this expression is " + toString());
        if (index <= getExpressionSymbols().size()) {
            for (Symbol s : beingMoved.getExpressionSymbols()) {
                if (getExpressionSymbols().contains(s)) {
                    Log.e("ab", "Tried to add duplicate object " + s.toString() + " in getExpressionSymbols(), it's already " + toString());
                } else {
                    if (index+beingMoved.getExpressionSymbols().lastIndexOf(s) <= getExpressionSymbols().size()) {
                        getExpressionSymbols().add(index+beingMoved.getExpressionSymbols().lastIndexOf(s), s);
                    } else {
                        Log.e("ab", "tried to add symbol at index " + (index+beingMoved.getExpressionSymbols().lastIndexOf(s))
                                + " but there are only " + getExpressionSymbols().size() + " symbols" );
                    }
                }
            }

            // see if there are duplicates
            Set<Symbol> nodupes = new HashSet<Symbol>(getExpressionSymbols());
            if (nodupes.size() < getExpressionSymbols().size()) {
                Log.e("ab", "there are duplicate objects in getExpressionSymbols()");
            }

        } else {
            Log.e("ab", "trying to insert into a too-big index for this list");
            return;
        }
        Log.d("ab", "end: this expression is " + toString());

        redrawAt(getExpressionSymbols().get(0).getX(), getExpressionSymbols().get(0).getY());

    }

    public void redrawAt(float pToX, float pToY) {
        for (Symbol s : getExpressionSymbols()) {
            s.registerEntityModifier(
                    new MoveModifier(0.1f,
                            s.getX(),
                            pToX + s.getIndex() * getMain().getSymbolWidth(), // + i * w
                            s.getY(),
                            pToY,
                            MainGameActivity.EASEFUNCTIONS[0]));
        }
    }

    public boolean collidesWith(Expression e) {
        for (Symbol s : getExpressionSymbols()) {
            for (Symbol t : e.getExpressionSymbols()) {
                if (s.getUID() != t.getUID()) {
                    if (s.collidesWith(t))
                        return true;
                }
            }
        }
        return false;
    }

//    public int getPrevExpCount() {
//        return this.prevExpCount;
//    }
//
//    public void setPrevExpCount(int a) {
//        this.prevExpCount = a;
//    }

    private enum NEXT_IN_TOUCH_CYCLE {
        TOUCH_DOWN, TOUCH_UP
    }

    NEXT_IN_TOUCH_CYCLE nextInTouchCycle;

    public ArrayList<Symbol> getExpressionSymbols() {
        return this.symbols;
    }

    public boolean isRemovable() {
        if (getExpressionSymbols() != null) {
            if (getExpressionSymbols().size() >= 1) {
                return getExpressionSymbols().get(0).isRemovable();
            }
        }
        return false;
    }

    public Expression() {
        this.symbols = new ArrayList<Symbol>(0);
        this.touchToCorner = new Vector2(0, 0);
        this.nextInTouchCycle = NEXT_IN_TOUCH_CYCLE.TOUCH_DOWN;
        this.padding = 0; // touch padding
        this.uid = UUID.randomUUID();
    }

    public UUID getUID() {
        return this.uid;
    }

//    public int getValue() {
//        return this.value;
//    }

    public MainGameActivity getMain() {
        if (this.main == null) {
            Log.e("ab", "trying to get main but it's null");
        }
        return this.main;
    }

    public void setMain(MainGameActivity m) {
        this.main = m;
    }

    public Scene getScene() {
        return getMain().getScene();
    }

    public boolean isInASpecialArea(float x, float y) {
        for (SpecialArea a : getMain().getSpecialAreas()) {
            if (a.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInASpecialArea(TouchEvent e) {
        return isInASpecialArea(e.getX(), e.getY());
    }

    public float getSpeed() {
        getVelocityTracker().computeCurrentVelocity(1000, 10000f); // pixels per
        // second
        return new Vector2(getVelocityTracker().getXVelocity(),
                getVelocityTracker().getYVelocity()).len();

    }

    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, Symbol symbol) {

        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {

            if (this.nextInTouchCycle != NEXT_IN_TOUCH_CYCLE.TOUCH_DOWN) {
                Log.d("ab", "down is out of cycle");
                return false;
            } else {
                this.nextInTouchCycle = NEXT_IN_TOUCH_CYCLE.TOUCH_UP;
            }

            this.downtime = System.currentTimeMillis();
            // Log.d("ab", "down at " + downtime);

            main.setExpressionCurrentlyBeingDragged(this);

            // Log.d("ab", "getting new vel tracker");
            getNewVelocityTracker();

            getVelocityTracker().addMovement(pSceneTouchEvent.getMotionEvent());

            symbol.setIsRemovable(true); // needed?

            if (isInASpecialArea(pSceneTouchEvent)) {
                // Log.d("ab", "is in a special area");

                touchToCorner = new Vector2(symbol.getX()
                        - pSceneTouchEvent.getX(), symbol.getY()
                        - pSceneTouchEvent.getY());

                // The copy stays behind
                Symbol symbolCopy = new Symbol(symbol.getX(), symbol.getY(),
                        symbol.pTiledTextureRegion, symbol.getMain(),
                        symbol.symbolCode, false);
                symbolCopy.parentExpression = new Expression();
                symbolCopy.parentExpression.setMain(getMain());
                symbolCopy.parentExpression.addSymbol(0, symbolCopy);
                return true;

            } else { // symbol touched on the board, should already be in an
                // expression, use getX() and getY() for it
                touchToCorner = new Vector2(getX() - pSceneTouchEvent.getX(),
                        getY() - pSceneTouchEvent.getY());

                // if the screen is small, push it up and left so it won't
                // be under the finger.
            }

        } else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE) {
            if (this.nextInTouchCycle != NEXT_IN_TOUCH_CYCLE.TOUCH_UP) {
                Log.d("ab", "move is out of cycle ");
                return false;
            }

            main.setExpressionCurrentlyBeingDragged(this);

            getVelocityTracker().addMovement(pSceneTouchEvent.getMotionEvent());
            getVelocityTracker().computeCurrentVelocity(1000, 10000f);

//            float factor = 50.0f / (1 + (float) Math
//                    .exp(-(getSpeed() - 150) / 2));
//            if (factor < 0.1)
//                factor = 0.0f;

//            Log.d("ab", "current velocity is (" + getVelocityTracker().getXVelocity() + ", "
//            + getVelocityTracker().getYVelocity() + ")");

            //to make up for drag lag
            float addX = 0;
            float addY = 0;
            float xv = getVelocityTracker().getXVelocity();
            float yv = getVelocityTracker().getYVelocity();
            double speed = Math.pow(Math.pow(xv,2) + Math.pow(yv,2),0.5f);
//            if (speed > 500) {
//                float factor = 0.05f;
//                addX = xv * factor;
//                addY = yv * factor;
////                Log.d("ab", "speed = "+speed+", added ("+addX+","+addY+")");
//            }

            if (getExpressionSymbols().size() == 0) {
                Log.e("ab", "trying to move an expression with no symbols");
            }

            // if the expression is being dragged fast enough, skip some frames
                if (Math.random() <=

                        1- 1/ ( 1 + Math.pow(2.71,-(speed-600)/120))

                        ) {
                redrawAt(pSceneTouchEvent.getX() + touchToCorner.x + padding + addX,
                    pSceneTouchEvent.getY() + touchToCorner.y + padding + addY);
                }

        } else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {

            if (this.nextInTouchCycle != NEXT_IN_TOUCH_CYCLE.TOUCH_UP) {
                Log.d("ab", "up is out of cycle");
                return false;
            } else {
                this.nextInTouchCycle = NEXT_IN_TOUCH_CYCLE.TOUCH_DOWN;
            }

            main.setExpressionCurrentlyBeingDragged(this);

            getMain().checkForCollisions();

            long uptime = System.currentTimeMillis();
            // Log.d("ab", "up at " + uptime);
            // Log.d("ab", "diff of " + (uptime - this.downtime));

            if (isInASpecialArea(pSceneTouchEvent)) {
                if (uptime - this.downtime < 200) { // is a click

                    // Is in a special area and user has clicked on a tile,
                    // put it out in the middle.

                    Symbol symbolCopy = new Symbol(
                            getMain().getCameraWidth() >> 1, getMain()
                            .getCameraHeight() >> 1,
                            symbol.pTiledTextureRegion, symbol.getMain(),
                            symbol.symbolCode, true);
                    symbolCopy.parentExpression = new Expression();
                    symbolCopy.parentExpression.setMain(getMain());
                    symbolCopy.parentExpression.addSymbol(0, symbolCopy);

                    // else it's above the keyboard or the trash can

                }
                Log.d("ab", "is in a special area");
                destroySelf();

            } else {
                // got a symbol already on the screen

                getVelocityTracker().addMovement(
                        pSceneTouchEvent.getMotionEvent());

                if (symbol.isRemovable()) {
                    getVelocityTracker().computeCurrentVelocity(1000, 10000f);
                    if (getSpeed() > 150) {

                        MoveModifier mm = new MoveModifier(0.1f, getX(), getX()
                                + 0.1f * getVelocityTracker().getXVelocity(),
                                getY(), getY() + 0.1f
                                * getVelocityTracker().getYVelocity(),
                                MainGameActivity.EASEFUNCTIONS[0]);

                        mm.addModifierListener(new IModifierListener<IEntity>() {

                            @Override
                            public void onModifierStarted(
                                    IModifier<IEntity> arg0, IEntity arg1) {

                            }

                            @Override
                            public void onModifierFinished(
                                    IModifier<IEntity> arg0, IEntity arg1) {

                                if (isInASpecialArea(getX(), getY())) {
                                    Log.d("ab", "is in onModifierFinished");
                                    destroySelf();
                                } else {
                                    getMain().checkForCollisions();
                                    getMain().resetExpressionCurrentlyBeingDragged();
                                }

                            }
                        });
                        registerEntityModifiers(mm);
                    }
                }
                getVelocityTracker().recycle();

            }

        }

        return true;
    }

    private VelocityTracker getVelocityTracker() {
        return this.velocityTracker;
    }

    private void registerEntityModifiers(MoveModifier m) {
        for (Symbol s : getExpressionSymbols()) {
            s.registerEntityModifier(m);
        }
    }

    public float getX() {
        if (getExpressionSymbols().size() == 0) {
            Log.e("ab",
                    "there are no symbols in the expression, can't give getX()");
            return 1;
        }
        return getExpressionSymbols().get(0).getX();
    }

    public float getY() {
        if (getExpressionSymbols().size() == 0) {
            Log.e("ab",
                    "there are no symbols in the expression, can't give getY()");
            return 1;
        }

        return getExpressionSymbols().get(0).getY();
    }

    public void destroySelf() {
        if (isRemovable()) {
            Log.d("ab", "size of expressions is "
                    + getMain().getOnScreenExpressions().size());
            getMain().getExpressions().remove(this);
            Log.d("ab", "[rem] size of expressions is "
                    + getMain().getOnScreenExpressions().size());
            for (final Symbol s : getExpressionSymbols()) {
                s.getMain().getScene().postRunnable(new Runnable() {

                    @Override
                    public void run() {
                        s.destroySelf();
                    }
                });
            }
        } else {
            Log.e("ab", "tried to destroy an immovable object");
        }
    }

    public boolean equals(Expression e) {
        if (getUID() == e.getUID()) {
            return true;
        } else {
            return false;
        }
    }

    public VelocityTracker getNewVelocityTracker() {
        while (getVelocityTracker() == null) {
            // Log.d("ab", "trying again to get a velocity tracker");
            setVelocityTracker(VelocityTracker.obtain());
        }
        return getVelocityTracker();
    }

    public void setVelocityTracker(VelocityTracker vt) {
        this.velocityTracker = vt;
    }

    public Expression addSymbol(int index, Symbol symbol) {
        if (!getExpressionSymbols().contains(symbol)) {
            getExpressionSymbols().add(index, symbol);
        }
        return this;
    }

    public void attachChildren() {
        for (Symbol s : getExpressionSymbols()) {
            if (s.hasParent() == false) {
                getScene().attachChild(s);
                // Log.d("ab", "attachChildren, attaching one");
            } else {
                // Log.d("ab", "attachChildren, has a parent already");
            }
        }
    }

    public void registerTouchAreas() {
        for (Symbol s : getExpressionSymbols()) {
            getScene().registerTouchArea(s);
        }
    }

    public float getMass() {
        return getExpressionSymbols().size();
    }

    @Override
    public String toString() {
        String returnString = "";
        for (Symbol s : getExpressionSymbols()) {
            returnString += s.toString();
        }
        return returnString;
    }

}
