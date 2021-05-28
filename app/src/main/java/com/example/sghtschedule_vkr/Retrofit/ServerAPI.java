package com.example.sghtschedule_vkr.Retrofit;

import com.example.sghtschedule_vkr.POJO.CheckWork;
import com.example.sghtschedule_vkr.POJO.Group;
import com.example.sghtschedule_vkr.POJO.StudentPair;
import com.example.sghtschedule_vkr.POJO.Teacher;
import com.example.sghtschedule_vkr.POJO.TeacherPair;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerAPI {

    @GET("php/api/getTeacherName")
    Call<Teacher> getTeacherName();

    @GET("php/api/getGroupInfo")
    Call<Group> getGroupInfo(@Query("course") Integer course);

    @GET("php/api/checkWork")
    Call<CheckWork> checkWork();

    @GET("php/api/getStudentPair")
    Call<StudentPair> getStudentPair();

    @GET("php/api/getTeacherPair")
    Call<TeacherPair> getTeacherPair();

}
