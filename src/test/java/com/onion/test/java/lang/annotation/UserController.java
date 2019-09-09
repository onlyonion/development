package com.onion.test.java.lang.annotation;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserController {

    @ApiOperation(value = "佣金提取记录.")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", name = "a", required = true, value = "token", dataType = "String", defaultValue ="a1"),
        @ApiImplicitParam(paramType = "header", name = "b", required = true, value = "id", dataType = "String", defaultValue = "b1"),
        @ApiImplicitParam(paramType = "header", name = "c", value = "", dataType = "String") })
    @RequestMapping(value="/{path1}/path2", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 401, message = "abc", response = Void.class) })
    @RolesAllowed("ROLE_COMMON_USER")
    public void index(){
    }

}
