package com.example.xiangmu.ui.me.collect;

import io.realm.RealmObject;

//TODO 收藏的bean类
public class Favorites extends RealmObject {
    private String pic;
    private String name;
    private String price;
    private String title;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}