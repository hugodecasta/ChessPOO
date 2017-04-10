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
 * @author i3mainz
 */
public class ModeAIrandom extends ModeEchecs
{
    JoueurEchecs jb, jn, act;
    public ModeAIrandom(JoueurEchecsHumain jb)
    {
        this.jb = jb;
        jn = new RandomAuPlayer(false);
    }
    @Override
    public void initMode(Echecs jeu)
    {
        jeu.setJoueurs(jb, jn);
        jeu.getEchiquier().initPlateau();
        jb.setCompteur(new CompteurEchecs(50*60*1000));
        jn.setCompteur(new CompteurEchecs(50*60*1000));
    }

    @Override
    public String toString()
    {
        return "Random AI...";
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
