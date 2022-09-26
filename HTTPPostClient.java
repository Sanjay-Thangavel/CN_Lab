/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GET_POST;
import java.io.*;
import java.net.*;

public class HTTPPostClient {
    public static void main(String a[]) throws Exception {
        try {
            BufferedReader ifu = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = new Socket("localhost", 6789);
            DataOutputStream ots = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader ifs = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("\nPOST url : ");
            String sentence = ifu.readLine();
            ots.writeBytes(sentence + '\n');
            String ms = ifs.readLine();
            System.out.println(ms);
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
