package MessageBusFiles.InternalWrappers;

public class OpenChat {
    String chatroomName;

    public OpenChat(String chatroomName){
        this.chatroomName = chatroomName;
    }

    public String getChatroomName() {
        return chatroomName;
    }
}
