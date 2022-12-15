package network1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TListServer {

    public static void main(String[] args) {

        //javac -sourcepath src -d classes src/network1/*.java
        //java -cp classes network1.TListServer 8080
        Integer port = Integer.parseInt(args[0]);

        //Create a threadpool
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        try{
            //Create server socket and accept connection from client
            ServerSocket svr = new ServerSocket(port);
            System.out.printf("Listening on port %d.\n", port);

            while(true) {
                System.out.println("Waiting for connection.");
                Socket skt = svr.accept();
                
                //Create a HandleClient to handle the client socket
                THandleClient hClient = new THandleClient(skt);
                threadPool.submit(hClient); //submit runnable to threadpool
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}