package sharypov.OrganizationRestApi.controller;

//import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sharypov.OrganizationRestApi.dao.OrganizationDao;
import sharypov.OrganizationRestApi.model.RestError;
import sharypov.OrganizationRestApi.model.Organization;

import java.util.List;

/**
 * REST API для получения информации об организации, обновлении, добавлении и удаления(CRUD)
 */
@Slf4j
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller extends RestError {

    @Autowired
    @Qualifier("Hibernate")
    private OrganizationDao organizationDao;

    @GetMapping("/organizations")
    //@ApiOperation(value = "Возвращает список всех организаций и их ИНН", notes = "В результате возвращается массив JSON-объектов с информацией")
    @ResponseStatus(HttpStatus.OK)
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAll();
    }

    @GetMapping("/organization/{Id}")
    @ResponseStatus(HttpStatus.OK)
    public Organization getOrganizationById(@PathVariable("Id") Long id) {
        return organizationDao.getById(id);
    }

    @GetMapping("/organization/inn/{Inn}")
    public @ResponseBody ResponseEntity<?> getOrganizationByInn(@PathVariable("Inn") String orgInn) {
        try {
            Organization org =  organizationDao.getByInn(orgInn);
            return new ResponseEntity<Organization>(org, null,HttpStatus.OK);
        } catch (Exception ex) {
            log.info("Такого INN не найдено");
            RestError org = new RestError("такого инн нет");
           // Organization org = new Organization("такого инн нет", "такого инн нет");
            return new ResponseEntity<RestError>(org, null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/organization")
    @ResponseStatus(HttpStatus.CREATED)
    public Organization createOrganization(@RequestBody Organization organization) {
        return organizationDao.create(organization);
    }

    @PutMapping("/organization/{OrganizationId}")
    public Organization updateOrganization(@PathVariable("OrganizationId") Long id,
                                           @RequestBody Organization organization) {
        return organizationDao.update(id, organization);
    }

    @DeleteMapping("/organization/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("Id") Long id) {
        organizationDao.delete(id);
    }
}
