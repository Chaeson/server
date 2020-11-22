package com.cjw.server.controller;

import com.cjw.server.repository.SalesRepository;
import com.cjw.server.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
@Controller
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping("/")
    @PostMapping("/")
    public JSONObject getStoreLocation( HttpServletRequest request
                                        , HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        salesService.getFindAll();
        return jsonObject;
    }
}
