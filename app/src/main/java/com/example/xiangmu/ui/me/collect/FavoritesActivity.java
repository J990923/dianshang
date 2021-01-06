package com.example.xiangmu.ui.me.collect;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseActivity;
import com.example.xiangmu.interfaces.IBasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

//TODO 添加数据库
public class FavoritesActivity extends BaseActivity {

    @BindView(R.id.recycler_favorites)
    RecyclerView mRlv;

    private List<Favorites> list;
    private FavoritesAdapter favoritesAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_favorites;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }


    @Override
    protected void initView() {
        list = new ArrayList<>();
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        favoritesAdapter = new FavoritesAdapter(this,list);
        mRlv.setAdapter(favoritesAdapter);

        //查询保存到数据库的值
        RealmResults<Favorites> all = Realms.getRealm(FavoritesActivity.this).where(Favorites.class).findAll();
        //list.clear();
        list.addAll(all);
        favoritesAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

    }

}