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
public abstract class ModeEchecs
{
    JoueurEchecs noir,blanc;
    
    public void ModeEchecs()
    {
    }
    
    public abstract void initMode(Echecs jeu);
    @Override
    public abstract String toString();
    public abstract JoueurEchecs joueurSuivant();
}
