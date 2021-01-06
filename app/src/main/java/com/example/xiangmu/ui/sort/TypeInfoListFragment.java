package com.example.xiangmu.ui.sort;

import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseFragment;
import com.example.xiangmu.interfaces.home.IBrand;
import com.example.xiangmu.interfaces.home.IHome;
import com.example.xiangmu.interfaces.home.ISort;
import com.example.xiangmu.model.home.bean.BrandBean;
import com.example.xiangmu.model.home.bean.BrandGoodsBean;
import com.example.xiangmu.model.home.bean.BrandListDetailsBean;
import com.example.xiangmu.model.home.bean.CategoryBean;
import com.example.xiangmu.model.home.bean.GoodsBean;
import com.example.xiangmu.model.home.bean.HomeBean;
import com.example.xiangmu.model.home.bean.SortBean;
import com.example.xiangmu.model.home.bean.SortDataBean;
import com.example.xiangmu.presenter.home.BrandPresenter;
import com.example.xiangmu.presenter.home.HomePresenter;
import com.example.xiangmu.presenter.home.SortPresenter;
import com.example.xiangmu.ui.home.adapter.CategoryGoodsAdapter;
import com.example.xiangmu.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TypeInfoListFragment extends BaseFragment <IHome.Presenter> implements IHome.View {

    @BindView(R.id.tv_channel1_title)
    TextView tv_Title;
    @BindView(R.id.tv_channel1_front_desc)
    TextView tv_Desc;
    @BindView(R.id.mRlv_channelType)
    RecyclerView mRlv;
    private int id;
    private String name;
    private String front_name;
    List<GoodsBean.DataBeanX.DataBean> list;
    private CategoryGoodsAdapter channelAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_channel;
    }



    @Override
    protected void initView() {
        TxtUtils.setTextView(tv_Title,name);
        TxtUtils.setTextView(tv_Desc,front_name);

        mRlv.setLayoutManager(new GridLayoutManager(mContext, 2));
        list = new ArrayList<>();
        channelAdapter = new CategoryGoodsAdapter(mContext, list);
        mRlv.setAdapter(channelAdapter);
    }

    @Override
    protected IHome.Presenter createPrenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initData() {
       presenter.getGoods(id,1,1000);
    }




    public void getId(int id) {
        this.id = id;
    }

    public void getName(String name, String front_name) {
        this.name = name;
        this.front_name = front_name;
    }




    @Override
    public void getHomeReturn(HomeBean result) {

    }

    @Override
    public void getCategoryReturn(CategoryBean result) {

    }

    @Override
    public void getGoodsReturn(GoodsBean result) {
        list.addAll(result.getData().getData());
        channelAdapter.notifyDataSetChanged();
    }

    @Override
    public void getBrandsReturn(BrandBean result) {

    }
}
