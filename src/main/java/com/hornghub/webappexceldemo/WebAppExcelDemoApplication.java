package com.hornghub.webappexceldemo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * WebAppExcelDemoApplication - Launcher
 *
 * @author stevenhorng
 * @date 2022/11/10
 */

@Slf4j
@SpringBootApplication(scanBasePackages = "com.hornghub.webappexceldemo")
@MapperScan("com.hornghub.webappexceldemo.mapper")
public class WebAppExcelDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAppExcelDemoApplication.class, args);

        log.info("Project Launch!");
    }

}
