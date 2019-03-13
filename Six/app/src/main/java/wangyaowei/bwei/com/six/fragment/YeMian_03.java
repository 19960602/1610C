package wangyaowei.bwei.com.six.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


import wangyaowei.bwei.com.six.R;

/**
 * *********************************
 * *
 * 作者:王耀威
 * 时间:2018年1月7号
 * 异步解析
 * <p>
 * <p>
 * ****************************************
 */
public class YeMian_03 extends Fragment {
    private ViewPager vp;
    private ArrayList<String> list;
    private TabLayout tab;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yemian_03, container, false);

        tab = view.findViewById(R.id.tab);
        vp = view.findViewById(R.id.vp);
        list = new ArrayList<>();
        list.add("热门影片");
        list.add("正在上映");
        list.add("即将上映");
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new YeMian_04());
        fragments.add(new YeMian_05());
        fragments.add(new YeMian_06());

        vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                YeMian_05 frag_04 = new YeMian_05();
                return frag_04;
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }
        });

        tab.setupWithViewPager(vp);


        return view;
    }
}
