public class CapacidadeIndisponivelException extends RuntimeException{
    public CapacidadeIndisponivelException(String message) {
        super(message);
    }
    public CapacidadeIndisponivelException() {
        super();
    }
    public CapacidadeIndisponivelException(String message, Throwable cause) {
        super(message, cause);
    }
    public CapacidadeIndisponivelException(Throwable cause) {
        super(cause);   
    }
}