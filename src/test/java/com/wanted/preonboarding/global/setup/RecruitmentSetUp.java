package com.wanted.preonboarding.global.setup;

import com.wanted.preonboarding.domain.recruitment.entity.Recruitment;
import com.wanted.preonboarding.domain.recruitment.repository.RecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class RecruitmentSetUp {

    @Autowired
    RecruitmentRepository recruitmentRepository;

    public Recruitment save() {
        final Recruitment recruitment = buildRecruitment(0);
        return recruitmentRepository.save(recruitment);
    }

    private Recruitment buildRecruitment(long companyId) {
        return Recruitment.builder()
                .companyId(companyId)
                .position("백엔드 주니어 개발자")
                .reward(1000000)
                .description("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .skill("Python")
                .build();
    }

}