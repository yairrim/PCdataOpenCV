package src.com.company;


import org.opencv.core.Core;

import java.io.*;
import java.util.Random;


import static src.com.company.ComputerDebugging.*;


public class Main{

    public static double masH=0;
    public static double mipH=0;
    public static double mapH=0;
    public static VideoCap videoCap;
    public static double masV=0;
    public static double mipV=0;
    public static double mapV=0;

    public static void main(String[] args) throws IOException {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        videoCap = new VideoCap();
        String serverName = "192.168.1.18";
        Thread t = new client(serverName, 1574);
        try {
            t.start();
            while (true) {
                sendDriveParameters(randomNumber(-1,1),randomNumber(-1,1),randomNumber(-1,1),randomNumber(-1,1));
                sendHorizontalParameters(randomNumber(-1,1),randomNumber(0,2000),randomNumber(0,2000));
                sendVerticalParameters(randomNumber(-1,1),randomNumber(0,2000),randomNumber(0,2000));
                sendPos(randomNumber(0,365.7),randomNumber(0,365.7),randomNumber(0,360));
                sendVideoFrame(videoCap.getOneFrame());
                markEndOfUpdate();
                System.out.println("sending");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static double randomNumber(double min, double max){
        Random r = new Random();
        double randomValue = min + (max - min) * r.nextDouble();
        return randomValue;
    }


}
