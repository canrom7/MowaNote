/**
 * @author Canrom7
 * 自定义上拉加载下拉刷新listview
 */
package com.example.canrom7.mowa.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.example.canrom7.mowa.R;

public class CanListView extends ListView implements OnScrollListener {
	// 保存事件
	private float mLastY = -1;
	// 用于滚动回
	private Scroller mScroller;
	// 用户滚动的侦听器
	private OnScrollListener mScrollListener;
	// 触发刷新和加载更多的接口。
	private ReflashListViewListener mListViewListener;
	// 标题视图
	private CanListViewHeader mHeaderView;
	// 头视图内容,用它来计算头部的高度。和隐藏它和禁用拉刷新。.
	private RelativeLayout mHeaderViewContent;
	private TextView mHeaderTimeView;
	// 标题视图的高度
	private int mHeaderViewHeight;
	private boolean mEnablePullRefresh = true;
	// 是否刷新
	private boolean mPullRefreshing = false;
	// 页脚视图
	private CanListViewFooter mFooterView;
	private boolean mEnablePullLoad;
	private boolean mPullLoading;
	private boolean mIsFooterReady = false;
	// 总列表项,用于检测列表视图的底部。
	private int mTotalItemCount;
	// mScroller,滚动页眉或页脚。
	private int mScrollBack;
	private final static int SCROLLBACK_HEADER = 0;
	private final static int SCROLLBACK_FOOTER = 1;
	// 滚动回时间
	private final static int SCROLL_DURATION = 400;
	// 当上拉距离 >= 50dp
	private final static int PULL_LOAD_MORE_DELTA = 50;
	// 向下拉触发载入更多
	private final static float OFFSET_RADIO = 1.8f;


