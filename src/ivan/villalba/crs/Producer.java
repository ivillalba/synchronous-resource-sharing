package ivan.villalba.crs;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iván
 */
public class Producer implements Runnable
{
    private PriorityBlockingQueue<Task> taskQueue;
    private String name;
    
    public Producer(PriorityBlockingQueue queue, String name)
    {
        this.taskQueue = queue;
        this.name = name;
    }

    @Override
    public void run()
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
            
        System.out.println(name + ": My task has been completed with result " + newTask.getResult());
    }
}
