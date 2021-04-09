package com.example.stackoverflow.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyResponse {

    @SerializedName("items")
    @Expose
    private List<Items> items;

    @SerializedName("has_more")
    @Expose
    private Boolean hasMore;

    public MyResponse() {
    }

    public MyResponse(List<Items> items, Boolean hasMore) {
        this.items = items;
        this.hasMore = hasMore;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
