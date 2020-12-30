package com.example.xiangmu.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.app.MyApp;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.base.BaseFragment;
import com.example.xiangmu.interfaces.home.IHome;
import com.example.xiangmu.model.home.bean.BrandBean;
import com.example.xiangmu.model.home.bean.CategoryBean;
import com.example.xiangmu.model.home.bean.GoodsBean;
import com.example.xiangmu.model.home.bean.HomeBean;
import com.example.xiangmu.presenter.home.HomePresenter;
import com.example.xiangmu.ui.home.activity.BrandActivity;
import com.example.xiangmu.ui.home.activity.BrandLIstActivity;
import com.example.xiangmu.ui.home.activity.CategoryActivity;
import com.example.xiangmu.ui.home.activity.GoogsDetailActicity;
import com.example.xiangmu.ui.home.activity.HotGoodActivity;
import com.example.xiangmu.ui.home.adapter.BrandAdapter;
import com.example.xiangmu.ui.home.adapter.CategoryAdapter;
import com.example.xiangmu.ui.home.adapter.GoodsAdapter;
import com.example.xiangmu.ui.home.adapter.HotGoodsAdapter;
import com.example.xiangmu.ui.home.adapter.TopicAdapter;
import com.example.xiangmu.utils.ItemDecoration;
import com.example.xiangmu.utils.TxtUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment <IHome.Presenter> implements IHome.View {

    @BindView(R.id.layout_tab)
    LinearLayout layoutTab;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.txt_brand_title)
    TextView txtBrandTitle;
    @BindView(R.id.recy_brand)
    RecyclerView recyBrand;
    @BindView(R.id.txt_newgood_title)
    TextView txtNewgoodTitle;
    @BindView(R.id.tv_special)
    TextView tv_special;
    @BindView(R.id.recy_newgood)
    RecyclerView recyNewgood;
    @BindView(R.id.recy_hotGoods)
    RecyclerView recyHotGoods;
    @BindView(R.id.recy_topic)
    RecyclerView recyTopic;
    @BindView(R.id.jujia)
    LinearLayout jujia;


    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPrenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presenter.getHome(); //初始化加载数据
        presenter.getBrands();
    }

    @Override
    public void getHomeReturn(HomeBean result) {
        if (result.getData() != null) {
            initBanner(result.getData().getBanner());
            initChannel(result.getData().getChannel());
            initBrand(result.getData().getBrandList());
            initGoods(result.getData().getNewGoodsList());
            initHotGoods(result.getData().getHotGoodsList());
            initTopic(result.getData().getTopicList());
            initCategory(result.getData().getCategoryList());
        }
    }

    @Override
    public void getCategoryReturn(CategoryBean result) {

    }

    @Override
    public void getGoodsReturn(GoodsBean result) {

    }
    /*****************品牌制造商点击*****************/
    @Override
    public void getBrandsReturn(BrandBean result) {
        initBrands(result.getData().getData());
        Log.i(TAG, "getBrandsReturn: "+result.getData().getData().get(0).getName());
    }
    /**
     * 初始化banner
     *
     * @param list
     */
    private void initBanner(List <HomeBean.DataBean.BannerBean> list) {
        if (list != null) {
            List <String> list1 = new ArrayList <>();
            for (int i = 0; i < list.size(); i++) {
                list1.add(list.get(i).getImage_url());
            }
            banner.setImages(list1).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load((String) path).into(imageView);
                }
            }).start();
        }
    }
    /**
     * 初始化channel
     */
    private void initChannel(List <HomeBean.DataBean.ChannelBean> list) {
        layoutTab.removeAllViews();
        for (HomeBean.DataBean.ChannelBean item : list) {
            View channel = LayoutInflater.from(getContext()).inflate(R.layout.layout_channel_item, null);
            ImageView image = channel.findViewById(R.id.img_channel);
            TextView text = channel.findViewById(R.id.txt_channel);
            channel.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.MarginLayoutParams.MATCH_PARENT, 1.0f));//设置weight
            String icon_url = item.getIcon_url();
            Glide.with(getActivity()).load(icon_url).into(image);
            text.setText(item.getName());
            layoutTab.addView(channel);
            channel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, CategoryActivity.class);
                    intent.putExtra("url", item.getUrl());
                    intent.putExtra("name", item.getName());
                    startActivity(intent);
                }
            });
        }
    }

    /**************厂家直供*****************/
    private void initBrand(List <HomeBean.DataBean.BrandListBean> brandList) {
        recyBrand.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        BrandAdapter brandAdapter = new BrandAdapter(getActivity(), brandList);
        recyBrand.setAdapter(brandAdapter);
        //点击条目进入详情页面
        brandAdapter.setOnClickItem(new BrandAdapter.OnClickItem() {
            @Override
            public void ClicItem(int pos) {
                Intent intent = new Intent(getContext(), BrandActivity.class);
                intent.putExtra("name",brandList.get(pos).getName());
                intent.putExtra("simple",brandList.get(pos).getSimple_desc());
                intent.putExtra("pic",brandList.get(pos).getList_pic_url());
                startActivity(intent);
            }
        });
    }

    /*******新品首发**********/
    private void initGoods(List <HomeBean.DataBean.NewGoodsListBean> newGoodsList) {
        recyNewgood.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        GoodsAdapter goodsAdapter = new GoodsAdapter(getActivity(), newGoodsList);
        recyNewgood.setAdapter(goodsAdapter);
        recyNewgood.addItemDecoration(new ItemDecoration(getActivity()));

        txtNewgoodTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HotGoodActivity.class));
            }
        });
        //点击条目跳转
        goodsAdapter.setOnClickItem(new GoodsAdapter.onClickItem() {
            @Override
            public void ClickItem(int pos) {
                int id = newGoodsList.get(pos).getId();
                Intent intent = new Intent(getActivity(), GoogsDetailActicity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

    }

    /*************人气推荐*************/
    private void initHotGoods(List <HomeBean.DataBean.HotGoodsListBean> hotGoodsList) {
        recyHotGoods.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotGoodsAdapter hotGoodsAdapter = new HotGoodsAdapter(getActivity(), hotGoodsList);
        recyHotGoods.setAdapter(hotGoodsAdapter);
        recyHotGoods.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        /*GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(ContextCompat.getColor(getActivity(), R.color.colorA));
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setSize(0, 3);
        recyHotGoods.addItemDecoration(new DividerItemDecoration(getActivity(),drawable));*/
        hotGoodsAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int id = hotGoodsList.get(pos).getId();
                Intent intent = new Intent(getActivity(), GoogsDetailActicity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    /***************专题精选******************/
    private void initTopic(List <HomeBean.DataBean.TopicListBean> topicList) {
        recyTopic.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        TopicAdapter topicAdapter = new TopicAdapter(mActivity, topicList);
        recyTopic.setAdapter(topicAdapter);
        tv_special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**************居家*******************/
    private void initCategory(List <HomeBean.DataBean.CategoryListBean> categoryList) {
        for (int i = 0; i < categoryList.size(); i++) {
            View view = View.inflate(mActivity, R.layout.item_category, null);
            TextView tv_heat_name = view.findViewById(R.id.tv_heat_name);
            RecyclerView ry_category = view.findViewById(R.id.ry_category);
            TxtUtils.setTextView(tv_heat_name, categoryList.get(i).getName());
            ry_category.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            CategoryAdapter categoryAdapter = new CategoryAdapter(mActivity, categoryList.get(i).getGoodsList());
            ry_category.setAdapter(categoryAdapter);
            ry_category.addItemDecoration(new ItemDecoration(getActivity()));
            jujia.addView(view);

            List <HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsList = categoryList.get(i).getGoodsList();
            categoryAdapter.addListClick(new BaseAdapter.IListClick() {
                @Override
                public void itemClick(int pos) {
                    int id = goodsList.get(pos).getId();
                    Intent intent = new Intent(getActivity(), GoogsDetailActicity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            });
        }
    }
    /*****************品牌制造商点击*****************/
    private void initBrands(List <BrandBean.DataBeanX.DataBean> data) {
        txtBrandTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApp.getMap().put("name",data);
                Intent intent = new Intent(getActivity(), BrandLIstActivity.class);
                startActivity(intent);
            }
        });
    }
    private static final String TAG = "HomeFragment";
}
