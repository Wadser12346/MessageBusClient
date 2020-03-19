package MessageBusFiles.InternalWrappers;

import CS4B.Messages.ChatMessage;

public class MessageReceived {
    String client;
    String chatroomName;
    ChatMessage message;

    public MessageReceived(String client, String chatroomName, ChatMessage message) {
        this.client = client;
        this.chatroomName = chatroomName;
        this.message = message;
    }

    public String getClient() {
        return client;
    }

    public String getChatroomName() {
        return chatroomName;
    }

    public ChatMessage getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return client + ": " + message.getStringMessage().toString();
    }
}
