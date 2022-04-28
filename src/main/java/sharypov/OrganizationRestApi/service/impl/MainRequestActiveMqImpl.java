package sharypov.OrganizationRestApi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sharypov.OrganizationRestApi.dao.OrganizationDao;
import sharypov.OrganizationRestApi.model.Organization;
import sharypov.OrganizationRestApi.model.OrganizationRequest;
import sharypov.OrganizationRestApi.model.OrganizationResponse;
import sharypov.OrganizationRestApi.sender.ActiveMqSender;

@Service
@Slf4j
public class MainRequestActiveMqImpl {
    @Autowired
    XmlMapperUnmarshallerImpl mapperUnmarshaller;
    @Autowired
    XmlMapperMarshallerImpl mapperMarshaller;
    @Autowired
    ActiveMqSender sender;
    @Autowired
    DtoMapperImpl dtoMapper;
    @Autowired
    @Qualifier("EntityManager")
    OrganizationDao dao;

    public void start(String xmlMessage) {
        OrganizationRequest request = mapperUnmarshaller.getModelOrganizationRequest(xmlMessage);
        log.info(request.toString());

        Organization organization = dao.getByInn(request.getOrgInn());
        log.info(organization.toString());

        OrganizationResponse response = dtoMapper.getOrganizationResponseFromOrganization(organization);
        log.info(response.toString());

        String xmlResponse = mapperMarshaller.getXmlStringOrganizationResponse(response);

        sender.send(xmlResponse);

    }
}
