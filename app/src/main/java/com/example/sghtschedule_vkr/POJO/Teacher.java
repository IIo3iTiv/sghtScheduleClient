package com.example.sghtschedule_vkr.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teacher {

    @SerializedName("data")
    @Expose
    private List<DatumTeacher> data = null;

    public List<DatumTeacher> getData() {
        return data;
    }

    public void setData(List<DatumTeacher> data) {
        this.data = data;
    }
}
