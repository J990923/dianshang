package com.example.xiangmu.ui.home.fragment;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseFragment;
import com.example.xiangmu.interfaces.home.IHome;
import com.example.xiangmu.model.home.bean.BrandBean;
import com.example.xiangmu.model.home.bean.CategoryBean;
import com.example.xiangmu.model.home.bean.GoodsBean;
import com.example.xiangmu.model.home.bean.HomeBean;
import com.example.xiangmu.presenter.home.HomePresenter;
import com.example.xiangmu.ui.home.adapter.CategoryGoodsAdapter;
import com.example.xiangmu.utils.ItemDecoration;

import java.util.List;

import butterknife.BindView;

public class CategoryFragment extends BaseFragment<IHome.Presenter> implements IHome.View {
    @BindView(R.id.tv_heat_name)
    TextView tvHeatName;
    @BindView(R.id.tv_front_name)
    TextView tvFrontName;
    @BindView(R.id.reycler)
    RecyclerView reycler;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView() {
        id = getArguments().getInt("id");
        String name = getArguments().getString("name");
        String front_name = getArguments().getString("front_name");
        tvHeatName.setText(name);
        tvFrontName.setText(front_name);
    }

    @Override
    protected IHome.Presenter createPrenter() {
        return new HomePresenter(this);
    }


    @Override
    protected void initData() {

        presenter.getGoods(id,1,20); //初始化加载数据
    }

    @Override
    public void getHomeReturn(HomeBean result) {

    }

    @Override
    public void getCategoryReturn(CategoryBean result) {

    }

    @Override
    public void getGoodsReturn(GoodsBean result) {
       initGoods(result.getData().getData());
    }

    @Override
    public void getBrandsReturn(BrandBean result) {

    }

    private void initGoods(List <GoodsBean.DataBeanX.DataBean> data) {
        if (data!=null){
            reycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
            CategoryGoodsAdapter categoryGoodsAdapter = new CategoryGoodsAdapter(getActivity(),data);
            reycler.setAdapter(categoryGoodsAdapter);
            reycler.addItemDecoration(new ItemDecoration(getActivity()));
        }else {
            Toast.makeText(mContext, "请稍等", Toast.LENGTH_SHORT).show();
        }
    }

    private static final String TAG = "CategoryFragment";
}
