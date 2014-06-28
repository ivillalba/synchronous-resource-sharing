package ivan.villalba.crs;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class Producer implements Runnable
{
    private PriorityBlockingQueue<Task> taskQueue;
    
    public Producer(PriorityBlockingQueue queue)
    {
        this.taskQueue = queue;
    }

    @Override
    public void run()
    {
        for(int i = 0; i < 5; i++)
        {
            Task newTask = TaskFactory.buildRandomTask();
            
            synchronized(newTask)
            {
                try
                {
                    this.taskQueue.add(newTask);
                    newTask.wait();
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try
            {
                Thread.sleep(2000L);
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
