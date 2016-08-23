package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 10:31
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class LineItemFlags {
    @XmlAttribute(name = "FasttrackFlag")
    private String fastTrackFlag = Constants.N;
    @XmlAttribute(name = "FasttrackReason")
    private String fastTrackReason = "";
    @XmlAttribute(name = "CellbuildFlag")
    private String cellBuildFlag = "";

    public String getCellBuildFlag() {
        return cellBuildFlag;
    }

    public void setCellBuildFlag(String cellBuildFlag) {
        this.cellBuildFlag = cellBuildFlag;
    }

    public String getFastTrackFlag() {
        return fastTrackFlag;
    }

    public void setFastTrackFlag(String fastTrackFlag) {
        this.fastTrackFlag = fastTrackFlag;
    }

    public String getFastTrackReason() {
        return fastTrackReason;
    }

    public void setFastTrackReason(String fastTrackReason) {
        this.fastTrackReason = fastTrackReason;
    }
}
