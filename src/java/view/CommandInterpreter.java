package view;

import model.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommandInterpreter {

    private Point machineHead;
    private Grid grid;

    public CommandInterpreter(Grid grid)
    {
        this.grid = grid;
        this.machineHead = new Point(50, grid.getGridImage().getHeight() - 50);
    }

    public void interpretCommands()
    {
        boolean cutting = false;
        String commandFileName = "src\\resources\\commandFile.txt";
        Scanner scanner;
        try {
            scanner = new Scanner(new File(commandFileName));
            while(scanner.hasNext())
            {
                switch (scanner.next())
                {
                    case "cut" -> cutting = true;
                    case "retrieve" -> cutting = false;
                    case "x" -> {
                        int moveLength = Integer.parseInt(scanner.next());
                        int step = moveLength / Math.abs(moveLength);
                        while(moveLength > 0)
                        {
                            if(cutting)
                                grid.getGridImage().setRGB(machineHead.getX(), machineHead.getY(), Color.red.getRGB());
                            machineHead.setX(machineHead.getX() + step);
                            moveLength--;
                            grid.paint(grid.getGraphics());
                            Thread.sleep(10);
                        }
                    }
                    case "y" -> {
                        int moveLength = Integer.parseInt(scanner.next());
                        int step = -1 * moveLength / Math.abs(moveLength);
                        while (moveLength > 0)
                        {
                            if(cutting)
                                grid.getGridImage().setRGB(machineHead.getX(), machineHead.getY(), Color.red.getRGB());
                            machineHead.setY(machineHead.getY() + step);
                            moveLength--;
                            grid.paint(grid.getGraphics());
                            Thread.sleep(10);
                        }
                    }
                    case "d" -> {
                        int moveLength = Integer.parseInt(scanner.next());
                        int xStep = moveLength / Math.abs(moveLength);
                        int yStep = Integer.parseInt(scanner.next());
                        yStep = -1 * yStep / Math.abs(yStep);
                        while (moveLength > 0)
                        {
                            if(cutting)
                                grid.getGridImage().setRGB(machineHead.getX(), machineHead.getY(), Color.red.getRGB());
                            machineHead = machineHead.add(new Point(xStep, yStep));
                            moveLength--;
                            grid.paint(grid.getGraphics());
                            Thread.sleep(10);
                        }
                    }
                    default -> throw new IllegalStateException();
                }

            }
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
