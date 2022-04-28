package sharypov.OrganizationRestApi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sharypov.OrganizationRestApi.service.XmlUtil;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class XmlUtilImpl implements XmlUtil {

    private final XMLInputFactory factory;

    public XmlUtilImpl() {
        factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD,"");
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
    }

    @Override
    public boolean checkXmlHasRoot(String xml, String rootElement) {
        boolean result = false;
        InputStream inputStream = null;
        XMLStreamReader xmlStreamReader = null;
        try {
            inputStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            xmlStreamReader = factory.createXMLStreamReader(inputStream);
            if (xmlStreamReader.hasNext()) {
                xmlStreamReader.next();
                if (xmlStreamReader.isStartElement() &&
                        xmlStreamReader.getLocalName().equals(rootElement)) {
                    result = true;
                    log.info("В XML есть root {}", rootElement);
                }
            }
        }catch (Exception ex) {
            log.error("Ошибка при получении стартового элемента из XML: {}", ex.getMessage());
        }
        return result;
    }
}
