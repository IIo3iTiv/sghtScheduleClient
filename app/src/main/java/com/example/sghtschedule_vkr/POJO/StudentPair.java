package com.example.sghtschedule_vkr.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentPair {

    @SerializedName("data")
    @Expose
    private List<DatumStudentPair> data = null;

    public List<DatumStudentPair> getData() {
        return data;
    }

    public void setData(List<DatumStudentPair> data) {
        this.data = data;
    }

}