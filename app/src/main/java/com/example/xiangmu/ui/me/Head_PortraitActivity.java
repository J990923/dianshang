package com.example.xiangmu.ui.me;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmu.R;
import com.example.xiangmu.app.MyApp;
import com.example.xiangmu.base.BaseActivity;
import com.example.xiangmu.interfaces.me.IUser;
import com.example.xiangmu.model.Login.LogoutBean;
import com.example.xiangmu.model.me.UserInfoBean;
import com.example.xiangmu.presenter.me.UserPresenter;
import com.example.xiangmu.utils.BitmapUtils;
import com.example.xiangmu.utils.Constants;
import com.example.xiangmu.utils.GlideEngine;
import com.example.xiangmu.utils.SpUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Head_PortraitActivity extends BaseActivity <IUser.Presenter> implements IUser.View {

    @BindView(R.id.include_head_portrait_one)
    ConstraintLayout inclue_one;
    @BindView(R.id.include_head_portrait_two)
    ConstraintLayout inclue_two;
    @BindView(R.id.include_head_portrait_three)
    ConstraintLayout inclue_three;
    @BindView(R.id.include_head_portrait_four)
    ConstraintLayout inclue_four;
    @BindView(R.id.iv_head_portrait_pic)
    ImageView iv_Pic;
    @BindView(R.id.iv_head_portrait_select_img)
    ImageView iv_Img;
    @BindView(R.id.button)
    Button button;
    String bucketName = "sisinannan";
    String ossPoint = "http://oss-cn-beijing.aliyuncs.com";

    String key = "LTAI4G4Bre4dsJf5adopPFJD";  //appkey
    String secret = "k0BUsIGbV9LtBwqwTwuU7PshB7nVlx";  //密码
    @BindView(R.id.btn_Save)
    Button btnSave;

    private OSS ossClient;
    private EditText nikename;
    private EditText two_right;
    private EditText mark;

    @Override
    protected int getLayout() {
        return R.layout.activity_head_portrait;
    }

    @Override
    protected IUser.Presenter createPrenter() {
        return new UserPresenter();
    }


    @Override
    protected void initView() {
        initOss();
        initIncludeView();

        iv_Pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhoto();
            }
        });
        String img = SpUtils.getInstance().getString("img");
        if (!TextUtils.isEmpty(img)) {
            Glide.with(this).load(img).apply(new RequestOptions().circleCrop()).into(iv_Pic);
        }

    }

    private void initIncludeView() {
        TextView one_left = inclue_one.findViewById(R.id.tv_head_portrait_left);
        TextView two_left = inclue_two.findViewById(R.id.tv_head_portrait_left);
        TextView three_left = inclue_three.findViewById(R.id.tv_head_portrait_left);
        TextView four_left = inclue_four.findViewById(R.id.tv_head_portrait_left);
        nikename = inclue_one.findViewById(R.id.tv_head_portrait_right);
        two_right = inclue_two.findViewById(R.id.tv_head_portrait_right);
        mark = inclue_three.findViewById(R.id.tv_head_portrait_right);
        EditText four_right = inclue_four.findViewById(R.id.tv_head_portrait_right);
        //点击名字弹出来
        ImageView one_img = inclue_one.findViewById(R.id.iv_head_portrait_img);
        ImageView three_img = inclue_three.findViewById(R.id.iv_head_portrait_img);

        //登录保存的用户名
        String name = SpUtils.getInstance().getString("name");

        String txtName = (String) MyApp.getMap().get("txtName");
        String Mark = (String) MyApp.getMap().get("txtMark");

        one_left.setText("昵称");
        nikename.setText(SpUtils.getInstance().getString("txtName"));//姓名 Username
        two_left.setText("微信号");
        two_right.setText(name);
        three_left.setText("签名");
        mark.setText(Mark);
        four_left.setText("更多");//Nikename
        four_right.setText("");
        //修改名字
        /*one_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {*/
                //打开输入的状态
             //   showInput();
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nickname = nikename.getText().toString();
                        if (!TextUtils.isEmpty(nickname)) {
                            HashMap <String, String> map = new HashMap <>();
                            map.put("nickname", nickname);
                          //  persenter.postUpdateUserInfo(map);

                            SpUtils.getInstance().remove("nickname");
                            SpUtils.getInstance().setValue("nickname", nickname);
                            SpUtils.getInstance().remove("txtName");
                            SpUtils.getInstance().setValue("txtName", nickname);
                            nikename.setText(nickname);
                            finish();
                        }
                    }
                });
          /*  }
        });*/

        //修改签名
        three_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* showInput();
                //打开输入的状态
                showInput();*/
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String markName = nikename.getText().toString();
                        if (!TextUtils.isEmpty(markName)) {
                            HashMap <String, String> map = new HashMap <>();
                            map.put("mark", markName);
                          //  persenter.postUpdateUserInfo(map);

                            SpUtils.getInstance().remove("mark");
                            SpUtils.getInstance().setValue("mark", markName);
                            mark.setText(markName);

                        }
                    }
                });
            }
        });
    }



    //初始化OSS
    private void initOss() {
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(key, secret, "");
        // 配置类如果不设置，会有默认配置。
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒。
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒。
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个。
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次。
        ossClient = new OSSClient(getApplicationContext(), ossPoint, credentialProvider);
    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logout();
                intent.putExtra("bo", false);
                setResult(200, intent);
                finish();
            }
        });
    }


    @OnClick({R.id.iv_head_portrait_pic, R.id.iv_head_portrait_select_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head_portrait_pic:
                openPhoto();
                break;
            case R.id.iv_head_portrait_select_img:

                break;
        }
    }

    //TODO 打开相册
    private void openPhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .maxSelectNum(1)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                List <LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() == 0) return;
                //获取本地图片的选择地址，上传到服务器

                //把选中的图片插入到列表
                try {
                    for (int i = 0; i < selectList.size(); i++) {
                        //头像的压缩和二次采样
                        Bitmap scaleBitmp = BitmapUtils.getScaleBitmap(selectList.get(i).getPath(), Constants.HEAD_WIDTH, Constants.HEAD_HEIGHT);
                        //Bitmap转uri
                        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), scaleBitmp, null, null));
                        //uri转字符串
                        String path = getRealPathFromUri(this, uri);
                        uploadHead(path);//上传头像
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);

        }
    }

    //TODO uri转字符串的方法
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    //上传到阿里云
    private void uploadHead(String path) {
        String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
        PutObjectRequest put = new PutObjectRequest(bucketName, fileName, path);
        put.setProgressCallback(new OSSProgressCallback <PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                //上次进度
                Log.i("oss_upload", currentSize + "/" + totalSize);
                // 进度百分比的计算
                // int p = (int) (currentSize/totalSize*100);
                if (currentSize == totalSize) {
                    //完成
                    String headUrl = request.getUploadFilePath();
                    //
                    Log.i("HeadUrl", headUrl);
                    //request.getUploadFilePath()
                }

            }
        });
        OSSAsyncTask task = ossClient.asyncPutObject(put, new OSSCompletedCallback <PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
                //成功的回调中读取相关的上传文件的信息  生成一个url地址
                String url = ossClient.presignPublicObjectURL(request.getBucketName(), request.getObjectKey());
                //TODO 刷新显示到界面上
                updateHead(url);
                //调用服务器接口，把url上传到服务器的接口
                SpUtils.getInstance().setValue("img", url);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常。
                if (clientExcepion != null) {
                    // 本地异常，如网络异常等。
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常。
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    //TODO 上传头像
    private void updateHead(String url) {
        iv_Pic.post(new Runnable() {
            @Override
            public void run() {
                Glide.with(iv_Pic).load(url).apply(new RequestOptions().circleCrop()).into(iv_Pic);
            }
        });
    }

    @Override
    public void updateUserInfoReturn(UserInfoBean result) {

    }

    @Override
    public void logoutReturn(LogoutBean result) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}