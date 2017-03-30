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
public class PieceCavalier extends Piece
{

    public PieceCavalier(Point pos, boolean blanc)
    {
        super(pos, blanc);
    }

    @Override
    public ArrayList<Point> pointsPossibles()
    {
        ArrayList<Point> ret = new ArrayList<>();
        int x = pos.x;
        int y = pos.y;
        
        for(int i=1;i<=2;++i)
        {
            int j = i==1?2:1;
            ret.add(new Point(x+i,y-j));
            ret.add(new Point(x-i,y+j));
            ret.add(new Point(x+i,y+j));
            ret.add(new Point(x-i,y-j));
        }
        
        return ret;
    }

    @Override
    public String toString()
    {
        return "♘♞";
    }    
}
