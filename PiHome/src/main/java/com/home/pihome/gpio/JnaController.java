package com.home.pihome.gpio;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.DoubleByReference;

public class JnaController {

    public interface DhtLib extends Library {

        public int initWitingPi ();

        public int getDht22Data(int dhtpin, DoubleByReference t, DoubleByReference h);
    }

    public void initDht22Lib() {
        System.setProperty("jna.library.path", "/home/pi/Projetos/DhtLib/");
        DhtLib dht = (DhtLib) Native.loadLibrary("dht22.so", DhtLib.class);
        dht.initWitingPi();
        
    }
    
    public void getDht22Data(DoubleByReference temperature, DoubleByReference humidity) {
       try{
        System.setProperty("jna.library.path", "/home/pi/Projetos/DhtLib/");
        DhtLib dht = (DhtLib) Native.loadLibrary("dht22.so", DhtLib.class);
        dht.getDht22Data(7,temperature,humidity);
       }catch(Exception e){
           System.out.println("Exception: " + e);
       }
    }
}
