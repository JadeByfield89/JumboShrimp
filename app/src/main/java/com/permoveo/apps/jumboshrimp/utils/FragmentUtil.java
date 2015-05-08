package com.permoveo.apps.jumboshrimp.utils;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.permoveo.apps.jumboshrimp.R;
import com.permoveo.apps.jumboshrimp.fragments.base.ProgressDialogFragment;

/**
 * Created by CJH on 5/9/2015.
 */
public class FragmentUtil {

    public static final String TAG = FragmentUtil.class.getSimpleName();

    public static void replaceFragmentToLayout(final FragmentManager fragmentManager, final int containerId,
                                               final Fragment fragment, final String tag) {
        final FragmentTransaction ft = fragmentManager.beginTransaction();
        final Fragment previousFragment = fragmentManager
                .findFragmentByTag(tag);
        if (previousFragment != null) {
            ft.remove(previousFragment);
        }
        ft.add(containerId, fragment, tag);
        ft.commit();
    }

    public static void addFragmentToLayout(final Context context,
                                           final int containerId, final FragmentManager fragmentManager,
                                           final Fragment fragment, final String tag) {
        final FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(containerId, fragment, tag);
        ft.commit();
    }

    public static void removeDialogFragment(final String tag,
                                            final FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            return;
        }
        final FragmentTransaction ft = fragmentManager.beginTransaction();
        final Fragment prev = fragmentManager.findFragmentByTag(tag);
        if (prev != null && prev.isAdded()) {
            ft.remove(prev);
        }
        ft.commitAllowingStateLoss();
    }

    public static void replaceFragment(FragmentManager manager, int parent,
                                       Fragment fragment) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(parent, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void showDialogFragment(final DialogFragment dialog,
                                          final String tag, final FragmentManager fragmentManager) {
        final FragmentTransaction ft = fragmentManager.beginTransaction();
        final Fragment prev = fragmentManager.findFragmentByTag(tag);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.add(dialog, tag);
        ft.commitAllowingStateLoss();
    }

    public static void showProgressDialog(FragmentActivity activity) {
        showProgressDialog(activity.getSupportFragmentManager(),
                activity.getString(R.string.message_progress), false);
    }

    public static void showProgressDialog(FragmentManager manager,
                                          String message, boolean cancelable) {
        final ProgressDialogFragment progressDialogFragment = ProgressDialogFragment
                .newInstance(message, cancelable);
        showDialogFragment(progressDialogFragment, ProgressDialogFragment.TAG,
                manager);
    }

    public static void clearProgressDialog(FragmentActivity activity) {
        clearProgressDialog(activity.getSupportFragmentManager());
    }

    public static void clearProgressDialog(FragmentManager manager) {
        try {
            removeDialogFragment(ProgressDialogFragment.TAG, manager);
        } catch (Exception e) {
        }
    }

}
