package com.psp.dummyonelink.controller;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.service.SwiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/swift")
public class SwiftController {


    @Autowired
    private SwiftService swiftService;

    @PostMapping
    public CustomResponseEntity addSwift (@RequestParam("code") String code ){
        return swiftService.createSwift(code);
    }
}
