package com.example.xiangmu.ui.me;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.xiangmu.R;
import com.example.xiangmu.app.MyApp;
import com.example.xiangmu.base.BaseFragment;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.ui.login.LoginActivity;

import butterknife.BindView;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmu.ui.me.collect.FavoritesActivity;
import com.example.xiangmu.utils.SpUtils;
import com.example.xiangmu.utils.ToastUtils;
import com.live.RoomActivity;


import butterknife.BindView;
import butterknife.OnClick;

//TODO 我的展示
public class MeFragment extends BaseFragment {
    @BindView(R.id.ll_my_address)
    LinearLayout ll_address;
    @BindView(R.id.ll_streaming)
    LinearLayout ll_streaming;
    @BindView(R.id.iv_my_img)
    ImageView iv_Img;
    /*@BindView(R.id.iv_my_return)
    ImageView iv_Return;*/



    public static final int LOGIN_ME = 10001; //登录成功的回传值
    public static final int LOGINOUT_ME = 10002; //退出登录的回传
    @BindView(R.id.tv_my_login)
    TextView tv_Login;


    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }



    @Override
    protected void initView() {
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @OnClick({R.id.ll_my_address, R.id.ll_five_collect, R.id.iv_my_img, R.id.tv_my_login,R.id.ll_streaming})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_streaming://开启直播
                Intent intent = new Intent(getActivity(), RoomActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_my_address://地址管理
               /* Intent intent = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);*/
                break;
            case R.id.ll_five_collect://收藏页面
                Intent intent1 = new Intent(getActivity(), FavoritesActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_my_img://点击头像
                Intent intent2 = new Intent(getActivity(), Head_PortraitActivity.class);
                startActivityForResult(intent2,LOGINOUT_ME);
                break;
       /*     case R.id.iv_my_return://退出登录
                loginOut();
                break;*/
            case R.id.tv_my_login://退出登录
                initLogin();
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_ME && resultCode == 100) {
            String name = data.getStringExtra("name");//登录之后显示名字
            tv_Login.setText(name);
        }
        if(requestCode == LOGINOUT_ME&&resultCode==200){
                loginOut();
        }
    }

    //判断是否登录
    private void initLogin() {
        String token = SpUtils.getInstance().getString("token");
        if (!TextUtils.isEmpty(token)) {
            openUserInfoDetail();
        } else {
            openLogin();
        }
    }

    //TODO 退出登录
    private void loginOut() {
        SpUtils.getInstance().remove("token");
        isLogin(false);
    }

    //TODO 打开登录页面
    private void openLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivityForResult(intent, LOGIN_ME);
    }

    //TODO 打开用户信息
    private void openUserInfoDetail() {
        ToastUtils.s(getActivity(), "此用户已登录");
    }



    //TODO 登录状态的界面控制

    private void isLogin(boolean b) {
        if (b) {
            String username = SpUtils.getInstance().getString("username");
            String nickname = SpUtils.getInstance().getString("nickname");
            String avatar = SpUtils.getInstance().getString("avatar");
            String mark = SpUtils.getInstance().getString("mark");
            if (!TextUtils.isEmpty(nickname)) {
                tv_Login.setText(username);
            } else {
                // tv_Login.setText(username);
            }
            //ImageLoaderUtils.loadImage(avatar, iv_Img);
            String img = SpUtils.getInstance().getString("img");
            Log.i(TAG, "isLogin: "+img);

            if (!TextUtils.isEmpty(img)) {
                //上传头像后选择头像并返回
                Glide.with(this).load(img).apply(new RequestOptions().circleCrop()).into(iv_Img);
            }
        }
    }


    //TODO 登录成功
    public void loginSuccess() {
        isLogin(true);

    }

    @Override
    protected void initData() {
        String token = SpUtils.getInstance().getString("token");
        if (!TextUtils.isEmpty(token)) {
            isLogin(true);
        } else {
            isLogin(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String txtName =  SpUtils.getInstance().getString("txtName");

        tv_Login.setText(txtName);
    }

    private static final String TAG = "MeFragment";
}
