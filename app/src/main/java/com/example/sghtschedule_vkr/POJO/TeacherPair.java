package com.example.sghtschedule_vkr.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeacherPair {

    @SerializedName("data")
    @Expose
    private List<DatumTeacherPair> data = null;

    public List<DatumTeacherPair> getData() {
        return data;
    }

    public void setData(List<DatumTeacherPair> data) {
        this.data = data;
    }

}