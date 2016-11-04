package com.home.pihome.bean;


import com.home.pihome.gpio.JnaController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;



@ManagedBean(eager=true)
@ApplicationScoped
public class ApplicationBean {
    
    private String texto;
    private Double temperature;
    private Double humidity;
    JnaController jna;
    
    public ApplicationBean(){
        jna = new JnaController();
        jna.initDht22Lib();        
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
