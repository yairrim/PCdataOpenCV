package src.com.company;

import static src.com.company.Main.*;
import static java.lang.Double.parseDouble;

public class messageProcessing {
    public static double[] horizontalPID = new double[3];
    public static double[] horizontalDATA = new double[3];
    public static double[] verticalPID = new double[3];
    public static double[] verticalDATA = new double[3];



    public messageProcessing() {

    }

    public static void processMessage(String receivedMessage) {
        System.out.println("Received: "+receivedMessage);

        String[] splitMessage =receivedMessage.split("%");

        for (String message : splitMessage){
            String[] splitString = message.split(",");
            String id = splitString[0];

            if (id.equals("PIDV")){
                processVerticalPID(splitString);
            }else {
                if (id.equals("DATAV")){
                    processVerticalDATA(splitString);
                }else {
                    if (id.equals("PIDH")){
                        processHorizontalPid(splitString);
                    }else {
                        if (id.equals("DATAH")){
                            processHorizontalDATA(splitString);
                        }
                    }
                }
            }
        }
    }

    private static void processVerticalPID(String[] splitString){
        verticalPID[0] = parseDouble(splitString[1]);
        verticalPID[1] = parseDouble(splitString[2]);
        verticalPID[2] = parseDouble(splitString[3]);
    }

    private static void processVerticalDATA(String[] splitString){
        masV = parseDouble(splitString[3]);
        mipV = parseDouble(splitString[1]);
        mapV = parseDouble(splitString[2]);
        //System.out.println(masV+" "+ mipV+" "+ mapV);
    }

    private static void processHorizontalPid(String[] splitString){
        horizontalPID[0] = parseDouble(splitString[1]);
        horizontalPID[1] = parseDouble(splitString[2]);
        horizontalPID[2] = parseDouble(splitString[3]);
    }

    private static void processHorizontalDATA(String[] splitString){
        mipH = parseDouble(splitString[1]);
        mapH = parseDouble(splitString[2]);
        masH = parseDouble(splitString[3]);
        //System.out.println(masH+" "+ mipH+" "+ mapH);
    }
}