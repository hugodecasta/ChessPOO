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
    Piece pieceSelected;
    Point caseSelected;
    JoueurEchecs JB,JN;
    Echecs jeuEchecs;
    Thread jeu;
    int width = 500;
    int height = 500;
    ArrayList<PieceGraphique>piecesG;
    //-------------------------------------------------
    @Override
    public void start(Stage primaryStage)
    {
        JB = new JoueurEchecsHumain(this);
        JN = new JoueurEchecsHumain(this);
        jeuEchecs = new Echecs(JB,JN);
        jeuEchecs.initEchecs();
        
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
    }
    //-------------------------------------------------
    @Override
    public void stop()
    {
        jeu.stop();
    }
    //-------------------------------------------------
    public Piece pieceSelected()
    {
        return pieceSelected;
    }
    public Point caseSelected()
    {
        return pieceSelected.pos==caseSelected?null:caseSelected;
    }
    
    public void caseClicked(int x, int y)
    {
        System.out.println(x+" - "+y);
    }
    //-------------------------------------------------
    public void initEchiquierFX(Pane root)
    {
        int caseSize = width/8;
        for(int i=0;i<8;++i)
        {
            for(int j=0;j<8;++j)
            {
                int j2 = 7-j;
                int x = i*caseSize;
                int y = j2*caseSize;
                final Rectangle rect = new Rectangle(x, y, caseSize, caseSize);
                final Color backColor = (i + j2)%2==0?Color.rgb(238, 238, 210):Color.rgb(118, 150, 86);
                rect.setFill(backColor);
                
                final int i1 = i;
                final int j1 = j;
                rect.setOnMouseEntered(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        rect.setFill(Color.RED);
                    }
                }
                );
                rect.setOnMouseExited(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        rect.setFill(backColor);
                    }
                }
                );
                rect.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        caseClicked(i1,j1);
                    }
                }
                );
                root.getChildren().add(rect);
            }
        }
        
        piecesG = new ArrayList<>();
        for(Piece p : jeuEchecs.getEchiquier().getPieces())
        {
            final PieceGraphique pg = new PieceGraphique(p);
            piecesG.add(pg);
            root.getChildren().add(pg.getRect());
        }
    }

    //-------------------------------------------------
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
