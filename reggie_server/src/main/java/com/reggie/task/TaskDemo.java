package com.reggie.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: ChenXW
 * @Date:2024/2/22 20:09
 * @Description:
 **/

@Component
@Slf4j
public class TaskDemo {

    // @Scheduled(cron = "0/5 * * * * ?")
    public void task() {
        log.info("任务触发" + new Date());
    }

}
