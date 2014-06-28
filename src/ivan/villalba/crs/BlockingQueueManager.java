package ivan.villalba.crs;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iván Villalba
 */
public class BlockingQueueManager implements Runnable
{
    private PriorityBlockingQueue<Task> taskQueue = new PriorityBlockingQueue(1024);
    private Integer processedTasksCount = 0;
    
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Task currentTask = taskQueue.take();
                System.out.println("Task dequeued.");
                processTask(currentTask);
                System.out.println("Task done. " + taskQueue.size() + " tasks left.");
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(BlockingQueueManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void processTask(Task task)
    {
        synchronized(task)
        {
            System.out.print("I'm " + task.getName());
            for (int i=0; i < 3; i++)
            {
                System.out.print(".");
                try
                {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(BlockingQueueManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println();
            
            processedTasksCount++;
            task.setResult(processedTasksCount);
            task.notify();
        }
    }
    
    public PriorityBlockingQueue<Task> getTaskQueue()
    {
        return taskQueue;
    }
}
