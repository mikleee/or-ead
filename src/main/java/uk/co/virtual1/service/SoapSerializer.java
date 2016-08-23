package uk.co.virtual1.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.xml.TransformerUtils;
import org.w3c.dom.Document;
import uk.co.virtual1.exception.SerializationException;
import uk.co.virtual1.model.xml.out.PurchaseOrder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mikhail Tkachenko created on 08.04.16 13:40
 */
@Service
public class SoapSerializer {
    private final static Logger LOGGER = Logger.getLogger(SoapSerializer.class);

    private static final String UTF_8 = "UTF-8";

    private JAXBContext jaxbContext;

    public SoapSerializer() {
        try {
            jaxbContext = JAXBContext.newInstance(
                    PurchaseOrder.class
            );
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private SOAPMessage readSOAPMessage(String xml) throws IOException, SOAPException {
        MessageFactory factory = MessageFactory.newInstance();
        xml = xml.replaceAll(Pattern.quote("<soap:Envelope>"), Matcher.quoteReplacement("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"));
        ByteArrayInputStream bis = new ByteArrayInputStream(xml.getBytes("utf8"));
        return factory.createMessage(null, bis);
    }

    public Object deserialize(String xml) throws SerializationException {
        try {
            StringReader stringReader = new StringReader(xml);
            Object object = jaxbContext.createUnmarshaller().unmarshal(stringReader);
            if (object instanceof JAXBElement) {
                object = ((JAXBElement<Object>) object).getValue();
            }
            return object;
        } catch (JAXBException e) {
            throw new SerializationException(e);
        }
    }

    public String serialize(Object o) throws SerializationException {
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, UTF_8);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            marshaller.marshal(o, stream);
            return new String(stream.toByteArray());
        } catch (JAXBException e) {
            throw new SerializationException(e);
        }
    }

    private DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        return dbf.newDocumentBuilder();
    }

    private Transformer createTransformer() throws ParserConfigurationException, TransformerConfigurationException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        TransformerUtils.enableIndenting(transformer);
        return transformer;
    }

    public String pretty(String src) {
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(src.getBytes());
            Document document = createDocumentBuilder().parse(is);

            Transformer transformer = createTransformer();
            DOMSource source = new DOMSource();
            source.setNode(document.getFirstChild());

            StreamResult result = new StreamResult(new ByteArrayOutputStream());
            transformer.transform(source, result);

            return result.getOutputStream().toString();
        } catch (Exception e) {
            LOGGER.warn("Cant transform xml document to pretty format, initial message will be returned. Details: " + e.getMessage());
            return src;
        }
    }

    private String raw(String src) {
        return src.replaceAll(">\\s*\\n*\\s<", "><");
    }

}
