package io.tchepannou.academy.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.tchepannou.academy.user.dto.ErrorResponse;
import io.tchepannou.academy.user.dto.auth.AuthRequest;
import io.tchepannou.academy.user.dto.auth.AuthResponse;
import io.tchepannou.academy.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = AuthResponse.class),
            @ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
            @ApiResponse(code = 409, message = "AuthenticationFailed", response = ErrorResponse.class),
    })
    public AuthResponse login(@RequestBody @Valid final AuthRequest request){
        return init(authService.login(request));
    }

    @RequestMapping(value="/access_token/{accessToken}", method = RequestMethod.GET)
    @ApiOperation(value = "validate", notes = "Validate an accessToken")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = AuthResponse.class),
            @ApiResponse(code = 404, message = "NotFound", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
    })
    public AuthResponse findByAccessToken(@PathVariable  String accessToken) {
        return init(authService.validate(accessToken));
    }
}
