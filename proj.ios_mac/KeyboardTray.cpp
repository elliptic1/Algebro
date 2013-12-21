//
//  KeyboardTray.cpp
//  Algebro
//
//  Created by Todd B Smith on 11/7/13.
//
//

#include "KeyboardTray.h"
#include "cocos2d.h"

// on "init" you need to initialize your instance
bool KeyboardTray::init()
{
    //////////////////////////////
    // 1. super init first
    if ( !Layer::init() )
    {
        return false;
    }
 
    
    auto sprite = cocos2d::Sprite::create("number0.png");
    
    // position the sprite on the center of the screen
//    sprite->setPosition(Point(visibleSize.width/2 + origin.x, visibleSize.height/2 + origin.y));
//  	sprite->setPosition(sprite::Point(4,4,));
    
    // add the sprite as a child to this layer
    this->addChild(sprite, 0);

    return true;
    
    
}