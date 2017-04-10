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
public class ModeHumain extends ModeEchecs
{
    int minutes;
    JoueurEchecsHumain joueurBlanc, joueurNoir, act;
    public ModeHumain(JoueurEchecsHumain jb, JoueurEchecsHumain jn, int minutes)
    {
        this.minutes = minutes;
        joueurBlanc = jb;
        joueurNoir = jn;
    }
    @Override
    public void initMode(Echecs jeu)
    {
        jeu.setJoueurs(joueurBlanc, joueurNoir);
        jeu.getEchiquier().initPlateau();
        joueurBlanc.setCompteur(new CompteurEchecs(minutes*60*1000));
        joueurNoir.setCompteur(new CompteurEchecs(minutes*60*1000));
    }

    @Override
    public String toString() {
        return "Humain "+minutes+" min";
    }

    @Override
    public JoueurEchecs joueurSuivant()
    {
        if(act==null || act==joueurNoir)
            act = joueurBlanc;
        else
            act = joueurNoir;
        return act;
    }
    
}
