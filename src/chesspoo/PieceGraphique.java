/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import Chess.Piece;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author p1608557
 */
public class PieceGraphique
{
    Piece piece;
    int x,y;
    Image image;
    Rectangle rect;

    public PieceGraphique(Piece piece) 
    {
        this.piece = piece;
    }
    
    public Rectangle getRect()
    {
        return rect;
    }
    
    
}
