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
        for (Point pt : coupsPossibles)
        {
            if (pt.egale(coup.sortie))
            {
                //on vérifie si la case est occupée
                Piece pieceCible = pointOccupe(coup.sortie);
                boolean cibleNulle = (pieceCible == null); 
                boolean memeCouleur = (!cibleNulle && (pieceCible.isBlanc() == coup.piece.isBlanc()));
                if (memeCouleur)
                    return null; // on ne mange pas ses potes

                if (coup.piece instanceof PieceCavalier || 
                    coup.piece instanceof PieceRoi)
                {
                    if(!cibleNulle)
                    {
                        //mangerPiece(pieceCible);
                        return pieceCible;
                    }
                    //coup.piece.pos = coup.sortie;
                    else
                        return coup.piece;
                } // Fin Cavalier et Roi
                else if (coup.piece instanceof PiecePion)
                {
                    if (coup.sortie.x == coup.piece.pos.x)
                    {
                        if (cibleNulle)
                        {
                            int dir = coup.piece.isBlanc() ? 1 : -1;
                            int yDepart = coup.piece.isBlanc() ? 1 : 6;  
                            if (coup.sortie.y == coup.piece.pos.y + dir || coup.piece.pos.y == yDepart)
                                //coup.piece.pos = coup.sortie;
                                return coup.piece;
                            else
                                return null;
                        }
                        else
                            return null;                         
                    }
                    else
                    {
                        if (cibleNulle)
                            return null;
                        else
                        {
                            //coup.piece.pos = coup.sortie;
                            //mangerPiece(pieceCible);
                            return pieceCible;
                        }
                    }
                    /*if(coup.sortie.y == 0 || coup.sortie.y == 7)
                    {
                        Piece newPiece = coup.joueur.getPromotion();
                        newPiece.pos = coup.sortie;

                        coup.piece.aUnePromotion();
                        pieces.remove(coup.piece);
                        pieces.add(newPiece);
                    }*/
                } // Fin Pion
                else
                {
                    // déplacement sur plusieurs cases pour le Fou, la Tour et la Dame
                    // on calcule d'abord la direction à évaluer
                    int dirx, diry;
                    if (coup.piece.pos.x < coup.sortie.x)
                        dirx = 1;
                    else if (coup.piece.pos.x == coup.sortie.x)
                        dirx = 0;
                    else
                        dirx = -1;
                    
                    if (coup.piece.pos.y < coup.sortie.y)
                        diry = 1;
                    else if (coup.piece.pos.y == coup.sortie.y)
                        diry = 0;
                    else
                        diry = -1;
                    
                    // Point permettant de parcourir les cases jusqu'à destination
                    Point temp = new Point(coup.piece.pos.x+dirx, coup.piece.pos.y+diry);
                    int i = 1;
                    
                    while (i < 8)
                    {
                        if (temp.egale(coup.sortie))
                        {
                            if(!cibleNulle)
                            {
                                //mangerPiece(pieceCible);
                                return pieceCible;
                            }
                            else
                                return coup.piece;
                            //coup.piece.pos = coup.sortie;
                            //break;
                        }
                        else if (pointOccupe(temp) != null)
                            return null;
                        else
                        {
                            temp.x += dirx;
                            temp.y += diry;
                            i++;
                        }
                    }
                    
                }
                
                //return true;
            }
        }
        return null;
    }
    public boolean testeJoueCoup(CoupEchecs coup)
    {
        Piece p = coupsPossible(coup);
        if (p == null)
            return false;
        
        // on teste si il y a échecs après le coup
        Point posCible = (Point) p.pos.clone();
        Point posPiece = (Point) coup.piece.pos.clone();
        
        if (p != coup.piece)
            mangerPiece(p);
        coup.piece.pos = coup.sortie;
        
        if (echecAuRoi(coup.joueur))
        {// on ne peut pas jouer le coup donc on remet l'échiquier comme avant
            if (p.isMange())
            {
                ressusciterPiece(p);
            }
            coup.piece.pos = posPiece;
            return false;
        }
        
        return true;
    }
    
    private void mangerPiece(Piece p)
    {
        p.seFaitManger();
        pieces.remove(p);
    }
    private void ressusciterPiece(Piece p)
    {
        p.resurrection();
        pieces.add(p);
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
    public boolean echecAuRoi(JoueurEchecs joueur)
    {
        CoupEchecs coupTemp = new CoupEchecs(null, null, joueur);
        boolean couleurRoi = joueur.isBlanc();
        for (Piece p : pieces)
        { // on trouve la position du roi
            if (p instanceof PieceRoi && p.isBlanc() == couleurRoi)
            {
                coupTemp.sortie = p.pos;
                break;
            }
        }
        
        for (Piece p2 : pieces)
        {
            if (p2.isBlanc() != couleurRoi)
            {
                coupTemp.piece = p2;
                if (coupsPossible(coupTemp) != null)
                {
                    return true;
                }
            }
        }
        return false;
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
