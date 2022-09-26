/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;

public class UDPechoserver {
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(4160);
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        System.out.println("Server is Running...");
        while (true) {
            server.receive(packet);
            String str = new String(packet.getData(), 0, packet.getLength());
            if (str.equals("STOP")) {
                System.out.println("Terminated...");
                break;
            }
            System.out.println("Client: " + str);
        }
        server.close();
    }
}