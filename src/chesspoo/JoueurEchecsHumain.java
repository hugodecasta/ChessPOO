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
    EchiquierGraphique echiquierG;
    
    public JoueurEchecsHumain(boolean isBlanc)
    {
        super(isBlanc);
    }
    
    public void setEchiquierGraphique(EchiquierGraphique echiquierG)
    {
        this.echiquierG = echiquierG;
    }
    
    @Override
    public CoupEchecs getCoup(Echiquier echiquier)
    {
        proposeNul = false;
        compteur.start();
        abandon = false;
        echiquierG.resetSelections();
        
        Piece sPiece = null;
        while(sPiece==null && !abandon)
        {
            sPiece = echiquierG.getPieceSelected();
            if((sPiece != null && sPiece.isBlanc() != isBlanc) || echiquierG.getCaseSelected()!=null)
            {
                sPiece = null;
                echiquierG.resetSelections();
            }
            if(abandon)
                return new CoupEchecs(this, abandon, proposeNul);
            //-----------------------
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(JoueurEchecsHumain.class.getName()).log(Level.SEVERE, null, ex);
            }
            //-----------------------
            if(compteur.getTime()<=0)
                abandonner();
        }
        
        Point sCase = null;
        while(sCase==null && !abandon)
        {
            sCase = echiquierG.getSelectedPoint();
            if(abandon)
                return new CoupEchecs(this, abandon, proposeNul);
            //-----------------------
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(JoueurEchecsHumain.class.getName()).log(Level.SEVERE, null, ex);
            }
            //-----------------------
            if(compteur.getTime()<=0)
                abandonner();
        }
        if(abandon)
            return new CoupEchecs(this, abandon, proposeNul);
        
        Piece p = echiquierG.getPieceSelected();
        Point pp = echiquierG.getSelectedPoint();
        echiquierG.resetSelections();
        compteur.pause();
        return new CoupEchecs(p, pp, this,false, proposeNul);
        /*boolean colorGood = false;
        while(!colorGood)
        {
            while(echiquierG.getPieceSelected()==null)
            {
                if(echiquierG.getCaseSelected()!=null)
                    echiquierG.resetSelections();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JoueurEchecsHumain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            colorGood = echiquierG.getPieceSelected().isBlanc() == this.isBlanc();
            if(!colorGood)
            {
                echiquierG.resetSelections();
            }
        }
        while(echiquierG.getSelectedPoint()==null)
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(JoueurEchecsHumain.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        Piece p = echiquierG.getPieceSelected();
        Point pp = echiquierG.getSelectedPoint();
        echiquierG.resetSelections();
        compteur.pause();
        return new CoupEchecs(p, pp, this);*/
    }

    @Override
    public Piece getPromotion()
    {
        compteur.pause();
        echiquierG.resetSelections();
        echiquierG.iNeedToPromot(this);
        while(echiquierG.getPieceSelected()==null)
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(JoueurEchecsHumain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Piece p = echiquierG.getPieceSelected();
        echiquierG.resetSelections();
        echiquierG.thancksForPromot();
        return p;
    }
    
}
