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
//    private final TakeCourseRepository takeCourseRepository;
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
//
//    @Override
//    public List<WebtoonDto> frontList(CourseParam parameter) {
//
//        if (parameter.getCategoryId() < 1) {
//            List<Course> courseList = courseRepository.findAll();
//            return WebtoonDto.of(courseList);
//        }
//
//        Optional<List<Course>> optionalCourses = courseRepository.findByCategoryId(parameter.getCategoryId());
//        if (optionalCourses.isPresent()) {
//            return WebtoonDto.of(optionalCourses.get());
//        }
//        return null;
//    }
//
//    @Override
//    public WebtoonDto frontDetail(long id) {
//
//        Optional<Course> optionalCourse = courseRepository.findById(id);
//        if (optionalCourse.isPresent()) {
//            return WebtoonDto.of(optionalCourse.get());
//        }
//        return null;
//    }
//
//    /**
//     * 수강신청
//     */
//    @Override
//    public ServiceResult req(TakeCourseInput parameter) {
//
//        ServiceResult result = new ServiceResult();
//
//        Optional<Course> optionalCourse = courseRepository.findById(parameter.getCourseId());
//        if (!optionalCourse.isPresent()) {
//            result.setResult(false);
//            result.setMessage("강좌 정보가 존재하지 않습니다.");
//            return result;
//        }
//
//        Course course = optionalCourse.get();
//
//        //이미 신청정보가 있는지 확인
//        String[] statusList = {TakeCourse.STATUS_REQ, TakeCourse.STATUS_COMPLETE};
//        long count = takeCourseRepository.countByCourseIdAndUserIdAndStatusIn(course.getId(), parameter.getUserId(), Arrays.asList(statusList));
//
//        if (count > 0) {
//            result.setResult(false);
//            result.setMessage("이미 신청한 강좌 정보가 존재합니다.");
//            return result;
//        }
//
//        TakeCourse takeCourse = TakeCourse.builder()
//                .courseId(course.getId())
//                .userId(parameter.getUserId())
//                .payPrice(course.getSalePrice())
//                .regDt(LocalDateTime.now())
//                .status(TakeCourse.STATUS_REQ)
//                .build();
//        takeCourseRepository.save(takeCourse);
//
//        result.setResult(true);
//        result.setMessage("");
//        return result;
//    }
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


























