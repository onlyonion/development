package com.onion.test.java.lang.annotation;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.beans.Transient;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import javax.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class Test {

    @ApiOperation(value = "")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", name = "a", required = true, value = "token", dataType = "String", defaultValue = "a1"),
        @ApiImplicitParam(paramType = "header", name = "b", required = true, value = "id", dataType = "String", defaultValue = "b1"),
        @ApiImplicitParam(paramType = "header", name = "c", value = "", dataType = "String")})
    @RequestMapping(value = "/{path1}/path2", method = RequestMethod.GET)
    @ApiResponses(value = {@ApiResponse(code = 401, message = "abc", response = Void.class)})
    @RolesAllowed("ROLE_COMMON_USER")
    public void index() {
    }


    @Simple
    public void list() {
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method index = Test.class.getMethod("index");

        Arrays.stream(index.getAnnotations()).forEach(annotation -> System.out.println(JSON.toJSONString(annotation.toString())));

        Method list = Test.class.getMethod("list");
        Arrays.stream(list.getAnnotations()).forEach(annotation -> System.out.println(JSON.toJSONString(annotation.toString())));

        Method date1 = Test.class.getMethod("date1");

        Arrays.stream(date1.getAnnotations()).forEach(annotation -> System.out.println(JSON.toJSONString(annotation.toString())));

        Method date2 = Test.class.getMethod("date2");

        Arrays.stream(date2.getAnnotations()).forEach(annotation -> System.out.println(JSON.toJSONString(annotation.toString())));

    }

    @Transient
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime date1() {
        return LocalDateTime.now();
    }

    @TransientLocalDateTime
    public LocalDateTime date2() {
        return LocalDateTime.now();
    }


}
