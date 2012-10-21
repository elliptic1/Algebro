package com.tbse.algebro;

import android.util.Log;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import java.util.UUID;

class Symbol extends AnimatedSprite {
    private MainGameActivity main;
    public boolean toBeDestroyed;
    private boolean isRemovable;

    public enum symbolTypeEnum {
        NUMBER, OPERATION, LEFT_PAREN, RIGHT_PAREN, VARIABLE, EQUALS, FRACTION_BAR
    }

    public symbolTypeEnum symbolType;
    public int symbolCode;
//    VelocityTracker velocityTracker;
    public Expression parentExpression;
    public Symbol leftNeighbor;
    public Symbol rightNeighbor;
    public TiledTextureRegion pTiledTextureRegion;
    private UUID uid;

    public Symbol(float pX, float pY, TiledTextureRegion pTiledTextureRegion,
                  MainGameActivity main, int symbolCode,
                  boolean isRemovable) {
        super(pX, pY, pTiledTextureRegion);
        setMain(main);
        this.symbolCode = symbolCode;
        if (symbolCode <= 9) {
            this.symbolType = symbolTypeEnum.NUMBER;
        }
        if (10 <= symbolCode && symbolCode <= 13) {
            this.symbolType = symbolTypeEnum.OPERATION;
        }
        if (14 == symbolCode ) {
            this.symbolType = symbolTypeEnum.LEFT_PAREN;
        }
        if (15 == symbolCode ) {
            this.symbolType = symbolTypeEnum.RIGHT_PAREN;
        }
        if (16 == symbolCode) {
            this.symbolType = symbolTypeEnum.FRACTION_BAR;
        }
        if (17 == symbolCode) {
            this.symbolType = symbolTypeEnum.VARIABLE;
        }
        if (18 == symbolCode) {
            this.symbolType = symbolTypeEnum.EQUALS;
        }
        this.toBeDestroyed = false;
        setIsRemovable(isRemovable);
        this.leftNeighbor = null;
        this.rightNeighbor = null;
        this.pTiledTextureRegion = pTiledTextureRegion;
        this.parentExpression = new Expression();
        getMain().addExpression(this.parentExpression);
        this.parentExpression.setMain(this.main);
        this.parentExpression.addSymbol(0, this);
        this.parentExpression.attachChildren();
        this.parentExpression.registerTouchAreas();
        this.setScale(getMain().getScaleFactor());
        this.uid = UUID.randomUUID();
    }

    @Override
    public String toString() {
        switch(symbolType) {
            case NUMBER:
                return ""+symbolCode;
            case LEFT_PAREN:
                return "(";
            case RIGHT_PAREN:
                return ")";
            case OPERATION:
                switch (symbolCode) {
                    case 10:
                        return "+";
                    case 11:
                        return "-";
                    case 12:
                        return "*";
                    case 13:
                        return "/";
                    default:
                        return "NO OP CODE";
                }
            case FRACTION_BAR:
                return "/";
            case VARIABLE:
                return "x";
            case EQUALS:
                return "=";
            default:
                return "";
        }
    }

    public UUID getUID() {
        return this.uid;
    }

    public int getIndex() {
        return this.parentExpression.getExpressionSymbols().lastIndexOf(this);
    }

    @Override
    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                 final float pTouchAreaLocalX, final float pTouchAreaLocalY) {

        this.parentExpression.onAreaTouched(pSceneTouchEvent, this);

        return true;
    }

    public void destroySelf() {

        if (isRemovable()) {
            getMain().getScene().unregisterTouchArea(this);
            getMain().getScene().detachChild(this);
        } else {
            Log.d("ab", "tried to remove a not removable symbol");
        }
    }

    public MainGameActivity getMain() {
        return this.main;
    }

    public void setMain(MainGameActivity main) {
        this.main = main;
    }

    public void setIsRemovable(boolean t) {
        this.isRemovable = t;
    }

    public boolean isRemovable() {
        return this.isRemovable;
    }

//	public symbolTypeEnum getSymbolType() {
//		return symbolType;
//	}

//	public void setSymbolType(symbolTypeEnum symbolType) {
//		this.symbolType = symbolType;
//	}

//	public boolean isANeighbor(Symbol s) {
//		if (this.leftNeighbor != null) {
//			if (this.leftNeighbor.equals(s)) {
//				return true;
//			}
//		}
//		if (this.rightNeighbor != null) {
//			if (this.rightNeighbor.equals(s)) {
//				return true;
//			}
//		}
//
//		return false;
//	}

//	public void setVelocityTracker(VelocityTracker vt) {
//		this.velocityTracker = vt;
//	}

//	public VelocityTracker getVelocityTracker() {
//		return this.velocityTracker;
//	}

}
