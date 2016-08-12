package com.example.canrom7.mowa;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.canrom7.mowa.DBbean.Note;
import com.example.canrom7.mowa.adpter.NoteListAdapter;
import com.example.canrom7.mowa.refresh.CanListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * A simple {@link Fragment} subclass.
 * 登录状态下,显示笔记列表的首页
 */
public class List_Fragment extends Fragment implements CanListView.ReflashListViewListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    private static final String TAG="记事列表List_Fragment====>";
    private Context mContext;
    private View mFragment_View;
    private CanListView listView;
    private List<Note> mNoteList=new ArrayList<>();
    private NoteListAdapter noteAdpter;
    private boolean isAnima=false;
    public List_Fragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=inflater.getContext();
        mFragment_View =inflater.inflate(R.layout.layout_note_list, container, false);
        //初始化ListView
       listView= (CanListView) mFragment_View.findViewById(R.id.listView_main);
        initView();
        return mFragment_View;
    }
    //初始化视图
    private void initView() {
        Log.e(TAG, "onStart: ");
        //设置ListView加载/刷新监听
        listView.setReflashListViewListener(this);
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(true);
        //设置item事件监听
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        initData();
    }

    //下拉刷新数据
    private void onRefreshData(){
        noteAdpter=new NoteListAdapter(mContext,mNoteList);
        listView.setAdapter(noteAdpter);
    }

    @Override
    //下拉刷新时数据时回调
    public void onRefresh() {
        if (isAnima)return;
        Log.d(TAG, "onRefresh: 笔记集合："+mNoteList.size());
        if (mNoteList.size()==0){
            initData();
        }else {
            onRefreshData();
            listView.stopRefresh();
        }
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
        String date=dateFormat.format(new Date());
        listView.setRefreshTime(date);
        Log.d(TAG, "onRefresh: 下拉同步笔记");
    }


    @Override
    //上拉加载数据时回调
    public void onLoadMore() {
        listView.stopLoadMore();
        Log.d(TAG, "onLoadMore: 查看更多");
    }

    @Override
    //点击item时回调
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick:item点击了");

    }

    @Override
    //长按item时回调
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemLongClick: item长按了");
        return false;
    }
    private void initData(){
        Log.e(TAG, "initData: 调用数据初始化方法");
        BmobQuery<Note> query=new BmobQuery<>();
        query.addWhereEqualTo("noteState",1);
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e==null){
                    mNoteList=list;
                    onRefreshData();
                    Log.e(TAG, "done: List"+ Arrays.toString(list.toArray()));
                    Log.e(TAG, "done: 数据已经返回"+e);
                    listView.stopRefresh();
                }else {
                    Log.e(TAG, "done: 获取数据失败"+e);
                }

            }
        });
    }
}
