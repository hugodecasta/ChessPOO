/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

/**
 *
 * @author p1408098
 */
public abstract class JoueurEchecs
{
    protected boolean isBlanc;
            
    public JoueurEchecs(boolean isBlanc)
    {
        this.isBlanc = isBlanc;
    }
    
    public abstract CoupEchecs getCoup(Echiquier echiquier);
    public abstract Piece getPromotion();
    
    public boolean isBlanc()
    {
        return isBlanc;
    }
}
