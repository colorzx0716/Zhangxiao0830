package com.bwie.zhangxiao0830;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bwie.zhangxiao0830.adapter.MyListAdapter;
import com.bwie.zhangxiao0830.bean.Bean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import view.xlistview.XListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private String url = "http://v.juhe.cn/toutiao/index?type=&key=22a108244dbb8d1f49967cd74a0c144d";

    @ViewInject(R.id.xlv)
    XListView xlv;
    private List<Bean.ResultBean.DataBean> data;
    private MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     // setContentView(R.layout.activity_main);

        x.view().inject(this);
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("username","abc");
        params.addParameter("password","123");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析数据
                System.out.println("result = " + result);
                Gson gson = new Gson();
                Bean bean = gson.fromJson(result, Bean.class);
                Bean.ResultBean result1 = bean.getResult();
                data = result1.getData();

                //适配器
                adapter = new MyListAdapter(MainActivity.this,data);
                xlv.setAdapter(adapter);

                xlv.setPullLoadEnable(true);
               xlv.setXListViewListener(MainActivity.this);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    @Override
    public void onRefresh() {
        data.addAll(data);
        adapter.notifyDataSetChanged();
        onLoad();
    }

    private void onLoad() {
        xlv.stopLoadMore();
        xlv.stopRefresh();
    }

    @Override
    public void onLoadMore() {
        data.addAll(data);
        adapter.notifyDataSetChanged();
        onLoad();
    }
}
