package com.rxkj.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * by alex
 * 2020.11.18
 * DTU设备信息
 */
//@Table(name = "deviceinfo")
public class DeviceInfo implements Serializable {

    //用于数据索引id,主键自增
    private Integer device_id;

    //设备通信id，心跳及登录发送内容为设备id
    private String device_ctid;

    //设备出厂身份识别id，为厂家编制的唯一身份识别id,物联网云id
    private String device_vertifyid;

    //设备名称
    private String device_name;

    //所在省份
    private String device_provice;

    //所在市（区）县
    private String device_cityandcounty;

    //设备地址
    private String device_location;

    //设备型号
    private String device_type;

    //设备信息描述
    private String device_description;

    //设备管理人，使用者
    private String device_keeper;

    //设备管理/使用者联系电话
    private String device_keeperphone;

    //设备创建时间
    private Date device_firstonline;

    //设备状态，1是在线，2是不在线
    private Integer device_state;

    //设备权限等级，预留设计项
    private String device_authlevel;

    //备注
    private String device_mark;

    public DeviceInfo() {
    }

    public DeviceInfo(Integer device_id, String device_ctid, String device_vertifyid, String device_name, String device_provice, String device_cityandcounty, String device_location, String device_type, String device_description, String device_keeper, String device_keeperphone, Date device_firstonline, Integer device_state, String device_authlevel, String device_mark) {
        this.device_id = device_id;
        this.device_ctid = device_ctid;
        this.device_vertifyid = device_vertifyid;
        this.device_name = device_name;
        this.device_provice = device_provice;
        this.device_cityandcounty = device_cityandcounty;
        this.device_location = device_location;
        this.device_type = device_type;
        this.device_description = device_description;
        this.device_keeper = device_keeper;
        this.device_keeperphone = device_keeperphone;
        this.device_firstonline = device_firstonline;
        this.device_state = device_state;
        this.device_authlevel = device_authlevel;
        this.device_mark = device_mark;
    }

    public Integer getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Integer device_id) {
        this.device_id = device_id;
    }

    public String getDevice_ctid() {
        return device_ctid;
    }

    public void setDevice_ctid(String device_ctid) {
        this.device_ctid = device_ctid;
    }

    public String getDevice_vertifyid() {
        return device_vertifyid;
    }

    public void setDevice_vertifyid(String device_vertifyid) {
        this.device_vertifyid = device_vertifyid;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_provice() {
        return device_provice;
    }

    public void setDevice_provice(String device_provice) {
        this.device_provice = device_provice;
    }

    public String getDevice_cityandcounty() {
        return device_cityandcounty;
    }

    public void setDevice_cityandcounty(String device_cityandcounty) {
        this.device_cityandcounty = device_cityandcounty;
    }

    public String getDevice_location() {
        return device_location;
    }

    public void setDevice_location(String device_location) {
        this.device_location = device_location;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_description() {
        return device_description;
    }

    public void setDevice_description(String device_description) {
        this.device_description = device_description;
    }

    public String getDevice_keeper() {
        return device_keeper;
    }

    public void setDevice_keeper(String device_keeper) {
        this.device_keeper = device_keeper;
    }

    public String getDevice_keeperphone() {
        return device_keeperphone;
    }

    public void setDevice_keeperphone(String device_keeperphone) {
        this.device_keeperphone = device_keeperphone;
    }

    public Date getDevice_firstonline() {
        return device_firstonline;
    }

    public void setDevice_firstonline(Date device_firstonline) {
        this.device_firstonline = device_firstonline;
    }

    public Integer getDevice_state() {
        return device_state;
    }

    public void setDevice_state(Integer device_state) {
        this.device_state = device_state;
    }

    public String getDevice_authlevel() {
        return device_authlevel;
    }

    public void setDevice_authlevel(String device_authlevel) {
        this.device_authlevel = device_authlevel;
    }

    public String getDevice_mark() {
        return device_mark;
    }

    public void setDevice_mark(String device_mark) {
        this.device_mark = device_mark;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "device_id=" + device_id +
                ", device_ctid='" + device_ctid + '\'' +
                ", device_vertifyid='" + device_vertifyid + '\'' +
                ", device_name='" + device_name + '\'' +
                ", device_provice='" + device_provice + '\'' +
                ", device_cityandcounty='" + device_cityandcounty + '\'' +
                ", device_location='" + device_location + '\'' +
                ", device_type='" + device_type + '\'' +
                ", device_description='" + device_description + '\'' +
                ", device_keeper='" + device_keeper + '\'' +
                ", device_keeperphone='" + device_keeperphone + '\'' +
                ", device_firstonline=" + device_firstonline +
                ", device_state=" + device_state +
                ", device_authlevel='" + device_authlevel + '\'' +
                ", device_mark='" + device_mark + '\'' +
                '}';
    }
}
