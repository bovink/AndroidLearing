package com.bovink.androidlearing;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/9/25
 */

public class ScrollBehavior extends FloatingActionButton.Behavior {

    public ScrollBehavior() {
    }

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        System.out.println("ScrollBehavior.onStartNestedScroll");
        if (child.isShown()) {
            System.out.println("hide");
            child.hide(new FloatingActionButton.OnVisibilityChangedListener() {
                @Override
                public void onShown(FloatingActionButton fab) {
                    super.onShown(fab);
                }

                @Override
                public void onHidden(FloatingActionButton fab) {
                    super.onHidden(fab);
                    fab.setVisibility(View.INVISIBLE);
                }
            });
        }
        return true;
    }
}
