/**

 * @author Canrom7
 * @description 脚页Footer
 */
package com.example.canrom7.mowa.refresh;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.canrom7.mowa.R;
public class CanListViewFooter extends LinearLayout {
	public final static int STATE_NORMAL = 0;//正常状态
	public final static int STATE_READY = 1;//刷新状态
	public final static int STATE_LOADING = 2;//加载状态
	private Context mContext;
	private View mContentView;//内容视图
	private View mProgressBar;//进度条
	private TextView mHintView;//加载更多
	
	public CanListViewFooter(Context context) {
		super(context);
		initView(context);
	}
	public CanListViewFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
	//设置状态
	public void setState(int state) {
		mHintView.setVisibility(View.INVISIBLE);
		mProgressBar.setVisibility(View.INVISIBLE);
		mHintView.setVisibility(View.INVISIBLE);
		if (state == STATE_READY) {
			mHintView.setVisibility(View.VISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_ready);
		} else if (state == STATE_LOADING) {
			mProgressBar.setVisibility(View.VISIBLE);
		} else {
			mHintView.setVisibility(View.VISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_normal);
		}
	}

	public void setBottomMargin(int height) {
		if (height < 0) return ;
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		lp.bottomMargin = height;
		mContentView.setLayoutParams(lp);
	}

	public int getBottomMargin() {
		LayoutParams layoutParams_bt = (LayoutParams)mContentView.getLayoutParams();
		return layoutParams_bt.bottomMargin;
	}
	
	
	//正常状态
	public void normal() {
		mHintView.setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.GONE);
	}
	//加载状态
	public void loading() {
		mHintView.setVisibility(View.GONE);
		mProgressBar.setVisibility(View.VISIBLE);
	}
	
	//隐藏页脚和加载更多
	public void hide() {
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		lp.height = 0;
		mContentView.setLayoutParams(lp);
	}
	
	//显示页脚加载更多
	public void show() {
		LayoutParams layoutParams = (LayoutParams)mContentView.getLayoutParams();
		layoutParams.height = LayoutParams.WRAP_CONTENT;
		mContentView.setLayoutParams(layoutParams);
	}

	//初始化脚加载视图
	private void initView(Context context) {
		mContext = context;
		LinearLayout moreView = (LinearLayout)LayoutInflater.from(mContext).inflate(R.layout.xlistview_footer, null);
		this.addView(moreView);
		moreView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		mContentView = moreView.findViewById(R.id.xlistview_footer_content);
		mProgressBar = moreView.findViewById(R.id.xlistview_footer_progressbar);
		mHintView = (TextView)moreView.findViewById(R.id.xlistview_footer_hint_textview);
	}
}
