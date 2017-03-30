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
public class PiecePion extends Piece
{
    boolean bouge;
    public PiecePion(Point pos, boolean blanc)
    {
        super(pos, blanc);
        bouge = false;
    }
    @Override
    public ArrayList<Point> pointsPossibles()
    {
        ArrayList<Point> ret = new ArrayList<>();
        int x = pos.x;
        int y = pos.y;
        int yDir = isBlanc()?1:-1;
        
        ret.add(new Point(x,y+yDir));
        ret.add(new Point(x+1,y+yDir));
        ret.add(new Point(x-1,y+yDir));
        if(!bouge)
            ret.add(new Point(x,y+2*yDir));
        
        return ret;
    }

    @Override
    public String toString()
    {
        return "♙♟";
    }    
    
}
