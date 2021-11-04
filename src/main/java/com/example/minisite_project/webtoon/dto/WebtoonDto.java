package com.example.minisite_project.webtoon.dto;

import com.example.minisite_project.webtoon.entity.Webtoon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WebtoonDto {
    
    Long id;
    long categoryId;
    String imagePath;
    String keyword;
    String subject;
    String author;
    String summary;
    String contents;
    long price;
    long salePrice;
    LocalDate saleEndDt;
    LocalDateTime regDt;//등록일(추가날짜)
    LocalDateTime udtDt;//수정일(수정날짜)
    
    String filename;
    String urlFilename;
    
    //추가컬럼
    long totalCount;
    long seq;
    
    public static WebtoonDto of(Webtoon webtoon) {
        return WebtoonDto.builder()
                .id(webtoon.getId())
                .categoryId(webtoon.getCategoryId())
                .imagePath(webtoon.getImagePath())
                .keyword(webtoon.getKeyword())
                .subject(webtoon.getSubject())
                .author(webtoon.getAuthor())
                .summary(webtoon.getSummary())
                .contents(webtoon.getContents())
                .price(webtoon.getPrice())
                .salePrice(webtoon.getSalePrice())
                .saleEndDt(webtoon.getSaleEndDt())
                .regDt(webtoon.getRegDt())
                .udtDt(webtoon.getUdtDt())
                .filename(webtoon.getFilename())
                .urlFilename(webtoon.getUrlFilename())
                .build();
    }
    
    public static List<WebtoonDto> of(List<Webtoon> webtoons) {
        
        if (webtoons == null) {
            return null;
        }
    
        List<WebtoonDto> webtoonList = new ArrayList<>();
        for(Webtoon x : webtoons) {
            webtoonList.add(WebtoonDto.of(x));
        }
        return webtoonList;
        
        /*
        if (webtoons != null) {
            List<webtoonDto> webtoonList = new ArrayList<>();
            for(webtoon x : webtoons) {
                webtoonList.add(webtoonDto.of(x));
            }
            return webtoonList;
        }
        return null;
        */
        
    }
    
}

















