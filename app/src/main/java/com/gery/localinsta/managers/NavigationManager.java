package com.gery.localinsta.managers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.gery.localinsta.R;

/**
 * Created by gguij002 on 6/1/17.
 */

public class NavigationManager {

    private static final int DEFAULT_LAYOUT = 0;

    private Context context;

    private NavigationManager(Context context) {
        this.context = context;
    }

    public static NavigationManager newInstance(Context context) {
        return new NavigationManager(context);
    }

    private FragmentManager getFragmentManager() {
        if (!(context instanceof AppCompatActivity)) {
            throw new IllegalArgumentException("Navigation Manager Context must be an Activity");
        }
        return ((AppCompatActivity) context).getSupportFragmentManager();
    }

    /**
     * Use this when intending to use the default containerId.
     *
     * @param fragmentToShow The Fragment to be displayed.
     * @param addToBackStack Boolean variable to determine to add this fragment to the back stack or
     *                       not
     */
    public void showFragment(@NonNull Fragment fragmentToShow, boolean addToBackStack) {
        showFragment(fragmentToShow, DEFAULT_LAYOUT, addToBackStack, null);
    }

    /**
     * @param fragmentToShow The Fragment to be displayed.
     * @param containerId    The id of the fragment container.
     * @param addToBackStack Boolean variable to determine to add this fragment to the back stack or
     *                       not
     * @param fragmentTag    The tag used to find the fragment we wish to hide. If null, we use
     *                       replace.
     */
    public void showFragment(@NonNull Fragment fragmentToShow,
                             int containerId,
                             boolean addToBackStack,
                             @Nullable String fragmentTag) {
        showFragment(fragmentToShow, containerId, addToBackStack, fragmentTag, null);
    }

    /**
     * @param fragmentToShow The Fragment to be displayed.
     * @param containerId    The id of the fragment container.
     * @param addToBackStack Boolean variable to determine to add this fragment to the back stack or
     *                       not
     * @param fragmentTag    The tag used to find the fragment we wish to hide. If null, we use
     *                       replace.
     * @param transaction    The fragment transaction to use. Add animations or shared elements to this transaction
     */
    public void showFragment(@NonNull Fragment fragmentToShow,
                             int containerId,
                             boolean addToBackStack,
                             @Nullable String fragmentTag,
                             @Nullable FragmentTransaction transaction) {
        if (transaction == null) {
            transaction = getFragmentManager().beginTransaction();
        }

        if (containerId == DEFAULT_LAYOUT) {
            containerId = R.id.fragment_container;
        }

        if (addToBackStack) {
            transaction.addToBackStack(fragmentToShow.getClass().getName());
        }

        if (fragmentTag != null) {
            transaction.hide(getFragmentManager().findFragmentByTag(fragmentTag));
            transaction.add(containerId, fragmentToShow);
        } else {
            transaction.replace(containerId, fragmentToShow, fragmentToShow.getClass().getName());
        }

        transaction.commit();
    }
}