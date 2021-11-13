package model;

public class Command {

    private char commandType;
    private Point movement;

    public Command(Point pointDifference)
    {
        this.movement = pointDifference;
        if(pointDifference.getX() != 0 && pointDifference.getY() != 0)
            this.commandType = 'd';
        else
        {
            if(pointDifference.getX() != 0)
                this.commandType = 'x';
            else
                this.commandType = 'y';
        }
    }

    public char getCommandType() {
        return commandType;
    }

    public void setCommandType(char commandType) {
        this.commandType = commandType;
    }

    public Point getMovement() {
        return movement;
    }

    public void setMovement(Point movement) {
        this.movement = movement;
    }

    public boolean sameType(Command command)
    {
        return this.commandType == command.commandType && Math.signum(this.movement.getX()) == Math.signum(command.movement.getX())
                && Math.signum(this.movement.getY()) == Math.signum(command.movement.getY());
    }

    @Override
    public String toString()
    {
        String stringToReturn = commandType + " ";
        stringToReturn = stringToReturn.concat(
                switch (commandType)
                        {
                            case 'x' -> String.valueOf(movement.getX());
                            case 'y' -> String.valueOf(movement.getY());
                            case 'd' -> movement.getX() + " " +movement.getY();
                            default -> throw new IllegalStateException("Unexpected value: " + commandType);
                        }
        );
        return stringToReturn;
    }
}
