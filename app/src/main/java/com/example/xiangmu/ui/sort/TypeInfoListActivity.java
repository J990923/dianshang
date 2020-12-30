package com.example.xiangmu.ui.sort;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.xiangmu.R;
import com.example.xiangmu.app.MyApp;
import com.example.xiangmu.base.BaseActivity;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.model.home.bean.SortDataBean;
import com.example.xiangmu.utils.CanSlidingViewpager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TypeInfoListActivity extends BaseActivity {


    @BindView(R.id.mTab_channel)
    TabLayout mTab;
    @BindView(R.id.mVp_channel)
    CanSlidingViewpager mVpChannel;

    private List <SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list;
    private ArrayList <TypeInfoListFragment> fragments;
    private String name;


    @Override
    protected int getLayout() {
        return R.layout.activity_channel;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }


    @Override
    protected void initView() {
        list = (List <SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean>) MyApp.getMap().get("typelist");
        fragments = new ArrayList <>();

        //禁止滑动
        mVpChannel.setScrollble(false);


        name = (String) MyApp.getMap().get("typename");
    }


    @Override
    protected void initData() {
        for (int i = 0; i < this.list.size(); i++) {
            int id = list.get(i).getId();
            String name = list.get(i).getName();
            String front_name = list.get(i).getFront_name();
            TypeInfoListFragment typeInfoListFragment = new TypeInfoListFragment();
            typeInfoListFragment.getId(id);
            typeInfoListFragment.getName(name, front_name);

            fragments.add(typeInfoListFragment);
        }

        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager());
        mVpChannel.setAdapter(vpAdapter);
        mTab.setupWithViewPager(mVpChannel);

        for (int i = 0; i < list.size(); i++) {
            if (this.name.equals(list.get(i).getName())) {
                mVpChannel.setCurrentItem(i);
                return;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public class VpAdapter extends FragmentStatePagerAdapter {
        public VpAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position).getName();
        }
    }
}