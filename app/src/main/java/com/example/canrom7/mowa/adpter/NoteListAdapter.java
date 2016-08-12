package com.example.canrom7.mowa.adpter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.canrom7.mowa.DBbean.Note;
import com.example.canrom7.mowa.R;

import java.util.List;

/**
 * Created by Canrom7 on 2016/8/1 .
 * Mailbox canrom7@163.com .
 * List列表项适配器
 */
public class NoteListAdapter extends BaseAdapter{
    private static final String TAG="NoteListAdapter适配器：";
    private Context mContext;
    private List<Note> noteList;
    //初始化笔记类型图
    private int[] noteTypeimg;

    public NoteListAdapter(Context context,List<Note> notes){
        this.mContext=context;
        this.noteList=notes;
        this.noteTypeimg= new int[]{R.mipmap.fenxiang, R.mipmap.rili,
                R.mipmap.shoucang, R.mipmap.riji, R.mipmap.biji,R.mipmap.zhangdan};
        Log.d(TAG, "NoteListAdapter: 适配器里有数据："+noteList.size());
    }
    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView: "+position);
        Log.d(TAG, "getView: 对象信息"+noteList.get(position).toString());
        ListItemHold listItemHold;
        if (convertView==null){
            listItemHold=new ListItemHold();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
            listItemHold.image_type= (ImageView) convertView.findViewById(R.id.image_type);
            listItemHold.text_city= (TextView) convertView.findViewById(R.id.text_noteCity);
            listItemHold.text_title= (TextView) convertView.findViewById(R.id.text_title);
            listItemHold.text_content= (TextView) convertView.findViewById(R.id.text_content);
            listItemHold.text_date= (TextView) convertView.findViewById(R.id.text_date);
            convertView.setTag(listItemHold);
        }
        listItemHold= (ListItemHold) convertView.getTag();
        listItemHold.image_type.setImageResource(noteTypeimg[noteList.get(position).getNoteType()]);
        listItemHold.text_city.setText(noteList.get(position).getEditSite());
        listItemHold.text_title.setText(noteList.get(position).getNoteTitle());
        listItemHold.text_content.setText("     "+noteList.get(position).getNoteInfo());
        listItemHold.text_date.setText(noteList.get(position).getUpdatedAt());
        return convertView;
    }

    class ListItemHold{
        TextView text_title;
        TextView text_content;
        ImageView image_type;
        TextView text_date;
        TextView text_city;
    }
}
