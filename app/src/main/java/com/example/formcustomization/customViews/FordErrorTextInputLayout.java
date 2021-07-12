/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.example.formcustomization.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formcustomization.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class FordErrorTextInputLayout extends TextInputLayout {

    public static final int NO_ERROR_MESSAGE = 0;
    private static final String EMPTY_ERROR_MESSAGE = "";
    private static boolean showErrorView = false;
    private static int errorDrawable = 0;
    private static int errorColor = 0;

    public FordErrorTextInputLayout(Context context) {
        super(context);
    }

    public FordErrorTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FordErrorTextInputLayout);
        try {
            errorDrawable = ta.getResourceId(R.styleable.FordErrorTextInputLayout_errorDrawable, R.drawable.ic_red_cross_error);
            errorColor = ta.getResourceId(R.styleable.FordErrorTextInputLayout_errorColor, R.color.alert_urgent);
        } finally {
            ta.recycle();
        }
    }

    public FordErrorTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        final TextView errorTextInput = (AppCompatTextView) findViewById(R.id.textinput_error);
        if (errorTextInput != null) {
            errorTextInput.setWidth(getWidth());
        }
    }

    @Override
    public void setError(CharSequence errorText) {
        setError(errorText, errorColor, errorDrawable);
    }

    public void setError(CharSequence errorText, int errorTextColor, int drawableIcon) {
        final TextView errorTextInput = (AppCompatTextView) findViewById(R.id.textinput_error);
        if (errorTextInput != null) {
            errorTextInput.setTextColor(getResources().getColor(errorTextColor));
            Drawable errorIcon = getResources().getDrawable(drawableIcon);
            errorIcon.setBounds(0, 0, errorIcon.getIntrinsicWidth(),
                    errorIcon.getIntrinsicHeight());
            errorTextInput.setCompoundDrawables(null, null, errorIcon, null);
        }
        super.setError(errorText);
    }

    @BindingAdapter("errorViewText")
    public static void showErrorMessage(TextInputLayout view, String value) {
        showErrorView = !TextUtils.isEmpty(value);
        view.setErrorEnabled(showErrorView);
        view.setError(value);
    }

    @BindingAdapter("fordErrorText")
    public static void showFordErrorText(TextInputLayout view, @StringRes Integer errorMessage) {
        showErrorView = (errorMessage != NO_ERROR_MESSAGE);
        view.setErrorEnabled(showErrorView);
        view.setError(showErrorView ? view.getContext().getString(errorMessage) : EMPTY_ERROR_MESSAGE);
    }

}
