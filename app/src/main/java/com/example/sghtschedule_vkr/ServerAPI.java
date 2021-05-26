package com.example.sghtschedule_vkr;

import com.example.sghtschedule_vkr.POJO.TeacherNameModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerAPI {

    @GET("php/api/getTeacherName")
    Call<TeacherNameModel> getTeacherName();

}
