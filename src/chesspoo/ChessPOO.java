/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import Chess.Echecs;
import Chess.JoueurEchecs;
import Chess.Piece;
import Chess.PieceRoi;
import Chess.Point;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    int width,tWidth;
    int height,tHeight;
    int xOffset;
    int yOffset;
    int caseSize;
    PieceGraphique rb,rn;
    PromotPanel promotP;
    JoueurEchecs lastJoueur;
    ArrayList<PieceGraphique>piecesG;
    ArrayList<Piece>pieceTracker;
    AnchorPane globalPan,echiquierPane;
    //-------------------------------------------------
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.getIcons().add(new Image("pion.png"));
        primaryStage.setTitle("My Chess");
        JB = new JoueurEchecsHumain(this,true);
        JN = new JoueurEchecsHumain(this,false);
        jeuEchecs = new Echecs(JB,JN);
        jeuEchecs.initEchecs();
        
        piecesG = new ArrayList<>();
        pieceTracker = new ArrayList<Piece>();
        
        caseSize = 100;
        width = caseSize*8;
        height = caseSize*8;
        
        jeu = new Thread(){
            @Override
            public void run()
            {
                jeuEchecs.partie();
            }
        };
        jeu.start();
        
        globalPan = new AnchorPane();
        echiquierPane = new AnchorPane();
        initEchiquierFX(echiquierPane);
        globalPan.getChildren().add(echiquierPane);
        Scene scene = new Scene(globalPan);
        primaryStage.setScene(scene);
        scene.setRoot(globalPan);
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
        if(jeuEchecs.getJoueur() != lastJoueur)
        {
            /*if(jeuEchecs.getJoueur().isBlanc())
                gamerColor.setFill(Color.WHITE);
            else
                gamerColor.setFill(Color.BLACK);*/
        }
        ArrayList<Piece> pp = jeuEchecs.getEchiquier().getPieces();
        for(Piece p : pp)
        {
            if(!pieceTracker.contains(p))
            {
                PieceGraphique pg = introducePieceGraphique(p, globalPan);
            }
        }
        for(PieceGraphique pg : piecesG)
        {
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
        }
        boolean newEchec = jeuEchecs.estEnEchec();
        if(newEchec != oldEchec)
        {
            if(newEchec)
            {
                oldEchec = true;
                if(jeuEchecs.getJoueur().isBlanc())
                    rb.echecAuRoi();
                else
                    rn.echecAuRoi();
            }
            else
            {
                oldEchec = false;
                rb.resetEchecAuRoi();
                rn.resetEchecAuRoi();
            }
            oldEchec = newEchec;
        }
    }
    boolean oldEchec = false;
    public void iNeedToPromot(JoueurEchecs j)
    {
        promotP.setupPiece(j.isBlanc());
        promotP.appear();
    }
    public void thancksForPromot()
    {
        promotP.vanish();
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
        pointSelect(cg.place);
        cg.select();
    }
    public void pointSelect(Point p)
    {
        selectedPoint = p;
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
    //-------------------------------------------------
    public void initEchiquierFX(Pane root)
    {
        xOffset = 5;
        yOffset = caseSize;
        /*gamerColor = new Rectangle(0,0,width+xOffset*2,height+yOffset*2);
        gamerColor.setFill(Color.WHITE);
        root.getChildren().add(gamerColor);*/
        Rectangle back = new Rectangle(0,0,width+2*xOffset,height+2*yOffset);
        back.setFill(Color.rgb(52, 73, 94));
        root.getChildren().add(back);
        tWidth = (int)back.getWidth();
        tHeight = (int)back.getHeight();
        
        Image img = new Image("chess.jpg");
        ImageView chessBoard = new ImageView(img);
        chessBoard.setX(xOffset);
        chessBoard.setY(yOffset);
        chessBoard.setFitHeight(caseSize*8);
        chessBoard.setFitWidth(caseSize*8);
        root.getChildren().add(chessBoard);
        
        for(int i=0;i<8;++i)
        {
            for(int j=0;j<8;++j)
            {
                int x = getXFromI(i);
                int j2 = 7-j;
                int y = getYFromJ(j2);
                //final Color backColor = (i + j)%2==0?Color.rgb(238, 238, 210):Color.rgb(118, 150, 86);
                //final Color backColor = (i + j)%2==0?Color.rgb(255,228,196):Color.rgb(139,69,19);
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
                root.getChildren().add(pan);
                }
        }
        
        for(Piece p : jeuEchecs.getEchiquier().getPieces())
        {
            introducePieceGraphique(p,root);
        }
        
        promotP = new PromotPanel(this);
        root.getChildren().add(promotP.getGraphics());
    }
    
    public PieceGraphique introducePieceGraphique(Piece p,Pane root)
    {
        int x = getXFromI(p.pos.x);
        int y = getYFromJ(p.pos.y);
        final PieceGraphique pg = new PieceGraphique(p,x,y,caseSize);
        if(p instanceof PieceRoi)
        {
            if(p.isBlanc())
                rb = pg;
            else
                rn = pg;
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
        root.getChildren().add(pan);
        return pg;
    }
    
    public int getXFromI(int i)
    {
        return i*caseSize+xOffset;
    }
    public int getYFromJ(int j)
    {
        int j2 = 7-j;
        return j2*caseSize+yOffset;
    }

    //-------------------------------------------------
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
