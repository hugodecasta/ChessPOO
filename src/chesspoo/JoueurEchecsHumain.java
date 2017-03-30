/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import Chess.CoupEchecs;
import Chess.Echiquier;
import Chess.JoueurEchecs;


/**
 *
 * @author p1408098
 */
public class JoueurEchecsHumain extends JoueurEchecs
{
    ChessPOO affichage;
    
    public JoueurEchecsHumain(ChessPOO affichage)
    {
        this.affichage = affichage;
    }
    
    @Override
    public CoupEchecs getCoup(Echiquier echiquier)
    {
        while(affichage.pieceSelected()==null);
        while(affichage.caseSelected()==null);
        
        return new CoupEchecs(affichage.pieceSelected(), affichage.caseSelected);
    }
    
}
