/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import Chess.Echecs;
import Chess.JoueurEchecs;
import Chess.Piece;
import Chess.Point;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author p1408098
 */
public class ChessPOO extends Application
{
    //-------------------------------------------------
    PieceGraphique pieceSelected;
    Point selectedPoint;
    CaseGraphique caseSelected;
    JoueurEchecs JB,JN;
    Echecs jeuEchecs;
    Thread jeu;
    int width = 500;
    int height = 500;
    int caseSize;
    ArrayList<PieceGraphique>piecesG;
    //-------------------------------------------------
    @Override
    public void start(Stage primaryStage)
    {
        JB = new JoueurEchecsHumain(this,true);
        JN = new JoueurEchecsHumain(this,false);
        jeuEchecs = new Echecs(JB,JN);
        jeuEchecs.initEchecs();
        
        caseSize = width/8;
        
        jeu = new Thread(){
            @Override
            public void run()
            {
                jeuEchecs.partie();
            }
        };
        jeu.start();
        
        AnchorPane root = new AnchorPane();
        initEchiquierFX(root);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.setRoot(root);
        primaryStage.show();
        
        new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                updateGrid();
            }
        }.start();
    }
    //-------------------------------------------------
    @Override
    public void stop()
    {
        jeu.stop();
    }        
    public void updateGrid()
    {
        for(PieceGraphique pg : piecesG)
        {
            Point p = pg.updatePiece();
            if(p!=null)
            {
                pg.moveToAnim(getXFromI(p.x), getYFromJ(p.y));
            }
        }
    }
    //-------------------------------------------------
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
        selectedPoint = cg.place;
        cg.select();
    }
    public void pieceClicked(PieceGraphique pg)
    {
        /*if(pieceSelected!=null && pg.piece != pieceSelected.piece)
        {
            caseClicked(pg.piece.pos.x,pg.piece.pos.y);
        }
        else*/
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
                selectedPoint = pg.piece.pos;
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
    //-------------------------------------------------
    public void initEchiquierFX(Pane root)
    {
        for(int i=0;i<8;++i)
        {
            for(int j=0;j<8;++j)
            {
                int j2 = 7-j;
                int x = i*caseSize;
                int y = j*caseSize;
                final Color backColor = (i + j2)%2==0?Color.rgb(238, 238, 210):Color.rgb(118, 150, 86);
                
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
                root.getChildren().add(pan);
                }
        }
        
        piecesG = new ArrayList<>();
        for(Piece p : jeuEchecs.getEchiquier().getPieces())
        {
            int j2 = 7-p.pos.y;
            int x = p.pos.x*caseSize;
            int y = j2*caseSize;
            final PieceGraphique pg = new PieceGraphique(p,x,y,caseSize);
            piecesG.add(pg);
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
            root.getChildren().add(pan);
        }
    }
    
    public int getXFromI(int i)
    {
        return i*caseSize;
    }
    public int getYFromJ(int j)
    {
        int j2 = 7-j;
        return j2*caseSize;
    }

    //-------------------------------------------------
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
