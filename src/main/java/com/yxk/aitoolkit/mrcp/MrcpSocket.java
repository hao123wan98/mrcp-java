package com.yxk.aitoolkit.mrcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MrcpSocket extends Thread {
    private String server_host = "169.254.239.13";
    private int server_port = 8060;
    private Socket socket = null;

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            while(true) {
                byte[] bytes = new byte[1024];
                StringBuilder sb = new StringBuilder();
                int len = -1;
                while ((len = inputStream.read(bytes)) != -1) {
                    //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                    sb.append(new String(bytes, 0, len, "UTF-8"));
                }
                OnDataReceived(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean connect() {
        try {
            socket = new Socket(server_host, server_port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.start();
        return true;
    }

    boolean close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    boolean sendData(String data) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(data.getBytes("UTF-8"));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    void OnDataReceived(String data) {

    }
}
