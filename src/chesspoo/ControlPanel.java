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
    JoueurEchecs joueurBlanc, joueurNoir;
    Text compteurB,compteurN;
    Echecs jeu;
    
    public ControlPanel(int caseSize,Echecs jeu) {
        super(0, 0, caseSize*8,caseSize*10, Color.rgb(100,100,100), Color.rgb(100,100,100), Color.rgb(100,100,100));
        
        this.joueurBlanc = jeu.getJoueurBlanc();
        this.joueurNoir = jeu.getJoueurNoir();
        
        this.jeu = jeu;
        
        compteurB = new Text("COUCOU");
        compteurB.setFont(new Font(30));
        compteurB.setFill(Color.WHITE);
        compteurB.setX(sizew/2);
        compteurB.setY(sizeh-15);
        
        compteurN = new Text("COUCOU");
        compteurN.setFont(new Font(30));
        compteurN.setFill(Color.WHITE);
        compteurN.setX(sizew/2);
        compteurN.setY(caseSize-15);
        
        addFreeElement(compteurB);
        addFreeElement(compteurN);
        
        GButton but = new GButton(10,caseSize-50-5,100,50,"Abandon");
        GButton but2 = new GButton(10,sizeh-caseSize+5,100,50,"Abandon");
        
        addFreeElement(but.getGraphics());
        addFreeElement(but2.getGraphics());
        
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
                joueurBlanc.abandonner();
            }
        }
        );
        but.getGraphics().setOnMouseClicked(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                joueurNoir.abandonner();
            }
        }
        );
    }
    
    public void updateControles()
    {
        compteurB.setText(getTimeString(joueurBlanc.getCompteur().getTime()));
        compteurN.setText(getTimeString(joueurNoir.getCompteur().getTime()));
        
        if(jeu.getJoueurActuel() == joueurNoir)
        {
            compteurB.setFill(Color.gray(0.5));
            compteurN.setFill(Color.gray(1));
        }
        else
        {
            compteurN.setFill(Color.gray(0.5));
            compteurB.setFill(Color.gray(1));
        }
    }
    
    public String getTimeString(long secondes)
    {
        long trueSecondes = secondes/1000;
        long H = trueSecondes/3600;
        long M = trueSecondes/60-(H*60);
        long S = trueSecondes-(M*60);
        long milli = secondes-(trueSecondes-(M*60))*1000;
        if(S>20)
        {
            return M+":"+S;
        }
        return S+":"+(milli/10);
    }
}
