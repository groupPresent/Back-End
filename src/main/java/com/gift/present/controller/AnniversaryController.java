package com.gift.present.controller;


import com.gift.present.service.AnniversaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnniversaryController {
    private final AnniversaryService anniversaryService;


}
