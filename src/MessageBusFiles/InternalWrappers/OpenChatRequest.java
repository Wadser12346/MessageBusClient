package MessageBusFiles.InternalWrappers;

public class OpenChatRequest {
    String chatroomName;

    public OpenChatRequest(String chatroomName){
        this.chatroomName = chatroomName;
    }

    public String getChatroomName() {
        return chatroomName;
    }
}
