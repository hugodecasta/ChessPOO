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

/**
 *
 * @author i3mainz
 */
public class PromotPanel extends ElementGraphique
{
    ChessPOO affichage;
    PieceGraphique dame,cavalier,tour,fou;
    public PromotPanel(ChessPOO affichage)
    {
        super(0, 0, affichage.width, Color.rgb(0,0,0,0.3), Color.rgb(0,0,0,0.3), Color.rgb(0,0,0,0.3));
        dame = new PieceGraphique(new PieceDame(new Point(0,0), true), affichage.getXFromI(3), affichage.getXFromI(3), affichage.caseSize);
        cavalier = new PieceGraphique(new PieceCavalier(new Point(0,0), true), affichage.getXFromI(3), affichage.getXFromI(4), affichage.caseSize);
        tour = new PieceGraphique(new PieceTour(new Point(0,0), true), affichage.getXFromI(4), affichage.getYFromJ(3), affichage.caseSize);
        fou = new PieceGraphique(new PieceFou(new Point(0,0), true), affichage.getXFromI(4), affichage.getYFromJ(4), affichage.caseSize);
        
        dame.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {public void handle(MouseEvent e)
        {affichage.pieceClicked(dame);}});
        cavalier.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {public void handle(MouseEvent e)
        {affichage.pieceClicked(cavalier);}});
        tour.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {public void handle(MouseEvent e)
        {affichage.pieceClicked(tour);}});
        fou.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {public void handle(MouseEvent e)
        {affichage.pieceClicked(fou);}});
        
        addElement(dame.getGraphics(),false);
        addElement(cavalier.getGraphics(),false);
        addElement(tour.getGraphics(),false);
        addElement(fou.getGraphics(),false);
        this.affichage = affichage;
        setOpacity(0);
        pane.setMouseTransparent(true);
    }
    
    public void setupPiece(boolean isBlanc)
    {
        dame.changePiece(new PieceDame(new Point(0,0), isBlanc));
        cavalier.changePiece(new PieceCavalier(new Point(0,0), isBlanc));
        tour.changePiece(new PieceTour(new Point(0,0), isBlanc));
        fou.changePiece(new PieceFou(new Point(0,0), isBlanc));
    }
    
    public void appear()
    {
        System.out.println("coucou");
        fade(true);
        pane.setMouseTransparent(false);
    }
    public void vanish()
    {
        fade(false);
        pane.setMouseTransparent(true);
    }
}
