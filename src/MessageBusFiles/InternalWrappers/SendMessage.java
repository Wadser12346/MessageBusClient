package MessageBusFiles.InternalWrappers;

import CS4B.Messages.ChatMessage;

public class SendMessage {
    String chatroomName;
    String username;
    ChatMessage message;

    public SendMessage(String chatroomName, String username, ChatMessage message) {
        this.chatroomName = chatroomName;
        this.username = username;
        this.message = message;
    }

    public String getChatroomName() {
        return chatroomName;
    }

    public ChatMessage getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }
}
