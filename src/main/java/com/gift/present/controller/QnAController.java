package com.gift.present.controller;

import com.gift.present.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QnAController {
    private final QnAService qnAService;

}
