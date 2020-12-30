package com.example.xiangmu.ui.topic;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmu.R;

import com.example.xiangmu.app.MyApp;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.base.BaseFragment;
import com.example.xiangmu.interfaces.topic.ITopic;
import com.example.xiangmu.model.topic.TwoBean;
import com.example.xiangmu.presenter.tpoic.TopicPresenter;
import com.example.xiangmu.ui.topic.activity.SpecialCommentActivity;
import com.example.xiangmu.ui.topic.activity.SpecialDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TopicFragment extends BaseFragment <ITopic.Presenter> implements ITopic.View {
    @BindView(R.id.recycler_main)
    RecyclerView recyclerMain;
    @BindView(R.id.bnt_one)
    Button bntOne;
    @BindView(R.id.bnt_two)
    Button bntTwo;
    @BindView(R.id.n)
    NestedScrollView n;
    private TwoAdapter twoAdapter;
    private List <TwoBean.DataBeanX.DataBean> list;

    @Override
    protected int getLayout() {
        return R.layout.fragment_topic;
    }


    @Override
    protected ITopic.Presenter createPrenter() {
        return new TopicPresenter(this);
    }


    @Override
    protected void initView() {
        list = new ArrayList <>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerMain.setLayoutManager(linearLayoutManager);
        recyclerMain.setHasFixedSize(true);
        twoAdapter = new TwoAdapter(getContext(), list);
        recyclerMain.setAdapter(twoAdapter);
        twoAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int id = list.get(pos).getId();
                MyApp.getMap().put("specialId",id);
                Intent intent = new Intent(getActivity(), SpecialDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getTwo(); //初始化加载数据
        presenter.getTwoo(); //初始化加载数据
    }

    @Override
    public void getTwoReturn(TwoBean twoBean) {
        Log.i(TAG, "getTwoReturn: " + twoBean.getData().getData().get(0).getTitle());
        initTopic1(twoBean.getData().getData());
    }

    /***************专题第一页数据***********************/
    private void initTopic1(List <TwoBean.DataBeanX.DataBean> data) {
        list.clear();
        list.addAll(data);
        twoAdapter.notifyDataSetChanged();
        bntOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bntOne.setEnabled(false);
                bntTwo.setEnabled(true);
                list.clear();
                list.addAll(data);
                twoAdapter.notifyDataSetChanged();
                // 返回顶部
                n.fullScroll(ScrollView.FOCUS_UP);
            }
        });

    }

    @Override
    public void getTwooReturn(TwoBean twoBean) {
        Log.i(TAG, "getTwooReturn: " + twoBean.getData().getData().get(0).getTitle());

        List <TwoBean.DataBeanX.DataBean> data = twoBean.getData().getData();
        bntTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bntOne.setEnabled(true);
                bntTwo.setEnabled(false);
                list.clear();
                list.addAll(data);
                twoAdapter.notifyDataSetChanged();
                recyclerMain.scrollToPosition(0);
                n.fullScroll(ScrollView.FOCUS_UP);
            }
        });

    }

    private static final String TAG = "TopicFragment";
}
