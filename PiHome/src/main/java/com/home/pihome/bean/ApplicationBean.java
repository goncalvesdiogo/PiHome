package com.home.pihome.bean;

import com.home.pihome.gpio.JnaController;
import com.pi4j.platform.PlatformManager;
import com.pi4j.system.NetworkInfo;
import com.pi4j.system.SystemInfo;
import java.io.IOException;

public class ApplicationBean {

    private static ApplicationBean instance = null;
    private String texto;
    private Double temperature;
    private Double humidity;
    private Double cpuTemp;
    private Double cpuVoltage;
    private Double memoryTotal;
    private Double memoryUsed;
    private Double memoryFree;
    private Double armFreq;
    private Double clockFreq;
    private String lastValidRead;

    private JnaController jna;

    public ApplicationBean getInstance() {
        if (instance == null) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>ApplicationBean<<<<<<<<<<<<<<<<<");
            instance = new ApplicationBean();
            jna = new JnaController();
            jna.initWiringPi();
        }
        return instance;
    }

    public void updateSystemInfo() {
        try {
            

            this.cpuTemp = Double.valueOf(SystemInfo.getCpuTemperature());
            this.cpuVoltage = Double.valueOf(SystemInfo.getCpuVoltage());
            this.memoryTotal = Double.valueOf(SystemInfo.getMemoryTotal()/1048576);
            this.memoryUsed = Double.valueOf(SystemInfo.getMemoryUsed()/1048576);
            this.memoryFree = Double.valueOf(SystemInfo.getMemoryFree()/1048576);
            this.armFreq = Double.valueOf(SystemInfo.getClockFrequencyArm()/1000000);
            this.clockFreq = Double.valueOf(SystemInfo.getClockFrequencyCore()/1000000);
        } catch (InterruptedException iex) {

        } catch (IOException ioex) {

        }
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public JnaController getJna() {
        return jna;
    }

    public void setJna(JnaController jna) {
        this.jna = jna;
    }

    public Double getCpuTemp() {
        return cpuTemp;
    }

    public void setCpuTemp(Double cpuTemp) {
        this.cpuTemp = cpuTemp;
    }

    public Double getCpuVoltage() {
        return cpuVoltage;
    }

    public void setCpuVoltage(Double cpuVoltage) {
        this.cpuVoltage = cpuVoltage;
    }

    public Double getMemoryTotal() {
        return memoryTotal;
    }

    public void setMemoryTotal(Double memoryTotal) {
        this.memoryTotal = memoryTotal;
    }

    public Double getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(Double memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public Double getMemoryFree() {
        return memoryFree;
    }

    public void setMemoryFree(Double memoryFree) {
        this.memoryFree = memoryFree;
    }

    public Double getArmFreq() {
        return armFreq;
    }

    public void setArmFreq(Double armFreq) {
        this.armFreq = armFreq;
    }

    public Double getClockFreq() {
        return clockFreq;
    }

    public void setClockFreq(Double clockFreq) {
        this.clockFreq = clockFreq;
    }

    public String getLastValidRead() {
        return lastValidRead;
    }

    public void setLastValidRead(String lastValidRead) {
        this.lastValidRead = lastValidRead;
    }

}
