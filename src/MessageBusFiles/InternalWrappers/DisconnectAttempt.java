package MessageBusFiles.InternalWrappers;

public class DisconnectAttempt {
    private String message;

    public DisconnectAttempt(){
        message = "DisconnectAttempt";
    }

    public String getMessage(){
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
