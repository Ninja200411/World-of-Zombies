package Lib;

import javax.swing.*;
import java.awt.*;

public record ImageObject(Vertex pos, Vertex velocity
        , double width, double height
        , String fileName, Image image)
        implements GameObject{

    public ImageObject{
        var iIcon
                = new ImageIcon(getClass().getClassLoader().getResource(fileName));
        width = iIcon.getIconWidth();
        height=iIcon.getIconHeight();
        image = iIcon.getImage();
    }

    public ImageObject(Vertex pos,Vertex velocity, String fileName){
        this(pos,velocity,100,100,fileName,null);

    }

    public ImageObject(String fileName){
        this(new Vertex(0,0),new Vertex(0,0),fileName);
    }

    public void paintTo(Graphics g, Vertex translate){
        g.drawImage(image(),(int)pos.x, (int)pos.y, null);
    }
}

