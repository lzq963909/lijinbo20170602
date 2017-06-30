package lijinbo.bwie.com.lijinbo20170602.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lijinbo.bwie.com.lijinbo20170602.R;
import lijinbo.bwie.com.lijinbo20170602.adapter.Myadapter;
import lijinbo.bwie.com.lijinbo20170602.bean.Commit;
import lijinbo.bwie.com.lijinbo20170602.utils.NetUtils;
import xunqaing.bwie.com.xlistview.XListView;

/**类用途  用于网络请求
 * Created by lijinbo on 2017/6/2.
 */

public class Fragment02 extends Fragment implements XListView.IXListViewListener{


    List<Commit.DataBean.ComicsBean> list=new ArrayList<>();
    private XListView xlist;
    private Myadapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment02, container, false);
        xlist = (XListView) view.findViewById(R.id.xList);
        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //进入程序做联网判断没有网跳转到设置网络页面


        if (NetUtils.isNetworkAvailable(getActivity())==true){
            //有网的情况加载数据
            getData();
        }else {
            //没网的情况下跳转到系统设置
            NetUtils.toSystemSetting(getActivity());
        }

        //加载适配器
        adapter = new Myadapter(getActivity(),list);
        xlist.setAdapter(adapter);
        //是否支持上拉刷新下拉加载更多
        xlist.setPullLoadEnable(true);
        xlist.setPullRefreshEnable(true);

    }



    //使用Xutils3.0请求数据
    private void getData() {
        String url="http://api.kkmh.com/v1/daily/comic_lists/0?since=0&gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3NzQyMjQwNjE1LCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjQuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tSG9tZXBhZ2VUYWJOYW1lIjoi54Ot6ZeoIiwiJGxpYl92ZXJzaW9uIjoiMS42LjEzIiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6ImJpZ25veCIsIkZyb21Ib21lcGFnZVVwZGF0ZURhdGUiOjAsIiRzY3JlZW5faGVpZ2h0IjoxMjgwLCJIb21lcGFnZVVwZGF0ZURhdGUiOjAsIlByb3BlcnR5RXZlbnQiOiJSZWFkSG9tZVBhZ2UiLCJGaW5kVGFiTmFtZSI6IuaOqOiNkCIsImFidGVzdF9ncm91cCI6MTEsIiRzY3JlZW5fd2lkdGgiOjcyMCwiJG9zIjoiQW5kcm9pZCIsIlRyaWdnZXJQYWdlIjoiSG9tZVBhZ2UiLCIkY2FycmllciI6IkNoaW5hIE1vYmlsZSIsIiRtb2RlbCI6IlZQaG9uZSIsIiRhcHBfdmVyc2lvbiI6IjMuNi4yIn0sInR5cGUiOiJ0cmFjayIsImRpc3RpbmN0X2lkIjoiQTo2YWRkYzdhZTQ1MjUwMzY1Iiwib3JpZ2luYWxfaWQiOiJBOjZhZGRjN2FlNDUyNTAzNjUiLCJldmVudCI6IlJlYWRIb21lUGFnZSJ9";
        RequestParams params=new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson=new Gson();
                Commit commit = gson.fromJson(result, Commit.class);
                List<Commit.DataBean.ComicsBean> comics = commit.getData().getComics();
                list.addAll(comics);
                adapter.notifyDataSetChanged();
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

   //下拉刷新
    @Override
    public void onRefresh() {
        list.clear();
        getData();
        adapter.notifyDataSetChanged();
        onLoad();
    }

    @Override
    public void onLoadMore() {
        onLoad();
    }

    // 获得数据后一定要调用onLoad()方法，否则刷新会一直进行，根本停不下来
    private void onLoad() {
       xlist.stopRefresh();//停止刷新
        xlist.stopLoadMore();//停止加载更多
        SimpleDateFormat formatter = new SimpleDateFormat("MM-ddHH:mm:ss");//设置日期显示格式
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);// 将时间装换为设置好的格式
        xlist.setRefreshTime(str);//设置时间
    }
}
