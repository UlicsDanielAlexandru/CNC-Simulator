package view;

import model.Point;

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

    public void resetMachineHead()
    {
        this.machineHead = new Point(50, grid.getGridImage().getHeight() - 50);
    }

    public void interpretCommands() throws CommandInterpreterException
    {
        boolean cutting = false;
        String commandFileName = "src\\resources\\commandFile.txt";
        Scanner scanner;
        try {
            scanner = new Scanner(new File(commandFileName));

            while(scanner.hasNext())
            {
                checkMachineHead();
                switch (scanner.next())
                {
                    case "cut" -> cutting = true;
                    case "retrieve" -> cutting = false;
                    case "x" -> {
                        int moveLength = Integer.parseInt(scanner.next());
                        int step = 10 * (int) Math.signum(moveLength);
                        moveLength = Math.abs(moveLength);
                        while(moveLength > 0)
                        {
                            if(cutting)
                                grid.paintRectangle(machineHead.getX(), machineHead.getY());
                            machineHead.setX(machineHead.getX() + step);
                            moveLength--;
                            grid.paint(grid.getGraphics());
                            Thread.sleep(10);
                        }
                    }
                    case "y" -> {
                        int moveLength = Integer.parseInt(scanner.next());
                        int step = -10 * (int) Math.signum(moveLength);
                        moveLength = Math.abs(moveLength);
                        while (moveLength > 0)
                        {
                            if(cutting)
                                grid.paintRectangle(machineHead.getX(), machineHead.getY());
                            machineHead.setY(machineHead.getY() + step);
                            moveLength--;
                            grid.paint(grid.getGraphics());
                            Thread.sleep(10);
                        }
                    }
                    case "d" -> {
                        int moveLength = Integer.parseInt(scanner.next());
                        int xStep = 10 * (int) Math.signum(moveLength);
                        int yStep = -10 * (int) Math.signum(Integer.parseInt(scanner.next()));
                        moveLength = Math.abs(moveLength);
                        while (moveLength > 0)
                        {
                            if(cutting)
                                grid.paintRectangle(machineHead.getX(), machineHead.getY());
                            machineHead = machineHead.add(new Point(xStep, yStep));
                            moveLength--;
                            grid.paint(grid.getGraphics());
                            Thread.sleep(10);
                        }
                    }
                    default -> throw new IllegalStateException();
                }

            }
            grid.paintRectangle(machineHead.getX(), machineHead.getY());
            grid.paint(grid.getGraphics());
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkMachineHead() throws CommandInterpreterException
    {
        if(machineHead.getX() < 0 || (machineHead.getX() + 10) > grid.getGridImage().getWidth()
                || (machineHead.getY() - 10) < 0 || machineHead.getY() > grid.getGridImage().getHeight())
            throw new CommandInterpreterException("Machine head is out of grid!");
    }

}
