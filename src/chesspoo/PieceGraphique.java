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
    Text backText,frontText;

    public PieceGraphique(Piece piece,int x,int y,int size) 
    {
        super(x,y,size,Color.rgb(0,0,0,0),Color.rgb(241, 196, 15,1.0),Color.rgb(241, 196, 15,0.5));
        
        backText = new Text(piece.toString().charAt(1)+"");
        backText.setFont(new Font(size-10));
        frontText = new Text(piece.toString().charAt(0)+"");
        frontText.setFont(new Font(size-10));
        
        changePiece(piece);
        
        addElement(backText);
        addElement(frontText);
    }
    
    public void changePiece(Piece piece)
    {
        Color col = piece.isBlanc()?Color.WHITE:Color.BLACK;
        this.piece = piece;
        oldPos = piece.pos;
        backText.setFill(col);
        frontText.setFill(Color.BLACK);
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
    
    public void vanish()
    {
        pane.setPickOnBounds(false);
        pane.setMouseTransparent(true);
        fade(false);
    }
    
    public void echecAuRoi()
    {
        System.out.println("Echec Au ROI");
        unselectedColor = Color.RED;
        selectedColor = Color.rgb(255, 50, 200,1.0);
        hoverColor = Color.rgb(255, 100, 100,0.5);
        new AnimationTimer()
        {
            int duration = 20;
            float opacityStart = 0;
            float opacityGoal = 1;
            int time = 0;
            
            @Override
            public void handle(long now)
            {
                if(time == duration)
                {
                    backRect.setFill(Color.rgb(255,0,0,1));
                    this.stop();
                }
                else
                {
                    float opac = EasingFunctions.linear(time, opacityStart, opacityGoal, duration);
                    backRect.setFill(Color.rgb(255,0,0,opac));
                    time++;
                }
            }
        }.start();
    }
    public void resetEchecAuRoi()
    {
        unselectedColor = Color.rgb(0,0,0,0);
        selectedColor = Color.rgb(241, 196, 15,1.0);
        hoverColor = Color.rgb(241, 196, 15,0.5);
        new AnimationTimer()
        {
            int duration = 20;
            float opacityStart = 0;
            float opacityGoal = 1;
            int time = 0;
            
            @Override
            public void handle(long now)
            {
                if(time == duration || unselectedColor.getOpacity()==0)
                {
                    backRect.setFill(Color.rgb(0,0,0,0));
                    this.stop();
                }
                else
                {
                    float opac = EasingFunctions.linear(time, opacityStart, opacityGoal, duration);
                    backRect.setFill(Color.rgb(255,0,0,1-opac));
                    time++;
                }
            }
        }.start();
    }
}
