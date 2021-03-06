/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

/**
 *
 * @author p1408098
 */
public class Point implements Cloneable
{
    public int x, y;
    
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString()
    {
        String s = "" + x + " " + y;
        return s;
    }
    
    public boolean egale(Point p)
    {
        return x==p.x && y==p.y;
    }
    
    @Override
    public Object clone()
    {
        Point p = null;
        try
        {
            p = (Point) super.clone();
        }
        catch(CloneNotSupportedException cnse)
        {
            cnse.printStackTrace(System.err);
        }
        
        return p;
    }
}
