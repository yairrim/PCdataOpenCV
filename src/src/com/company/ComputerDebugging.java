package src.com.company;


import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class ComputerDebugging {
    public static StringBuilder messageBuilder = new StringBuilder();

    private static DecimalFormat df = new DecimalFormat("#.00");


    public static void markEndOfUpdate() throws IOException {
        messageBuilder.append("CLEAR,%");
        client.send(messageBuilder.toString());
        messageBuilder.delete(0, messageBuilder.length());
    }


    public static void sendDriveParameters(double lfp, double lbp, double rfp, double rbp) {
        messageBuilder.append("DRIVE,");
        messageBuilder.append(df.format(lfp));
        messageBuilder.append(",");
        messageBuilder.append(df.format(lbp));
        messageBuilder.append(",");
        messageBuilder.append(df.format(rfp));
        messageBuilder.append(",");
        messageBuilder.append(df.format(rbp));
        messageBuilder.append("%");
    }

    public static void sendHorizontalParameters(double power, double currentPos, double wantedPos) {
        messageBuilder.append("HORIZONTAL,");
        messageBuilder.append(df.format(power));
        messageBuilder.append(",");
        messageBuilder.append(df.format(currentPos));
        messageBuilder.append(",");
        messageBuilder.append(df.format(wantedPos));
        messageBuilder.append("%");
    }

    public static void sendVerticalParameters(double power, double currentPos, double wantedPos) {
        messageBuilder.append("VERTICAL,");
        messageBuilder.append(df.format(power));
        messageBuilder.append(",");
        messageBuilder.append(df.format(currentPos));
        messageBuilder.append(",");
        messageBuilder.append(df.format(wantedPos));
        messageBuilder.append("%");
    }

    public static void sendPos(double x, double y, double heading){
        messageBuilder.append("POS,");
        messageBuilder.append(df.format(x));
        messageBuilder.append(",");
        messageBuilder.append(df.format(y));
        messageBuilder.append(",");
        messageBuilder.append(df.format(heading));
        messageBuilder.append("%");
    }

    public static void sendVideoFrame(BufferedImage VidFrame){
        messageBuilder.append("FRAME,");
        messageBuilder.append(encodeToString(VidFrame,"png"));
        messageBuilder.append("%");
    }
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
}