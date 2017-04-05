/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author p1608557
 */
public class ElementGraphique
{
    protected StackPane pane;
    protected boolean isSelected;
    protected int x,y,size;
    Color selectedColor,hoverColor,unselectedColor;
    final Rectangle backRect;
    
    public ElementGraphique(int x, int y, int size)
    {
        pane = new StackPane();
        selectedColor = Color.RED;
        hoverColor = Color.BEIGE;
        unselectedColor = Color.GRAY;
        
        backRect = new Rectangle(0,0,size,size);
        backRect.setFill(unselectedColor);
        
        pane.setOnMouseEntered(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                backRect.setFill(hoverColor);
            }
        }
        );
        pane.setOnMouseExited(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                if(!isSelected)
                    backRect.setFill(unselectedColor);
            }
        }
        );
        
        addElement(backRect);
    }
    protected void addElement(Node elm)
    {
        pane.getChildren().add(elm);
    }
    public Pane getGraphics()
    {
        return pane;
    }
    
    public void select()
    {
        isSelected = true;
        backRect.setFill(selectedColor);
    }
    public void unselect()
    {
        isSelected = false;
        backRect.setFill(unselectedColor);
    }
    
    
}
