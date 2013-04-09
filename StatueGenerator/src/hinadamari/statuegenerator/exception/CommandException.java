package hinadamari.statuegenerator.exception;

/**
 * CommandException (CommandException.java)
 * @author syam(syamn)
 */
public class CommandException extends Exception{
    private static final long serialVersionUID = 1413660793408779965L;

    public CommandException(String message){
        super(message);
    }

    public CommandException(Throwable cause){
        super(cause);
    }

    public CommandException(String message, Throwable cause){
        super(message, cause);
    }
}
