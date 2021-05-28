package com.example.sghtschedule_vkr.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group {

    @SerializedName("data")
    @Expose
    private List<DatumGroup> group = null;

    public List<DatumGroup> getGroup() {
        return group;
    }

    public void setGroup(List<DatumGroup> group) {
        this.group = group;
    }

}