/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upd_trans;
import java.io.*;
import java.net.*;

public class UDPchatclient {

    public static void main(String[] args) throws IOException {
        int c_port = 4150, s_port = 4160;
        DatagramSocket client = new DatagramSocket(c_port);
        InetAddress add = InetAddress.getByName("localhost");
        byte buf[] = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Client is Running... Type 'STOP' to Quit");
        while (true) {
            String str = new String(dis.readLine());
            buf = str.getBytes();
            if (str.equals("STOP")) {
                System.out.println("Terminated...");
                client.send(new DatagramPacket(buf, str.length(), add, s_port));
                break;
            }
            client.send(new DatagramPacket(buf, str.length(), add, s_port));
            client.receive(dp);
            String str2 = new String(dp.getData(), 0, dp.getLength());
            System.out.println("Server: " + str2);

        }

        client.close();
    }
}