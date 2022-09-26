/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GET_POST;
import java.io.*;
import java.net.*;

public class HTTPGetServer {
    private static final String USER_AGENT = "Google Chrome";

    static String sendGET(String GET_URL) throws Exception {
        URL URLObj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) URLObj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return (response.toString());
        } else {
            System.out.println("GET request not worked");
            return (null);
        }
    }

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(6789);
        while (true) {
            Socket conSoc = ss.accept();
            BufferedReader ifc = new BufferedReader(new InputStreamReader(conSoc.getInputStream()));
            DataOutputStream otc = new DataOutputStream(conSoc.getOutputStream());
            String cs = ifc.readLine() + '\n';
            System.out.println("RECEIVED : " + cs);
            String GET_URL = cs;
            otc.writeBytes(sendGET(GET_URL) + '\n');
            System.out.println("GET DONE");
            break;
        }
        ss.close();
    }
}
