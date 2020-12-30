package com.example.xiangmu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.xiangmu.ui.home.HomeFragment;
import com.example.xiangmu.ui.me.MeFragment;
import com.example.xiangmu.ui.shop.ShopFragment;
import com.example.xiangmu.ui.sort.SortFragment;
import com.example.xiangmu.ui.topic.TopicFragment;
import com.example.xiangmu.utils.CanSlidingViewpager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    CanSlidingViewpager viewPager;

    HomeFragment homeFragment;
    TopicFragment topicFragment;
    SortFragment sortFragment;
    ShopFragment shopFragment;
    MeFragment meFragment;

    private Disposable disposable;
    private ViewPager mVp;
    private TextView tv_dao;
    private PopupWindow window;
    private boolean aBoolean=true;

    List<Fragment> fragments;
    MyFragmentPagerAdapter myFragmentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.nav_view);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setScrollble(false);
    }

    private void initVpData() {
        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_topic, R.id.navigation_sort,R.id.navigation_shop,R.id.navigation_me)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);*/

        //实现bottomNavigationView结合viewpager进行切换
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(myFragmentPagerAdapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        item.setIcon(R.mipmap.ic_menu_choice_pressed);
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_topic:
                        item.setIcon(R.mipmap.ic_menu_topic_pressed);
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_sort:
                        item.setIcon(R.mipmap.ic_menu_sort_pressed);
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_shop:
                        item.setIcon(R.mipmap.ic_menu_shoping_pressed);
                        viewPager.setCurrentItem(3);
                        return true;
                    case R.id.navigation_me:
                        item.setIcon(R.mipmap.ic_menu_me_pressed);
                        viewPager.setCurrentItem(4);
                        return true;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //将fragment放到集合中
    private void initFragment(){
        fragments = new ArrayList <>();
        fragments.add(new HomeFragment());
        fragments.add(new TopicFragment());
        fragments.add(new SortFragment());
        fragments.add(new ShopFragment());
        fragments.add(new MeFragment());
    }
    //适配器
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments;
        public MyFragmentPagerAdapter(@NonNull FragmentManager fm, List <Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    protected void onResume() {

        int id = getIntent().getIntExtra("id",0);
        viewPager.setCurrentItem(id);
        super.onResume();
    }

    private void initPop() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(R.mipmap.a1);
        integerList.add(R.mipmap.a2);
        integerList.add(R.mipmap.a3);

        View view = View.inflate(this,R.layout.layout_pop,null);
        window = new PopupWindow(view, -1,-1);
        tv_dao = view.findViewById(R.id.tv_dao);
        mVp = view.findViewById(R.id.mVp);
        VpAdapter vpAdapter = new VpAdapter(this,integerList, window);
        mVp.setAdapter(vpAdapter);
        popupVpCli();
        window.showAsDropDown(this.findViewById(R.id.viewPager));
    }

    private void popupVpCli() {
        //页码的点击监听
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2){//在最后一页执行倒计时
                    tv_dao.setVisibility(View.VISIBLE);
                    //TODO       Interval操作符(有范围)：创建一个按照固定时间发射整数序列的Observable
                    disposable = Observable.intervalRange(0, 6, 0, 1, TimeUnit.SECONDS) //起始值，发送总数量，初始延迟，固定延迟
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer <Long>() {
                                @Override
                                public void accept(Long aLong) throws Exception {
                                    long time = 5 - aLong;
                                    tv_dao.setText(time + "s");
                                    if (time == 0) {
                                        window.dismiss();
                                    }
                                    tv_dao.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            window.dismiss();
                                        }
                                    });
                                }
                            });
                }else{
                    tv_dao.setVisibility(View.GONE);
                    cancelCallback();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (aBoolean){
            //initPop();
            initFragment();
            initVpData();
            aBoolean=false;
        }

    }

    //取消订阅的方法
    private void cancelCallback() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}