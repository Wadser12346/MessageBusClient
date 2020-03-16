package MessageBusFiles.InternalWrappers;

public class ConnectionAttempt {
    private String serverName;
    private String portNumber;
    private String username;

    public ConnectionAttempt(String serverName, String portNumber, String username) {
        this.serverName = serverName;
        this.portNumber = portNumber;
        this.username = username;
    }

    public ConnectionAttempt(String serverName, String portNumber) {
        this(serverName, portNumber, "Anonymous");
    }

    public String getServerName() {
        return serverName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public String getUsername() {
        return username;
    }
}
