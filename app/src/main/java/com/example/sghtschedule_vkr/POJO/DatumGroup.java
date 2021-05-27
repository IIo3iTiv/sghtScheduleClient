package com.example.sghtschedule_vkr.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumGroup {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("shortName")
    @Expose
    private String shortName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}