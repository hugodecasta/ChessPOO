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
public abstract class Piece
{
    public Point pos;
    private boolean blanc;
    //-------------------------------------------------------
    public Piece(Point pos, boolean blanc)
    {
        this.pos = pos;
        this.blanc = blanc;
    }
    //-------------------------------------------------------
    public boolean isBlanc()
    {
        return blanc;
    }
    //-------------------------------------------------------
    public abstract ArrayList<Point> pointsPossibles();
    @Override
    public abstract String toString();
}
