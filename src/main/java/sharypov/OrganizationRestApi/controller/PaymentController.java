package sharypov.OrganizationRestApi.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sharypov.OrganizationRestApi.model.dto.PaymentDto;
import sharypov.OrganizationRestApi.service.impl.PaymentServiceImpl;

import javax.validation.Valid;

/**
 * Контроллер для осуществление платежей
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/check", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

    @Autowired
    PaymentServiceImpl service;

    @ApiOperation(value = "Запрос на осуществление платежа", notes = "В результате возвращается массив JSON-объектов с информацией о совершенном платеже или с отказом в совершении платежа")
    @PostMapping("/payment")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDto createCheckPayment(@RequestBody PaymentDto paymentDto) {
        return service.transfer(paymentDto);
    }
}
