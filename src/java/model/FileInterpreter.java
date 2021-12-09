package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInterpreter {

    public static void interpretFile(String path) throws InitialCommandException
    {
        Pattern verificationPattern = Pattern.compile("\\(?[ ]*(\\d+)[ ]*,[ ]*(\\d+)[ ]*\\)[ ]*,[ ]*\\([ ]*(\\d+)[ ]*,[ ]*(\\d+)[ ]*\\)(?:[ ]*,[ ]*(\\d+))?$");
        int line = 1;
        String commandFileName = "src\\resources\\commandFile.txt";
        String initialCommand;
        Matcher matcher;
        Scanner scanner = null;
        BufferedWriter writer = null;
        try {
            scanner = new Scanner(new File(path));
            writer=new BufferedWriter(new java.io.FileWriter(commandFileName));
            while(scanner.hasNextLine())
            {
                initialCommand = scanner.nextLine();
                if(initialCommand.equals("cut"))
                    writer.write("cut\n");
                else
                {
                    if(initialCommand.equals("retrieve"))
                        writer.write("retrieve\n");
                    else
                    {
                        matcher = verificationPattern.matcher(initialCommand);
                        if(!matcher.matches())
                            throw new InitialCommandException("Invalid command at line: " + line + "!");
                        else
                        {
                            Point startPoint;
                            Point endPoint;
                            int arcRadius;
                            if(matcher.group(5) == null)
                            {
                                startPoint = new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                                endPoint = new Point(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
                                try
                                {
                                    List<Point> points = Algorithms.getSegmentPoints(startPoint, endPoint);
                                    writer.write(pointsToCommand(points));
                                }
                                catch (InitialCommandException e)
                                {
                                    throw new InitialCommandException("Error at line: " + line + "! " + e.getMessage());
                                }
                            }
                            else
                            {
                                startPoint = new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                                endPoint = new Point(Integer.parseInt(matcher.group(3)),Integer.parseInt(matcher.group(4)));
                                arcRadius = Integer.parseInt(matcher.group(5));
                                try
                                {
                                    Point center = Algorithms.getCircleCenter(startPoint, endPoint, arcRadius);
                                    List<Point> points = Algorithms.getArcPoints(startPoint, endPoint, center, arcRadius);
                                    writer.write(pointsToCommand(points));
                                }
                                catch (InitialCommandException e)
                                {
                                    throw new InitialCommandException("Error at line: " + line + "! " + e.getMessage());
                                }
                            }
                        }
                    }
                }
                line++;
            }

        }
        catch (FileNotFoundException e) {
            throw new InitialCommandException("File not found!");
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static String pointsToCommand(List<Point> points)
    {
        StringBuilder stringBuilder = new StringBuilder();
        Command command = new Command(points.get(1).subtract(points.get(0)));
        for(int index = 1; index < points.size() - 1; index++)
        {
            Command newCommand = new Command(points.get(index + 1).subtract(points.get(index)));
            if(command.sameType(newCommand))
            {
                command.setMovement(command.getMovement().add(newCommand.getMovement()));
                if(index == (points.size() - 2))
                    stringBuilder.append(command).append("\n");
            }
            else
            {
                stringBuilder.append(command).append("\n");
                command = newCommand;
            }
        }
        if(stringBuilder.length()==0)
            stringBuilder.append(command).append("\n");
        return String.valueOf(stringBuilder);
    }
}
