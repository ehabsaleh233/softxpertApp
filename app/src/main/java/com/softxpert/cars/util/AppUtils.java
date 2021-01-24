package com.softxpert.cars.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.softxpert.cars.R;
import com.softxpert.cars.util.netWork.NetworkUtil;


public class AppUtils {



    private static AppUtils iAppUtilities = null;
    public static String APP_NAME = "WinchClient";
     Activity activity;
    Context iContext;
    public static Activity mActivity;

    public static AppUtils getInstance() {
        if (iAppUtilities == null) {
            iAppUtilities = new AppUtils(mActivity);
        }

        return iAppUtilities;
    }

    AppUtils(Activity a) {
        this.mActivity = a;
    }


    public boolean checkNetWork(Activity aActivity) {
        switch (NetworkUtil.getConnectivityStatus(aActivity)) {
            case OFFLINE:
                //  MessageUtility.showToast(aActivity, aActivity.getResources().getString(R.string.no_internet));
                return false;
            case WIFI_CONNECTED_WITHOUT_INTERNET:
                //   MessageUtility.showToast(aActivity, aActivity.getResources().getString(R.string.no_internet));
                return false;
            case MOBILE_DATA_CONNECTED:
            case WIFI_CONNECTED_WITH_INTERNET:
                return true;
            case UNKNOWN:
                //   MessageUtility.showToast(aActivity, aActivity.getResources().getString(R.string.no_internet));
                return false;
            default:
                return false;
        }
    }


    public void showSnackBar(Context aContext, String aMsg, View aView, int aLayout) {
        Snackbar mSnackbar = Snackbar.make(aView.findViewById(aLayout), aMsg, Snackbar.LENGTH_LONG);
        View mView = mSnackbar.getView();
         mSnackbar.getView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        mSnackbar.getView().setBackgroundColor(aContext.getResources().getColor(R.color.colorAccent));
        TextView mTextView = mView.findViewById(R.id.snackbar_text);
         mTextView.setTextColor(aContext.getResources().getColor(R.color.colorPrimaryDark));
        mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            mTextView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        else
            mTextView.setGravity(Gravity.START);
        mSnackbar.show();
    }

    public void showErrorSnackBar(Context aContext, String aMsg, View aView, int aLayout) {
        Snackbar mSnackbar = Snackbar.make(aView.findViewById(aLayout), aMsg, Snackbar.LENGTH_LONG);
        View mView = mSnackbar.getView();
         mSnackbar.getView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        mSnackbar.getView().setBackgroundColor(aContext.getResources().getColor(R.color.colorPrimaryDark));
        TextView mTextView = mView.findViewById(R.id.snackbar_text);
         mTextView.setTextColor(aContext.getResources().getColor(R.color.white));
        mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            mTextView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        else
            mTextView.setGravity(Gravity.START);
        mSnackbar.show();
    }

}