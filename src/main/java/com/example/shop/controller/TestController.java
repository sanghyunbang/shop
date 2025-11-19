package com.example.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Operation(
            summary = "테스트 엔드포인트",
            description = "루트 경로(/) 호출 시 \"hello\" 문자열을 반환하는 테스트용 API"
    )
    @GetMapping("/")
    public String test(){
        return "hello";
    }
}
