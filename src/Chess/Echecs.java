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
    JoueurEchecs joueurB, joueurN;
    JoueurEchecs joueur;
    
    public Echecs(JoueurEchecs joueurB, JoueurEchecs joueurN)
    {
        echiquier = new Echiquier();
        joueur = null;
        this.joueurB = joueurB;
        this.joueurN = joueurN;
    }
    
    public void initEchecs()
    {
        echiquier.initPlateau();
    }
    
    public boolean partieTerminee()
    {
        return false;
    }
    
    public JoueurEchecs getJoueur()
    {
        return joueur;
    }
    
    public JoueurEchecs joueurSuivant()
    {
        if(joueur == null)
            return joueurB;
        else if(joueur == joueurB)
            return joueurN;
        else
            return joueurB;
    }
    
    public Echiquier getEchiquier()
    {
        return echiquier;
    }
    
    public JoueurEchecs partie()
    {
        while(!partieTerminee())
        {
            joueur = joueurSuivant();
            
            boolean pieceCoupPossible = false;
            while(!pieceCoupPossible)
            {
                CoupEchecs coup = joueur.getCoup(echiquier);
                pieceCoupPossible = echiquier.testeCoup(coup);
            }            
        }
        return joueur;
    }
}
