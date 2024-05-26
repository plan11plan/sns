package com.example.sns.common.infrstructure;

import com.example.sns.common.service.port.TimeHolder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class SystemTimeHolder implements TimeHolder {

    public LocalDateTime nowDateTime(){
        return LocalDateTime.now();
    }
    public LocalDate nowDate(){
        return LocalDate.now();
    }
}
