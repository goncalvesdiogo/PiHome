package com.home.pihome.quartz.jobs;

import com.home.pihome.bean.ApplicationBean;
import com.home.pihome.gpio.JnaController;
import com.sun.jna.ptr.DoubleByReference;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class PiHomeDht22Job implements Job {
    
    public ApplicationBean applicationBean = new ApplicationBean().getInstance();
    private JnaController jna = new JnaController();
    private DoubleByReference temperature;
    private DoubleByReference humidity;

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>PiHomeDht22Job<<<<<<<<<<<<<<<<<");

            int tries = 0;

            temperature = new DoubleByReference(-99);
            humidity = new DoubleByReference(-99);

            while ((temperature.getValue() < 0 || humidity.getValue() < 0) && tries <= 10) {
                //jna.getDht22Data(temperature, humidity);
                tries++;
            }

            if (temperature.getValue() < 0 && humidity.getValue() < 0) {
                applicationBean.setTemperature(0.0);
                applicationBean.setHumidity(0.0);
            } else {
                applicationBean.setTemperature(temperature.getValue());
                applicationBean.setHumidity(humidity.getValue());
                System.out.println("Temperature: " + temperature.getValue() + " - Humidity: " + humidity.getValue());
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
