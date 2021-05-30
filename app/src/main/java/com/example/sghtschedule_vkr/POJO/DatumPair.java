package com.example.sghtschedule_vkr.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumPair {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("numPair")
    @Expose
    private String numPair;
    @SerializedName("timeStart")
    @Expose
    private String timeStart;
    @SerializedName("timeEnd")
    @Expose
    private String timeEnd;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("coursStudy")
    @Expose
    private String coursStudy;
    @SerializedName("teacher")
    @Expose
    private String teacher;
    @SerializedName("discipline")
    @Expose
    private String discipline;
    @SerializedName("auditorium")
    @Expose
    private String auditorium;
    @SerializedName("subgroup")
    @Expose
    private String subgroup;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumPair() {
        return numPair;
    }

    public void setNumPair(String numPair) {
        this.numPair = numPair;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCoursStudy() {
        return coursStudy;
    }

    public void setCoursStudy(String group) {
        this.coursStudy = coursStudy;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(String auditorium) {
        this.auditorium = auditorium;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

}