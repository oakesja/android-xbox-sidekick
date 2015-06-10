package com.example.joakes.xbox_sidekick.views;

/**
 * Created by joakes on 6/9/15.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

public class CircularProgressBar extends View {
    private static final int STROKE_WIDTH = 20;
    private String mTitle = "";
    private int mStrokeWidth = STROKE_WIDTH;
    private final RectF mCircleBounds = new RectF();
    private final Paint mProgressColorPaint = new Paint();
    private final Paint mBackgroundColorPaint = new Paint();
    private final Paint mTitlePaint = new Paint();
    private boolean mHasShadow = true;
    private int mShadowColor = Color.BLACK;
    private int mProgress = 0;

    public CircularProgressBar(Context context) {
        super(context);
        init();
    }

    public CircularProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mProgressColorPaint.setAntiAlias(true);
        mProgressColorPaint.setStyle(Paint.Style.STROKE);
        mProgressColorPaint.setStrokeWidth(mStrokeWidth);
        mProgressColorPaint.setColor(Color.GREEN);

        mBackgroundColorPaint.setAntiAlias(true);
        mBackgroundColorPaint.setStyle(Paint.Style.STROKE);
        mBackgroundColorPaint.setStrokeWidth(mStrokeWidth);
        mBackgroundColorPaint.setColor(Color.WHITE);

        mTitlePaint.setTextSize(60);
        mTitlePaint.setStyle(Style.FILL);
        mTitlePaint.setAntiAlias(true);
        mTitlePaint.setTypeface(Typeface.create("Roboto-Thin", Typeface.NORMAL));
        mTitlePaint.setShadowLayer(0.1f, 0, 1, Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(mCircleBounds, 0, 360, false, mBackgroundColorPaint);

        float scale = (float) mProgress / 100 * 360;

        if (mHasShadow)
            mProgressColorPaint.setShadowLayer(3, 0, 1, mShadowColor);
        canvas.drawArc(mCircleBounds, 270, scale, false, mProgressColorPaint);


        if (!TextUtils.isEmpty(mTitle)) {
            int xPos = (int) (getMeasuredWidth() / 2 - mTitlePaint.measureText(mTitle) / 2);
            int yPos = getMeasuredHeight() / 2;

            float titleHeight = Math.abs(mTitlePaint.descent() + mTitlePaint.ascent());
            yPos += titleHeight / 2;
            canvas.drawText(mTitle, xPos, yPos, mTitlePaint);
        }

        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(width, height);
        setMeasuredDimension(min + 2 * STROKE_WIDTH, min + 2 * STROKE_WIDTH);

        mCircleBounds.set(STROKE_WIDTH, STROKE_WIDTH, min + STROKE_WIDTH, min + STROKE_WIDTH);
    }

    public void setProgress(int progress) {
        mProgress = progress;
        setTitle(progress + "%");
    }

    public void setTitle(String title) {
        this.mTitle = title;
        invalidate();
    }

    public int getProgress(){
        return mProgress;
    }
}