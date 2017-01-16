package com.home.pihome.quartz.jobs;

import com.home.pihome.gpio.JnaController;
import com.sun.jna.ptr.DoubleByReference;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class PiHomeDht22Job implements Job {

    private JnaController jna;
    private DoubleByReference temperature;
    private DoubleByReference humidity;

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>PiHomeDht22Job<<<<<<<<<<<<<<<<<");

            int tries = 10;

            temperature = new DoubleByReference(-99);
            humidity = new DoubleByReference(-99);
            jna = new JnaController();
            jna.getDht22Data(temperature, humidity, tries);
            
            
            

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
