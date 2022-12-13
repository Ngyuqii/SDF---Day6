package Thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RandThread {

    public static void main(String[] args) {
        
        List<Integer> numList = new LinkedList<>();
        
        //Requests for threads from threadpool
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        //Create 3 more threads on top of main thread
        //thr.run(); is a method call
        for (Integer i = 0; i < 3; i++) {
            RandThreadClass thr = new RandThreadClass ("thr-%d".formatted(i), 100, numList);
            threadPool.submit(thr); // Schedule a thread to the runnable
        }

        System.out.println("The program is running.");

        while (true) {
            System.out.printf(">> numList: %d , size: %d.\n", numList, numList.size());
            try {
                Thread.sleep(1 * 1000); // Sleep for 1 sec bfore printing out line 27
            }
            catch (InterruptedException ex)
            {

            }
        }
    
    }
    
}