package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Grid extends JPanel {

    private BufferedImage gridImage;

    public Grid(int width, int height)
    {
        this.setPreferredSize(new Dimension(942, 650));
        gridImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        setupGrid();
    }

    public void setupGrid()
    {
        Graphics initializer = gridImage.getGraphics();
        initializer.setColor(Color.white);
        initializer.fillRect(0,0,gridImage.getWidth(),gridImage.getHeight());
        initializer.setColor(Color.BLACK);
        int gridUnit = 0;
        for(int index = 43; index < gridImage.getWidth() - 50; index += 50)
        {
            initializer.drawString(String.valueOf(gridUnit),index,gridImage.getHeight());
            initializer.drawString(String.valueOf(gridUnit + 5),0,gridImage.getHeight() - index - 45);
            gridUnit += 5;
        }
        initializer.drawString(String.valueOf(gridUnit),gridImage.getWidth() - 15,gridImage.getHeight());
        for(int column = 0; column < gridImage.getWidth(); column += 10)
        {
            for(int row = gridImage.getHeight() - 1; row >= 0; row--)
            {
                if(column >= 50 && row < (gridImage.getHeight() - 50 + 1))
                    gridImage.setRGB(column,row,Color.black.getRGB());
                if(row >= 50 && column < (gridImage.getHeight() - 50 + 1))
                    gridImage.setRGB(row,column,Color.black.getRGB());
            }
        }
    }

    public void paintRectangle(int x, int y)
    {
        Graphics painter = gridImage.getGraphics();
        painter.setColor(Color.red);
        painter.fillRect(x + 1, y - 9, 9, 9);
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(gridImage,(912 - gridImage.getWidth()) / 2,0,this);
    }

    public BufferedImage getGridImage() {
        return gridImage;
    }

}
