package uk.co.virtual1.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.virtual1.exception.ProvisioningException;
import uk.co.virtual1.model.xml.out.OrderDetail;
import uk.co.virtual1.salesforcebox.SalesforceConstants;
import uk.co.virtual1.salesforcebox.object.Access;
import uk.co.virtual1.salesforcebox.object.Case;
import uk.co.virtual1.salesforcebox.object.PricingEntry;
import uk.co.virtual1.service.ApplicationEnvironment;
import uk.co.virtual1.service.EnvironmentKey;
import uk.co.virtual1.service.FtlTemplateService;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mikhail Tkachenko created on 23.08.16 15:26
 */
@Service
public class MessageFactoryUtils {
    @Autowired
    private FtlTemplateService ftlTemplateService;
    @Autowired
    private ApplicationEnvironment environment;


    XMLGregorianCalendar calendar() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        return calendar(gregorianCalendar);
    }

    /**
     * adds working days to date
     */
    XMLGregorianCalendar calendar(Date date, int workingDays) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        while (workingDays > 0) {
            gregorianCalendar.add(Calendar.DATE, 1);
            int weekDay = gregorianCalendar.get(Calendar.DAY_OF_WEEK);
            if (weekDay != Calendar.SUNDAY && weekDay != Calendar.SATURDAY) {
                workingDays--;
            }
        }
        return calendar(gregorianCalendar);
    }

    XMLGregorianCalendar requestedDeliveryDate(Case sfCase) {
        Integer days = environment.getInt(EnvironmentKey.VIRTUAL1_NUMBER_OF_WORKING_DAYS); // TODO: 26.08.16  
        return calendar(sfCase.getOrderReceived(), days);
    }

    private XMLGregorianCalendar calendar(GregorianCalendar gregorianCalendar) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException();
        }
    }

    String buyerRefNum(Case sfCase) {
        return sfCase.getCaseNumber() + "-" + sfCase.getAccess().getName();
    }

    // TODO: 25.08.16
    String circuitInterfaceType(Access access) {
        String interfaceType = access.getCarrierInterfaceBEnd();
        String bearerSpeed = access.getBearerSpeed();

        if ("Ethernet RJ45".equals(interfaceType) && "30mb".equals(bearerSpeed)) {
            return "100Base - T (RJ-45)";
        } else if ("Ethernet RJ45".equals(interfaceType) && "100mb".equals(bearerSpeed)) {
            return "100Base - T (RJ-45)";
        } else if ("LC Single mode".equals(interfaceType) && "1000mb".equals(bearerSpeed)) {
            return "1000Base-LX(SMF)";
        } else {
            return "100Base - T (RJ-45)"; //todo value need to be clarified
        }
    }

    BigDecimal unitPrice(Access access) throws ProvisioningException {
        for (PricingEntry pricingEntry : access.getPricingEntryList()) {
            if (SalesforceConstants.BILLING_FREQUENCY_QUARTERLY.equals(pricingEntry.getBillingFrequency())) {
                return pricingEntry.getAmount();
            }
        }
        throw new ProvisioningException("The access dont contain pricing entry with billing frequency" + SalesforceConstants.BILLING_FREQUENCY_QUARTERLY);
    }

    BigDecimal totalAmount(List<OrderDetail> orderDetails) {
        BigDecimal result = BigDecimal.ZERO;
        for (OrderDetail orderDetail : orderDetails) {
            BigDecimal price = orderDetail.getBuyerExpectedUnitPrice().getPrice().getUnitPrice();
            if (price != null) {
                result = result.add(price);
            }
        }
        return result;
    }

    String generalNote(Case sfCase) {
        Map<String, Object> params = new HashMap<>();

        params.put("virtual1ContactName", environment.get(EnvironmentKey.VIRTUAL1_CONTACT_NAME));
        params.put("virtual1ContactPhone", environment.get(EnvironmentKey.VIRTUAL1_CONTACT_PHONE));
        params.put("popPort", "ge-2/0/7"); //todo value need to be clarified
        params.put("exchange", "LLUC90004048"); //todo value need to be clarified
        params.put("floor", "3rd Floor"); //todo value need to be clarified
        params.put("room", "381 / 382"); //todo value need to be clarified
        params.put("rack", "432"); //todo value need to be clarified
        params.put("contactName", sfCase.getContact().getName());
        params.put("contactPhone", sfCase.getContact().getTelephone());

        return ftlTemplateService.process("general-note.ftl", params).replaceAll("\\r+\\n", " ");
    }

}
