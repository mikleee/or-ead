package uk.co.virtual1.factory;

import org.springframework.stereotype.Service;
import uk.co.virtual1.salesforce.object.Access;
import uk.co.virtual1.salesforce.object.Case;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Mikhail Tkachenko created on 23.08.16 15:26
 */
@Service
public class MessageFactoryUtils {



    XMLGregorianCalendar calendar() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        return calendar(gregorianCalendar);
    }

    XMLGregorianCalendar calendar(Date date) {
        return calendar(date, 0);
    }

    XMLGregorianCalendar calendar(Date date, int days) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.add(Calendar.DATE, days);
        return calendar(gregorianCalendar);
    }

    private XMLGregorianCalendar calendar(GregorianCalendar gregorianCalendar) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException();
        }
    }

    String buyerRefNum(Case aCase) {
        return aCase.getCaseNumber() + "-" + aCase.getAccess().getName();
    }


    String circuitInterfaceType(Access access) {
        String interfaceType = access.getCarrierInterfaceBEnd();
        String bearerSpeed = access.getBearerSpeed();

        if ("Ethernet RJ45".equals(interfaceType) && "30mb".equals(bearerSpeed)) {
            return "100 Base - T (RJ-45)";
        } else if ("Ethernet RJ45".equals(interfaceType) && "100mb".equals(bearerSpeed)) {
            return "100 Base - T (RJ-45)";
        } else if ("LC Single mode".equals(interfaceType) && "1000mb".equals(bearerSpeed)) {
            return "1000 Base-LX(SMF)";
        } else {
            return null;
        }
    }

}
