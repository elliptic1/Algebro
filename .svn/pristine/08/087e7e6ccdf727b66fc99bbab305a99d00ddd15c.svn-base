package com.tbse.algebro;

import android.content.res.Configuration;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.modifier.ease.EaseExponentialOut;
import org.anddev.andengine.util.modifier.ease.IEaseFunction;

import java.util.ArrayList;
import java.util.Arrays;

public class MainGameActivity extends BaseGameActivity {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private Scene scene;
    private Engine engine;

    static int CAMERA_WIDTH;
    static int CAMERA_HEIGHT;
    private Expression expressionCurrentlyBeingDragged;
    // protected static final int MENU_HOME = 1;

    private TiledTextureRegion mTrashCornerTextureRegion;
    private TiledTextureRegion mXTextureRegion;
    private TiledTextureRegion mNumber0TextureRegion;
    private TiledTextureRegion mNumber1TextureRegion;
    private TiledTextureRegion mNumber2TextureRegion;
    private TiledTextureRegion mNumber3TextureRegion;
    private TiledTextureRegion mNumber4TextureRegion;
    private TiledTextureRegion mNumber5TextureRegion;
    private TiledTextureRegion mNumber6TextureRegion;
    private TiledTextureRegion mNumber7TextureRegion;
    private TiledTextureRegion mNumber8TextureRegion;
    private TiledTextureRegion mNumber9TextureRegion;
    private TiledTextureRegion mOpPlusTextureRegion;
    private TiledTextureRegion mOpMinusTextureRegion;
    private TiledTextureRegion mOpMultiplyTextureRegion;
    private TiledTextureRegion mOpDivideTextureRegion;
    private TiledTextureRegion mOpEqualsTextureRegion;
    private TiledTextureRegion mLeftParenTextureRegion;
    private TiledTextureRegion mRightParenTextureRegion;
    private TiledTextureRegion mFractionBarTextureRegion;
    private TiledTextureRegion mResetButtonTextureRegion;

    private ArrayList<SpecialArea> specialAreas;
    public ArrayList<Expression> expressions;
    public float scaleFactor;

    public static final IEaseFunction[] EASEFUNCTIONS = new IEaseFunction[]{EaseExponentialOut
            .getInstance()};

    // EaseElasticOut.getInstance(),
    // EaseLinear.getInstance(), EaseBackIn.getInstance(),
    // EaseBackOut.getInstance(), EaseBackInOut.getInstance(),
    // EaseBounceIn.getInstance(), EaseBounceOut.getInstance(),
    // EaseBounceInOut.getInstance(), EaseCircularIn.getInstance(),
    // EaseCircularOut.getInstance(), EaseCircularInOut.getInstance(),
    // EaseCubicIn.getInstance(), EaseCubicOut.getInstance(),
    // EaseCubicInOut.getInstance(), EaseElasticIn.getInstance(),
    // EaseElasticInOut.getInstance(), EaseExponentialIn.getInstance(),
    // EaseExponentialInOut.getInstance(), EaseQuadIn.getInstance(),
    // EaseQuadOut.getInstance(), EaseQuadInOut.getInstance(),
    // EaseQuartIn.getInstance(), EaseQuartOut.getInstance(),
    // EaseQuartInOut.getInstance(), EaseQuintIn.getInstance(),
    // EaseQuintOut.getInstance(), EaseQuintInOut.getInstance(),
    // EaseSineIn.getInstance(), EaseSineOut.getInstance(),
    // EaseSineInOut.getInstance(), EaseStrongIn.getInstance(),
    // EaseStrongOut.getInstance(), EaseStrongInOut.getInstance() };

    @Override
    public void onLoadComplete() {

    }

    @SuppressWarnings("deprecation")
    private static Point getDisplaySize(final Display display) {
        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        return point;
    }

    @Override
    public org.anddev.andengine.engine.Engine onLoadEngine() {
        final Display display = getWindowManager().getDefaultDisplay();
        Point screenSize = getDisplaySize(display);
        CAMERA_WIDTH = screenSize.x;
        CAMERA_HEIGHT = screenSize.y;
        ZoomCamera mCamera = new ZoomCamera(0, 0, getCameraWidth(), getCameraHeight());

        System.loadLibrary("gdx");

        EngineOptions options = new EngineOptions(true,
                ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(
                getCameraWidth(), getCameraHeight()), mCamera);

        options.getTouchOptions().enableRunOnUpdateThread();

        this.engine = new org.anddev.andengine.engine.Engine(options);
        return engine;

    }

