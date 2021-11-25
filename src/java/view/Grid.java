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

    private void setupGrid()
    {
        Graphics initializer = gridImage.getGraphics();
        initializer.setColor(Color.white);
        initializer.fillRect(0,0,gridImage.getWidth(),gridImage.getHeight());
        initializer.setColor(Color.BLACK);
        int gridUnit = 0;
        for(int index = 43; index < gridImage.getWidth() - 50; index += 50)
        {
            initializer.drawString(String.valueOf(gridUnit),index,gridImage.getHeight());
            initializer.drawString(String.valueOf(gridUnit + 10),0,gridImage.getHeight() - index - 45);
            gridUnit += 10;
        }
        initializer.drawString(String.valueOf(gridUnit),gridImage.getWidth() - 19,gridImage.getHeight());
        for(int column = 0; column < gridImage.getWidth(); column += 50)
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

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(gridImage,(912 - gridImage.getWidth()) / 2,0,this);
    }

}
