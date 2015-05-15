package com.socks.jiandan.view;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Mr_Wrong on 2015/5/15.
 */
public class SlideInOutBottomItemAnimator extends BaseItemAnimator {

    private float mOriginalY;
    private float mDeltaY;

    public SlideInOutBottomItemAnimator(RecyclerView recyclerView) {
        super(recyclerView);
    }

    protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        final View view = holder.itemView;

        ViewCompat.animate(view).cancel();
        ViewCompat.animate(view).setDuration(getRemoveDuration()).
                translationY(+mDeltaY).setListener(new VpaListenerAdapter() {
            @Override
            public void onAnimationEnd(View view) {
                ViewCompat.setTranslationY(view, +mDeltaY);
                dispatchRemoveFinished(holder);
                mRemoveAnimations.remove(holder);
                dispatchFinishedWhenDone();
            }
        }).start();
        mRemoveAnimations.add(holder);
    }

    @Override
    protected void prepareAnimateAdd(RecyclerView.ViewHolder holder) {
        retrieveItemPosition(holder);
        ViewCompat.setTranslationY(holder.itemView, +mDeltaY);
    }

    protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
        final View view = holder.itemView;

        ViewCompat.animate(view).cancel();
        ViewCompat.animate(view).translationY(0)
                .setDuration(getAddDuration()).
                setListener(new VpaListenerAdapter() {
                    @Override
                    public void onAnimationCancel(View view) {
                        ViewCompat.setTranslationY(view, 0);
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        dispatchAddFinished(holder);
                        mAddAnimations.remove(holder);
                        dispatchFinishedWhenDone();
                    }
                }).start();
        mAddAnimations.add(holder);
    }

    private void retrieveItemPosition(final RecyclerView.ViewHolder holder) {
        mOriginalY = mRecyclerView.getLayoutManager().getDecoratedTop(holder.itemView);
        mDeltaY = mRecyclerView.getHeight() - mOriginalY;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        return false;
    }
}