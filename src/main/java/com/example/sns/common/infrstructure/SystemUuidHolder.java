package com.example.sns.common.infrstructure;

import com.example.sns.common.service.port.UuidHolder;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class SystemUuidHolder implements UuidHolder {

    @Override
    public String random() {
        return UUID.randomUUID().toString();
    }
}
