package hinadamari.statuegenerator.exception;

/**
 * StateException (StateException.java)
 * @author syam(syamn)
 */
public class StateException extends RuntimeException{
    private static final long serialVersionUID = -6944340579562397785L;

    public StateException(String message){
        super(message);
    }

    public StateException(Throwable cause){
        super(cause);
    }

    public StateException(String message, Throwable cause){
        super(message, cause);
    }
}