	public CanListView(Context context) {
		super(context);
		initWithContext(context);
	}
	public CanListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initWithContext(context);
	}
	public CanListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initWithContext(context);
	}

	private void initWithContext(Context context) {
		mScroller = new Scroller(context, new DecelerateInterpolator());
		// XListView需要滚动事件,它将调度事件用户的侦听器(作为代理)。
		super.setOnScrollListener(this);
		// 初始化头视图
		mHeaderView = new CanListViewHeader(context);
		mHeaderViewContent = (RelativeLayout) mHeaderView
				.findViewById(R.id.xlistview_header_content);
		mHeaderTimeView = (TextView) mHeaderView
				.findViewById(R.id.xlistview_header_time);
		addHeaderView(mHeaderView);

		// 初始化页脚视图
		mFooterView = new CanListViewFooter(context);
		// 初始化头高度
		mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						mHeaderViewHeight = mHeaderViewContent.getHeight();
						getViewTreeObserver().removeOnGlobalLayoutListener(this);
					}
				});
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		// 确保XListViewFooter最后一页脚视图,只添加一次。
		if (mIsFooterReady == false) {
			mIsFooterReady = true;
			addFooterView(mFooterView);
		}
		super.setAdapter(adapter);
	}

	//启用或禁用下拉刷新功能。
	public void setPullRefreshEnable(boolean enable) {
		mEnablePullRefresh = enable;
		if (!mEnablePullRefresh) { // 禁用,隐藏内容
			mHeaderViewContent.setVisibility(View.INVISIBLE);
		} else {
			mHeaderViewContent.setVisibility(View.VISIBLE);
		}
	}

	//启用或者禁用上拉加载更多
	public void setPullLoadEnable(boolean enable) {
		mEnablePullLoad = enable;
		if (!mEnablePullLoad) {
			mFooterView.hide();
			mFooterView.setOnClickListener(null);
			//拉动时,不会显示在底部视图时一页
			setFooterDividersEnabled(false);
		} else {
			mPullLoading = false;
			mFooterView.show();
			mFooterView.setState(CanListViewFooter.STATE_NORMAL);
			//确保“拉动时”不会显示在底部视图一页
			setFooterDividersEnabled(true);
			// “打开”和“点击”将调用加载更多。
			mFooterView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					startLoadMore();
				}
			});
		}
	}

	//停止刷新时重置头页高度
	public void stopRefresh() {
		if (mPullRefreshing == true) {
			mPullRefreshing = false;
			resetHeaderHeight();
		}
	}

	//停止刷新时重置脚页高度
	public void stopLoadMore() {
		if (mPullLoading == true) {
			mPullLoading = false;
			mFooterView.setState(CanListViewFooter.STATE_NORMAL);
		}
	}

	//设置更新时间
	public void setRefreshTime(String time) {
		mHeaderTimeView.setText(time);
	}

	private void invokeOnScrolling() {
		if (mScrollListener instanceof OnCanScrollListener) {
			OnCanScrollListener Listener = (OnCanScrollListener) mScrollListener;
			Listener.onXScrolling(this);
		}
	}

	private void updateHeaderHeight(float delta) {
		mHeaderView.setVisiableHeight((int) delta
				+ mHeaderView.getVisiableHeight());
		if (mEnablePullRefresh && !mPullRefreshing) {
			// 未处于刷新状态，更新箭头
			if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
				mHeaderView.setState(CanListViewHeader.STATE_READY);
			} else {
				mHeaderView.setState(CanListViewHeader.STATE_NORMAL);
			}
		}
		// 每次滚动到顶部
		setSelection(0);
	}

	//重置头页高度
	private void resetHeaderHeight() {
		int height = mHeaderView.getVisiableHeight();
		if (height == 0) // 头页视图不可见
			return;
		// 更新和标题不显示完全。什么也不做。
		if (mPullRefreshing && height <= mHeaderViewHeight) {
			return;
		}
		int finalHeight = 0; // 默认值:滚动回头。
		// 令人耳目一新,滚动显示所有标题。
		if (mPullRefreshing && height > mHeaderViewHeight) {
			finalHeight = mHeaderViewHeight;
		}
		mScrollBack = SCROLLBACK_HEADER;
		mScroller.startScroll(0, height, 0, finalHeight - height,
				SCROLL_DURATION);
		// 触发computeScroll
		invalidate();
	}
	private void updateFooterHeight(float delta) {
		int height = mFooterView.getBottomMargin() + (int) delta;
		if (mEnablePullLoad && !mPullLoading) {
			if (height > PULL_LOAD_MORE_DELTA) { // 高度足以调用加载更多
				mFooterView.setState(CanListViewFooter.STATE_READY);
			} else {
				mFooterView.setState(CanListViewFooter.STATE_NORMAL);
			}
		}
		mFooterView.setBottomMargin(height);
//		setSelection(mTotalItemCount - 1);滚动到最下
	}

	private void resetFooterHeight() {
		int bottomMargin = mFooterView.getBottomMargin();
		if (bottomMargin > 0) {
			mScrollBack = SCROLLBACK_FOOTER;
			mScroller.startScroll(0, bottomMargin, 0, -bottomMargin,
					SCROLL_DURATION);
			invalidate();
		}
	}

	private void startLoadMore() {
		mPullLoading = true;
		mFooterView.setState(CanListViewFooter.STATE_LOADING);
		if (mListViewListener != null) {
			mListViewListener.onLoadMore();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (mLastY == -1) {
			mLastY = ev.getRawY();
		}

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastY = ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float deltaY = ev.getRawY() - mLastY;
			mLastY = ev.getRawY();
			if (getFirstVisiblePosition() == 0
					&& (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)) {
				// 第一项显示,标题显示或下拉。
				updateHeaderHeight(deltaY / OFFSET_RADIO);
				invokeOnScrolling();
			} else if (getLastVisiblePosition() == mTotalItemCount - 1
					&& (mFooterView.getBottomMargin() > 0 || deltaY < 0)) {
				// 最后一项,已经停在了或者想拉起。
				updateFooterHeight(-deltaY / OFFSET_RADIO);
			}
			break;
		default:
			mLastY = -1; // 复位
			if (getFirstVisiblePosition() == 0) {
				// 调用刷新
				if (mEnablePullRefresh
						&& mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
					mPullRefreshing = true;
					mHeaderView.setState(CanListViewHeader.STATE_REFRESHING);
					if (mListViewListener != null) {
						mListViewListener.onRefresh();
					}
				}
				resetHeaderHeight();
			} else if (getLastVisiblePosition() == mTotalItemCount - 1) {
				// 调用加载更多。
				if (mEnablePullLoad
				    && mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA
				    && !mPullLoading) {
					startLoadMore();
				}
				resetFooterHeight();
			}
			break;
		}
		return super.onTouchEvent(ev);
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			if (mScrollBack == SCROLLBACK_HEADER) {
				mHeaderView.setVisiableHeight(mScroller.getCurrY());
			} else {
				mFooterView.setBottomMargin(mScroller.getCurrY());
			}
			postInvalidate();
			invokeOnScrolling();
		}
		super.computeScroll();
	}

	@Override
	public void setOnScrollListener(OnScrollListener l) {
		mScrollListener = l;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (mScrollListener != null) {
			mScrollListener.onScrollStateChanged(view, scrollState);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// 发送到用户的侦听器
		mTotalItemCount = totalItemCount;
		if (mScrollListener != null) {
			mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
					totalItemCount);
		}
	}

	public void setReflashListViewListener(ReflashListViewListener ListViewListener ) {
		mListViewListener = ListViewListener;
	}
	//可以听列表视图。OnScrollListener或这一个。它将调用onXScrolling时页眉/页脚滚动。
	public interface OnCanScrollListener extends OnScrollListener {
		public void onXScrolling(View view);
	}

	//实现这个接口回调刷新/加载更多的事件。
	public interface ReflashListViewListener {
		public void onRefresh();
		public void onLoadMore();
	}
}
