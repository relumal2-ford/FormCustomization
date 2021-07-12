/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.example.formcustomization.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formcustomization.PermissionResult;
import com.example.formcustomization.viewModel.ActivityResult;
import com.example.formcustomization.viewModel.ViewCallbackEmitter;
import com.google.android.material.snackbar.Snackbar;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.example.formcustomization.viewModel.ViewCallbackEmitter.LEAVE_ACTIVITY;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String ACTIVITY_KILLED_BY_MEMORY_MANAGEMENT = "ACTIVITY_KILLED_BY_MEMORY_MANAGEMENT";
    private static final int NO_FLAGS = 0;
    private static final int SNACKBAR_DEFAULT_MAXLINE = 2;


    @Nullable
    protected CompositeDisposable registerUnboundViewEvents() {
        return null;
    }

    private final CompositeDisposable lifecycleSubscriptions = new CompositeDisposable();
    private final ViewCallbackEmitter viewCallbackEmitter = new ViewCallbackEmitter();

    private Snackbar snackbar;
    private Dialog fordDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.getBoolean(ACTIVITY_KILLED_BY_MEMORY_MANAGEMENT, false)) {
            Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public boolean isDistractionWarningEnabled() {
        return true;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getViewCallbackEmitter().fireEvent(ViewCallbackEmitter.ViewCallback.ON_ACTIVITY_POST_RESUME, null);
    }

    @Override
    protected void onPause() {
        lifecycleSubscriptions.clear();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (viewCallbackEmitter.fireOnBackPressed() == LEAVE_ACTIVITY) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        viewCallbackEmitter.fireEvent(ViewCallbackEmitter.ViewCallback.ON_OPTIONS_ITEM_SELECTED, item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getViewCallbackEmitter().fireEvent(ViewCallbackEmitter.ViewCallback.ON_ACTIVITY_RESULT, new ActivityResult(requestCode, resultCode, data));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        viewCallbackEmitter.fireEvent(ViewCallbackEmitter.ViewCallback.ON_WINDOW_FOCUS_CHANGED, hasFocus);
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        subscribeToEventBus(); // onRequestPermissionsResult() is called before onResume(). so we have to register here.
        getViewCallbackEmitter().fireEvent(ViewCallbackEmitter.ViewCallback.ON_REQUEST_PERMISSIONS_RESULT, new PermissionResult(this, requestCode, permissions, grantResults));
   }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!isFinishing()) {
            outState.putBoolean(ACTIVITY_KILLED_BY_MEMORY_MANAGEMENT, true);
        }
        viewCallbackEmitter.fireEvent(ViewCallbackEmitter.ViewCallback.ON_SAVE_INSTANCE_STATE, null);
        super.onSaveInstanceState(outState);
    }

    protected ViewCallbackEmitter getViewCallbackEmitter() {
        return viewCallbackEmitter.init(getLifecycle());
    }

    protected void subscribeOnLifecycle(Disposable disposable) {
        lifecycleSubscriptions.add(disposable);
    }

    private void subscribeToEventBus() {
        lifecycleSubscriptions.clear();
        CompositeDisposable eventsSubscription = registerUnboundViewEvents();
        if (eventsSubscription != null) {
            lifecycleSubscriptions.add(eventsSubscription);
        }
    }

    private void launchActivity(Class launchActivityClazz) {
        Intent intent = new Intent(this, launchActivityClazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
