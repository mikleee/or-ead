package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 13:45
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class Location {
    @XmlElement(name = "Floor", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private long floor;
    @XmlElement(name = "Room", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private String room;
    @XmlElement(name = "Position", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private String position;


    public long getFloor() {
        return floor;
    }

    public void setFloor(long floor) {
        this.floor = floor;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
