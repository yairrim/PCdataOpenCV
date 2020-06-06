package src.com.company;

import java.io.*;
import java.net.Socket;

public class client extends Thread{
    static Socket s;
    OutputStream os;
    static ObjectOutputStream dos;
    InputStream is;
    static DataInputStream dis;
    public client(String ip, int port){
        try {
            s = new Socket(ip, port);
            os = s.getOutputStream();
            dos = new ObjectOutputStream(os);

            is = s.getInputStream();
            dis = new DataInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run(){
        try {
            while (true){
                messageProcessing.processMessage(get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void send(String msg){
        try {
            dos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get() throws IOException {
        String h = dis.readUTF();
        return h;
    }

    public static void close(){
        try {
            s.close();
            dos.close();
            dis.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
