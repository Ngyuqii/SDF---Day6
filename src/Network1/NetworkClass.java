package Network1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkClass {

    public static String read(Socket skt) throws IOException {

        // Get the input stream
        InputStream is = skt.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);
        
        String comms = dis.readUTF();
        System.out.printf("Data input > %s\n", comms);

        //is.close(); returns error

        return comms;    
    
    }

    public static void write(Socket skt, String comms) throws IOException {

        // Get the output stream
        OutputStream os = skt.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        System.out.printf("Data output > %s\n", comms);
        dos.writeUTF(comms);
        dos.flush();

        //os.close(); returns error

    }

}
