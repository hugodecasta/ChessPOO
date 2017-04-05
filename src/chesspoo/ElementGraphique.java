/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import javafx.animation.AnimationTimer;
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
    
    public ElementGraphique(int x, int y, int size, Color back, Color sel, Color hover)
    {
        pane = new StackPane();
        moveTo(x, y);
        pane.setMinSize(size,size);
        pane.setMaxSize(size,size);
        selectedColor = sel;
        hoverColor = hover;
        unselectedColor = back;
        
        backRect = new Rectangle(0,0,size,size);
        backRect.setFill(unselectedColor);
        
        pane.setOnMouseEntered(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                if(!isSelected)
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
    public void moveTo(int x, int y)
    {
        this.x = x;
        this.y = y;
        pane.setLayoutX(x);
        pane.setLayoutY(y);
    }
    public void moveToAnim(int x1,int y1,int steps)
    {        
        new AnimationTimer()
        {
            int xStart = 0;
            int yStart = 0;
            int xGoal = x1-x;
            int yGoal = y1-y;
            int time = 0;
            int startXX = x;
            int startYY = y;
            int duration = steps;
            
            @Override
            public void handle(long now)
            {
                if(time == 0)
                {
                    System.out.println(xGoal);
                }
                if(time == duration)
                {
                    moveTo(x1,y1);
                    this.stop();
                }
                else
                {
                    double xx = startXX + expo(time, xStart, xGoal, duration);
                    double yy = startYY + expo(time, yStart, yGoal, duration);
                    moveTo((int)xx,(int)yy);
                    time++;
                }
            }
            
            public float expo(float t,float b , float c, float d) {
		if (t==0) return b;
		if (t==d) return b+c;
		if ((t/=d/2) < 1) return c/2 * (float)Math.pow(2, 10 * (t - 1)) + b;
		return c/2 * (-(float)Math.pow(2, -10 * --t) + 2) + b;
	}
        }.start();
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
