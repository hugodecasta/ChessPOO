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
public class PieceRoi extends Piece
{
    public PieceRoi(Point point, boolean blanc) 
    {
        super(point, blanc);
    }

    @Override
    public ArrayList<Point> pointsPossibles()
    {
        ArrayList<Point> ret = new ArrayList<>();
        int x = pos.x;
        int y = pos.y;
        
        for(int i=-1;i<=1;++i)
        {
            for(int j=-1;j<=1;++j)
            {
                if(i!=0 || j!=0)
                {
                    ret.add(new Point(x+i,y+j));
                }
            }
            if (i != 0)
                ret.add(new Point(x+2*i, y)); // pour le roque
        }
        
        return ret;
    }
    
    @Override
    public String toString()
    {
        return "♔♚";
    }   
    
}
