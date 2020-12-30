package com.example.xiangmu.ui.sort;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseFragment;
import com.example.xiangmu.interfaces.home.ISort;
import com.example.xiangmu.model.home.bean.SortBean;
import com.example.xiangmu.model.home.bean.SortDataBean;
import com.example.xiangmu.presenter.home.SortPresenter;
import com.example.xiangmu.ui.home.activity.CategoryActivity;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class SortFragment extends BaseFragment<ISort.Presenter> implements ISort.View {
    @BindView(R.id.tab)
    VerticalTabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList <String> mTitlesList;
    private ArrayList<Fragment> mFragmentsList;
    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected ISort.Presenter createPrenter() {
        return new SortPresenter(this);
    }


    @Override
    protected void initView() {

    }


    @Override
    protected void initData() {
        presenter.getSort();

    }

    @Override
    public void getSortReturn(SortBean result) {
        initSort(result.getData().getCategoryList());
    }
        //tab数据
    private void initSort(List <SortBean.DataBean.CategoryListBean> categoryList) {
        mTitlesList=new ArrayList <>();
        mFragmentsList = new ArrayList<>();//添加碎片
        for (int i = 0; i < categoryList.size(); i++) {
            mTitlesList.add(categoryList.get(i).getName());
            int id = categoryList.get(i).getId();
            SortDataFragment sortFragment = new SortDataFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id",id);
            sortFragment.setArguments(bundle);
            mFragmentsList.add(sortFragment);
        }
        VpAdapter vpAdapter = new VpAdapter(getParentFragmentManager());
        vp.setAdapter(vpAdapter);
        tab.setupWithViewPager(vp);
    }

    @Override
    public void getSortDataReturn(SortDataBean result) {

    }
        class VpAdapter extends FragmentPagerAdapter {

            public VpAdapter(@NonNull FragmentManager fm) {
                super(fm);
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragmentsList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentsList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitlesList.get(position).toString();

            }
        }
}
