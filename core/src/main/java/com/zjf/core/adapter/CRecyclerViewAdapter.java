package com.zjf.core.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-02 上午 11:10
 */

public abstract class CRecyclerViewAdapter<T> extends RecyclerView.Adapter<CRecyclerViewViewHolder> {

    protected List<T> mData;
    protected Context mContext;
    protected int[] mItemLayoutIds;

    public static final int TYPE_HEADER = -1;
    public static final int TYPE_FOOTER = -2;
    public static final int TYPE_LOADING = -3;


    private View mHeaderView, mFooterView, mEmptyView, mLoadingView;
    private RecyclerView.LayoutManager mManager;
    private RecyclerView mRecyclerView;


    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyDataSetChanged();
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyDataSetChanged();
    }

    public void setLoadingView(View loadingView) {
        mLoadingView = loadingView;
        notifyItemInserted(getItemCount());
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(getItemCount() - 1);
        }
    }


    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }


    public void removeHeaderView() {
        if (hasHeaderView()) {
            mHeaderView = null;
            notifyDataSetChanged();
        }
    }

    public void removeFooterView() {
        if (hasFooterView()) {
            mFooterView = null;
            notifyDataSetChanged();
        }
    }

    public void removeLoadingView() {
        if (hasLoadingView()) {
            mLoadingView = null;
            notifyItemRemoved(getItemCount());
        }
    }

    public boolean hasLoadingView() {
        return mLoadingView != null;
    }

    public boolean hasHeaderView() {
        return mHeaderView != null;
    }

    public boolean hasFooterView() {
        return mFooterView != null;
    }

    public boolean hasEmptyView() {
        return mEmptyView != null;
    }

    public CRecyclerViewAdapter(Context context, List<T> data, int... itemLayoutIds) {
        this.mContext = context;
        this.mData = data;
        this.mItemLayoutIds = itemLayoutIds;
    }


    public List<T> getData() {
        return mData;
    }


    @Override
    public CRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CRecyclerViewViewHolder holder = null;
        View itemView = null;
        if (viewType == TYPE_HEADER) {
            itemView = mHeaderView;
        } else if (viewType == TYPE_FOOTER) {
            itemView = mFooterView;
        } else if (viewType == TYPE_LOADING) {
            itemView = mLoadingView;
        } else {
            holder = CRecyclerViewViewHolder.createViewHolder(mContext, parent, mItemLayoutIds[viewType], viewType);
        }

        if (itemView != null) {
            if (mManager instanceof StaggeredGridLayoutManager) {
                ViewGroup.LayoutParams targetParams = itemView.getLayoutParams();
                StaggeredGridLayoutManager.LayoutParams staggerLayoutParams;
                if (targetParams != null) {
                    staggerLayoutParams = new StaggeredGridLayoutManager.LayoutParams(targetParams.width, targetParams.height);
                } else {
                    staggerLayoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                staggerLayoutParams.setFullSpan(true);
                itemView.setLayoutParams(staggerLayoutParams);
            }
            holder = CRecyclerViewViewHolder.createViewHolder(mContext, itemView, viewType);
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowHeader(position)) {
            return TYPE_HEADER;
        } else if (isShowFooter(position)) {
            return TYPE_FOOTER;
        } else if (isShowLoading(position)) {
            return TYPE_LOADING;
        } else {
            return getItemType(position);
        }
    }

    public int getItemType(int position) {
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (mManager == null) {
            mRecyclerView = recyclerView;
            mManager = recyclerView.getLayoutManager();
            setGridWight(mManager);
        }
    }

    private void setGridWight(RecyclerView.LayoutManager manager) {
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isShowFooter(position) || isShowHeader(position) || isShowLoading(position)) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    public boolean isShowHeader(int position) {
        return (position == 0 && hasHeaderView());
    }

    public boolean isShowFooter(int position) {
        return (position == getItemCount() - 2 && hasLoadingView() && hasFooterView())
                || (position == getItemCount() - 1 && hasFooterView() && !hasLoadingView());
    }

    public boolean isShowLoading(int position) {
        return (position == getItemCount() - 1 && hasLoadingView());
    }


    @Override
    public void onBindViewHolder(CRecyclerViewViewHolder holder, int position) {
        if (isShowHeader(position) || isShowFooter(position) || isShowLoading(position)) {
            return;
        }
        setConvertView(holder, mData.get(position - getHeaderViewCount()),
                holder.getAdapterPosition() - getHeaderViewCount());
    }

    protected abstract void setConvertView(CRecyclerViewViewHolder holder, T item, int position);


    @Override
    public int getItemCount() {
        setEmptyViewState();
        return (mData != null ? mData.size() : 0) +
                getHeaderViewCount() + getFooterViewCount() + getLoadingViewCount();
    }

    private int getLoadingViewCount() {
        return hasLoadingView() ? 1 : 0;
    }

    public int getHeaderViewCount() {
        return hasHeaderView() ? 1 : 0;
    }

    public int getFooterViewCount() {
        return hasFooterView() ? 1 : 0;
    }


    /**
     * 数据数量（除头视图足视图外）
     *
     * @return
     */
    public int getRealCount() {
        return mData != null ? mData.size() : 0;
    }

    /**
     * 空视图状态
     */
    private void setEmptyViewState() {
        if (hasEmptyView()) {
            if (getRealCount() == 0) {
                mEmptyView.setVisibility(View.VISIBLE);
                if (hasHeaderView()) {
                    mHeaderView.setVisibility(View.GONE);
                }
                if (hasFooterView()) {
                    mFooterView.setVisibility(View.GONE);
                }
            } else {
                mEmptyView.setVisibility(View.GONE);
                if (hasHeaderView()) {
                    mHeaderView.setVisibility(View.VISIBLE);
                }
                if (hasFooterView()) {
                    mFooterView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

//======================================一些数据操作========================================

    /**
     * 添加单条数据
     *
     * @param position
     * @param t
     */
    public void addItemData(int position, T t) {
        mData.add(position, t);
        notifyItemInserted(position);
    }

    /**
     * 添加单条数据
     *
     * @param t
     */
    public void addItemData(T t) {
        int size = mData.size();
        mData.add(size, t);
        notifyItemInserted(size);
    }

    /**
     * 移除单条数据
     *
     * @param position
     */
    public void removeItemData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 修改单条数据
     *
     * @param position
     * @param t
     */
    public void changItemData(int position, T t) {
        mData.set(position, t);
        notifyItemChanged(position);
    }

    /**
     * 刷新数据
     *
     * @param newData
     */
    public void flushData(List<T> newData) {
        mData.clear();
        this.addNewData(newData);
    }

    /**
     * 添加新数据集合
     *
     * @param newData
     */
    public void addNewData(List<T> newData) {
        mData.addAll(newData);
        notifyDataSetChanged();
    }

//=============================================关于单条点击=========================================

    /**
     * 单条点击监听，使用RecyclerView的addOnItemTouchListener添加
     */
    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private View childView;
        private RecyclerView touchView;

        public RecyclerItemClickListener(Context context, final OnBaseClickListener mListener) {
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent ev) {
                    if (childView != null && mListener != null) {
                        if (mListener instanceof OnItemClickListener) {
                            ((OnItemClickListener) mListener).onItemClick(childView, touchView.getChildAdapterPosition(childView));
                        }
                    }
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent ev) {
                    if (childView != null && mListener != null) {
                        if (mListener instanceof OnItemLongClickListener) {
                            ((OnItemLongClickListener) mListener).onLongClick(childView, touchView.getChildAdapterPosition(childView));
                        }
                    }
                }
            });
        }

        GestureDetector mGestureDetector;

        @Override
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            mGestureDetector.onTouchEvent(motionEvent);
            childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            touchView = recyclerView;
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    /**
     * 单条点击监听回调基类
     */
    private interface OnBaseClickListener {

    }

    /**
     * 单条普通点击监听回调
     */
    public interface OnItemClickListener extends OnBaseClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 单条长点击监听回调
     */
    public interface OnItemLongClickListener extends OnBaseClickListener {

        void onLongClick(View view, int posotion);
    }

    /**
     * 单条普通点击、长点击监听回调
     */
    public interface OnItemAllClickListener extends OnItemClickListener, OnItemLongClickListener {

    }
    //=================================加载更多相关=============================================

    protected static boolean isLoading = false;


    public static abstract class RefreshListenter extends RecyclerView.OnScrollListener {


        protected int mLastPosition;

        protected int[] mLastPositions;


        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            int totleCount = recyclerView.getAdapter().getItemCount();
            int visibilityCount = recyclerView.getLayoutManager().getChildCount();
            if (visibilityCount > 0
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                    && totleCount == mLastPosition + 1) {

                if (!isLoading) {
                    addLaodView(recyclerView);
                    isLoading = true;
                    loadMore();
                }
            }
        }


        protected void addLaodView(RecyclerView recyclerView) {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter instanceof CRecyclerViewAdapter) {
                CRecyclerViewAdapter cAdapter = (CRecyclerViewAdapter) adapter;
                cAdapter.setLoadingView(makeLoadView(recyclerView));
            }
        }

        /**
         * 加载更多视图
         *
         * @param view
         * @return
         */
        private View makeLoadView(RecyclerView view) {
            LinearLayout loadView = new LinearLayout(view.getContext());
            loadView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT));
            loadView.setOrientation(LinearLayout.HORIZONTAL);
            ProgressBar progressBar = new ProgressBar(view.getContext());
            progressBar.setLayoutParams(new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1));
            loadView.addView(progressBar);
            TextView textView = new TextView(view.getContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 2));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setText("正在加载...");
            loadView.addView(textView);
            loadView.setPadding(10, 5, 5, 10);
            return loadView;
        }

        protected abstract void loadMore();


    }

    public boolean isLoading() {
        return isLoading;
    }

    public void onCompleteLoading() {
        removeLoadingView();
        isLoading = false;
    }

    public static abstract class LLM_RefreshListenter extends RefreshListenter {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            mLastPosition = manager.findLastVisibleItemPosition();
        }
    }

    public static abstract class GLM_RefreshListenter extends RefreshListenter {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
            mLastPosition = manager.findLastVisibleItemPosition();
        }

    }

    public static abstract class SGLM_RefreshListenter extends RefreshListenter {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) manager;
            if (mLastPositions == null) {
                mLastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
            }
            staggeredGridLayoutManager.findLastVisibleItemPositions(mLastPositions);
            mLastPosition = findMax(mLastPositions);
        }

        private int findMax(int[] lastPositions) {
            int max = lastPositions[0];
            for (int value : lastPositions) {
                max = value > max ? value : max;
            }
            return max;
        }

        private int findMin(int[] lastPositions) {
            int min = lastPositions[0];
            for (int value : lastPositions) {
                min = value < min ? value : min;
            }
            return min;
        }
    }

}
