package com.example.cinephonia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class filmRESTController {
    @Autowired
    filmService filmService;
}
