/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesspoo;

import java.util.concurrent.Callable;
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
    protected int x,y,sizew,sizeh;
    protected float opacity;
    Color selectedColor,hoverColor,unselectedColor;
    final Rectangle backRect;
    
    public ElementGraphique(int x, int y, int size, Color back, Color sel, Color hover)
    {
        this(x, y, size, size, back, sel, hover);
    }
    
    public ElementGraphique(int x, int y, int sizew, int sizeh, Color back, Color sel, Color hover)
    {
        pane = new StackPane();
        moveTo(x, y);
        pane.setMinSize(sizew,sizeh);
        pane.setMaxSize(sizew,sizeh);
        selectedColor = sel;
        hoverColor = hover;
        unselectedColor = back;
        
        backRect = new Rectangle(0,0,sizew,sizeh);
        backRect.setFill(unselectedColor);
        setOpacity(1);
        
        pane.setOnMousePressed(
        new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                if(!isSelected)
                    backRect.setFill(selectedColor);
            }
        }
        );
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
        addElement(elm,true);
    }
    protected void addElement(Node elm,boolean manage)
    {
        elm.setManaged(manage);
        pane.getChildren().add(elm);
    }
    public void setOpacity(float opacity)
    {
        this.opacity = opacity;
        pane.setOpacity(opacity);
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
                if(time == duration)
                {
                    moveTo(x1,y1);
                    this.stop();
                }
                else
                {
                    double xx = startXX + EasingFunctions.expo(time, xStart, xGoal, duration);
                    double yy = startYY + EasingFunctions.expo(time, yStart, yGoal, duration);
                    moveTo((int)xx,(int)yy);
                    time++;
                }
            }
        }.start();
    }
    public void fade(boolean in)
    {        
        new AnimationTimer()
        {
            int duration = 20;
            float opacityStart = 0;
            float opacityGoal = 1;
            int time = 0;
            
            @Override
            public void handle(long now)
            {
                if(time == duration)
                {
                    setOpacity(in?opacityGoal:1-opacityGoal);
                    this.stop();
                }
                else
                {
                    float opac = EasingFunctions.linear(time, opacityStart, opacityGoal, duration);
                    setOpacity(in?opac:1-opac);
                    time++;
                }
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
