package com.example.xiangmu.ui.home.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseActivity;
import com.example.xiangmu.interfaces.home.IHome;
import com.example.xiangmu.model.home.bean.BrandBean;
import com.example.xiangmu.model.home.bean.CategoryBean;
import com.example.xiangmu.model.home.bean.GoodsBean;
import com.example.xiangmu.model.home.bean.HomeBean;
import com.example.xiangmu.presenter.home.HomePresenter;
import com.example.xiangmu.ui.home.fragment.CategoryFragment;
import com.example.xiangmu.utils.CanSlidingViewpager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends BaseActivity <IHome.Presenter> implements IHome.View {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    CanSlidingViewpager viewpager;
    private String url;
    private String name;
    private ArrayList <Fragment> fragments;
    private List <CategoryBean.DataBean.CategoryListBean> categoryList;

    @Override
    protected int getLayout() {
        return R.layout.activity_category;
    }

    @Override
    protected IHome.Presenter createPrenter() {
        return new HomePresenter(this);
    }


    @Override
    protected void initView() {
        url = getIntent().getStringExtra("url");
        name = getIntent().getStringExtra("name");
        viewpager.setScrollble(false);
    }

    @Override
    protected void initData() {
        presenter.getCategory(url); //初始化加载数据
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getHomeReturn(HomeBean result) {

    }

    @Override
    public void getCategoryReturn(CategoryBean result) {
        categoryList = result.getData().getCategoryList();
        initCurrent(categoryList);

    }

    @Override
    public void getGoodsReturn(GoodsBean result) {

    }

    @Override
    public void getBrandsReturn(BrandBean result) {

    }

    private void initCurrent(List <CategoryBean.DataBean.CategoryListBean> categoryList) {
        fragments = new ArrayList <>();
        for (int i = 0; i < categoryList.size(); i++) {
            int id = categoryList.get(i).getId();
            String name = categoryList.get(i).getName();
            String front_name = categoryList.get(i).getFront_name();
            CategoryFragment categoryFragment = new CategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id",id);
            bundle.putString("name",name);
            bundle.putString("front_name",front_name);
            fragments.add(categoryFragment);
            categoryFragment.setArguments(bundle);
        }
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager());
        viewpager.setAdapter(vpAdapter);
        tablayout.setupWithViewPager(viewpager);
        for (int i = 0; i < categoryList.size(); i++) {
            if (this.name.equals(categoryList.get(i).getName())){
                viewpager.setCurrentItem(i);
                return;
            }
        }

    }
class VpAdapter extends FragmentPagerAdapter{

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
        return categoryList.get(position).getName();

    }
}
    /******************tablayout数据*********************/


    private static final String TAG = "CategoryActivity";
}
