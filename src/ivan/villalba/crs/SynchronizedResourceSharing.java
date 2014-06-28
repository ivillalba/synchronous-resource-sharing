package ivan.villalba.crs;

/**
 *
 * @author Iván
 */
public class SynchronizedResourceSharing
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        BlockingQueueManager bqManager = new BlockingQueueManager();
        
        new Thread(bqManager).start();
        
        for (int i = 0; i < 5; i++)
        {
            new Thread(new Producer(bqManager.getTaskQueue())).start();
        }
    }
}
