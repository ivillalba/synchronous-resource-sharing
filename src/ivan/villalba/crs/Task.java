package ivan.villalba.crs;

/**
 *
 * @author Juan
 */
public class Task implements Comparable<Task>
{
    private Integer priority;
    private String name;
    private Integer result;

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getResult()
    {
        return result;
    }

    public void setResult(Integer result)
    {
        this.result = result;
    }

    @Override
    public int compareTo(Task anotherTask)
    {
        return this.priority.compareTo(anotherTask.getPriority());
    }
}
