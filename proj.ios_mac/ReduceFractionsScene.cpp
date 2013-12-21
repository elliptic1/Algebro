#include "ReduceFractionsScene.h"

USING_NS_CC;


Scene* ReduceFractionsScene::createScene()
{
    // 'scene' is an autorelease object
    auto scene = Scene::create();
    
    // 'layer' is an autorelease object
    auto layer = ReduceFractionsScene::create();
    
    // add layer as a child to scene
    scene->addChild(layer);
    
    // return the scene
    return scene;
}

// on "init" you need to initialize your instance
bool ReduceFractionsScene::init()
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
//    auto closeItem = MenuItemImage::create(
//                                           "CloseNormal.png",
//                                           "CloseSelected.png",
//                                           CC_CALLBACK_1(ReduceFractionsScene::menuCloseCallback, this)
//                                           );
//    
//	closeItem->setPosition(Point(origin.x + visibleSize.width - closeItem->getContentSize().width/2 ,
//                                 origin.y + closeItem->getContentSize().height/2));
//    
//    // create menu, it's an autorelease object
//    auto menu = Menu::create(closeItem, NULL);
//    menu->setPosition(Point::ZERO);
//    this->addChild(menu, 1);
    
    
    
    auto homeButton = MenuItemImage::create("homeButton.png", "homeButton.png",
                                            CC_CALLBACK_1(ReduceFractionsScene::homeButtonCallback, this));
    
    homeButton->setPosition(origin.x + visibleSize.width  - homeButton->getContentSize().width /2/2,
                            origin.y + visibleSize.height - homeButton->getContentSize().height/2/2
                            );
    
    homeButton->setScale(0.5f);
    
    // create menu, it's an autorelease object
    auto menu2 = Menu::create(homeButton, NULL);
    menu2->setPosition(Point::ZERO);
    this->addChild(menu2, 1);
    
    
    
    
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
    auto sprite = Sprite::create("chalkboard.png");
    
    // position the sprite on the center of the screen
    sprite->setPosition(Point(visibleSize.width/2 + origin.x, visibleSize.height/2 + origin.y));
    
    // add the sprite as a child to this layer
    this->addChild(sprite, 0);
    
    return true;
}

void ReduceFractionsScene::homeButtonCallback(Object* pSender) {
    Director::getInstance()->popScene();
}

void ReduceFractionsScene::menuCloseCallback(Object* pSender)
{
    Director::getInstance()->end();
    
#if (CC_TARGET_PLATFORM == CC_PLATFORM_IOS)
    exit(0);
#endif
}
