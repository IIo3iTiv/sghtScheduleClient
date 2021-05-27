package com.example.sghtschedule_vkr;

import com.example.sghtschedule_vkr.POJO.CheckWork;
import com.example.sghtschedule_vkr.POJO.GroupInfo;
import com.example.sghtschedule_vkr.POJO.TeacherName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerAPI {

    @GET("php/api/getTeacherName")
    Call<TeacherName> getTeacherName();

    @GET("php/api/getGroupInfo")
    Call<GroupInfo> getGroupInfo(@Query("course") Integer course);

    @GET("php/api/checkWork")
    Call<CheckWork> checkWork();

}
