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
public class Echiquier
{
    ArrayList<Piece> pieces;
    
    public Echiquier()
    {
        pieces = new ArrayList<>();
    }
    
    public void initPlateau()
    {
        pieces = new ArrayList<>();
        placePieceCouleur(true);
        placePieceCouleur(false);
    }
    
    public ArrayList<Piece> getPieces()
    {
        return pieces;
    }
    
    private void placePieceCouleur(boolean estBlanc)
    {
        int y = estBlanc?0:7;
        int yPion = estBlanc?1:6;
        
        pieces.add(new PieceTour(new Point(0,y),estBlanc));
        pieces.add(new PieceCavalier(new Point(1,y),estBlanc));
        pieces.add(new PieceFou(new Point(2,y),estBlanc));
        
        pieces.add(new PieceDame(new Point(3,y),estBlanc));
        pieces.add(new PieceRoi(new Point(4,y),estBlanc));
        
        pieces.add(new PieceFou(new Point(5,y),estBlanc));
        pieces.add(new PieceCavalier(new Point(6,y),estBlanc));
        pieces.add(new PieceTour(new Point(7,y),estBlanc));
        
        for(int i=0;i<8;++i)
        {
            pieces.add(new PiecePion(new Point(i,yPion),estBlanc));
        }
    }
    
    public Piece coupsPossible(CoupEchecs coup)
    {
        ArrayList<Point> coupsPossibles = coup.piece.pointsPossibles();
        System.out.println(coup.sortie);
        System.out.println("Salut");

        for (Point pt : coupsPossibles)
        {
            System.out.println(pt);
            if (pt.x == coup.sortie.x && pt.y == coup.sortie.y)
            {
                //on vérifie si la case est occupée
                Piece pieceCible = pointOccupe(coup.sortie);
                System.out.println("Coucou");
                boolean cibleNulle = (pieceCible == null); 
                boolean memeCouleur = (!cibleNulle && (pieceCible.isBlanc() == coup.piece.isBlanc()));
                if (memeCouleur)
                    return null; // on ne mange pas ses potes

                if (coup.piece instanceof PieceCavalier)
                {
                    if (cibleNulle)
                        return coup.piece;
                    else
                        return pieceCible;
                }
                if (coup.piece instanceof PieceRoi)
                {
                    if (cibleNulle)
                        return coup.piece;
                    else
                        return pieceCible;
                } 
            }
        }
        return null;
    }
    
    public Piece pointOccupe (Point point)
    {
        for (Piece p : pieces)
        {
            if (p.pos == point)
                return p;
        }
        return null;
    }
    
    @Override
    public String toString()
    {
        String ret = "";
        for(Piece p : pieces)
        {
            ret += p.toString()+"\n";
        }
        
        return ret;
    }
}
