package model;

public class InitialCommandException extends Exception{

    public InitialCommandException()
    {
        super("Error in command file!");
    }

    public InitialCommandException(String message)
    {
        super(message);
    }
}
