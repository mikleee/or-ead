package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikhail Tkachenko created on 22.08.16 10:31
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class SupplierPartyDetails {
    @XmlElement(name = "ListOfIdentifier")
    private List<Identifier> listOfIdentifier = new ArrayList<>();

    public List<Identifier> getListOfIdentifier() {
        return listOfIdentifier;
    }

    public void setListOfIdentifier(List<Identifier> listOfIdentifier) {
        this.listOfIdentifier = listOfIdentifier;
    }

}
