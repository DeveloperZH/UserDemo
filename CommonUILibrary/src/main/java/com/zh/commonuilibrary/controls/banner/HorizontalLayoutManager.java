package com.zh.commonuilibrary.controls.banner;

import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @describe: 水平无限滚动的RvLayoutManager
 * @author: Z H
 * @date: 2020/3/2 17:38
 * @pkgName: com.zh.commonuilibrary.controls.banner
 */
public class HorizontalLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Nullable
    @Override
    public PointF computeScrollVectorForPosition(int targetPosition) {
        if (targetPosition == 0) {
            return null;
        }
        int firstChildPos = getPosition(getChildAt(0));
        int direction = targetPosition < firstChildPos ? -1 : 1;
        return new PointF(direction, 0f);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        //分离并且回收当前附加的所有View
        detachAndScrapAttachedViews(recycler);
        if (getItemCount() == 0) {
            return;
        }
        int offsetX = 0;
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view, 0, 0);
            int viewWidth = getDecoratedMeasuredWidth(view);
            int viewHeight = getDecoratedMeasuredHeight(view);
            layoutDecorated(view, offsetX, 0, offsetX + viewWidth, viewHeight);
            offsetX += viewWidth;
            if (offsetX > getWidth()) {
                break;
            }
        }

    }

    @Override
    public boolean canScrollHorizontally() {
//        return super.canScrollHorizontally();
        return true;
    }


    private void recycleViews(int dx, RecyclerView.Recycler recycler) {
        for (int i = 0; i < getItemCount(); i++) {
            View childView = getChildAt(i);
            //左滑
            if (dx > 0) {
                //移除并回收 原点 左侧的子View
                if (childView.getRight() - dx < 0) {
                    removeAndRecycleViewAt(i, recycler);
                }
            }
            //右滑
            else {
                //移除并回收 右侧即RecyclerView宽度之以外的子View
                if (childView.getLeft() - dx > getWidth()) {
                    removeAndRecycleViewAt(i, recycler);
                }
            }
        }
    }


    private void fill(int dx, RecyclerView.Recycler recycler) {
        //左滑
        if (dx > 0) {
            while (true) {
                //得到当前已添加（可见）的最后一个子View
                View lastVisibleView = getChildAt(getChildCount() - 1);
                //如果滑动过后，View还是未完全显示出来就 不进行绘制下一个View
                if (lastVisibleView.getRight() - dx > getWidth()) {
                    break;
                }


                //得到View对应的位置
                int layoutPosition = getPosition(lastVisibleView);
                /**
                 * 例如要显示20个View，当前可见的最后一个View就是第20个，那么下一个要显示的就是第一个
                 * 如果当前显示的View不是第20个，那么就显示下一个，如当前显示的是第15个View，那么下一个显示第16个
                 * 注意区分 childCount 与 itemCount
                 */
                View nextView;
                if (layoutPosition == getItemCount() - 1) {
                    nextView = recycler.getViewForPosition(0);
                } else {
                    nextView = recycler.getViewForPosition(layoutPosition + 1);
                }
                addView(nextView);
                measureChildWithMargins(nextView, 0, 0);
                int viewWidth = getDecoratedMeasuredWidth(nextView);
                int viewHeight = getDecoratedMeasuredHeight(nextView);
                int offsetX = lastVisibleView.getRight();
                layoutDecorated(nextView, offsetX, 0, offsetX + viewWidth, viewHeight);
            }
        } else { //右滑
            while (true) {
                View firstVisibleView = getChildAt(0);

                if (firstVisibleView.getLeft() - dx < 0) {
                    return;
                }
                int layoutPosition = getPosition(firstVisibleView);
                /**
                 * 如果当前第一个可见View为第0个，则左侧显示第20个View 如果不是，下一个就显示前一个
                 */
                View nextView;
                if (layoutPosition == 0) {
                    nextView = recycler.getViewForPosition(getItemCount() - 1);
                } else {
                    nextView = recycler.getViewForPosition(layoutPosition - 1);
                }

                addView(nextView, 0);
                measureChildWithMargins(nextView, 0, 0);
                int viewWidth = getDecoratedMeasuredWidth(nextView);
                int viewHeight = getDecoratedMeasuredHeight(nextView);
                int offsetX = firstVisibleView.getLeft();
                layoutDecorated(nextView, offsetX - viewWidth, 0, offsetX, viewHeight);
            }
        }
    }


    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
//        return super.scrollHorizontallyBy(dx, recycler, state);
        recycleViews(dx, recycler);
        fill(dx, recycler);
        offsetChildrenHorizontal(dx * -1);
        return dx;
    }
}
