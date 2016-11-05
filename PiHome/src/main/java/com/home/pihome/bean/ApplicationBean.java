package com.home.pihome.bean;


import com.home.pihome.gpio.JnaController;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;



@ManagedBean(eager=true)
public class ApplicationBean implements Serializable{
    
    private static ApplicationBean instance = null;
    private String texto;
    private Double temperature;
    private Double humidity;
    JnaController jna;
    
    
    public ApplicationBean getInstance() {
        if(instance == null) {    
            System.out.println(">>>>>>>>>>>>>>>>>>>>>ApplicationBean<<<<<<<<<<<<<<<<<");
            instance = new ApplicationBean();
            jna = new JnaController();
            jna.initWiringPi();
        }
        return instance;
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
    
    
}
