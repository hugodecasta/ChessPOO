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
    private boolean mange;
    //-------------------------------------------------------
    public Piece(Point pos, boolean blanc)
    {
        this.pos = pos;
        this.blanc = blanc;
        this.mange = false;
    }
    //-------------------------------------------------------
    public boolean isBlanc()
    {
        return blanc;
    }
    //-------------------------------------------------------
    public boolean isMange()
    {
        return mange;
    }
    public void seFaitManger()
    {
        mange = true;
    }
    public void resurrection()
    {
        mange = false;
    }
    public void aUnePromotion()
    {
        mange = true;
    }
    //-------------------------------------------------------
    public abstract ArrayList<Point> pointsPossibles();
    @Override
    public abstract String toString();
}
