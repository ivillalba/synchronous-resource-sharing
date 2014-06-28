package ivan.villalba.crs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Iván
 */
public class SynchronizedResourceSharing
{
    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException
    {
        BlockingQueueManager bqManager = new BlockingQueueManager();
        List<Thread> producerThreads = new ArrayList();
        
        Thread manager = new Thread(bqManager);
        manager.start();
        
        for (int i = 0; i < 5; i++)
        {
            Thread t = new Thread(new Producer(bqManager.getTaskQueue(), "Producer[" + i + "]"));
            t.start();
            producerThreads.add(t);
        }
        
        for (Thread t : producerThreads)
        {
            t.join();
        }
        
        manager.interrupt();
    }
}
