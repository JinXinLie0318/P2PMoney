package com.example.administrator.p2pmoney.Ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/2/25.
 */
public class MyScrollView extends ScrollView {

    private View innerView;
    private float y;
    private Rect rect = new Rect();//用矩形记录四个点的位置
    private boolean animationFinish =true;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //自定义控件的回调方法

    //      第一步：获取操作的子View布局
    @Override
    protected void onFinishInflate() {
        int childCount = getChildCount();
        if (childCount > 0) {
            innerView = getChildAt(0);
        }
    }

    //      第二步：重写ontouch事件
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (innerView == null) {
            return super.onTouchEvent(ev);
        } else {
            commonTouchEvent(ev);
        }
        return super.onTouchEvent(ev);

    }

    /**
     * 自己的touch事件的处理
     *
     * @param ev
     */
    private void commonTouchEvent(MotionEvent ev) {
        if (animationFinish) {
            int action = ev.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    y = ev.getY();//具体父控件的距离，而不是距离屏幕的距离
                    break;
                case MotionEvent.ACTION_MOVE:
                    float preY = y == 0 ? ev.getY() : y;//如果按下还没抬起来就还是那点，如果动了就是y
                    float nowY = ev.getY();
                    int detailY = (int) (preY - nowY);
                    //根据detail移动innerView的位置大概一半的距离
                    if (isNeedMove()) {
                        //移动布局：移动布局之前，把布局的正常位置记录一下
                        if (rect.isEmpty()) {
                            rect.set(innerView.getLeft(), innerView.getTop(), innerView.getRight(), innerView.getBottom());
                        }
                        innerView.layout(innerView.getLeft(), rect.top - detailY / 2, innerView.getRight(), rect.bottom - detailY / 2);
            }
                    break;
                case MotionEvent.ACTION_UP:
                    y = 0;
                    //把布局回滚到正常位置(伴随一个移动的动画开始回滚)
                    if (!rect.isEmpty()) {
                        animation();
                    }

                    break;
            }
        }
    }

    /**
     * 动画回滚
     */
    private void animation() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, rect.top - innerView.getTop());
        ta.setDuration(250);
        ta.setAnimationListener(new Animation.AnimationListener() {


            @Override
            public void onAnimationStart(Animation animation) {
                animationFinish = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                innerView.clearAnimation();//清除动画
                innerView.layout(rect.left, rect.top, rect.right, rect.bottom);//回滚到正常位置
                rect.setEmpty();
                animationFinish = true;//重新指为ture，可以繼續滾
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        innerView.startAnimation(ta);//执行动画
    }

    /**
     * 判断移动布局的所有情况
     *
     * @return
     */
    private boolean isNeedMove() {
        int offset = innerView.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
//        Log.i("test", "offset:" + offset + "   scrollY:" + scrollY);
        if (scrollY == 0 || offset == scrollY) {
            return true;
        }
        return false;
    }
}
