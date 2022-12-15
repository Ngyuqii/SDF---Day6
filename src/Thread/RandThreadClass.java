package Thread;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

//Implements runnable interface to create main thread
public class RandThreadClass implements Runnable {
    
    private String name;
    private Integer range;
    private List<Integer> numList;
    
    //Constructor
    public RandThreadClass (String name, Integer range , List<Integer> numList) { 
        this.name = name;
        this.range = range;
        this.numList = numList;
    }
    
    //This is the body of the thread
    @Override
    public void run() {

        Random rand = new SecureRandom();

        for (Integer i = 0; i < 10; i++) {
            Integer num = rand.nextInt(range);
            numList.add(num);
            System.out.printf("%d. %s > %d\n", i+1, name, num);
                
            //Extra - Sleep for 2sec before continuing to run the for loop
            try {
                Thread.sleep(2 * 1000);
            }
            catch (InterruptedException e) {
                System.out.println("Error executing sleep.\n");
            }
        }

    }

}
    
