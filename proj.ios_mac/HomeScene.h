#ifndef __HOME_SCENE_H__
#define __HOME_SCENE_H__

#include "cocos2d.h"
#include "SolveEquationsScene.h"
#include "ReduceFractionsScene.h"

//#include "symbolic/constants.h"

typedef struct tagResource {
    cocos2d::Size size;
    char directory[100];
} Resource;

class HomeScene : public cocos2d::Layer {
public:
    // there's no 'id' in cpp, so we recommend returning the class instance pointer
    static cocos2d::Scene* createScene();

    // Here's a difference. Method 'init' in cocos2d-x returns bool, instead of returning 'id' in cocos2d-iphone
    virtual bool init();  
    
    // a selector callback
    void menuCloseCallback(Object* pSender);

    // a selector callback
    void solveEquationsButtonCallback(Object* pSender);
    
    // a selector callback
    void reduceFractionsButtonCallback(Object* pSender);
    
    // implement the "static create()" method manually
    CREATE_FUNC(HomeScene);
};

#endif // __HOME_SCENE_H__
