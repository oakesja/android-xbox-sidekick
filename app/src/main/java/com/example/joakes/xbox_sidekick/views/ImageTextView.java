package com.example.joakes.xbox_sidekick.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by joakes on 5/31/15.
 */
public class ImageTextView extends TextView {

    public ImageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDrawable(int id) {
        Drawable drawable = getResources().getDrawable(id);
        int h = getLineHeight();
        int w = getLineHeight();
        if (drawable != null) {
            drawable.setBounds(0, 0, h, w);
        }
        setCompoundDrawables(drawable, null, null, null);
    }

    public void setImageAndTextIfValid(int toText, int drawable) {
        if (toText == -1) {
            setVisibility(View.INVISIBLE);
        } else {
            setTextAndDrawable(drawable, "" + toText);
        }
    }

    public void setImageAndTextIfValid(int numerator, int denominator, int drawable) {
        if (numerator < 0) {
            setVisibility(View.INVISIBLE);
        } else if (denominator < 0) {
            setTextAndDrawable(drawable, "" + numerator);
        } else {
            String s = String.format("%d/%d", numerator, denominator);
            setTextAndDrawable(drawable, s);
        }
    }

    public void setImageAndTextIfValid(String text, int drawable) {
        if (text == null || text.equals("")) {
            setVisibility(ImageView.INVISIBLE);
        } else {
            setTextAndDrawable(drawable, text);
        }
    }

    private void setTextAndDrawable(int drawable, String text) {
        setText(text);
        setDrawable(drawable);
        setCompoundDrawablePadding(5);
    }
}
