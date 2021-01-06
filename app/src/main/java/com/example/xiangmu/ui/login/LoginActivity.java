package com.example.xiangmu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseActivity;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.interfaces.home.ILogin;
import com.example.xiangmu.model.home.bean.LoginBean;
import com.example.xiangmu.presenter.Login.LoginPresenter;
import com.example.xiangmu.utils.SpUtils;
import com.example.xiangmu.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<ILogin.Presenter> implements ILogin.View{
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.img_pw)
    ImageView imgPw;
    @BindView(R.id.layout_pw)
    FrameLayout layoutPw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_zhuce)
    Button btn_zhuce;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected ILogin.Presenter createPrenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        imgPw.setTag(1);
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.btn_login,R.id.img_pw,R.id.btn_zhuce})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.img_pw:
                int tag = (int) imgPw.getTag();
                if (tag == 1) {
                    imgPw.setImageResource(R.mipmap.ic_pw_open);
                    imgPw.setTag(2);
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    imgPw.setImageResource(R.mipmap.ic_pw_close);
                    imgPw.setTag(1);
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.btn_zhuce:
                Intent intent = new Intent(this, MeRegistActivity.class);
                startActivity(intent);
                break;
        }
    }
        private void login(){
            String username = etUsername.getText().toString();
            String pw = etPwd.getText().toString();
            if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw)){
                presenter.login(username,pw);
            }else{
                ToastUtils.s(this,getString(R.string.tips_login));
            }
        }

    @Override
    public void loginReturn(LoginBean loginBean) {
        if(!TextUtils.isEmpty(loginBean.getData().getToken())){
            SpUtils.getInstance().setValue("token",loginBean.getData().getToken());
            SpUtils.getInstance().setValue("uid",loginBean.getData().getUserInfo().getUid());
            finish();
        }
    }
}
