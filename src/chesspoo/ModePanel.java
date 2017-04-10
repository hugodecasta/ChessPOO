/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import Chess.JoueurEchecs;
import Chess.ModeEchecs;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author i3mainz
 */
public class ModePanel extends ElementGraphique
{
    public ModePanel(int caseSize, ArrayList<ModeEchecs>modes, ChessPOO chessGraphique)
    {
        super(0, 0, caseSize*8,caseSize*10, Color.rgb(100,100,100,0.7), Color.rgb(100,100,100,0.7), Color.rgb(100,100,100,0.7));
        setOpacity(0);
        pane.setMouseTransparent(true);
        
        int hei = 50;
        int y = (caseSize*10)/2 - (modes.size()/2)*60;
        for(ModeEchecs mode:modes)
        {
            GButton but = new GButton(caseSize, y, caseSize*6, 50, mode.toString());
            addFreeElement(but.getGraphics());
            but.getGraphics().setOnMouseClicked(
            new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent e)
                {
                    chessGraphique.startGame(mode);
                }
            }
            );
            y = y+60;
        }
    }
    
    public void winning(JoueurEchecs joueur)
    {
        String str = joueur.isBlanc()?"Les blancs gagnent":"Les noirs gagnent";
        appear();
    }
}
