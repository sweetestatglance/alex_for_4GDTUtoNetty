package com.rxkj.entity;

import java.io.Serializable;

/**
 * by alex
 * 2020.11.18
 * 设备下挂载的泵的信息
 */
public class PumpCtrl implements Serializable {

    //id
    private Integer id;

    //泵的编号
    private String pumpid;

    //泵名称，用处
    private String pumpname;

    //所属设备，挂载到哪个通信id下的
    private String ownedevice_id;

    //状态，开关状态,1表示开着，2表示关闭状态
    private Integer state;

    //开启指令，hex字符串
    private String open_command;

    //关闭指令，hex字符串
    private String off_command;

    //从机地址，或者mobus地址 hex
    private String codehead;

    //写寄存器code功能码
    private String writereg_code;

    //寄存器地址
    private String register_addr;

    //开启的功能码
    private String pumponcode;

    //关闭的功能码
    private String pumpoffcode;

    //额定电压
    private Double rated_voltage;

    //额定电流
    private Double rated_current;

    //额定功率
    private Double rated_power;

    //过载电流 保护值
    private Double overload_current;

    //备注
    private String mark;

    public PumpCtrl() {
    }

    public PumpCtrl(Integer id, String pumpid, String pumpname, String ownedevice_id, Integer state, String open_command, String off_command, String codehead, String writereg_code, String register_addr, String pumponcode, String pumpoffcode, Double rated_voltage, Double rated_current, Double rated_power, Double overload_current, String mark) {
        this.id = id;
        this.pumpid = pumpid;
        this.pumpname = pumpname;
        this.ownedevice_id = ownedevice_id;
        this.state = state;
        this.open_command = open_command;
        this.off_command = off_command;
        this.codehead = codehead;
        this.writereg_code = writereg_code;
        this.register_addr = register_addr;
        this.pumponcode = pumponcode;
        this.pumpoffcode = pumpoffcode;
        this.rated_voltage = rated_voltage;
        this.rated_current = rated_current;
        this.rated_power = rated_power;
        this.overload_current = overload_current;
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPumpid() {
        return pumpid;
    }

    public void setPumpid(String pumpid) {
        this.pumpid = pumpid;
    }

    public String getPumpname() {
        return pumpname;
    }

    public void setPumpname(String pumpname) {
        this.pumpname = pumpname;
    }

    public String getOwnedevice_id() {
        return ownedevice_id;
    }

    public void setOwnedevice_id(String ownedevice_id) {
        this.ownedevice_id = ownedevice_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOpen_command() {
        return open_command;
    }

    public void setOpen_command(String open_command) {
        this.open_command = open_command;
    }

    public String getOff_command() {
        return off_command;
    }

    public void setOff_command(String off_command) {
        this.off_command = off_command;
    }

    public String getCodehead() {
        return codehead;
    }

    public void setCodehead(String codehead) {
        this.codehead = codehead;
    }

    public String getWritereg_code() {
        return writereg_code;
    }

    public void setWritereg_code(String writereg_code) {
        this.writereg_code = writereg_code;
    }

    public String getRegister_addr() {
        return register_addr;
    }

    public void setRegister_addr(String register_addr) {
        this.register_addr = register_addr;
    }

    public String getPumponcode() {
        return pumponcode;
    }

    public void setPumponcode(String pumponcode) {
        this.pumponcode = pumponcode;
    }

    public String getPumpoffcode() {
        return pumpoffcode;
    }

    public void setPumpoffcode(String pumpoffcode) {
        this.pumpoffcode = pumpoffcode;
    }

    public Double getRated_voltage() {
        return rated_voltage;
    }

    public void setRated_voltage(Double rated_voltage) {
        this.rated_voltage = rated_voltage;
    }

    public Double getRated_current() {
        return rated_current;
    }

    public void setRated_current(Double rated_current) {
        this.rated_current = rated_current;
    }

    public Double getRated_power() {
        return rated_power;
    }

    public void setRated_power(Double rated_power) {
        this.rated_power = rated_power;
    }

    public Double getOverload_current() {
        return overload_current;
    }

    public void setOverload_current(Double overload_current) {
        this.overload_current = overload_current;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "PumpCtrl{" +
                "id=" + id +
                ", pumpid='" + pumpid + '\'' +
                ", pumpname='" + pumpname + '\'' +
                ", ownedevice_id='" + ownedevice_id + '\'' +
                ", state=" + state +
                ", open_command='" + open_command + '\'' +
                ", off_command='" + off_command + '\'' +
                ", codehead='" + codehead + '\'' +
                ", writereg_code='" + writereg_code + '\'' +
                ", register_addr='" + register_addr + '\'' +
                ", pumponcode='" + pumponcode + '\'' +
                ", pumpoffcode='" + pumpoffcode + '\'' +
                ", rated_voltage=" + rated_voltage +
                ", rated_current=" + rated_current +
                ", rated_power=" + rated_power +
                ", overload_current=" + overload_current +
                ", mark='" + mark + '\'' +
                '}';
    }

}
