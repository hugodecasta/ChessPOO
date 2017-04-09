/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

/**
 *
 * @author i3mainz
 */
public class ModeSimple extends ModeEchecs
{
    JoueurEchecs jn,jb,actJ;
    public ModeSimple()
    {
        
    }

    @Override
    public void initMode(Echecs jeu)
    {
        this.jb = jeu.getJoueurBlanc();
        this.jn = jeu.getJoueurNoir();
        jb.compteur = new CompteurEchecs(59*1000);
        jn.compteur = new CompteurEchecs(59*1000);
        jeu.getEchiquier().initPlateau();
    }

    @Override
    public JoueurEchecs joueurSuivant()
    {
        if(actJ==null || actJ==jn)
            actJ = jb;
        else
            actJ = jn;
        return actJ;
    }

    @Override
    public String toString()
    {
        return "1 minute";
    }
    
}
