//
//  KeyboardTray.h
//  Algebro
//
//  Created by Todd B Smith on 11/7/13.
//
//


#ifndef __Algebro__KeyboardTray__
#define __Algebro__KeyboardTray__

#include "cocos2d.h"

class KeyboardTray : public cocos2d::Layer {
public:
    // there's no 'id' in cpp, so we recommend returning the class instance pointer
    static cocos2d::Scene* createScene();
    
    // Here's a difference. Method 'init' in cocos2d-x returns bool, instead of returning 'id' in cocos2d-iphone
    virtual bool init();
    
    // implement the "static create()" method manually
    CREATE_FUNC(KeyboardTray);
};

#endif /* defined(__Algebro__KeyboardTray__) */
