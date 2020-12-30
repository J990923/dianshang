package com.example.xiangmu.ui.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.tv.TvView;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.MainActivity;
import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseActivity;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.interfaces.home.IGoodsDetail;
import com.example.xiangmu.model.home.bean.CategoryBean;
import com.example.xiangmu.model.home.bean.SPGoodsDetail;
import com.example.xiangmu.model.home.bean.SPGoodsRelated;
import com.example.xiangmu.model.shap.AddCarBean;
import com.example.xiangmu.presenter.home.GoodsDetailPresenter;
import com.example.xiangmu.ui.home.adapter.CategoryBigImageAdapter;
import com.example.xiangmu.ui.home.adapter.CategoryButtomInfoAdapter;
import com.example.xiangmu.ui.home.adapter.CategoryIssueAdapter;
import com.example.xiangmu.ui.home.adapter.CategoryParameterAdapter;
import com.example.xiangmu.ui.me.collect.Favorites;
import com.example.xiangmu.ui.me.collect.Realms;
import com.example.xiangmu.utils.ItemDecoration;
import com.example.xiangmu.utils.ToastUtils;
import com.example.xiangmu.utils.TxtUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class GoogsDetailActicity extends BaseActivity <IGoodsDetail.Presenter> implements IGoodsDetail.View {
    //    @BindView(R.id.webView_category)
//    WebView webView;
    @BindView(R.id.mRlv_category_all)
    RecyclerView mRlv_all;//底部列表数据
    @BindView(R.id.mRlv_category_issue)
    RecyclerView mRlv_issue;//常见问题
    @BindView(R.id.mRlv_category_parameter)
    RecyclerView mRlv_parameter;//商品参数
    @BindView(R.id.home__detail_info_price)
    TextView home__detail_info_price;//商品价格
    @BindView(R.id.home__detail_info_title)
    TextView home__detail_info_title;//商品名称
    @BindView(R.id.tv_category_addCar1)
    TextView tv_category_addCar;//加入购物车
    @BindView(R.id.banner_category)
    Banner banner_category;//商品参数
    @BindView(R.id.tv_category_number)
    TextView tv_category_number;//购物车 数据
     @BindView(R.id.cl_number)
    ConstraintLayout cl_number;//购物车 数据

    @BindView(R.id.mRlv_categroy_bigimage)
    RecyclerView mRlv_bigimage;
    @BindView(R.id.iv_category_car)
    ImageView imgcategory;
    @BindView(R.id.img_collect)//收藏
    ImageView img_collect;
    SPGoodsDetail spGoodsDetail;
    private int shu = 1;
    int buyNumber=1; //购买的数量

    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";
    private int id;
    private ArrayList <SPGoodsRelated.DataBean.GoodsListBean> goodsList;//底部列表集合
    private ArrayList <SPGoodsDetail.DataBeanX.IssueBean> issuelist;//常见问题集合
    private ArrayList <SPGoodsDetail.DataBeanX.AttributeBean> attributeList;//商品参数集合
    private CategoryButtomInfoAdapter categoryButtomInfoAdapter;
    private CategoryIssueAdapter categoryIssueAdapter;
    private CategoryParameterAdapter categoryParameterAdapter;
    private TextView tv_shu;
    private SPGoodsDetail.DataBeanX.InfoBean info;
    private PopupWindow popupWindow;
    int i=1;

    @Override
    protected int getLayout() {
        return R.layout.activity_goodsdetail;
    }

    @Override
    protected IGoodsDetail.Presenter createPrenter() {
        return new GoodsDetailPresenter(this);
    }

    @Override
    protected void initView() {
        id = getIntent().getIntExtra("id", 0);
        initViewIssue();//常见问题布局
        initcategory();//购物车
        initBottomInfo();//底部列表数据
        initViewParameter();//商品参数
        initImager();
        initnumber();
        initcollect();
    }

    private void initcollect() {
        img_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_collect.setImageResource(R.mipmap.icon_collect);
                //添加数据库
                Realms.getRealm(GoogsDetailActicity.this).executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Favorites favorites = realm.createObject(Favorites.class);
                        favorites.setName(info.getName());
                        favorites.setPic(info.getList_pic_url());
                        favorites.setPrice(info.getRetail_price());
                        favorites.setTitle(info.getGoods_brief());
                        Toast.makeText(GoogsDetailActicity.this, "收藏成功 ", Toast.LENGTH_SHORT).show();
                        Log.e("TAG", "execute: " + info.getName() + info.getList_pic_url());
                    }
                });
            }
        });
    }

    private void initnumber() {
        cl_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initShowShowPop();
            }
        });
    }

    private void initImager() {
        imgcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCar();
            }
        });
    }

    // 点击弹出pop购物车页面
    private void initcategory() {
        tv_category_addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initShowShowPop();
            }
        });
    }
    private  boolean pop;
    private void initShowShowPop() {
        if (info != null) {
            View join_view = LayoutInflater.from(this).inflate(R.layout.join_item, null);
            popupWindow = new PopupWindow(join_view, GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT);
            ImageView image_pop = join_view.findViewById(R.id.image_pop);
            TextView price_pop = join_view.findViewById(R.id.tv_price_pop);
            Button btn_jia = join_view.findViewById(R.id.btn_jia);
            Button btn_jian = join_view.findViewById(R.id.btn_jian);
            tv_shu = join_view.findViewById(R.id.btn_shu);
            TextView tv_back = join_view.findViewById(R.id.tv_back);
            popupWindow.setFocusable(true);
            Glide.with(this).load(info.getList_pic_url()).into(image_pop);
            price_pop.setText("￥" + info.getRetail_price() + "");
            popupWindow.setOutsideTouchable(true);
            ClickListener clickListener = new ClickListener();
            btn_jia.setOnClickListener(clickListener);
            btn_jian.setOnClickListener(clickListener);
            popupWindow.showAtLocation(mRlv_all, Gravity.BOTTOM, 0, 130);
            tv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();

                }
            });
        }
    }

    class ClickListener implements View.OnClickListener {
        TextView number = findViewById(R.id.tv_category_number);


        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.btn_jia:
                    i++;
                    if (i > 0) {
                        shu+=i;
                        tv_shu.setText(String.valueOf(i));
                        String s = number.getText().toString();
                        TxtUtils.setTextView(number,Integer.valueOf(s)+1+"");
                    }
                    break;
                case R.id.btn_jian:
                    i--;
                    if (i > 0) {
                        shu-=i;
                        tv_shu.setText(String.valueOf(i));

                        String s1 = number.getText().toString();
                        TxtUtils.setTextView(number,Integer.valueOf(s1)-1+"");
                    }else {
                        shu=1;
                        return;
                    }
                    break;


            }
        }
    }
    /**
     * 添加进购物车
     */
    private void addCar(){
        if(i <= 0){
            ToastUtils.s(this,"请选中购买商品数量");
            return;
        }
        if(spGoodsDetail.getData().getProductList().size() > 0){
            int goodsId = this.spGoodsDetail.getData().getProductList().get(0).getGoods_id();
            int productid = this.spGoodsDetail.getData().getProductList().get(0).getId();
            Map <String,String> map = new HashMap <>();
            map.put("goodsId",String.valueOf(goodsId));
            map.put("number",String.valueOf(i));
            map.put("productId",String.valueOf(productid));
            presenter.addGoodCar(map);
            popupWindow.dismiss();
            Intent intent = new Intent();
            intent.setClass(GoogsDetailActicity.this, MainActivity.class);
            intent.putExtra("id",3);
            startActivity(intent);
        }

    }
    //商品参数布局
    private void initViewParameter() {
        attributeList = new ArrayList <>();
        mRlv_parameter.setLayoutManager(new LinearLayoutManager(this));
        categoryParameterAdapter = new CategoryParameterAdapter(this, attributeList);
        mRlv_parameter.setAdapter(categoryParameterAdapter);
    }
    //常见问题布局
    private void initViewIssue() {
        issuelist = new ArrayList <>();
        mRlv_issue.setLayoutManager(new LinearLayoutManager(this));
        categoryIssueAdapter = new CategoryIssueAdapter(this, issuelist);
        mRlv_issue.setAdapter(categoryIssueAdapter);
    }
    // 底部列表数据
    private void initBottomInfo() {
        goodsList = new ArrayList <>();
        mRlv_all.setLayoutManager(new GridLayoutManager(this, 2));
        mRlv_all.addItemDecoration(new ItemDecoration(this));
        categoryButtomInfoAdapter = new CategoryButtomInfoAdapter(this, goodsList);
        mRlv_all.setAdapter(categoryButtomInfoAdapter);
    }
    @Override
    protected void initData() {
        presenter.getGoodsDetail(id);
        presenter.getGoodsRelated(id);
    }
    @Override
    public void getGoodsDetailReturn(SPGoodsDetail result) {
        this.spGoodsDetail=result;
        if (result != null) {
            //文本数据
            info = result.getData().getInfo();
            initText(result.getData().getInfo());//文本数据
            //常见问题数据
            initIssue(result.getData().getIssue());
            //h5 商品详情
            initGoodDetail(result.getData().getInfo().getGoods_desc());
            //商品参数
            initParameter(result.getData().getAttribute());
            //bann
            initbann(result.getData().getGallery());
            //展示goods_desc
            showImage(result.getData().getInfo().getGoods_desc());
        }
    }
    private void showImage(String goods_desc) {
        ArrayList <String> listUrl = new ArrayList <>();
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(goods_desc);
        while (matcher.find()) {
            String word = matcher.group();
            int start = word.indexOf("\"") + 1;
            int end = word.indexOf(".jpg");
            if (end > 0) {//如果是jpg格式的就截取jpg
                String url = word.substring(start, end);
                if (url != null) {
                    url = url + ".jpg";
                    listUrl.add(url);
                } else {
                    return;
                }
            } else {
                int end1 = word.indexOf(".png");//如果是png格式的就截取png
                String url = word.substring(start, end1);
                if (url != null) {
                    url = url + ".png";
                    listUrl.add(url);
                } else {
                    return;
                }
            }
        }
        //mRlv_bigimage.setVisibility(View.VISIBLE);
        mRlv_bigimage.setLayoutManager(new LinearLayoutManager(this));
        CategoryBigImageAdapter categoryBigImageAdapter = new CategoryBigImageAdapter(this, listUrl);
        mRlv_bigimage.setAdapter(categoryBigImageAdapter);
        //点击条目跳转
        categoryBigImageAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("image", listUrl);
                Intent intent = new Intent(GoogsDetailActicity.this, BigImageActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }
    // 商品文本数据
    private void initText(SPGoodsDetail.DataBeanX.InfoBean info) {
        home__detail_info_price.setText("￥" + info.getRetail_price());
        home__detail_info_title.setText(info.getName());

    }
    /**
     * 商品详情数据  h5
     */
    // 商品参数数据
    private void initParameter(List <SPGoodsDetail.DataBeanX.AttributeBean> attribute) {
        attributeList.addAll(attribute);
        categoryParameterAdapter.notifyDataSetChanged();
    }
    // 常见问题数据
    private void initIssue(List <SPGoodsDetail.DataBeanX.IssueBean> issue) {
        issuelist.addAll(issue);
        categoryIssueAdapter.notifyDataSetChanged();
    }
    // h5 商品详情数据
    private void initGoodDetail(String webData) {
        String content = h5.replace("word", webData);
        Log.i("TAG", content);
        //webView.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
    }
    /*
     *  bann轮播图
     * */
    private void initbann(List <SPGoodsDetail.DataBeanX.GalleryBean> gallery) {
        if (gallery != null) {
            List <String> list1 = new ArrayList <>();
            for (int i = 0; i < gallery.size(); i++) {
                list1.add(gallery.get(i).getImg_url());
            }
            banner_category.setImages(list1).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load((String) path).into(imageView);
                }
            }).start();
        }
    }
    @Override
    public void getGoodsRelatedReturn(SPGoodsRelated result) {
        List <SPGoodsRelated.DataBean.GoodsListBean> data = result.getData().getGoodsList();
        goodsList.addAll(data);
        categoryButtomInfoAdapter.notifyDataSetChanged();
    }
/*
* 添加购物车
* */
    @Override
    public void addGoodCarReturn(AddCarBean addCarBean) {
        if (addCarBean!=null){
            //添加成功以后跟新数量显示
            int number = addCarBean.getData().getCartTotal().getGoodsCount();
            tv_shu.setText(String.valueOf(number));
            tv_shu.setVisibility(View.VISIBLE);
            tv_category_number.setText(String.valueOf(number));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
