package com.onion.test.java.lang.annotation;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.security.RolesAllowed;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiImplicitParams({
    @ApiImplicitParam(paramType = "header", name = "a", required = true, value = "token", dataType = "String", defaultValue = "a1"),
    @ApiImplicitParam(paramType = "header", name = "b", required = true, value = "id", dataType = "String", defaultValue = "b1"),
    @ApiImplicitParam(paramType = "header", name = "c", value = "", dataType = "String")})
@RequestMapping(value = "/{path1}/path2", method = RequestMethod.GET)
@ApiResponses(value = {@ApiResponse(code = 401, message = "abc", response = Void.class)})
@RolesAllowed("ROLE_COMMON_USER")
public @interface Simple {
}
