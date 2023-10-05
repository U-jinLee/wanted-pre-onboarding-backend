package com.wanted.preonboarding.domain.applicant.service;

import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryRequestDto;
import com.wanted.preonboarding.domain.applicant.dto.ApplicationHistoryResponseDto;
import com.wanted.preonboarding.domain.applicant.entity.Applicant;
import com.wanted.preonboarding.domain.applicant.entity.ApplicationHistory;
import com.wanted.preonboarding.domain.applicant.repository.ApplicantRepository;
import com.wanted.preonboarding.domain.applicant.repository.ApplicationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ApplicationHistoryPostService {

    private final ApplicantRepository applicantRepository;
    private final ApplicationHistoryRepository applicationHistoryRepository;

    @Transactional
    public ApplicationHistoryResponseDto postApplicationHistory(long id, ApplicationHistoryRequestDto requestDto) {

        Applicant applicant = applicantRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 지원자가 존재하지 않습니다: " + id));

        ApplicationHistory applicationHistory = applicationHistoryRepository.save(
                ApplicationHistory.builder()
                        .applicant(applicant)
                        .recruitmentId(requestDto.getRecruitmentId())
                        .build()
        );

        return ApplicationHistoryResponseDto.from(applicationHistory);
    }
}