/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import Chess.PieceCavalier;
import Chess.PieceDame;
import Chess.PieceFou;
import Chess.PieceTour;
import Chess.Point;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author i3mainz
 */
public class PromotPanel extends ElementGraphique
{
    PieceGraphique dame,cavalier,tour,fou;
    public PromotPanel(EchiquierGraphique echG)
    {
        super(0, 0, echG.sizew, echG.sizeh, Color.rgb(0,0,0,1), Color.rgb(0,0,0,1), Color.rgb(0,0,0,1));
        dame = new PieceGraphique(new PieceDame(new Point(0,0), true), 
                echG.getXFromI(3), echG.getYFromJ(3), echG.caseSize);
        cavalier = new PieceGraphique(new PieceCavalier(new Point(0,0), true), 
                echG.getXFromI(3), echG.getYFromJ(4), echG.caseSize);
        tour = new PieceGraphique(new PieceTour(new Point(0,0), true), 
                echG.getXFromI(4), echG.getYFromJ(3), echG.caseSize);
        fou = new PieceGraphique(new PieceFou(new Point(0,0), true), 
                echG.getXFromI(4), echG.getYFromJ(4), echG.caseSize);
        int more = echG.caseSize/2;
        
        
        dame.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {public void handle(MouseEvent e)
        {echG.pieceClicked(dame);}});
        cavalier.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {public void handle(MouseEvent e)
        {echG.pieceClicked(cavalier);}});
        tour.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {public void handle(MouseEvent e)
        {echG.pieceClicked(tour);}});
        fou.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {public void handle(MouseEvent e)
        {echG.pieceClicked(fou);}});
        
        addFreeElement(dame.getGraphics());
        addFreeElement(cavalier.getGraphics());
        addFreeElement(tour.getGraphics());
        addFreeElement(fou.getGraphics());
        setOpacity(0);
        pane.setMouseTransparent(true);
    }
    
    public void setupPiece(boolean isBlanc)
    {
        dame.changePiece(new PieceDame(new Point(0,0), isBlanc));
        cavalier.changePiece(new PieceCavalier(new Point(0,0), isBlanc));
        tour.changePiece(new PieceTour(new Point(0,0), isBlanc));
        fou.changePiece(new PieceFou(new Point(0,0), isBlanc));
        int col = isBlanc?0:255;
        selectedColor = Color.rgb(col,col,col,0.7);
        hoverColor = Color.rgb(col,col,col,0.7);
        unselectedColor = Color.rgb(col,col,col,0.7);
    }
}
