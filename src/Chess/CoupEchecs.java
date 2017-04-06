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
public class CoupEchecs
{
    public Piece piece;
    public Point sortie;
    public JoueurEchecs joueur;
    public CoupEchecs(Piece piece, Point sortie, JoueurEchecs joueur)
    {
        this.piece = piece;
        this.sortie = sortie;
        this.joueur = joueur;
    }
}
