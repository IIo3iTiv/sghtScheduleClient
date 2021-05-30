package com.example.sghtschedule_vkr.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pair {

    @SerializedName("data")
    @Expose
    private List<DatumPair> data = null;

    public List<DatumPair> getData() {
        return data;
    }

    public void setData(List<DatumPair> data) {
        this.data = data;
    }

}