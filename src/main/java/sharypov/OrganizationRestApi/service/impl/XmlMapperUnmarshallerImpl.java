package sharypov.OrganizationRestApi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sharypov.OrganizationRestApi.exception.CommonException;
import sharypov.OrganizationRestApi.model.activeMq.OrganizationRequest;
import sharypov.OrganizationRestApi.service.XmlMapperUnmarshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Slf4j
@Service
public class XmlMapperUnmarshallerImpl implements XmlMapperUnmarshaller {

    private final Unmarshaller unmarshaller;

    public XmlMapperUnmarshallerImpl() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(OrganizationRequest.class);
        unmarshaller = context.createUnmarshaller();
    }

    @Override
    public OrganizationRequest getModelOrganizationRequest(String xmlMessage) {
        OrganizationRequest organizationRequest;
        try {
            StringReader reader = new StringReader(xmlMessage);
            organizationRequest = (OrganizationRequest) unmarshaller.unmarshal(reader);
        } catch (JAXBException jaxbException) {
            throw new CommonException(jaxbException.getMessage(), jaxbException);
        }
        return organizationRequest;
    }
}
