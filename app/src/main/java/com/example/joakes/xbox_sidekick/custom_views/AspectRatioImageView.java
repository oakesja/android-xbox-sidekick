package com.example.joakes.xbox_sidekick.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by joakes on 6/8/15.
 */
public class AspectRatioImageView extends ImageView {

    public AspectRatioImageView(Context context) {
        super(context);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if(getDrawable() != null){
            int width = height * getDrawable().getIntrinsicWidth() / getDrawable().getIntrinsicHeight();
            setMeasuredDimension(width, height);
        } else {
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
