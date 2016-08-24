package uk.co.virtual1.factory;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.virtual1.exception.ProvisioningException;
import uk.co.virtual1.model.xml.out.OrderDetail;
import uk.co.virtual1.salesforce.SalesforceConstants;
import uk.co.virtual1.salesforce.object.Access;
import uk.co.virtual1.salesforce.object.Case;
import uk.co.virtual1.salesforce.object.PricingEntry;
import uk.co.virtual1.salesforce.object.VLAN;
import uk.co.virtual1.service.FtlTemplateService;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Mikhail Tkachenko created on 23.08.16 15:26
 */
@Service
public class MessageFactoryUtils {
    @Autowired
    private FtlTemplateService ftlTemplateService;


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

        for (VLAN vlan : sfCase.getAccess().getVlans()) {
            if (StringUtils.isNotBlank(vlan.getRelatedLANPort())) {
                params.put("port", vlan.getRelatedLANPort());
                break;
            }
        }

        return ftlTemplateService.process("general-note.ftl", params).replaceAll("\\r+\\n", "");
    }

}
