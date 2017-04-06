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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author p1408098
 */
public class JoueurEchecsHumain extends JoueurEchecs
{
    ChessPOO affichage;
    
    public JoueurEchecsHumain(ChessPOO affichage,boolean isBlanc)
    {
        super(isBlanc);
        this.affichage = affichage;
    }
    
    @Override
    public CoupEchecs getCoup(Echiquier echiquier)
    { 
        affichage.resetSelections();
        boolean colorGood = false;
        while(!colorGood)
        {
            while(affichage.getPieceSelected()==null)
            {
                if(affichage.getCaseSelected()!=null)
                    affichage.resetSelections();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JoueurEchecsHumain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            colorGood = affichage.getPieceSelected().isBlanc() == this.isBlanc();
            if(!colorGood)
            {
                affichage.resetSelections();
            }
        }
        while(affichage.getSelectedPoint()==null)
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(JoueurEchecsHumain.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        Piece p = affichage.getPieceSelected();
        Point pp = affichage.getSelectedPoint();
        affichage.resetSelections();
        return new CoupEchecs(p, pp, this);
    }

    @Override
    public Piece getPromotion()
    {
        affichage.resetSelections();
        affichage.iNeedToPromot(this);
        while(affichage.getPieceSelected()==null)
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(JoueurEchecsHumain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Piece p = affichage.getPieceSelected();
        affichage.resetSelections();
        affichage.thancksForPromot();
        return p;
    }
    
}
