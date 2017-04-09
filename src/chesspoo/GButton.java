/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author i3mainz
 */
public class GButton extends ElementGraphique
{
    
    public GButton(int x, int y, int sizew, int sizeh, String text)
    {
        super(x, y, sizew, sizeh, Color.rgb(50, 50, 50), Color.rgb(255, 255, 255), Color.rgb(150, 150, 150));
        
        Text t = new Text(text);
        t.setFill(Color.rgb(255,255,255));
        t.setFont(new Font(20));
        
        addElement(t);
    }
    
}
