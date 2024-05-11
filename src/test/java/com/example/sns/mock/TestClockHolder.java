package com.example.sns.mock;

import com.example.sns.core.common.service.port.ClockHolder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestClockHolder  implements ClockHolder {
    private final long mills;


    @Override
    public long millis() {
        return mills;
    }
}
