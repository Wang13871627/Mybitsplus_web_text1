package com.huayu.mybitsplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Employee {
    @TableId(value = "eid",type = IdType.AUTO)
    private Integer eid;
    private String name;
    private String password;
    private String sex;
    private Integer age;
    private String hobby;
    private String idcardup;
    private String idcarddown;
    private String postid;
    private Integer deptid;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public String getIdcardup() {
        return idcardup;
    }

    public void setIdcardup(String idcardup) {
        this.idcardup = idcardup == null ? null : idcardup.trim();
    }

    public String getIdcarddown() {
        return idcarddown;
    }

    public void setIdcarddown(String idcarddown) {
        this.idcarddown = idcarddown == null ? null : idcarddown.trim();
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid == null ? null : postid.trim();
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                ", idcardup='" + idcardup + '\'' +
                ", idcarddown='" + idcarddown + '\'' +
                ", postid='" + postid + '\'' +
                ", deptid=" + deptid +
                '}';
    }
}