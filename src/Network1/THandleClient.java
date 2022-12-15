package Network1;

import java.io.IOException;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class THandleClient implements Runnable {

    private Socket skt;

    //Constructor
    public THandleClient(Socket skt) {
        this.skt = skt;
    }

    //Main body
    @Override
    public void run() {

        System.out.printf("New connection on port %d.\n", skt.getPort());

        try {
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
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }

}