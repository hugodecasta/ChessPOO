/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import Chess.Echecs;
import Chess.JoueurEchecs;
import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author i3mainz
 */
public class ControlPanel extends ElementGraphique
{
    Text compteurB,compteurN;
    ElementGraphique abandonBlanc, abandonNoir;
    Echecs jeu;
    
    public ControlPanel(int caseSize,Echecs jeu) {
        super(0, 0, caseSize*8,caseSize*10, Color.rgb(100,100,100), Color.rgb(100,100,100), Color.rgb(100,100,100));
                
        this.jeu = jeu;
        
        compteurB = new Text("Blanc");
        compteurB.setFont(new Font(caseSize/2));
        compteurB.setFill(Color.WHITE);
        compteurB.setX(sizew/2);
        compteurB.setY(caseSize*9+caseSize/2+5);
        
        compteurN = new Text("Noir");
        compteurN.setFont(new Font(caseSize/2));
        compteurN.setFill(Color.WHITE);
        compteurN.setX(sizew/2);
        compteurN.setY(caseSize-15);
        
        addFreeElement(compteurB);
        addFreeElement(compteurN);
        
        GButton but = new GButton(10,caseSize-50-5,100,50,"Abandon");
        GButton but2 = new GButton(10,sizeh-caseSize+5,100,50,"Abandon");
        addFreeElement(but.getGraphics());
        addFreeElement(but2.getGraphics());
        
        GButton butt = new GButton(10+110,caseSize-50-5,150,50,"Proposer Nul");
        GButton butt2 = new GButton(10+110,sizeh-caseSize+5,150,50,"Proposer Nul");
        addFreeElement(butt.getGraphics());
        addFreeElement(butt2.getGraphics());
        
        int tailleAbandon = caseSize-20;
        abandonBlanc = new ElementGraphique(sizew-tailleAbandon-10, sizeh-tailleAbandon-10, tailleAbandon, 
                Color.rgb(255, 0, 0), 
                Color.rgb(255, 0, 0), 
                Color.rgb(255, 0, 0));
        abandonBlanc.setOpacity(0);        
        addFreeElement(abandonBlanc.getGraphics());
        abandonNoir = new ElementGraphique(sizew-tailleAbandon-10, 10, tailleAbandon, 
                Color.rgb(255, 0, 0), 
                Color.rgb(255, 0, 0), 
                Color.rgb(255, 0, 0));
        abandonNoir.setOpacity(0);        
        addFreeElement(abandonNoir.getGraphics());
        
        new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                updateControles();
            }
        }.start();
        
        but2.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                jeu.getJoueurBlanc().abandonner();
            }
        }
        );
        but.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                jeu.getJoueurNoir().abandonner();
            }
        }
        );
        
        butt2.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                jeu.getJoueurBlanc().proposerNul();
            }
        }
        );
        butt.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                jeu.getJoueurNoir().proposerNul();
            }
        }
        );
    }
    
    public void updateControles()
    {
        if(jeu.getJoueurBlanc()==null)
            return;
        compteurB.setText(getTimeString(jeu.getJoueurBlanc().getCompteur().getTime()));
        compteurN.setText(getTimeString(jeu.getJoueurNoir().getCompteur().getTime()));
        
        if(jeu.getJoueurActuel() == jeu.getJoueurNoir())
        {
            compteurB.setFill(Color.gray(0.5));
            compteurN.setFill(Color.gray(1));
        }
        else
        {
            compteurN.setFill(Color.gray(0.5));
            compteurB.setFill(Color.gray(1));
        }
        
        if(jeu.getJoueurBlanc().estProposeNul() && !oldBlancNul)
        {
            abandonBlanc.appear();
            oldBlancNul = true;
        }
        else if(!jeu.getJoueurBlanc().estProposeNul() && oldBlancNul)
        {
            abandonBlanc.vanish();
            oldBlancNul = false;
        }
        if(jeu.getJoueurNoir().estProposeNul() && !oldNoirNul)
        {
            abandonNoir.appear();
            oldNoirNul = true;
        }
        else if(!jeu.getJoueurNoir().estProposeNul() && oldNoirNul)
        {
            abandonNoir.vanish();
            oldNoirNul = false;
        }
    }
    boolean oldBlancNul,oldNoirNul;
    
    public String getTimeString(long secondes)
    {
        long trueSecondes = secondes/1000;
        long H = trueSecondes/3600;
        long M = trueSecondes/60-(H*60);
        long S = trueSecondes-(M*60);
        long milli = secondes-(trueSecondes-(M*60))*1000;
        if(S>20 || M>=1 || H>=1)
        {
            return M+":"+S;
        }
        return S+":"+(milli/10);
    }
}
