package com.home.pihome.gpio;

import com.home.pihome.bean.ApplicationBean;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.DoubleByReference;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JnaController {

    public ApplicationBean applicationBean = new ApplicationBean().getInstance();

    public interface DhtLib extends Library {

        int initWiringPi();

        int read_dht22_dat(int DHTPIN, DoubleByReference temperature, DoubleByReference humidity);
    }

    public void initWiringPi() {
        try {
            System.setProperty("jna.library.path", "/home/pi/Projetos/DhtLib/");
            DhtLib dht = (DhtLib) Native.loadLibrary("dht22.so", DhtLib.class);
            if (dht.initWiringPi() == 0) {
                System.out.println("Erro ao iniciar WiringPi.");
            } else {
                System.out.println("WiringPi iniciado com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public void getDht22Data(DoubleByReference temperature, DoubleByReference humidity, int tries) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        try {
            System.setProperty("jna.library.path", "/home/pi/Projetos/DhtLib/");
            DhtLib dht = (DhtLib) Native.loadLibrary("dht22.so", DhtLib.class);
            

            while (dht.read_dht22_dat(7, temperature, humidity) == 0 && tries > 0) {
                synchronized (this) {
                    this.wait(1500);
                }
                tries--;
            }

            if (temperature.getValue() > 0 && humidity.getValue() > 0) {
                applicationBean.setTemperature(temperature.getValue());
                applicationBean.setHumidity(humidity.getValue());
                applicationBean.setLastValidRead(sdf.format(new Date()) + " Temperature: " + applicationBean.getTemperature() + " - Humidity: " + applicationBean.getHumidity());

                System.out.println("Time: " + sdf.format(new Date()) + " Temperature: " + applicationBean.getTemperature() + " - Humidity: " + applicationBean.getHumidity());
            } else {
                System.out.println("Bad data...");
            }
            dht = null;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
