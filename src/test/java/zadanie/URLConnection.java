package zadanie;

import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class URLConnection {

    public static void main (String[] agrs) {

        int number = (int)(Math.random()*1001);
        String urlAdress = "http://numbersapi.com/" + number + "/trivia";
        HttpURLConnection connection = null;

        try{
            connection = (HttpURLConnection) new URL(urlAdress).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(260);
            connection.setReadTimeout(250);

            connection.connect();

            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                BufferedReader in= new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }

                System.out.println(sb.toString());
                Map<Character,Integer> result = new HashMap<Character, Integer>();

                String str = String.valueOf(sb);
                str = str.replaceAll ("[^A-Za-z0-9]", "");
                int count = 0;
                for(int i = 0; i < str.length(); i++)
                {
                    char ch = str.charAt(i);
                    if(result.get(ch) != null){
                        result.put(ch,result.get(ch)+1);
                    }else{
                        result.put(ch,1);
                    }
                }
                for(Map.Entry entry: result.entrySet()){

                    System.out.println(entry.getKey()+"-->"+entry.getValue());

                }


            } else{
                System.out.println("fail"+ connection.getResponseCode() + "," + connection.getResponseMessage());
            }

        } catch (Throwable cause ){
            cause.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
        }


    }

}
