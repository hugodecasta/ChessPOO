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
    public void initMode(Echiquier echiquier, JoueurEchecs jb, JoueurEchecs jn)
    {
        this.jb = jb;
        this.jn = jn;
        echiquier.initPlateau();
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
    
}
