package com.example.sns.mock;

import com.example.sns.common.service.port.TimeHolder;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestTimeHolder implements TimeHolder {
    private final LocalDateTime fixedDateTime;

    public TestTimeHolder(LocalDateTime fixedDateTime) {
        this.fixedDateTime = fixedDateTime;
    }

    @Override
    public LocalDateTime nowDateTime() {
        return fixedDateTime;
    }

    @Override
    public LocalDate nowDate() {
        return fixedDateTime.toLocalDate();
    }
}