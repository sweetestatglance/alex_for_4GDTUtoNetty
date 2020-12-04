package com.rxkj.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * by alex
 * 2020.11.18
 * 泵运行参数类
 */
public class PumpOperateData implements Serializable {

    //id
    private Integer id;

    //所属泵的id
    private Integer owne_pumpid;

    //所属设备通信id
    private String ownedevice_id;

    //实时电压
    private Double voltage;

    //实时电流
    private Double current;

    //实时流量
    private Double flow;

    //采集时间
    private Date collectiontime;

    //备注
    private String mark;

    public PumpOperateData() {
    }

    public PumpOperateData(Integer id, Integer owne_pumpid, String ownedevice_id, Double voltage, Double current, Double flow, Date collectiontime, String mark) {
        this.id = id;
        this.owne_pumpid = owne_pumpid;
        this.ownedevice_id = ownedevice_id;
        this.voltage = voltage;
        this.current = current;
        this.flow = flow;
        this.collectiontime = collectiontime;
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwne_pumpid() {
        return owne_pumpid;
    }

    public void setOwne_pumpid(Integer owne_pumpid) {
        this.owne_pumpid = owne_pumpid;
    }

    public String getOwnedevice_id() {
        return ownedevice_id;
    }

    public void setOwnedevice_id(String ownedevice_id) {
        this.ownedevice_id = ownedevice_id;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Double getFlow() {
        return flow;
    }

    public void setFlow(Double flow) {
        this.flow = flow;
    }

    public Date getCollectiontime() {
        return collectiontime;
    }

    public void setCollectiontime(Date collectiontime) {
        this.collectiontime = collectiontime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "PumpOperateData{" +
                "id=" + id +
                ", owne_pumpid=" + owne_pumpid +
                ", ownedevice_id='" + ownedevice_id + '\'' +
                ", voltage=" + voltage +
                ", current=" + current +
                ", flow=" + flow +
                ", collectiontime=" + collectiontime +
                ", mark='" + mark + '\'' +
                '}';
    }
}
