package util;

/**
 *
 * @author frederic
 */
public class SnackException
        extends RuntimeException
{
    
    public SnackException()
    {
        super();
    }
    
    public SnackException(String message)
    {
        super(message);
    }
    
    public SnackException(Throwable t)
    {
        super(t);
    }
    
    public SnackException(String message, Throwable t)
    {
        super(message, t);
    }
    
}
