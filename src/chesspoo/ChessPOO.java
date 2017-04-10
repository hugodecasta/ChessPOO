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
    
    Echecs jeuEchecs;
    Thread jeu;
    int width,tWidth;
    int height,tHeight;
    int xOffset;
    int yOffset;
    int caseSize;
    
    AnchorPane globalPan;
    
    AnchorPane modePane;
    ControlPanel controlPanel;
    MatPanel matPanel;
    
    JoueurEchecs gagnant;    
    EchiquierGraphique echecsGraphiques;
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------
    @Override
    public void start(Stage primaryStage)
    {        
        initWindow(primaryStage);
        
        initEchecsLogic();
        initEchecsGraphics();
        
        startAll(primaryStage);
    }
    //---------------------------------------------------------------------- UPDATER
    //----------------------------------------------------------------------
    public void update()
    {
        
    }
    //---------------------------------------------------------------------- INITIALISATIONS
    //----------------------------------------------------------------------
    private void startAll(Stage primaryStage)
    {
        primaryStage.show();
        
        matPanel.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                matPanel.vanish();
                startAsker();
            }
        }
        );
        startAsker();
    }
    //-----------------------------
    private void initEchecsLogic()
    {
        jeuEchecs = new Echecs();
    }
    //-----------------------------
    private void initWindow(Stage primaryStage)
    {
        primaryStage.getIcons().add(new Image("pion.png"));
        primaryStage.setTitle("My Chess");
        globalPan = new AnchorPane();
        Scene scene = new Scene(globalPan);
        primaryStage.setScene(scene);
        scene.setRoot(globalPan);
    }
    //-----------------------------
    private void initEchecsGraphics()
    {
        caseSize = 60;
        // Background
        controlPanel = new ControlPanel(caseSize, jeuEchecs);
        globalPan.getChildren().add(controlPanel.getGraphics());
        // Echiquier
        echecsGraphiques = new EchiquierGraphique(0, caseSize, caseSize, jeuEchecs.getEchiquier());
        globalPan.getChildren().add(echecsGraphiques.getGraphics());
        // Mat
        matPanel = new MatPanel(caseSize);
        globalPan.getChildren().add(matPanel.getGraphics());
    }
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------
    @Override
    public void stop()
    {
        jeu.stop();
    }
    public void startAsker()
    {
        jeu = new Thread(){
            @Override
            public void run()
            {
                JoueurEchecsHumain jb = new JoueurEchecsHumain(true);
                JoueurEchecsHumain jn = new JoueurEchecsHumain(false);
                jb.setEchiquierGraphique(echecsGraphiques);
                jn.setEchiquierGraphique(echecsGraphiques);
                gagnant = jeuEchecs.partie(new ModeHumain(jb, jn, 1));
                matPanel.winning(gagnant);
            }
        };
        jeu.start();
    }    

    //-------------------------------------------------
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
