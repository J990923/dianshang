package com.example.xiangmu.ui.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseActivity;
import com.example.xiangmu.interfaces.Login.IMeRegist;
import com.example.xiangmu.model.Login.MeRegisterBean;
import com.example.xiangmu.presenter.Login.MeRegistPresenter;
import com.example.xiangmu.utils.CodeUtils;
import com.example.xiangmu.utils.SpUtils;
import com.example.xiangmu.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//注册界面
public class MeRegistActivity extends BaseActivity <IMeRegist.Presenter> implements IMeRegist.View {


    @BindView(R.id.me_regist_input_username)
    EditText meRegistInputUsername;
    @BindView(R.id.me_regist_input_pw)
    EditText meRegistInputPw;
    @BindView(R.id.me_regist_input_pw2)
    EditText meRegistInputPw2;
    @BindView(R.id.me_regist_input_verification)
    EditText meRegistInputVerification;
    @BindView(R.id.me_regist_btn_verification)
    ImageView meRegistBtnVerification;
    @BindView(R.id.me_regist_btn_regist)
    Button meRegistBtnRegist;
    private String username;
    @Override
    protected int getLayout() {
        return R.layout.activity_regist;
    }

    @Override
    protected IMeRegist.Presenter createPrenter() {
        return new MeRegistPresenter(this);
    }


    @Override
    protected void initView() {

        //验证码
        Bitmap bitmap = CodeUtils.getInstance().createBitmap();
        meRegistBtnVerification.setImageBitmap(bitmap);

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.me_regist_btn_verification, R.id.me_regist_btn_regist})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_regist_btn_verification:
                //验证码
                Bitmap bitmap = CodeUtils.getInstance().createBitmap();
                meRegistBtnVerification.setImageBitmap(bitmap);
                break;
            case R.id.me_regist_btn_regist:
                initRegist();
                break;
        }
    }

    private void initRegist() {

        username = meRegistInputUsername.getText().toString();
        String pw = meRegistInputPw.getText().toString();
        String pw2 = meRegistInputPw2.getText().toString();
        String ver = meRegistInputVerification.getText().toString();

        //不能为空
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw) && !TextUtils.isEmpty(pw2)){
            //密码和确认密码必须一致
            if (pw.equals(pw2)){
                //密码大于6位
                if(pw.length() >= 6){
                    //验证码不能为空
                    if(ver.equals("") || ver.length() != 0){
                        //取出sp中存入的username
                        String string = SpUtils.getInstance().getString(username);
                        Log.e("TAG", "initRegist: "+string );
                        //判断sp中是否有存入的username
                        if (!TextUtils.isEmpty(string)){     //如果有存入的
                            //用户名已经注册
                            ToastUtils.s(this,getString(R.string.tips_regist_));
                            return;
                        }else{     //没有存入的
                            //注册方法
                            zhuce(username,pw);
                        }
                    }else{
                        //验证码不能为空
                        ToastUtils.s(this,getString(R.string.tips_regist_ver));
                    }
                }else{
                    //密码大于6位
                    ToastUtils.s(this,getString(R.string.tips_regist_pw_6));
                }
            }else {
                //密码不一样
                ToastUtils.s(this,getString(R.string.tips_regist_pw_pw2));
            }
        }else{
            //null
            ToastUtils.s(this,getString(R.string.tips_regist));
        }
    }

    private void zhuce(String username, String pw) {
        /**
         * 1.注册
         * 2.将用户名最为key 密钥（token）作为value 存入sp (sp.....set)
         */
        presenter.login(username, pw);
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void loginReturn(MeRegisterBean meRegisterBean) {
        if (meRegisterBean.getData().getToken().toString()!=null){
            String token = meRegisterBean.getData().getToken().toString();
            //如果获得token不为空
            if(!TextUtils.isEmpty(token)){

                //保存到sp中
                SpUtils.getInstance().setValue(username, token);
                SpUtils.getInstance().setValue("uid",meRegisterBean.getData().getUserInfo().getUid());
                //回传值到登录界面
                String name = meRegistInputUsername.getText().toString();
                String pw = meRegistInputPw.getText().toString();
                Intent intent = getIntent();
                intent.putExtra("name",name);
                intent.putExtra("pw",pw);
                setResult(100,intent);
                finish();//关闭当前页面返回之前页面
            }
        }



    }

}