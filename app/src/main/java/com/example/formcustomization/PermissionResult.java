package com.example.formcustomization;

import android.content.Context;

public class PermissionResult {
    
    Context context;
    int requestCode;
    String[] permissions;
    int[] permissionGrantedResults;

    public PermissionResult(Context context, int requestCode, String[] permissions, int[] grantResults) {
        this.context = context;
        this.requestCode = requestCode;
        this.permissions = permissions;
        this.permissionGrantedResults = grantResults;
    }

    public int getRequestCode() { return requestCode; }

    public String[] getPermissions() { return permissions; }

    public int[] getGrantResults() { return permissionGrantedResults; }

    public Context getContext() { return context; }
}