/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import Chess.Echiquier;
import Chess.JoueurEchecs;
import Chess.Piece;
import Chess.PieceRoi;
import Chess.Point;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author i3mainz
 */
public class EchiquierGraphique extends ElementGraphique
{
    int caseSize;
    ArrayList<PieceGraphique>piecesG;
    ArrayList<Piece>pieceTracker;
    Echiquier echiquier;
    PieceGraphique roiB,roiN;
    PieceGraphique pieceSelected;
    Point selectedPoint;
    CaseGraphique caseSelected;
    AnchorPane global,anPane;
    PromotPanel promotP;
    PieceGraphique pEnEchec;
    
    
    public EchiquierGraphique(int x,int y, int caseSize, Echiquier echiquier) {
        super(x, y, caseSize*8,caseSize*8, Color.rgb(0,0,0,0), Color.rgb(0,0,0,0), Color.rgb(0,0,0,0));
        
        this.caseSize = caseSize;
        this.echiquier = echiquier;
        global = new AnchorPane();
        addElement(global);
        
        anPane = new AnchorPane();
        promotP = new PromotPanel(this);
        initEchiquierFX();
        global.getChildren().add(anPane);
        global.getChildren().add(promotP.getGraphics());
        
         new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                updatePlateau();
            }
        }.start();
    }
    //-----------------------------------------------------UPDATE
    public void updatePlateau()
    {
        ArrayList<Piece> pp = echiquier.getPieces();
        for(Piece p : pp)
        {
            if(!pieceTracker.contains(p))
            {
                PieceGraphique pg = introducePieceGraphique(p);
            }
        }
        PieceGraphique newPEnEchec = null;
        ArrayList<PieceGraphique>toRemove = new ArrayList<>();
        ArrayList<Piece>toRemoveTracker = new ArrayList<>();
        for(PieceGraphique pg : piecesG)
        {
            if(pg.piece.isMange())
            {
                toRemoveTracker.add(pg.piece);
                if(pg.opacity==0)
                    toRemove.add(pg);
            }
            
            Point p = pg.updatePiece();
            if(p!=null)
            {
                if(pg.piece.isMange())
                {
                    pg.vanish();
                }
                else
                {
                    pg.moveToAnim(getXFromI(p.x), getYFromJ(p.y),8);
                }
            }
                
            if(pg.oldEchec != pg.piece.enEchec)
            {
                if(pg.piece.enEchec)
                    pg.echecAuRoi();
                else
                    pg.resetEchecAuRoi();
                pg.oldEchec = pg.piece.enEchec;
            }
            else
            {
                if(pg.unselectedColor.getRed()==0 && pg.piece.enEchec)
                    pg.echecAuRoi();
                if(pg.unselectedColor.getRed()==255 && !pg.piece.enEchec)
                    pg.resetEchecAuRoi();
            }
        }
        
        for(PieceGraphique p: toRemove)
            piecesG.remove(p);
        for(Piece p: toRemoveTracker)
            pieceTracker.remove(p);
    }
    //-----------------------------------------------------PROMOT
    public void iNeedToPromot(JoueurEchecs j)
    {
        promotP.setupPiece(j.isBlanc());
        promotP.appear();
    }
    public void thancksForPromot()
    {
        promotP.vanish();
    }
    //-------------------------------------------------------------------
    public void initEchiquierFX()
    {        
        piecesG = new ArrayList<>();
        pieceTracker = new ArrayList<Piece>();
        
        Image img = new Image("chess.jpg");
        ImageView chessBoard = new ImageView(img);
        chessBoard.setFitHeight(caseSize*8);
        chessBoard.setFitWidth(caseSize*8);
        anPane.getChildren().add(chessBoard);
        
        for(int i=0;i<8;++i)
        {
            for(int j=0;j<8;++j)
            {
                int x = getXFromI(i);
                int j2 = 7-j;
                int y = getYFromJ(j2);
                final Color backColor = (i + j)%2==0?Color.rgb(0,0,0,0):Color.rgb(139,69,19,0.5);
                
                final CaseGraphique cg = new CaseGraphique(new Point(i,j2),x,y,caseSize,backColor);
                Pane pan = cg.getGraphics();
                pan.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        caseClicked(cg);
                    }
                }
                );
                anPane.getChildren().add(pan);
            }
        }
    }
    
    public PieceGraphique introducePieceGraphique(Piece p)
    {
        int x = getXFromI(p.pos.x);
        int y = getYFromJ(p.pos.y);
        final PieceGraphique pg = new PieceGraphique(p,x,y,caseSize);
        if(p instanceof PieceRoi)
        {
            pEnEchec = null;
            if(p.isBlanc())
                roiB = pg;
            else
                roiN = pg;
        }
        pg.setOpacity(0);
        pg.fade(true);
        piecesG.add(pg);
        pieceTracker.add(p);
        Pane pan = pg.getGraphics();
        pan.setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                pieceClicked(pg);
            }
        }
        );
        anPane.getChildren().add(pan);
        return pg;
    }
    //-------------------------------------------------------------------------
    public Piece getPieceSelected()
    {
        if(pieceSelected==null)
            return null;
        return pieceSelected.piece;
    }
    public Point getSelectedPoint()
    {
        return selectedPoint;
    }
    public Point getCaseSelected()
    {
        if(caseSelected==null)
            return null;
        return caseSelected.place;
    }
    
    public void caseClicked(CaseGraphique cg)
    {
        caseSelected = cg;
        pointSelect(cg.place);
        cg.select();
    }
    public void pointSelect(Point p)
    {
        selectedPoint = p;
    }
    public void pieceClicked(PieceGraphique pg)
    {
        if(pieceSelected!=null)
        {
            if(pieceSelected.piece.isBlanc() == pg.piece.isBlanc())
            {
                pieceSelected.unselect();
                pieceSelected = pg;
                pg.select();
            }
            else
            {
                pointSelect(pg.piece.pos);
            }
        }
        else
        {
            pieceSelected = pg;
            pg.select();
        }
    }
    public void resetSelections()
    {
        if(pieceSelected!=null)
        {
            pieceSelected.unselect();
        }if(caseSelected!=null)
        {
            caseSelected .unselect();
        } 
        caseSelected = null;
        pieceSelected = null;
        selectedPoint = null;
    }
    //---------------------------------------------------------------------
    public int getXFromI(int i)
    {
        return i*caseSize;
    }
    public int getYFromJ(int j)
    {
        int j2 = 7-j;
        return j2*caseSize;
    }
    
}
