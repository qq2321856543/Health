package com.wd.home.custom;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Time: 2020/5/25
 * Author: 王冠华
 * Description:
 */
public class HomeFlowLayout extends ViewGroup {
    public HomeFlowLayout(Context context) {
        super(context);
    }

    public HomeFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int wid = MeasureSpec.getSize(widthMeasureSpec);
        int heigh = MeasureSpec.getSize(heightMeasureSpec);
        init(wid-getPaddingLeft());
        setMeasuredDimension(wid,heigh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            Rect rect= (Rect) child.getTag();

            child.layout(rect.left,rect.top,rect.right,rect.bottom);
        }
    }
    public void init(int width) {
        int usedWid = getPaddingLeft();
        int usedHeigh = getPaddingTop();
        for (int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();
            LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) child.getLayoutParams();
            int childWid= measuredWidth+layoutParams.leftMargin+layoutParams.rightMargin;
            int childHeigh = measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin;
            if(usedWid+childWid>width){
                usedWid = getPaddingLeft();
                usedHeigh+=childHeigh;
            }
            usedWid+=childWid;
            Rect rect = new Rect();
            rect.left=usedWid-childWid;
            rect.top=usedHeigh;
            rect.right=usedWid;
            rect.bottom=usedHeigh+childHeigh;
            child.setTag(rect);
        }
    }
}
