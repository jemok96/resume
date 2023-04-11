package com.resume.dto;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class MapperConnection {
    @Test
    void dbConnectionTest() {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://jmsj.cwgb1dyb6cj0.ap-northeast-2.rds.amazonaws.com:3306/jmsj?characterEncoding=UTF-8&serverTimezone=UTC",
                    "admin",
                    "gkdlfn12");
            log.info("con={}", con);
        }
        catch (Exception e){
            log.error("error ",e);
        }

    }
}
