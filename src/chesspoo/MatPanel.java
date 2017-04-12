/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import Chess.JoueurEchecs;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author i3mainz
 */
public class MatPanel extends ElementGraphique
{
    Text winner;
    public MatPanel(int caseSize)
    {
        super(0, 0, caseSize*8,caseSize*10, Color.rgb(100,100,100,0.7), Color.rgb(100,100,100,0.7), Color.rgb(100,100,100,0.7));
        setOpacity(0);
        pane.setMouseTransparent(true);
        winner = new Text("No one");
        winner.setFill(Color.WHITE);
        winner.setFont(new Font(caseSize/2));
        addElement(winner);
        
    }
    
    public void winning(JoueurEchecs joueur)
    {
        String str = "";
        if(joueur != null)
            str = joueur.isBlanc()?"Les blancs gagnent":"Les noirs gagnent";
        else
            str = "Game Drawn";
        winner.setText(str);
        appear();
    }
    
}
