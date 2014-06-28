package ivan.villalba.crs;

import java.util.Random;

/**
 *
 * @author Iván
 */
public class TaskFactory
{
    private static String[] taskNames = {"cooking", "programming in JAVA", "reading", "learning AngularJS"};
    
    public static Task buildRandomTask()
    {
        Random randomGenerator = new Random();
        Integer taskNameIndex = randomGenerator.nextInt(taskNames.length);
        Integer priority = randomGenerator.nextInt(10) + 1;     // From 1 to 10
        
        Task task = new Task();
        task.setName(taskNames[taskNameIndex]);
        task.setPriority(priority);
        
        return task;
    }
}
