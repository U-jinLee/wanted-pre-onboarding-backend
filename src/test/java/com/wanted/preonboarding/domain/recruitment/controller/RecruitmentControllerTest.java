package com.wanted.preonboarding.domain.recruitment.controller;

import com.wanted.preonboarding.domain.recruitment.dto.RecruitmentPostRequestDto;
import com.wanted.preonboarding.global.setup.IntegrationTest;
import com.wanted.preonboarding.global.setup.RecruitmentSetUp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RecruitmentControllerTest extends IntegrationTest {

    @Autowired
    RecruitmentSetUp recruitmentSetUp;

    @Test
    void 채용공고_등록_성공() throws Exception {

        //given
        Long companyId = 0L;
        String position = "백엔드 주니어 개발자";
        Integer reward = 1000000;
        String description = "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..";
        String skill = "Python";

        RecruitmentPostRequestDto request =
                RecruitmentPostRequestDto.of(companyId, position, reward, description, skill);

        //when
        ResultActions resultActions = requestRecruitmentPost(request);


        //then
        resultActions
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("companyId").value(companyId))
                .andExpect(jsonPath("position").value(position))
                .andExpect(jsonPath("reward").value(reward))
                .andExpect(jsonPath("description").value(description))
                .andExpect(jsonPath("skill").value(skill));
    }

    private ResultActions requestRecruitmentPost(RecruitmentPostRequestDto request) throws Exception {
        return mvc.perform(post("/api/recruitments")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));
    }


}