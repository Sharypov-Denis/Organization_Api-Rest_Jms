package sharypov.OrganizationRestApi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sharypov.OrganizationRestApi.exception.CommonException;
import sharypov.OrganizationRestApi.model.activeMq.OrganizationResponse;
import sharypov.OrganizationRestApi.service.XmlMapperMarshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;


@Service
@Slf4j
public class XmlMapperMarshallerImpl implements XmlMapperMarshaller {
    private final Marshaller marshaller;
    private final String CODINGSTANDART = "UTF-8";

    public XmlMapperMarshallerImpl() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(OrganizationResponse.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, CODINGSTANDART);
    }

    @Override
    public String getXmlStringOrganizationResponse(OrganizationResponse response) {
        String result = "";
        try {
            log.info("");
            StringWriter sw = new StringWriter();
            marshaller.marshal(response, sw);
            result = sw.toString();
            log.info("");
            return result;
        } catch (JAXBException jaxbException) {
            log.error("");
            throw new CommonException(jaxbException.getMessage(), jaxbException);
        }
    }
}
