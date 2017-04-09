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
public class Echecs
{
    Echiquier echiquier;
    JoueurEchecs joueurB, joueurN, joueurG;
    JoueurEchecs joueur;
    JoueurEchecs estEnEchec;
    
    public Echecs(JoueurEchecs joueurB, JoueurEchecs joueurN)
    {
        echiquier = new Echiquier();
        joueur = null;
        this.joueurB = joueurB;
        this.joueurN = joueurN;
    }
    
    public Echiquier getEchiquier()
    {
        return echiquier;
    }
    
    public JoueurEchecs partie(ModeEchecs mode)
    {
        mode.initMode(echiquier,joueurB,joueurN);
        joueurG = null;
        
        while(true)
        {
            joueur = mode.joueurSuivant();
            estEnEchec = echiquier.echecAuRoi(joueur,true)?joueur:null;
            boolean matPat = echiquier.matOuPat(joueur);
            
            if (matPat)
            {                
                return joueur;
            }
            
            boolean pieceCoupPossible = false;
            while(!pieceCoupPossible)
            {
                CoupEchecs coup = joueur.getCoup(echiquier);
                if(coup == null)
                    return mode.joueurSuivant();
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
