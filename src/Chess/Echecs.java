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
    
    public JoueurEchecs joueurSuivant()
    {
        if(joueur == null)
            return joueurB;
        else if(joueur == joueurB)
            return joueurN;
        else
            return joueurB;
    }
    
    public JoueurEchecs partie()
    {
        while(!partieTerminee())
        {
            joueur = joueurSuivant();
            System.out.println("Le joueur suivant a été selectionné");
            CoupEchecs coup = joueur.getCoup(echiquier);
            System.out.println("Essaie du coup Piece: "+coup.piece.toString().charAt(1));
            System.out.println("Essaie du coup Posit: "+coup.sortie.x+" - "+coup.sortie.y);
            ArrayList<Point> coupsPossibles = echiquier.coupsPossibles(coup.piece);
            if(coupsPossibles.contains(coup.sortie))
            {
                System.out.println("Coup validé");
                coup.piece.pos = coup.sortie;
            }
            
        }
        return joueur;
    }
}
