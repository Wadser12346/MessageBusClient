package MessageBusFiles.InternalWrappers;

public class InternalPacket {
    String packetType;
    Object packet;

    public InternalPacket(String packetType, Object packet) {
        this.packetType = packetType;
        this.packet = packet;
    }

    public String getPacketType() {
        return packetType;
    }

    public Object getPacket() {
        return packet;
    }
}
