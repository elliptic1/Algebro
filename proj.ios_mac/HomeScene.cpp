#include "HomeScene.h"

USING_NS_CC;


Scene* HomeScene::createScene()
{
    // 'scene' is an autorelease object
    auto scene = Scene::create();
    
    // 'layer' is an autorelease object
    auto layer = HomeScene::create();

    // add layer as a child to scene
    scene->addChild(layer);

    // return the scene
    return scene;
}

// on "init" you need to initialize your instance
bool HomeScene::init()
{
    //////////////////////////////
    // 1. super init first
    if ( !Layer::init() )
    {
        return false;
    }
    
    Size visibleSize = Director::getInstance()->getVisibleSize();
    Point origin = Director::getInstance()->getVisibleOrigin();
    
    
    static Resource smallResource  =  { cocos2d::Size(480, 320),   "iphone" };
    static Resource mediumResource =  { cocos2d::Size(1024, 768),  "ipad"   };
    static Resource largeResource  =  { cocos2d::Size(2048, 1536), "ipadhd" };
    static cocos2d::Size designResolutionSize = cocos2d::Size(480, 320);


    /////////////////////////////
    // 2. add a menu item with "X" image, which is clicked to quit the program
    //    you may modify it.

    // add a "close" icon to exit the progress. it's an autorelease object
    auto closeItem = MenuItemImage::create(
                                           "CloseNormal.png",
                                           "CloseSelected.png",
                                           CC_CALLBACK_1(HomeScene::menuCloseCallback, this)
                                           );
    
	closeItem->setPosition(Point(origin.x + visibleSize.width - closeItem->getContentSize().width/2 ,
                                origin.y + closeItem->getContentSize().height/2));

    // create menu, it's an autorelease object
    auto closeButtonMenu = Menu::create(closeItem, NULL);
    closeButtonMenu->setPosition(Point(300,400));
    this->addChild(closeButtonMenu, 1);
    
    
    
    auto solveEquationsButton = MenuItemImage::create("solveEquationsButton.png", "solveEquationsButton.png",
                                                      CC_CALLBACK_1(HomeScene::solveEquationsButtonCallback, this));
    auto reduceFractionsButton = MenuItemImage::create("reduceFractionsButton.png", "reduceFractionsButton.png",
                                                       CC_CALLBACK_1(HomeScene::reduceFractionsButtonCallback, this));
    
    
    
    solveEquationsButton->setPosition(origin.x + visibleSize.width /2 - reduceFractionsButton->getContentSize().width /2,
                            origin.y + visibleSize.height/2 - reduceFractionsButton->getContentSize().height);

    reduceFractionsButton->setPosition(origin.x + visibleSize.width /2 + reduceFractionsButton->getContentSize().width /2,
                                       origin.y + visibleSize.height/2 - reduceFractionsButton->getContentSize().height);

    
    
    solveEquationsButton->setScale(0.7f);
    reduceFractionsButton->setScale(0.7f);
    
    
    
    
	    // create menu, it's an autorelease object
    auto solveEquationsButtonMenu = Menu::create(solveEquationsButton, NULL);
    solveEquationsButtonMenu->setPosition(Point::ZERO);
    this->addChild(solveEquationsButtonMenu, 1);
    
    
    // create menu, it's an autorelease object
    auto reduceFractionsButtonMenu = Menu::create(reduceFractionsButton, NULL);
    reduceFractionsButtonMenu->setPosition(Point::ZERO);
    this->addChild(reduceFractionsButtonMenu, 1);

    
    

    /////////////////////////////
    // 3. add your codes below...

    // add a label shows "Hello World"
    // create and initialize a label
    
//    auto label = LabelTTF::create("Algebro", "Arial", 44);
//    
//    // position the label on the center of the screen
//    label->setPosition(Point(origin.x + visibleSize.width/2,
//                            origin.y + visibleSize.height - label->getContentSize().height));
//
//    // add the label as a child to this layer
//    this->addChild(label, 1);

    // add "HelloWorld" splash screen"
    auto sprite = Sprite::create("Home@2x.png");

    // position the sprite on the center of the screen
    sprite->setPosition(Point(visibleSize.width/2 + origin.x, visibleSize.height/2 + origin.y));
    
    // add the sprite as a child to this layer
    this->addChild(sprite, 0);
    
    return true;
}

void HomeScene::reduceFractionsButtonCallback(Object* pSender) {
    Director::getInstance()->pushScene(ReduceFractionsScene::createScene());
}

void HomeScene::solveEquationsButtonCallback(Object* pSender) {
    Director::getInstance()->pushScene(SolveEquationsScene::createScene());
}

void HomeScene::menuCloseCallback(Object* pSender)
{
    Director::getInstance()->end();

#if (CC_TARGET_PLATFORM == CC_PLATFORM_IOS)
    exit(0);
#endif
}
