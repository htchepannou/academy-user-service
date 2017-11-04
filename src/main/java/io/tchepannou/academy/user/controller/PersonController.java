package io.tchepannou.academy.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.tchepannou.academy.user.client.ErrorResponse;
import io.tchepannou.academy.user.client.auth.AuthResponse;
import io.tchepannou.academy.user.client.person.PersonResponse;
import io.tchepannou.academy.user.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/academy/v1/persons", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/academy/v1/person", description = "Persons")
public class PersonController extends BaseController {

    @Autowired
    private PersonService service;

    @RequestMapping(value="/roles/{roleId}", method = RequestMethod.GET)
    @ApiOperation(value = "findByRole")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = AuthResponse.class),
            @ApiResponse(code = 404, message = "NotFound", response = ErrorResponse.class),
    })
    public PersonResponse findByRole(@PathVariable Integer roleId){
        return init(service.findByRole(roleId));
    }
}
