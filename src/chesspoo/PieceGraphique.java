/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import Chess.Piece;
import Chess.Point;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author p1608557
 */
public class PieceGraphique extends ElementGraphique
{
    Piece piece;
    Point oldPos;
    boolean oldMange;

    public PieceGraphique(Piece piece,int x,int y,int size) 
    {
        
        super(x,y,size,Color.rgb(0,0,0,0),Color.rgb(241, 196, 15,1.0),Color.rgb(241, 196, 15,0.5));
        this.piece = piece;
        oldPos = piece.pos;
        
        Color col = piece.isBlanc()?Color.WHITE:Color.BLACK;
        
        Text backText = new Text(piece.toString().charAt(1)+"");
        backText.setFont(new Font(50));
        backText.setFill(col);
        Text frontText = new Text(piece.toString().charAt(0)+"");
        frontText.setFont(new Font(50));
        frontText.setFill(Color.BLACK);
        
        addElement(backText);
        addElement(frontText);
    }
    
    public Point updatePiece()
    {
        Point pos = null;
        if(piece.pos != oldPos || oldMange != piece.isMange())
            pos = piece.pos;
        oldMange = piece.isMange();
        oldPos = piece.pos;
        return pos;
    }
}
