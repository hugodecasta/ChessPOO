/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import Chess.CompteurEchecs;
import Chess.Echecs;
import Chess.JoueurEchecs;
import Chess.ModeEchecs;

/**
 *
 * @author p1608557
 */
public class Mode960 extends ModeEchecs
{
    JoueurEchecs jb, jn, act;
    public Mode960(JoueurEchecsHumain jb,JoueurEchecsHumain jn)
    {
        this.jb = jb;
        this.jn = jn;
    }
    @Override
    public void initMode(Echecs jeu)
    {
        act = null;
        jeu.setJoueurs(jb, jn);
        jeu.getEchiquier().initPlateau960();
        jb.setCompteur(new CompteurEchecs(50*60*1000));
        jn.setCompteur(new CompteurEchecs(50*60*1000));
    }

    @Override
    public String toString()
    {
        return "Chess 960";
    }

    @Override
    public JoueurEchecs joueurSuivant()
    {
        if(act==null || act==jn)
            act = jb;
        else
            act = jn;
        return act;
    }
    
}
