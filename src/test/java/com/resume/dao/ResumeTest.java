package com.resume.dao;

import com.resume.dto.ResumeDTO;
import com.resume.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class ResumeTest {
    @Autowired
    private ResumeService service;

//    @AfterEach
    void delete(){
        service.deleteAll();
    }

    @Test
    @DisplayName("insert 테스트")
    void insertTest() throws Exception{
        log.info("service ={}",service);

        ResumeDTO dto = ResumeDTO.builder()
                .userid("rudnf9605")
                .title("성격의 장단점1")
                .contents("하이료오오오오오오1").build();
        int result = service.insertResume(dto);
        Assertions.assertThat(result==1);

    }
    @Test
    @DisplayName("아이디로 조회")
    void findResumeById() throws Exception{
        List<ResumeDTO> dto = service.findResumeById("rudnf9605");
        log.info("rudnf9605 dto ={}",dto);
        Assertions.assertThat(dto).isNotNull();

    }
    @Test
    @DisplayName("Update 테스트 유저 이름 일치")
    void updateSameUserId() throws Exception{
        ResumeDTO dto = ResumeDTO.builder()
                .userid("rudnf9605")
                .title("Insert Test")
                .contents("Junit Insert Test")
                .build();
        Assertions.assertThat(service.insertResume(dto) ==1);
        ResumeDTO resumeDTO = service.testCode(dto);

        ResumeDTO dto2 = ResumeDTO.builder()
                        .resumeno(resumeDTO.getResumeno())
                        .userid(resumeDTO.getUserid())
                        .title("Update Test")
                        .contents("Junit Update Test")
                        .build();
        Assertions.assertThat(service.updateResume(dto2) ==0);


    }
    @Test
    void test() throws Exception{
        ResumeDTO dto = ResumeDTO.builder()
                .resumeno(29)
                .userid("rudnf9605")
                .title("Update Test")
                .contents("Junit Update Test")
                .build();
        service.updateResume(dto);


    }


}
