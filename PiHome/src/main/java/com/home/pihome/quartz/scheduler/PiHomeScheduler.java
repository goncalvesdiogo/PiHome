package com.home.pihome.quartz.scheduler;

import com.home.pihome.quartz.jobs.PiHomeDht22Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class PiHomeScheduler {

    private static Scheduler scheduler;

    public static void init() {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            iniciarDht22Job("IniciarDht22Job", 60);
        } catch (SchedulerException se) {
            System.out.println("Exception: " + se);
        }
    }

    public static void destroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException se) {
            System.out.println("Exception: " + se);
        }
    }

    private static void iniciarDht22Job(String jobName, int timeInSeconds) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(PiHomeDht22Job.class).withIdentity("job" + jobName, "group" + jobName).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("job" + jobName, "group" + jobName).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(timeInSeconds).repeatForever()).build();
        scheduler.scheduleJob(job, trigger);
        scheduler.startDelayed(15);
    }

}
