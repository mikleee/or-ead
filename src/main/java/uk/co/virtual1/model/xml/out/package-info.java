@javax.xml.bind.annotation.XmlSchema(
        namespace = Ns.GLOBAL_NS, elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(namespaceURI = Ns.SERVICE_REQUEST_ORDER_NS, prefix = "sro"),
                @XmlNs(namespaceURI = Ns.EAD_PRODUCT_NS, prefix = "ead"),
                @XmlNs(namespaceURI = Ns.GLOBAL_NS, prefix = "")
        }
)
package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlNs;