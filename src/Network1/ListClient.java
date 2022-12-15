package network1;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListClient {

    public static void main(String[] args) {

        //javac -sourcepath src -d classes src/network1/*.java
        //java -cp classes network1.ListClient 10 100 localhost 8080
        Integer numSize = Integer.parseInt(args[0]); //get the list size
        Integer numLimit = Integer.parseInt(args[1]); //get the number limit
        
        String host = args[2];
        Integer port = Integer.parseInt(args[3]);
        
        try {
            //Create server socket and accept connection from client
            Socket skt = new Socket (host, port);
            System.out.printf("Connected to %s : %d.\n", host, port);

            //Add 10 random numbers in a list
            List<Integer> numList = new LinkedList<>();
            numList.add(numSize);
            numList.add(numLimit);

            Random rand = new SecureRandom();
            for (Integer i = 0; i < numSize; i++) {
                Integer num = rand.nextInt(numLimit);
                numList.add(num);
            }
            String stringList = numList.toString(); //writeUTF can only write string
            
            //Client output to Server
            NetworkClass.write(skt, stringList);

            //Client input from Server
            String comms = NetworkClass.read(skt);

            //Find the average of the input numbers
            String inter = comms.replaceAll("\\p{Punct}", "");
            String[] values = inter.split(" ");
            
            Float sum = 0f;
            for (String n: values) {
                Float z = Float.parseFloat(n);
                sum += z;
            }
            Float avg = sum / values.length;
            System.out.printf("The average of the generated numbers is %.2f.\n", avg);

            skt.close();
        }
        catch(UnknownHostException e) {
            System.out.println("Unable to reach the host.");
        }
        catch (IOException e) {
            System.out.println("IO Error");
        }

    }
    
}