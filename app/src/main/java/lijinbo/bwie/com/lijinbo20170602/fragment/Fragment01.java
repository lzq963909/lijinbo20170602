package lijinbo.bwie.com.lijinbo20170602.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lijinbo.bwie.com.lijinbo20170602.R;

/**类用途 tablayout+viewPager布局
 * Created by lijinbo on 2017/6/2.
 */

public class Fragment01 extends Fragment{


    private TabLayout tab;
    private ViewPager vPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.fragment01,container,false);

        tab = (TabLayout) view.findViewById(R.id.tab);
        vPager = (ViewPager) view.findViewById(R.id.vPager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         //设置适配器
        MyAdapter adapter = new MyAdapter(getChildFragmentManager());
        vPager.setAdapter(adapter);
        //将tablayout于viewPager关联
        tab.setupWithViewPager(vPager);
        //设置标题背景色
        tab.setTabTextColors(Color.BLACK,Color.RED);
        tab.setSelectedTabIndicatorColor(Color.RED);

      //设置tabLayout模式
        tab.setTabMode(TabLayout.MODE_FIXED);

    }
    //设置tablayout适配器
    class MyAdapter extends FragmentPagerAdapter {


        public String[] TITLE = {"周一","周二","周三","周四","周五","昨天","今天"};


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new Fragment02();
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position];
        }
    }


}
