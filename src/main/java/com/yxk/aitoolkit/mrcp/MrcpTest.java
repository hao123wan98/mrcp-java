package com.yxk.aitoolkit.mrcp;

public class MrcpTest {
    public static void main(String args[]) {
        MrcpTcpClient client = new MrcpTcpClient();
        client.connect();
        while (true) {
            client.sendData("1234");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
