package com.example.minisite_project.admin.notice.service;

import com.example.minisite_project.admin.notice.dto.NoticeDto;
import com.example.minisite_project.admin.notice.entity.Notice;
import com.example.minisite_project.admin.notice.mapper.NoticeMapper;
import com.example.minisite_project.admin.notice.model.NoticeInput;
import com.example.minisite_project.admin.notice.model.NoticeParam;
import com.example.minisite_project.admin.notice.repository.NoticeRepository;
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
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeMapper noticeMapper;

    @Override
    public boolean add(NoticeInput parameter) {

        Notice notice = Notice.builder()
                .title(parameter.getTitle())
                .contents(parameter.getContents())
                .regDt(LocalDateTime.now())
                .build();
        noticeRepository.save(notice);

        return true;
    }

    @Override
    public boolean set(NoticeInput parameter) {
        Optional<Notice> optionalNotice = noticeRepository.findById(parameter.getId());
        if (!optionalNotice.isPresent()) {
            //수정할 데이터가 없음
            return false;
        }

        Notice notice = optionalNotice.get();
        notice.setTitle(parameter.getTitle());
        notice.setContents(parameter.getContents());
        notice.setUdtDt(LocalDateTime.now());

        noticeRepository.save(notice);

        return true;
    }

    @Override
    public List<NoticeDto> list(NoticeParam parameter) {

        long totalCount = noticeMapper.selectListCount(parameter);

        List<NoticeDto> list = noticeMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (NoticeDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public NoticeDto getById(long id) {
        return noticeRepository.findById(id).map(NoticeDto::of).orElse(null);
    }


}
