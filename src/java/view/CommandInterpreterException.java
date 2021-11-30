package view;

public class CommandInterpreterException extends Exception{

    public CommandInterpreterException(String message)
    {
        super(message);
    }

    public CommandInterpreterException()
    {
        super("An error occurred while interpreting commands!");
    }

}
