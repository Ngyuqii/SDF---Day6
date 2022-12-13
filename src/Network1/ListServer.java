package Network1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListServer {

    public static void main(String[] args) {

        //javac -sourcepath src -d classes src/network1/*.java
        //java -cp classes Network1.ListServer 8080
        Integer port = Integer.parseInt(args[0]);

        try{
            //Create server socket and accept connection from client
            ServerSocket svr = new ServerSocket(port);
            System.out.printf("Listening on port %d.\n", port);

            while(true) {
                System.out.println("Waiting for connection.");
                Socket skt = svr.accept();
                System.out.printf("New connection on port %d.\n", port);

                //Server gets input from Client
                String comms = NetworkClass.read(skt);

                String inter = comms.replaceAll("\\p{Punct}", "");
                String[] values = inter.split(" ");
                Integer numSize = Integer.parseInt(values[0]);
                Integer numLimit = Integer.parseInt(values[1]);
                
                //Generate random integers into List<Integer>
                List<Integer> numList = new LinkedList<>();

                Random rand = new SecureRandom();
                for (Integer i = 0; i < numSize; i++) {
                    Integer num = rand.nextInt(numLimit);
                    numList.add(num);
                }
                String stringList = numList.toString(); //Change into List<String>

                //Server output to Client
                NetworkClass.write(skt, stringList);

                skt.close();
                svr.close(); //If no further connection
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}