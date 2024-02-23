package com.reggie.service;

import java.time.LocalDateTime;

public interface ReportService {
    Double getTurnover(LocalDateTime beginTime, LocalDateTime endTime);

}