    @Override
    public void onLoadResources() {

        BitmapTextureAtlas number0Texture;
        BitmapTextureAtlas number1Texture;
        BitmapTextureAtlas number2Texture;
        BitmapTextureAtlas number3Texture;
        BitmapTextureAtlas number4Texture;
        BitmapTextureAtlas number5Texture;
        BitmapTextureAtlas number6Texture;
        BitmapTextureAtlas number7Texture;
        BitmapTextureAtlas number8Texture;
        BitmapTextureAtlas number9Texture;
        BitmapTextureAtlas trashCornerTexture;
        BitmapTextureAtlas xTexture;
        BitmapTextureAtlas opPlusTexture;
        BitmapTextureAtlas opMinusTexture;
        BitmapTextureAtlas opMultiplyTexture;
        BitmapTextureAtlas opDivideTexture;
        BitmapTextureAtlas opEqualsTexture;
        BitmapTextureAtlas leftParenTexture;
        BitmapTextureAtlas rightParenTexture;
        BitmapTextureAtlas fractionBarTexture;
        BitmapTextureAtlas resetButtonTexture;

        xTexture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        number0Texture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        number1Texture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        number2Texture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        number3Texture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        number4Texture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        number5Texture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        number6Texture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        number7Texture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        number8Texture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        number9Texture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        opPlusTexture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        opMinusTexture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        opMultiplyTexture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        opDivideTexture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        leftParenTexture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        rightParenTexture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        fractionBarTexture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        opEqualsTexture = new BitmapTextureAtlas(128, 128,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        resetButtonTexture = new BitmapTextureAtlas(512, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        trashCornerTexture = new BitmapTextureAtlas(512, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        this.setmTrashCornerTextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(trashCornerTexture, this,
                        "gfx/trashcorner.png", 0, 0, 2, 1));
        this.setmXTextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(xTexture, this, "gfx/x.png", 0, 0,
                        1, 1));
        this.setmNumber0TextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(number0Texture, this,
                        "gfx/number0.png", 0, 0, 1, 1));
        this.setmNumber1TextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(number1Texture, this,
                        "gfx/number1.png", 0, 0, 1, 1));
        this.setmNumber2TextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(number2Texture, this,
                        "gfx/number2.png", 0, 0, 1, 1));
        this.setmNumber3TextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(number3Texture, this,
                        "gfx/number3.png", 0, 0, 1, 1));
        this.setmNumber4TextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(number4Texture, this,
                        "gfx/number4.png", 0, 0, 1, 1));
        this.setmNumber5TextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(number5Texture, this,
                        "gfx/number5.png", 0, 0, 1, 1));
        this.setmNumber6TextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(number6Texture, this,
                        "gfx/number6.png", 0, 0, 1, 1));
        this.setmNumber7TextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(number7Texture, this,
                        "gfx/number7.png", 0, 0, 1, 1));
        this.setmNumber8TextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(number8Texture, this,
                        "gfx/number8.png", 0, 0, 1, 1));
        this.setmNumber9TextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(number9Texture, this,
                        "gfx/number9.png", 0, 0, 1, 1));
        this.setmOpPlusTextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(opPlusTexture, this,
                        "gfx/opplus.png", 0, 0, 1, 1));
        this.setmOpMinusTextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(opMinusTexture, this,
                        "gfx/opminus.png", 0, 0, 1, 1));
        this.setmOpMultiplyTextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(opMultiplyTexture, this,
                        "gfx/opmultiply.png", 0, 0, 1, 1));
        this.setmOpDivideTextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(opDivideTexture, this,
                        "gfx/opdivide.png", 0, 0, 1, 1));
        this.setmLeftParenTextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(leftParenTexture, this,
                        "gfx/leftparen.png", 0, 0, 1, 1));
        this.setmRightParenTextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(rightParenTexture, this,
                        "gfx/rightparen.png", 0, 0, 1, 1));
        this.setmFractionBarTextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(fractionBarTexture, this,
                        "gfx/fractionbar.png", 0, 0, 1, 1));
        this.setmOpEqualsTextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(opEqualsTexture, this,
                        "gfx/opequals.png", 0, 0, 1, 1));
        this.setmResetButtonTextureRegion(BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(resetButtonTexture, this,
                        "gfx/resetbutton.png", 0, 0, 2, 1));

        this.mEngine.getTextureManager().loadTexture(xTexture);
        this.mEngine.getTextureManager().loadTexture(trashCornerTexture);
        this.mEngine.getTextureManager().loadTexture(number0Texture);
        this.mEngine.getTextureManager().loadTexture(number1Texture);
        this.mEngine.getTextureManager().loadTexture(number2Texture);
        this.mEngine.getTextureManager().loadTexture(number3Texture);
        this.mEngine.getTextureManager().loadTexture(number4Texture);
        this.mEngine.getTextureManager().loadTexture(number5Texture);
        this.mEngine.getTextureManager().loadTexture(number6Texture);
        this.mEngine.getTextureManager().loadTexture(number7Texture);
        this.mEngine.getTextureManager().loadTexture(number8Texture);
        this.mEngine.getTextureManager().loadTexture(number9Texture);
        this.mEngine.getTextureManager().loadTexture(opPlusTexture);
        this.mEngine.getTextureManager().loadTexture(opMinusTexture);
        this.mEngine.getTextureManager().loadTexture(opMultiplyTexture);
        this.mEngine.getTextureManager().loadTexture(opDivideTexture);
        this.mEngine.getTextureManager().loadTexture(opEqualsTexture);
        this.mEngine.getTextureManager().loadTexture(leftParenTexture);
        this.mEngine.getTextureManager().loadTexture(rightParenTexture);
        this.mEngine.getTextureManager().loadTexture(fractionBarTexture);
        this.mEngine.getTextureManager().loadTexture(resetButtonTexture);

        this.expressions = new ArrayList<Expression>(0);

    }

    public Symbol addPaletteSymbol(float x, float y, int symbolCode) {
        Symbol symbol;
        TiledTextureRegion t;

        switch (symbolCode) {
            case 0:
                t = getmNumber0TextureRegion();
                break;
            case 1:
                t = getmNumber1TextureRegion();
                break;
            case 2:
                t = getmNumber2TextureRegion();
                break;
            case 3:
                t = getmNumber3TextureRegion();
                break;
            case 4:
                t = getmNumber4TextureRegion();
                break;
            case 5:
                t = getmNumber5TextureRegion();
                break;
            case 6:
                t = getmNumber6TextureRegion();
                break;
            case 7:
                t = getmNumber7TextureRegion();
                break;
            case 8:
                t = getmNumber8TextureRegion();
                break;
            case 9:
                t = getmNumber9TextureRegion();
                break;
            case 10:
                t = getmOpPlusTextureRegion();
                break;
            case 11:
                t = getmOpMinusTextureRegion();
                break;
            case 12:
                t = getmOpMultiplyTextureRegion();
                break;
            case 13:
                t = getmOpDivideTextureRegion();
                break;
            case 14:
                t = getmLeftParenTextureRegion();
                break;
            case 15:
                t = getmRightParenTextureRegion();
                break;
            case 16:
                t = getmFractionBarTextureRegion();
                break;
            case 17:
                t = getmXTextureRegion();
                break;
            case 18:
                t = getmOpEqualsTextureRegion();
                break;
            default:
                Log.e("ab", "returning default texture region 0");
                t = getmNumber0TextureRegion();
        }
        symbol = new Symbol(x, y, t, this, symbolCode, false);
        symbol.setScale(getScaleFactor());

        return symbol;

    }

    public int getCameraWidth() {
        return MainGameActivity.CAMERA_WIDTH;
    }

    public int getCameraHeight() {
        return MainGameActivity.CAMERA_HEIGHT;
    }

    @Override
    public Scene onLoadScene() {

        // this.createMenuScene();

        final Scene scene = new Scene();
        setScene(scene);
        getScene().setBackground(
                new ColorBackground(176 / 255f, 226 / 255f, 255 / 255f)); // lightskyblue

        // // add Palette options to screen

        // Calculate starting x and y
        int A = 100;
        setScaleFactor(1f);
        if (getCameraWidth() - 10 * A < 0) {
            setScaleFactor(0.7f);
            A = Math.round(A * getScaleFactor());
        }
        int spacer = Math.round(10 * getScaleFactor());
        int numbersRowStartingX = (getCameraWidth() - 10 * (A + spacer) - spacer) >> 1;
        int numbersRowStartingY = getCameraHeight() - 2 * (A + spacer) - spacer;
        int symbolsRowStartingX = (getCameraWidth() - 9 * (A + spacer) - spacer) >> 1;
        int symbolsRowStartingY = numbersRowStartingY + (A + spacer) - spacer;

        SpecialArea paletteArea, trashArea;

        paletteArea = new SpecialArea(numbersRowStartingX,
                numbersRowStartingX + 10 * (A + spacer) + spacer,
                numbersRowStartingY, symbolsRowStartingY + (A + spacer)
                + spacer);
        trashArea = new SpecialArea(-150, 130, -150, 130);

        this.setSpecialAreas(new ArrayList<SpecialArea>(Arrays.asList(
                paletteArea, trashArea)));

        addPaletteSymbols(numbersRowStartingX, numbersRowStartingY,
                symbolsRowStartingX, symbolsRowStartingY, A, scaleFactor);

        TiledSprite resetButton = new TiledSprite(0, 110,
                getmResetButtonTextureRegion()) {

            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX, final float pTouchAreaLocalY) {

                if (pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    this.setCurrentTileIndex(1); // swap pics
                    resetExpressionCurrentlyBeingDragged();
                    Log.d("ab", "reset");
                    if (getOnScreenExpressions() != null) {
                        if (getOnScreenExpressions().size() >= 1) {
                            for (final Expression e : getOnScreenExpressions()) {
                                runOnUpdateThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        e.destroySelf();
                                    }
                                });

                            }
                        } else {
                            Log.d("ab", "no expressions");
                        }
                    } else {
                        Log.d("ab", "exprs are null ");
                    }
                } else if (pSceneTouchEvent.getAction() == MotionEvent.ACTION_UP) {
                    this.setCurrentTileIndex(0);

                }
                return true;
            }

        };

        TiledSprite trashAreaSprite = new TiledSprite(0, -10,
                getmTrashCornerTextureRegion()) {

            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX, final float pTouchAreaLocalY) {

                if (pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN
                        || pSceneTouchEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    this.setCurrentTileIndex(1); // swap pics
                    resetExpressionCurrentlyBeingDragged();

                } else if (pSceneTouchEvent.getAction() == MotionEvent.ACTION_UP) {
                    this.setCurrentTileIndex(0);

                }
                return true;
            }
        };

        getScene().attachChild(resetButton);
        getScene().registerTouchArea(resetButton);
        getScene().attachChild(trashAreaSprite);
        getScene().registerTouchArea(trashAreaSprite);
        getScene().setTouchAreaBindingEnabled(true);

        return getScene();
    }

    public ArrayList<Expression> getExpressions() {
        return this.expressions;
    }

    public void removeExpressionFromScreen(Expression e) {
        if (getExpressions() != null) {
            getExpressions().remove(e);
        }
    }

    public ArrayList<Expression> getOnScreenExpressions() {
        ArrayList<Expression> temp = new ArrayList<Expression>(0);
        if (getExpressions() != null) {
            if (getExpressions().size() > 1) {
                for (Expression e : getExpressions()) {
                    if (e.isRemovable()) {
                        temp.add(e);
                    }
                }
            }
        }
        return temp;
    }

    public void checkForCollisions() {
        if (getOnScreenExpressions() != null) {
            if (getOnScreenExpressions().size() >= 2) {

                ArrayList<Expression> currentExpressions = new ArrayList<Expression>(
                        getOnScreenExpressions());

                Log.d("ab", "There are " + currentExpressions.size()
                        + " expressions on the screen. "
                        + "\nChecking if something is being dragged.");

                // make sure something is being dragged
                if (getExpressionCurrentlyBeingDragged() != null) {
                    Log.d("ab", getExpressionCurrentlyBeingDragged()
                            .toString()
                            + " is being dragged.");

                    for (Expression e : currentExpressions) {
                        Log.d("ab", "ose: " + e.toString());
                        if (e.equals(getExpressionCurrentlyBeingDragged())) {
                            continue;
                        }

                        if (e.collidesWith(getExpressionCurrentlyBeingDragged())) {
                            // get index of insert
                            float diff = getExpressionCurrentlyBeingDragged()
                                    .getX() - e.getX();
                            byte index = 0;
                            if (diff > 0) {
                                double numberSymbolsCouldFit = (double) diff / getSymbolWidth();
                                double floor = Math.floor(numberSymbolsCouldFit);
                                index = (byte) Math.round(floor + 1);
                            }
                            Log.d("ab", "Inserted exp. dragged into " + e.toString() + " at index "

                                    + index);

                            e.insertExpression(
                                    getExpressionCurrentlyBeingDragged(), index);

                        } else {
//							Log.d("ab", "They don't collide.");
                        }

                    }
                }
            }
        }
    }

    private void addPaletteSymbols(int numbersRowStartingX,
                                   int numbersRowStartingY, int symbolsRowStartingX,
                                   int symbolsRowStartingY, int A, float scale) {
        addPaletteSymbol(numbersRowStartingX, numbersRowStartingY, 0)
                .setScale(scale);
        addPaletteSymbol(numbersRowStartingX + A, numbersRowStartingY, 1)
                .setScale(scale);
        addPaletteSymbol(numbersRowStartingX + 2 * A, numbersRowStartingY, 2)
                .setScale(scale);
        addPaletteSymbol(numbersRowStartingX + 3 * A, numbersRowStartingY, 3)
                .setScale(scale);
        addPaletteSymbol(numbersRowStartingX + 4 * A, numbersRowStartingY, 4)
                .setScale(scale);
        addPaletteSymbol(numbersRowStartingX + 5 * A, numbersRowStartingY, 5)
                .setScale(scale);
        addPaletteSymbol(numbersRowStartingX + 6 * A, numbersRowStartingY, 6)
                .setScale(scale);
        addPaletteSymbol(numbersRowStartingX + 7 * A, numbersRowStartingY, 7)
                .setScale(scale);
        addPaletteSymbol(numbersRowStartingX + 8 * A, numbersRowStartingY, 8)
                .setScale(scale);
        addPaletteSymbol(numbersRowStartingX + 9 * A, numbersRowStartingY, 9)
                .setScale(scale);
        addPaletteSymbol(symbolsRowStartingX, symbolsRowStartingY, 10)
                .setScale(scale);
        addPaletteSymbol(symbolsRowStartingX + A, symbolsRowStartingY, 11)
                .setScale(scale);
        addPaletteSymbol(symbolsRowStartingX + 2 * A, symbolsRowStartingY, 12)
                .setScale(scale);
        addPaletteSymbol(symbolsRowStartingX + 3 * A, symbolsRowStartingY, 13)
                .setScale(scale);
        addPaletteSymbol(symbolsRowStartingX + 4 * A, symbolsRowStartingY, 14)
                .setScale(scale);
        addPaletteSymbol(symbolsRowStartingX + 5 * A, symbolsRowStartingY, 15)
                .setScale(scale);
        addPaletteSymbol(symbolsRowStartingX + 6 * A,
                symbolsRowStartingY + 0.3f * A, 16).setScale(scale); // ---
        // fraction
        // bar
        addPaletteSymbol(symbolsRowStartingX + 7 * A, symbolsRowStartingY, 17)
                .setScale(scale);
        addPaletteSymbol(symbolsRowStartingX + 8 * A, symbolsRowStartingY, 18)
                .setScale(scale);

    }

    public void addExpression(Expression e) {
        this.expressions.add(e);
    }

    public Scene getScene() {
        return this.scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Engine getEngine() {
        return engine;
    }

    public TiledTextureRegion getmTrashCornerTextureRegion() {
        return mTrashCornerTextureRegion;
    }

    public TiledTextureRegion getmNumber0TextureRegion() {
        return mNumber0TextureRegion;
    }

    public TiledTextureRegion getmNumber1TextureRegion() {
        return mNumber1TextureRegion;
    }

    public TiledTextureRegion getmNumber2TextureRegion() {
        return mNumber2TextureRegion;
    }

    public TiledTextureRegion getmNumber3TextureRegion() {
        return mNumber3TextureRegion;
    }

    public TiledTextureRegion getmNumber4TextureRegion() {
        return mNumber4TextureRegion;
    }

    public TiledTextureRegion getmNumber5TextureRegion() {
        return mNumber5TextureRegion;
    }

    public TiledTextureRegion getmNumber6TextureRegion() {
        return mNumber6TextureRegion;
    }

    public TiledTextureRegion getmNumber7TextureRegion() {
        return mNumber7TextureRegion;
    }

    public TiledTextureRegion getmNumber8TextureRegion() {
        return mNumber8TextureRegion;
    }

    public TiledTextureRegion getmNumber9TextureRegion() {
        return mNumber9TextureRegion;
    }

    public TiledTextureRegion getmOpPlusTextureRegion() {
        return mOpPlusTextureRegion;
    }

    public TiledTextureRegion getmOpMinusTextureRegion() {
        return mOpMinusTextureRegion;
    }

    public TiledTextureRegion getmOpMultiplyTextureRegion() {
        return mOpMultiplyTextureRegion;
    }

    public TiledTextureRegion getmOpDivideTextureRegion() {
        return mOpDivideTextureRegion;
    }

    public TiledTextureRegion getmOpEqualsTextureRegion() {
        return mOpEqualsTextureRegion;
    }

    public TiledTextureRegion getmLeftParenTextureRegion() {
        return mLeftParenTextureRegion;
    }

    public TiledTextureRegion getmRightParenTextureRegion() {
        return mRightParenTextureRegion;
    }

    public TiledTextureRegion getmFractionBarTextureRegion() {
        return mFractionBarTextureRegion;
    }

    public TiledTextureRegion getmResetButtonTextureRegion() {
        return mResetButtonTextureRegion;
    }

    public void setmNumber1TextureRegion(TiledTextureRegion mFaceTextureRegion) {
        this.mNumber1TextureRegion = mFaceTextureRegion;
    }

    private void setmFractionBarTextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mFractionBarTextureRegion = createTiledFromAsset;
    }

    private void setmRightParenTextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mRightParenTextureRegion = createTiledFromAsset;
    }

    private void setmLeftParenTextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mLeftParenTextureRegion = createTiledFromAsset;
    }

    private void setmOpDivideTextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mOpDivideTextureRegion = createTiledFromAsset;
    }

    private void setmOpMultiplyTextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mOpMultiplyTextureRegion = createTiledFromAsset;
    }

    private void setmOpMinusTextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mOpMinusTextureRegion = createTiledFromAsset;
    }

    private void setmOpEqualsTextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mOpEqualsTextureRegion = createTiledFromAsset;
    }

    private void setmTrashCornerTextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mTrashCornerTextureRegion = createTiledFromAsset;
    }

    private void setmResetButtonTextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mResetButtonTextureRegion = createTiledFromAsset;
    }

    private void setmOpPlusTextureRegion(TiledTextureRegion createTiledFromAsset) {
        this.mOpPlusTextureRegion = createTiledFromAsset;
    }

    private void setmNumber9TextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mNumber9TextureRegion = createTiledFromAsset;
    }

    private void setmNumber8TextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mNumber8TextureRegion = createTiledFromAsset;
    }

    private void setmNumber7TextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mNumber7TextureRegion = createTiledFromAsset;
    }

    private void setmNumber6TextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mNumber6TextureRegion = createTiledFromAsset;
    }

    private void setmNumber5TextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mNumber5TextureRegion = createTiledFromAsset;
    }

    private void setmNumber4TextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mNumber4TextureRegion = createTiledFromAsset;
    }

    private void setmNumber3TextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mNumber3TextureRegion = createTiledFromAsset;
    }

    private void setmNumber0TextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mNumber0TextureRegion = createTiledFromAsset;
    }

    private void setmNumber2TextureRegion(
            TiledTextureRegion createTiledFromAsset) {
        this.mNumber2TextureRegion = createTiledFromAsset;
    }

    public TiledTextureRegion getmXTextureRegion() {
        return mXTextureRegion;
    }

    public void setmXTextureRegion(TiledTextureRegion mXTextureRegion) {
        this.mXTextureRegion = mXTextureRegion;
    }

    public ArrayList<SpecialArea> getSpecialAreas() {
        return specialAreas;
    }

    public void setSpecialAreas(ArrayList<SpecialArea> specialAreas) {
        this.specialAreas = specialAreas;
    }

    public float getScaleFactor() {
        return this.scaleFactor;
    }

    public void setScaleFactor(float s) {
        this.scaleFactor = s;
    }

    public void setExpressionCurrentlyBeingDragged(Expression e) {
        this.expressionCurrentlyBeingDragged = e;
    }

    public void resetExpressionCurrentlyBeingDragged() {
        this.expressionCurrentlyBeingDragged = null;
    }

    public Expression getExpressionCurrentlyBeingDragged() {
        return this.expressionCurrentlyBeingDragged;
    }

    public float getSymbolWidth() {
        return 100 * this.getScaleFactor();
    }

}
