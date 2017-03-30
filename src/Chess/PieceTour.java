/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import java.util.ArrayList;

/**
 *
 * @author p1408098
 */
public class PieceTour extends Piece
{

    public PieceTour(Point pos, boolean blanc)
    {
        super(pos, blanc);
    }

    @Override
    public ArrayList<Point> pointsPossibles()
    {
        ArrayList<Point> ret = new ArrayList<>();
        int x = pos.x;
        int y = pos.y;
        
        for(int i=1;i<=7;++i)
        {
            ret.add(new Point(x+i,y));
            ret.add(new Point(x-i,y));
            ret.add(new Point(x,y+i));
            ret.add(new Point(x,y-i));
        }
        
        return ret;
    }

    @Override
    public String toString()
    {
        return "♖♜";
    }  
    
}
