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
    
    public boolean coupsPossible(CoupEchecs coup)
    {
        ArrayList<Point> coupsPossibles = coup.piece.pointsPossibles();
        for (Point pt : coupsPossibles)
        {
            if (pt.egale(coup.sortie))
            {
                //on vérifie si la case est occupée
                Piece pieceCible = pointOccupe(coup.sortie);
                boolean cibleNulle = (pieceCible == null); 
                boolean memeCouleur = (!cibleNulle && (pieceCible.isBlanc() == coup.piece.isBlanc()));
                if (memeCouleur)
                    return false; // on ne mange pas ses potes

                if (coup.piece instanceof PieceCavalier || 
                    coup.piece instanceof PieceRoi)
                {
                    if(!cibleNulle)
                    {
                        mangerPiece(pieceCible);
                    }
                    coup.piece.pos = coup.sortie;
                }
                else if (coup.piece instanceof PiecePion)
                {
                    if (coup.sortie.x == coup.piece.pos.x)
                    {
                        if (cibleNulle)
                        {
                            int dir = coup.piece.isBlanc() ? 1 : -1;
                            int yDepart = coup.piece.isBlanc() ? 1 : 6;  
                            if (coup.sortie.y == coup.piece.pos.y + dir || coup.piece.pos.y == yDepart)
                                coup.piece.pos = coup.sortie;
                            else
                                return false;
                        }
                        else
                            return false;                         
                    }
                    else
                    {
                        if (cibleNulle)
                            return false;
                        else
                        {
                            coup.piece.pos = coup.sortie;
                            mangerPiece(pieceCible);
                        }
                    }
                    if(coup.sortie.y == 0 || coup.sortie.y == 7)
                    {
                        Piece newPiece = coup.joueur.getPromotion();
                        newPiece.pos = coup.sortie;

                        coup.piece.aUnePromotion();
                        pieces.remove(coup.piece);
                        pieces.add(newPiece);
                    }
                }
                return true;
            }
        }
        return false;
    }
    private void mangerPiece(Piece p)
    {
        p.seFaitManger();
        pieces.remove(p);
    }
    public Piece pointOccupe (Point point)
    {
        for (Piece p : pieces)
        {
            if (p.pos.egale(point))
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
