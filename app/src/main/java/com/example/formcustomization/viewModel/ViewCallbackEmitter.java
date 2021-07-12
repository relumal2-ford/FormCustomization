/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.example.formcustomization.viewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

import com.example.formcustomization.PermissionResult;

import java.util.ArrayList;
import java.util.List;

public class ViewCallbackEmitter {

    public enum ViewCallback {
        ON_HIDDEN,   //Fragment ON_HIDDEN_CHANGED, hidden = true
        ON_UNHIDDEN,  //Fragment ON_HIDDEN_CHANGED, hidden = false
        ON_OPTIONS_ITEM_SELECTED,
        ON_REQUEST_PERMISSIONS_RESULT,
        ON_ACTIVITY_RESULT,
        ON_ACTIVITY_POST_RESUME,
        ON_BACK_PRESSED,
        ON_FRAGMENT_VISIBILITY_CHANGE,
        ON_SAVE_INSTANCE_STATE,
        ON_BACK_PRESSED_STAY_ON_SCREEN,
        ON_WINDOW_FOCUS_CHANGED
    }

    public static final boolean LEAVE_ACTIVITY = true;
    public static final boolean CLOSE_MODAL = false;

    private Lifecycle baseLifecycle;

    private List<ViewCallbackObserver> viewCallbackObservers = new ArrayList<>();

    public ViewCallbackEmitter init(Lifecycle lifecycle) {
        baseLifecycle = lifecycle;
        return this;
    }

    public void fireEvent(@NonNull ViewCallback callback, @Nullable Object data) {
        for (ViewCallbackObserver observer : viewCallbackObservers) {
            switch (callback) {
                case ON_HIDDEN:
                    observer.onHidden();
                    break;
                case ON_UNHIDDEN:
                    observer.onUnhidden();
                    break;
                case ON_OPTIONS_ITEM_SELECTED:
                    observer.onOptionsItemSelected((int) data);
                    break;
                case ON_REQUEST_PERMISSIONS_RESULT:
                    if (data != null) {
                        observer.onPermissionsResult((PermissionResult) data);
                    }
                    break;
                case ON_ACTIVITY_RESULT:
                    if (data != null) {
                        observer.onActivityResult((ActivityResult) data);
                    }
                    break;
                case ON_FRAGMENT_VISIBILITY_CHANGE:
                    observer.setUserVisibleHint((boolean) data);
                    break;
                case ON_SAVE_INSTANCE_STATE:
                    observer.onSaveInstanceState();
                    break;
                case ON_ACTIVITY_POST_RESUME:
                    observer.onPostResume();
                    break;
                case ON_WINDOW_FOCUS_CHANGED:
                    observer.onWindowFocusChanged((boolean) data);
                    break;
            }
        }
    }

    public boolean fireOnBackPressed() {
        for (ViewCallbackObserver observer : viewCallbackObservers) {
            if (!observer.onBackPressed()) {
                return CLOSE_MODAL;
            }
        }
        return LEAVE_ACTIVITY;
    }

    public void addObserver(ViewCallbackObserver viewCallbackObserver) {
        baseLifecycle.addObserver(viewCallbackObserver);
        this.viewCallbackObservers.add(viewCallbackObserver);
    }

    public Lifecycle getBaseLifecycle() {
        return baseLifecycle;
    }

    public boolean fireOnFragmentBackPressed() {
        for (ViewCallbackObserver observer : viewCallbackObservers) {
            if (observer.onFragmentBackPressed()) {
                return true;
            }
        }
        return false;
    }
}
