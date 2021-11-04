package com.example.minisite_project.webtoon.service;

import com.example.minisite_project.webtoon.dto.WebtoonDto;
import com.example.minisite_project.webtoon.entity.Webtoon;
import com.example.minisite_project.webtoon.mapper.WebtoonMapper;
import com.example.minisite_project.webtoon.model.WebtoonInput;
import com.example.minisite_project.webtoon.model.WebtoonParam;
import com.example.minisite_project.webtoon.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WebtoonServiceImpl implements WebtoonService {
    
    private final WebtoonRepository webtoonRepository;
    private final WebtoonMapper webtoonMapper;
    
    
    private LocalDate getLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
        }
        
        return null;
    }
    
    @Override
    public boolean add(WebtoonInput parameter) {
        
        LocalDate saleEndDt = getLocalDate(parameter.getSaleEndDtText());

        Webtoon webtoon = Webtoon.builder()
                .categoryId(parameter.getCategoryId())
                .subject(parameter.getSubject())
                .author(parameter.getAuthor())
                .keyword(parameter.getKeyword())
                .summary(parameter.getSummary())
                .contents(parameter.getContents())
                .price(parameter.getPrice())
                .salePrice(parameter.getSalePrice())
                .saleEndDt(saleEndDt)
                .regDt(LocalDateTime.now())
                .filename(parameter.getFilename())
                .urlFilename(parameter.getUrlFilename())
                .build();
        webtoonRepository.save(webtoon);
        
        return true;
    }
    
    @Override
    public boolean set(WebtoonInput parameter) {

        LocalDate saleEndDt = getLocalDate(parameter.getSaleEndDtText());

        Optional<Webtoon> optionalWebtoon = webtoonRepository.findById(parameter.getId());
        if (!optionalWebtoon.isPresent()) {
            //수정할 데이터가 없음
            return false;
        }

        Webtoon webtoon = optionalWebtoon.get();
        webtoon.setCategoryId(parameter.getCategoryId());
        webtoon.setSubject(parameter.getSubject());
        webtoon.setAuthor(parameter.getAuthor());
        webtoon.setKeyword(parameter.getKeyword());
        webtoon.setSummary(parameter.getSummary());
        webtoon.setContents(parameter.getContents());
        webtoon.setPrice(parameter.getPrice());
        webtoon.setSalePrice(parameter.getSalePrice());
        webtoon.setSaleEndDt(saleEndDt);
        webtoon.setUdtDt(LocalDateTime.now());
        webtoon.setFilename(parameter.getFilename());
        webtoon.setUrlFilename(parameter.getUrlFilename());

        webtoonRepository.save(webtoon);

        return true;
    }

    @Override
    public List<WebtoonDto> list(WebtoonParam parameter) {

        long totalCount = webtoonMapper.selectListCount(parameter);

        List<WebtoonDto> list = webtoonMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (WebtoonDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public WebtoonDto getById(long id) {
        return webtoonRepository.findById(id).map(WebtoonDto::of).orElse(null);
    }

    @Override
    public boolean del(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    webtoonRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public List<WebtoonDto> frontList(WebtoonParam parameter) {

        if (parameter.getCategoryId() < 1) {
            List<Webtoon> webtoonList = webtoonRepository.findAll();
            return WebtoonDto.of(webtoonList);
        }

        Optional<List<Webtoon>> optionalWebtoons = webtoonRepository.findByCategoryId(parameter.getCategoryId());
        if (optionalWebtoons.isPresent()) {
            return WebtoonDto.of(optionalWebtoons.get());
        }
        return null;
    }

    @Override
    public WebtoonDto frontDetail(long id) {

        Optional<Webtoon> optionalWebtoon = webtoonRepository.findById(id);
        if (optionalWebtoon.isPresent()) {
            return WebtoonDto.of(optionalWebtoon.get());
        }
        return null;
    }


//
//    @Override
//    public List<WebtoonDto> listAll() {
//
//        List<Course> courseList = courseRepository.findAll();
//
//        return WebtoonDto.of(courseList);
//    }
//
}


























