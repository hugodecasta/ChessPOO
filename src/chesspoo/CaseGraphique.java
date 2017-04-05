/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import Chess.Point;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author p1608557
 */
public class CaseGraphique extends ElementGraphique
{
    Point place;
    
    public CaseGraphique(Point p,int x,int y, int size, Color backColor)
    {
        super(x,y,size,backColor,Color.RED,Color.rgb(255,0,0,0.5));
        place = p;
    }
    
    
}
