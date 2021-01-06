package com.example.xiangmu.ui.sort;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.app.MyApp;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.base.BaseFragment;
import com.example.xiangmu.interfaces.home.ISort;
import com.example.xiangmu.model.home.bean.SortBean;
import com.example.xiangmu.model.home.bean.SortDataBean;
import com.example.xiangmu.presenter.home.SortPresenter;

import java.util.List;

import butterknife.BindView;

public class SortDataFragment extends BaseFragment <ISort.Presenter> implements ISort.View {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_heat_name)
    TextView tvHeatName;
    @BindView(R.id.recyler)
    RecyclerView recyler;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sortdata;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        id = arguments.getInt("id");
    }

    @Override
    protected ISort.Presenter createPrenter() {
        return new SortPresenter(this);
    }


    @Override
    protected void initData() {
        presenter.getSortData(id);
    }

    @Override
    public void getSortReturn(SortBean result) {

    }

    @Override
    public void getSortDataReturn(SortDataBean result) {
        initSortData(result.getData().getCurrentCategory());
        initRecucler(result.getData().getCurrentCategory().getSubCategoryList());
    }


    private void initSortData(SortDataBean.DataBean.CurrentCategoryBean currentCategory) {
        tvName.setText(currentCategory.getFront_name());
        tvHeatName.setText(currentCategory.getName()+"分类");
        Glide.with(getActivity()).load(currentCategory.getWap_banner_url()).into(image);
    }


    private void initRecucler(List <SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList) {
        recyler.setLayoutManager(new GridLayoutManager(getActivity(),3));
        SortDataAdapter sortDataAdapter = new SortDataAdapter(getActivity(),subCategoryList);
        recyler.setAdapter(sortDataAdapter);
        sortDataAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(getActivity(),TypeInfoListActivity.class);
                MyApp.getMap().put("typelist",subCategoryList);

                String name = subCategoryList.get(pos).getName();
                MyApp.getMap().put("typename", name);

                startActivity(intent);
            }
        });
    }
}
