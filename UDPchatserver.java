/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upd_trans;
import java.io.*;
import java.net.*;

public class UDPchatserver {
    public static void main(String[] args) throws IOException {
        int c_port = 4150, s_port = 4160;
        DatagramSocket server = new DatagramSocket(s_port);
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        InetAddress ia = InetAddress.getLocalHost();
        System.out.println("Server is Running...");
        while (true) {
            server.receive(packet);
            String str = new String(packet.getData(), 0, packet.getLength());
            if (str.equals("STOP")) {
                System.out.println("Terminated...");
                break;
            }
            System.out.println("Client: " + str);
            String s = new String(br.readLine());
            buf = s.getBytes();
            server.send(new DatagramPacket(buf, s.length(), ia, c_port));
        }
        server.close();
    }
}