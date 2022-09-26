/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upd_trans;
import java.io.*;
import java.net.*;

public class UDPechoclient {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress add = InetAddress.getByName("localhost");
        byte buf[] = new byte[1024];
        BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Client is Running... Type 'STOP' to Quit");
        while (true) {
            String str = new String(dis.readLine());
            buf = str.getBytes();
            if (str.equals("STOP")) {
                System.out.println("Terminated...");
                client.send(new DatagramPacket(buf, str.length(), add, 4160));
                break;
            }
            client.send(new DatagramPacket(buf, str.length(), add, 4160));

        }

        client.close();
    }
}