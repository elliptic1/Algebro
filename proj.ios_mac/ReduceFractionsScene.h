#ifndef __REDUCEFRACTIONS_SCENE_H__
#define __REDUCEFRACTIONS_SCENE_H__

#include "cocos2d.h"
#include "HomeScene.h"

class ReduceFractionsScene : public cocos2d::Layer {
public:
    // there's no 'id' in cpp, so we recommend returning the class instance pointer
    static cocos2d::Scene* createScene();
    
    // Here's a difference. Method 'init' in cocos2d-x returns bool, instead of returning 'id' in cocos2d-iphone
    virtual bool init();
    
    // a selector callback
    void menuCloseCallback(Object* pSender);
   
    // a selector callback
    void homeButtonCallback(Object* pSender);
    
    // implement the "static create()" method manually
    CREATE_FUNC(ReduceFractionsScene);
};

#endif // __REDUCEFRACTIONS_SCENE_H__
