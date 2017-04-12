/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import Chess.CoupEchecs;
import Chess.Echiquier;
import Chess.JoueurEchecs;
import Chess.Piece;
import Chess.PieceDame;
import Chess.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author i3mainz
 */
public class RandomAuPlayer extends JoueurEchecs
{
    int milliSimu;
    public RandomAuPlayer(boolean isBlanc, int sleepTime)
    {
        super(isBlanc);
        milliSimu = sleepTime;
    }

    @Override
    public CoupEchecs getCoup(Echiquier echiquier)
    {
        compteur.start();
        try
        {
            Thread.sleep(milliSimu);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(RandomAuPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Piece> pieces = echiquier.getPieces();
        ArrayList<Piece> goods = new ArrayList<>();
        for(Piece p:pieces)
            if(p.isBlanc() == isBlanc)
                goods.add(p);
        ArrayList<Point> points = new ArrayList<>(0);
        Piece p = null;
        while(points.size()==0)
        {
            int randPiece = (int)(Math.random()*goods.size());
            p = goods.get(randPiece);
            points = echiquier.CoupsValidesPiece(p,this);//p.pointsPossibles();
        }
        int randPoint = (int)(Math.random()*points.size());
        Point pp = points.get(randPoint);
        CoupEchecs coup = new CoupEchecs(p, pp, this);
        compteur.pause();
        return coup;
    }

    @Override
    public Piece getPromotion()
    {
        return new PieceDame(new Point(0,0),isBlanc);
    }
    
}
