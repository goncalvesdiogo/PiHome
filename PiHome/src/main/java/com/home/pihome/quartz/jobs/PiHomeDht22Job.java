package com.home.pihome.quartz.jobs;

import com.home.pihome.bean.ApplicationBean;
import com.home.pihome.gpio.JnaController;
import com.sun.jna.ptr.DoubleByReference;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class PiHomeDht22Job implements Job {

    @Inject
    private ApplicationBean applicationBean;
    private JnaController jna = new JnaController();
    private DoubleByReference temperature;
    private DoubleByReference humidity;

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            //FacesContext context = FacesContext.getCurrentInstance();
            //applicationBean = context.getApplication().evaluateExpressionGet(context, "#{applicationBean}", ApplicationBean.class);
            int tries = 0;

            temperature= new DoubleByReference(-99);
            humidity = new DoubleByReference(-99);

            while ((temperature.getValue() < 0 || humidity.getValue() < 0) && tries <= 10) {
                jna.getDht22Data(temperature, humidity);
                tries++;
            }
            System.out.println("Temperature: " + temperature.getValue() + " - Humidity: " + humidity.getValue());
            applicationBean.setTemperature(temperature.getValue());
            applicationBean.setHumidity(humidity.getValue());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
