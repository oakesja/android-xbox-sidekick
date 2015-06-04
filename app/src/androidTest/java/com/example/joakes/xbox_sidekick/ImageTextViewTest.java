package com.example.joakes.xbox_sidekick;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.views.ImageTextView;

import static org.assertj.android.api.Assertions.assertThat;

/**
 * Created by joakes on 6/3/15.
 */
public class ImageTextViewTest extends AndroidTestCase {
    private ImageTextView imageTextView;
    private int drawable = R.drawable.ic_gamerscore;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        imageTextView = new ImageTextView(getContext(), null);
    }

    public void testSetImageAndTextInvalidNumber() {
        imageTextView.setImageAndTextIfValid(-1, drawable);
        assertThat(imageTextView).isInvisible();
    }

    public void testSetImageAndTextValidNumber() {
        int num = 1;
        imageTextView.setImageAndTextIfValid(num, drawable);
        assertThat(imageTextView).isVisible();
        assertThat(imageTextView).containsText("" + num);
    }

    public void testSetImageAndTextNullString() {
        imageTextView.setImageAndTextIfValid(null, drawable);
        assertThat(imageTextView).isInvisible();
    }

    public void testSetImageAndTextEmptyString() {
        imageTextView.setImageAndTextIfValid("", drawable);
        assertThat(imageTextView).isInvisible();
    }

    public void testSetImageAndTextValidString() {
        String text = "test";
        imageTextView.setImageAndTextIfValid(text, drawable);
        assertThat(imageTextView).isVisible();
        assertThat(imageTextView).containsText(text);
    }

    public void testSetImageAndTextIfInvalidNumerator(){
        imageTextView.setImageAndTextIfValid(-1, 1, drawable);
        assertThat(imageTextView).isInvisible();
    }

    public void testSetImageAndTextIfInvalidDenominator(){
        imageTextView.setImageAndTextIfValid(1, -1, drawable);
        assertThat(imageTextView).containsText("1");
    }

    public void testSetImageAndTextIfValidNumAndDenom(){
        imageTextView.setImageAndTextIfValid(1, 1, drawable);
        assertThat(imageTextView).containsText("1/1");
    }
}
