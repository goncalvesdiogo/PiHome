package com.home.pihome.quartz.listener;

import com.home.pihome.quartz.scheduler.PiHomeScheduler;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PiHomeListener implements ServletContextListener{
    
    @Override
    public void contextInitialized(ServletContextEvent sce){
        PiHomeScheduler.init();
    }
    
     
    @Override
    public void contextDestroyed(ServletContextEvent sce){
        PiHomeScheduler.destroy();
    }
}
