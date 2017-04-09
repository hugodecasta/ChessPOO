/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

/**
 *
 * @author i3mainz
 */
public class CompteurEchecs
{
    boolean start;
    long startingTime;
    long countingFromTime;
    long offset,lastCheck;
    
    public CompteurEchecs(long startMilli)
    {
        countingFromTime = startMilli;
    }
    
    public void pause()
    {
        if(start)
        {
            start = false;
        }
    }
    
    public void start()
    {
        if(!start) 
        {
            start = true;
            lastCheck = System.currentTimeMillis();
        }
    }
    
    public void setTime(long startMilli)
    {
        countingFromTime = 0;
    }
    
    public long getTime()
    {
        if(!start) 
        {
            return countingFromTime;
        }
        else
        {
            long now = System.currentTimeMillis();
            long spentTime = now-lastCheck;
            lastCheck = now;
            countingFromTime = countingFromTime-spentTime;
            return countingFromTime;
        }
    }
}
