package com.example.sns.common.service.port;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TimeHolder {
    LocalDateTime nowDateTime();

    LocalDate nowDate();
}
