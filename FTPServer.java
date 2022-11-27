/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.net.*;
import java.io.*;
public class FTPServer {
    Socket ss;
    ServerSocket serverSoc;
    DataInputStream din;
    DataOutputStream dout;
    String fileName;
    File fileObject;
    public  FTPServer() throws Exception{
        serverSoc = new ServerSocket(6060);
        System.out.println("[INFO] : Server started at "+serverSoc.getInetAddress());
        ss = serverSoc.accept();
        System.out.println("[INFO] : Socket connected to server from "+ss.getInetAddress());        
        din = new DataInputStream(ss.getInputStream());
        dout = new DataOutputStream(ss.getOutputStream());
    }
    public void startTransfer() throws Exception{
        int readByte;
        try (FileInputStream fStream = new FileInputStream(fileName)) {
            System.out.println("[INFO] : Requested file tranfer to "+ss.getInetAddress()+" starting");
            while((readByte = fStream.read()) != -1) dout.writeUTF(String.valueOf(readByte));
            dout.writeUTF("-1");
            System.out.println("[INFO] : Requested file tranfer to "+ss.getInetAddress()+" completed successfully");
        } 
    }
    public void sendFile() throws Exception{
        fileName = din.readUTF();
        System.out.println("[INFO] : Requested File name: "+fileName);
        fileObject = new File(fileName);
        if(fileObject.exists()){
            dout.writeUTF("1");
            this.startTransfer();
        }
        else{
            System.out.println("[ERROR] : Requested File not found.");
            dout.writeUTF("0");
        }
    }
    public static void main(String[] args) throws Exception{
        FTPServer instance = new FTPServer();
        instance.sendFile();
        instance.ss.close();
        instance.serverSoc.close();
        System.out.println("[INFO] : Socket disconnected and Server closed");
    }
}
