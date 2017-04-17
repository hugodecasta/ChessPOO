/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1408098
 */
public class Echecs
{
    Echiquier echiquier;
    JoueurEchecs joueurB, joueurN, joueurG;
    JoueurEchecs joueur;
    JoueurEchecs estEnEchec;
    
    public Echecs()
    {
        echiquier = new Echiquier();
        joueur = null;
    }
    
    public void setJoueurs(JoueurEchecs jb, JoueurEchecs jn)
    {
        this.joueurB = jb;
        this.joueurN = jn;
    }
    
    public Echiquier getEchiquier()
    {
        return echiquier;
    }
    
    public JoueurEchecs getJoueurBlanc()
    {
        return joueurB;
    }
    
    public JoueurEchecs getJoueurNoir()
    {
        return joueurN;
    }
    
    public JoueurEchecs getJoueurActuel()
    {
        return joueur;
    }
    
    public JoueurEchecs partie(ModeEchecs mode)
    {
        mode.initMode(this);
        joueurG = null;
        boolean proposeNull = false;
        
        while(true)
        {
            joueur = mode.joueurSuivant();
            estEnEchec = echiquier.echecAuRoi(joueur,true)?joueur:null;
            boolean matPat = echiquier.matOuPat(joueur);
            
            if (matPat)
            {         
                if (estEnEchec != null)
                    return mode.joueurSuivant();
                else
                    return null;
            }
            if (echiquier.pasAssezPieces())
                return null;
            
            boolean pieceCoupPossible = false;
            
            while(!pieceCoupPossible)
            {
                CoupEchecs coup = joueur.getCoup(echiquier);
                if(coup.abandon)
                    return mode.joueurSuivant();
                else if(coup.proposeNul)
                {
                    joueur.compteur.pause();
                    if(!proposeNull)
                        proposeNull = true;
                    else
                        return null;
                    pieceCoupPossible = true;
                    continue;
                }
                else
                {
                    proposeNull = false;
                }
                pieceCoupPossible = echiquier.testeJoueCoup(coup);
            }
            estEnEchec = echiquier.echecAuRoi(joueur,true)?joueur:null;
        }
    }
    
    public JoueurEchecs estEnEchec()
    {
        return estEnEchec;
    }
}
