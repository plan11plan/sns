package com.example.sns.common.infrstructure;

import com.example.sns.common.service.port.ClockHolder;
import java.time.Clock;
import org.springframework.stereotype.Component;

@Component
public class SystemClockHolder implements ClockHolder {

    @Override
    public long millis() {
        return Clock.systemUTC().millis();
    }
}
