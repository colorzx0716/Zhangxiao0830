package com.bwie.zhangxiao0830.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.zhangxiao0830.R;
import com.bwie.zhangxiao0830.bean.Bean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 张肖肖 on 2017/8/30.
 */

public class MyListAdapter extends BaseAdapter {
    private final Context context;
    private final List<Bean.ResultBean.DataBean> data;

    private final int atype = 0;
    private final int btype = 1;

    public MyListAdapter(Context context, List<Bean.ResultBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2 == 0){
            return atype;
        }else{
            return btype;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        ViewHolder1 viewHolder1;
        ViewHolder2 viewHolder2;
        if(view == null){
            switch (type){
                case atype:
                    viewHolder1 = new ViewHolder1();
                    view = View.inflate(context, R.layout.list_item, null);
                    viewHolder1.list_img = view.findViewById(R.id.list_img);
                    viewHolder1.list_title = view.findViewById(R.id.list_title);
                    viewHolder1.list_name = view.findViewById(R.id.list_name);
                    viewHolder1.list_date = view.findViewById(R.id.list_date);
                    view.setTag(viewHolder1);

                    break;
                case btype:
                    viewHolder2 = new ViewHolder2();
                    view = View.inflate(context, R.layout.grid_item, null);
                    viewHolder2.g_img = view.findViewById(R.id.g_img);
                    viewHolder2.g_title = view.findViewById(R.id.g_title);
                    viewHolder2.g_name = view.findViewById(R.id.g_name);
                    viewHolder2.g_date = view.findViewById(R.id.g_date);
                    view.setTag(viewHolder2);
                    break;
            }

        }else{
            switch (type){
                case atype:
                    viewHolder1 = (ViewHolder1) view.getTag();
                    viewHolder1.list_title.setText(data.get(i).getTitle());
                    viewHolder1.list_name.setText(data.get(i).getAuthor_name());
                    viewHolder1.list_date.setText(data.get(i).getDate());
                    ImageLoader.getInstance().displayImage(data.get(i).getThumbnail_pic_s(),viewHolder1.list_img);

                    break;
                case btype:
                    viewHolder2 = (ViewHolder2) view.getTag();
                    viewHolder2.g_title.setText(data.get(i).getTitle());
                    viewHolder2.g_name.setText(data.get(i).getAuthor_name());
                    viewHolder2.g_date.setText(data.get(i).getDate());
                    ImageLoader.getInstance().displayImage(data.get(i).getThumbnail_pic_s(),viewHolder2.g_img);

                    break;
            }
        }
        return view;
    }

    private class ViewHolder1 {

        public ImageView list_img;
        public TextView list_title;
        public TextView list_name;
        public TextView list_date;
    }
    private class ViewHolder2 {

        public ImageView g_img;
        public TextView g_title;
        public TextView g_name;
        public TextView g_date;
    }
}
