package com.ecommerce.apigateway.testcontroller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/test")
public class TestingController {




    @GetMapping(value = "")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public List<TestData> testGateWay(){
        List<TestData>  data = List.of( new TestData(UUID.randomUUID(), "Testing"));
        return data;
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "http://localhost:8080";
    }
    @Data
    @AllArgsConstructor
    static  class  TestData {
        UUID id;
        String description;
    }
}
