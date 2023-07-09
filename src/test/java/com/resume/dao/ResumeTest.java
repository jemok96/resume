package com.resume.dao;

import com.resume.dto.SelfIntroDTO;
import com.resume.service.SelfIntroService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class ResumeTest {
    @Autowired
    private SelfIntroService service;

//    @AfterEach
    void delete(){
        service.deleteAll();
    }

    @Test
    @DisplayName("insert 테스트")
    void insertTest() throws Exception{
        log.info("service ={}",service);

        SelfIntroDTO dto = SelfIntroDTO.builder()
                .userid("rudnf9605")
                .title("성격의 장단점1")
                .contents("하이료오오오오오오1").build();
        int result = service.insertResume(dto);
        Assertions.assertThat(result==1);

    }
    @Test
    @DisplayName("아이디로 조회")
    void findResumeById() throws Exception{
        List<SelfIntroDTO> dto = service.findResumeById("rudnf9605");
        log.info("rudnf9605 dto ={}",dto);
        Assertions.assertThat(dto).isNotNull();

    }
    @Test
    @DisplayName("CRUD TEST")
    void updateSameUserId() throws Exception{
        //Insert
        SelfIntroDTO dto = SelfIntroDTO.builder()
                .userid("rudnf9605")
                .title("Insert Test")
                .contents("Junit Insert Test")
                .build();
        Assertions.assertThat(service.insertResume(dto) ==1);
        SelfIntroDTO resumeDTO = service.testCode(dto);

        //update
        SelfIntroDTO dto2 = SelfIntroDTO.builder()
                        .resumeno(resumeDTO.getResumeno())
                        .userid(resumeDTO.getUserid())
                        .title("Update Test")
                        .contents("Junit Update Test")
                        .build();
        service.updateResume(dto2);
        SelfIntroDTO resumeDTO1 = service.testCode(dto2);
        Assertions.assertThat(resumeDTO1.getTitle()).isEqualTo("Update Test");
        //delete
        Assertions.assertThat(service.deleteOne(resumeDTO1.getResumeno()) ==1);
    }




}
