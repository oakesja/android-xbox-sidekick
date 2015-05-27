package com.example.joakes.xbox_sidekick.helpers;

import android.widget.TextView;

import junit.framework.Assert;

import org.assertj.android.api.widget.AbstractTextViewAssert;

/**
 * Created by joakes on 5/17/15.
 */
public class TextViewAssert extends AbstractTextViewAssert<TextViewAssert, TextView> {
    public static TextViewAssert assertTextView(TextView actual){
        return new TextViewAssert(actual);
    }

    public TextViewAssert(TextView actual){
        super(actual, TextViewAssert.class);
    }

    public TextViewAssert hasAnyText(){
        isNotNull();
        Assert.assertNotSame("Expected textview to have text but did not", "", actual.getText());
        return this;
    }
}
