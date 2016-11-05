package com.home.pihome.gpio;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.DoubleByReference;

public class JnaController {

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
        try {
            System.setProperty("jna.library.path", "/home/pi/Projetos/DhtLib/");
            DhtLib dht = (DhtLib) Native.loadLibrary("dht22.so", DhtLib.class);

            while (dht.read_dht22_dat(7, temperature, humidity) == 0 && tries > 0) {
                tries--;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
