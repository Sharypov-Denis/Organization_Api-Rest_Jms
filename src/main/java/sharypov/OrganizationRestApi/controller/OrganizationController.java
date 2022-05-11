package sharypov.OrganizationRestApi.controller;

//import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sharypov.OrganizationRestApi.dao.OrganizationDao;
import sharypov.OrganizationRestApi.model.error.RestError;
import sharypov.OrganizationRestApi.model.Organization;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * REST API для получения информации об организации, обновлении, добавлении и удаления(CRUD)
 */
@Validated
@Slf4j
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationController extends RestError {

    @Autowired
    // @Qualifier("Hibernate")
    @Qualifier("EntityManager")
    private OrganizationDao organizationDao;

    @ApiOperation(value = "Возвращает список всех организаций", notes = "В результате возвращается массив JSON-объектов с информацией")
    @GetMapping("/organizations")
    @ResponseStatus(HttpStatus.OK)
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAll();
    }

    @ApiOperation(value = "Возвращает организацию по ID", notes = "В результате возвращается массив JSON-объектов с информацией")
    @GetMapping(value = "/organization/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Organization getOrganizationById(@ApiParam(
            name = "Id",
            type = "Long",
            value = "Id организации",
            example = "100",
            required = true) @PathVariable("Id") Long id) {
        return organizationDao.getById(id);
    }

    @ApiOperation(value = "Возвращает организацию по ИНН", notes = "В результате возвращается массив JSON-объектов с информацией")
    @GetMapping("/organization/inn/{Inn}")
    public @ResponseBody
    ResponseEntity<?> getOrganizationByInn(@ApiParam(
            name = "Inn",
            type = "String",
            value = "ИНН организации",
            example = "01010166",
            required = true)@PathVariable("Inn") String orgInn) {
        try {
            Organization org = organizationDao.getByInn(orgInn);
            return new ResponseEntity<Organization>(org, null, HttpStatus.OK);
        } catch (Exception ex) {
            log.info("Такого INN не найдено");
            RestError org = new RestError("такого инн нет");
            // Organization org = new Organization("такого инн нет", "такого инн нет");
            return new ResponseEntity<RestError>(org, null, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Создание новое организации", notes = "В результате возвращается массив JSON-объектов с информацией")
    @PostMapping("/organization")
    @ResponseStatus(HttpStatus.CREATED)
    public Organization createOrganization(@RequestBody Organization organization) {
        return organizationDao.create(organization);
    }

    @ApiOperation(value = "Обновляет организацию. Для обновления необходимо указать ID организации", notes = "В результате возвращается массив JSON-объектов с информацией")
    @PutMapping("/organization/{OrganizationId}")
    public Organization updateOrganization(@PathVariable("OrganizationId") Long id,
                                           @RequestBody Organization organization) {
        return organizationDao.update(id, organization);
    }

    @ApiOperation(value = "Удаляет организацию по ID")
    @DeleteMapping("/organization/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("Id") Long id) {
        organizationDao.delete(id);
    }
}
