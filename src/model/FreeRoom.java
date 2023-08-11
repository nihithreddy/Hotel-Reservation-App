package model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber,RoomType roomType) {
        super(roomNumber,0.0D,roomType);
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=$" + price +
                ", roomType=" + roomType +
                '}';
    }
}
