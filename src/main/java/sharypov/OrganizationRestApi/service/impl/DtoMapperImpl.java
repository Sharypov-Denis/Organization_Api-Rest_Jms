package sharypov.OrganizationRestApi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sharypov.OrganizationRestApi.model.Organization;
import sharypov.OrganizationRestApi.model.activeMq.OrganizationResponse;
import sharypov.OrganizationRestApi.service.DtoMapper;


@Service
@Slf4j
public class DtoMapperImpl implements DtoMapper {

    public OrganizationResponse getOrganizationResponseFromOrganization(Organization organization) {
        OrganizationResponse response = OrganizationResponse.builder()
                .name(organization.getName())
                .orgInn(organization.getInn())
                .build();
        return response;
    }
}
