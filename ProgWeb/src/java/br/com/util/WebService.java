package br.com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

public class WebService {

    private WebService() {
    }

    private static String openURL(String url) throws IOException {
        URL end = new URL(url);
        URLConnection conn = end.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine, content = "";
        while ((inputLine = br.readLine()) != null) {
            content += inputLine + "\n";
        }
        br.close();
        return content.substring(1, content.length() -1);
    }

    public static JSONObject lerJSON(String title, int offset){
        String url = "http://www.myapifilms.com/imdb?title="+title+"&format=JSON&aka=0&business=0&seasons=0&seasonYear=0&technical=0&filter=M&exactFilter=0&limit=10&offset="+offset+"&lang=pt-br&actors=F&biography=0&trailer=0&uniqueName=0&filmography=0&bornDied=0&starSign=0&actorActress=1&actorTrivia=0&movieTrivia=0&awards=0";
        try {
            return new JSONObject(openURL(url));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
