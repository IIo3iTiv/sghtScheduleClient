package com.example.sghtschedule_vkr.Retrofit;

import com.example.sghtschedule_vkr.POJO.Group;
import com.example.sghtschedule_vkr.POJO.Pair;
import com.example.sghtschedule_vkr.POJO.Teacher;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerAPI {

    @GET("php/api/getTeacherName")
    Call<Teacher> getTeacherName();

    @GET("php/api/getGroupInfo")
    Call<Group> getGroupInfo(@Query("course") Integer course);

    @GET("php/api/getStudentPair")
    Call<Pair> getStudentPair(@Query("date") String date, @Query("group") String group, @Query("subGroup") String subGroup, @Query("foreign") String foreign);

    @GET("php/api/getTeacherPair")
    Call<Pair> getTeacherPair(@Query("date") String date, @Query("teacher") String teacherIndex);

}
