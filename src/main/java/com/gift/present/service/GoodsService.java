package com.gift.present.service;

import com.gift.present.config.S3Uploader;
import com.gift.present.dto.goodsdto.GoodsRequestDto;
import com.gift.present.model.Goods;
import com.gift.present.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final S3Uploader s3Uploader;
    private final String goodsImgDirName = "goods";

    private final String defaultImg = "https://tave-present-bucket.s3.ap-northeast-2.amazonaws.com/goods/57d04b24-1648-4aea-ad16-042b3ee0319adefaultProduct.png";


    // 추천 상품 등록하기 메소드
    public void createGoods(MultipartFile goodsPhoto, GoodsRequestDto goodsRequestDto) {
        String goodsPhotoUrl = null;
        if (!goodsPhoto.getOriginalFilename().equals("delete")) {
            try {
                goodsPhotoUrl = s3Uploader.upload(goodsPhoto, goodsImgDirName);
            } catch (Exception e) {
                goodsPhotoUrl = defaultImg;
            }
        } else {
            goodsPhotoUrl = defaultImg;
        }
        Goods goods = new Goods(goodsRequestDto.getGoodsName(), goodsPhotoUrl);
        goodsRepository.save(goods);
    }

    // 추천 상품 삭제하기 메소드
    public void deleteGoods(Long goodsId) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 상품이 존재하지 않습니다.")
        );
        goodsRepository.deleteById(goods.getId());
    }
}
