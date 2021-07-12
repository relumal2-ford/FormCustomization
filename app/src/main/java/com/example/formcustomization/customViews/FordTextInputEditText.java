/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.example.formcustomization.customViews;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.material.textfield.TextInputEditText;

public class FordTextInputEditText extends TextInputEditText {

    private static final int START_DRAWABLE_POSITION = 0;

    public FordTextInputEditText(Context context) {
        super(context);
//        setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
    }

    public FordTextInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
    }

    public FordTextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
    }

    public void setStartDrawableColor(@ColorInt int colorRes) {
        setDrawableColor(colorRes, START_DRAWABLE_POSITION);
    }

    private void setDrawableColor(@ColorInt int colorRes, int position) {
        Drawable drawable = getCompoundDrawables()[position];
        if (null != drawable) {
            DrawableCompat.wrap(drawable).mutate().setColorFilter(colorRes, PorterDuff.Mode.SRC_IN);
        }
    }
}
