package com.bwie.zhangxiao0830;

import android.os.Bundle;

import com.bwie.zhangxiao0830.adapter.MyListAdapter;
import com.bwie.zhangxiao0830.bean.Bean;
import com.bwie.zhangxiao0830.fragment.LeftFragment;
import com.bwie.zhangxiao0830.fragment.RightFragment;
import com.google.gson.Gson;
import com.kson.slidingmenu.SlidingMenu;
import com.kson.slidingmenu.app.SlidingFragmentActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import view.xlistview.XListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends SlidingFragmentActivity implements XListView.IXListViewListener {

    private String url = "http://v.juhe.cn/toutiao/index?type=&key=22a108244dbb8d1f49967cd74a0c144d";

    @ViewInject(R.id.xlv)
    XListView xlv;
    private List<Bean.ResultBean.DataBean> data;
    private MyListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     // setContentView(R.layout.activity_main);

        initMenu();

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

    private void initMenu() {
        //添加左菜单
        setBehindContentView(R.layout.left_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.left_menu,new LeftFragment()).commit();

        //设置slidingmenu相关属性
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.BehindOffsetRes);  // 设置滑动菜单视图的宽度

        //设置右菜单
        menu.setSecondaryMenu(R.layout.right_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.right_menu,new RightFragment()).commit();
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
