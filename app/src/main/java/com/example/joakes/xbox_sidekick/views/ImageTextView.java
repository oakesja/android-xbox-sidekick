package com.example.joakes.xbox_sidekick.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
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
        int h = this.getLineHeight();
        int w = this.getLineHeight();
        if (drawable != null) {
            drawable.setBounds(0, 0, h, w);
        }
        this.setCompoundDrawables(drawable, null, null, null);
    }

    public void setImageAndTextIfValid(int toText, int drawable) {
        if (toText == -1) {
            this.setVisibility(ImageView.INVISIBLE);
        } else {
            setTextAndDrawable(drawable, "" + toText);
        }
    }

    public void setImageAndTextIfValid(String toText, int drawable) {
        if (toText.equals("")) {
            this.setVisibility(ImageView.INVISIBLE);
        } else {
            setTextAndDrawable(drawable, toText);
        }
    }

    private void setTextAndDrawable(int drawable, String text) {
        this.setText(text);
        this.setDrawable(drawable);
        this.setCompoundDrawablePadding(5);
    }
}
