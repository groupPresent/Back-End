package com.gift.present.controller;

import com.gift.present.dto.fundingdto.FundingRequestDto;
import com.gift.present.dto.goodsdto.GoodsRequestDto;
import com.gift.present.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;

    // 추천상품 등록하기
    @PostMapping("/goods")
    public void createGoods(@RequestPart(value = "goodsPhoto") MultipartFile goodsPhoto,
                            @RequestPart(value = "goodsRequestDto", required = false) GoodsRequestDto goodsRequestDto) {
        goodsService.createGoods(goodsPhoto, goodsRequestDto);
    }

    // 추천상품 삭제하기
    @DeleteMapping("/delete/goods/{goodsId}")
    public void deleteGoods(@PathVariable Long goodsId) {
        goodsService.deleteGoods(goodsId);
    }
}
