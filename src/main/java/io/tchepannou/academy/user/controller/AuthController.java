package io.tchepannou.academy.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.tchepannou.academy.user.dto.auth.AuthRequest;
import io.tchepannou.academy.user.dto.auth.AuthResponse;
import io.tchepannou.academy.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/academy/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/academy/v1/auth", description = "Authentication")
public class AuthController extends BaseController {

    @Autowired
    private AuthService authService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "login", notes = "Authenticate a user")
    public AuthResponse login(@RequestBody @Valid final AuthRequest request){
        return init(authService.login(request));
    }


}
