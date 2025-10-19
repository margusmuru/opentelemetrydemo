package com.margusmuru.opentelemetrydemo.service;

import com.margusmuru.opentelemetrydemo.persistence.SomeDataEntity;
import com.margusmuru.opentelemetrydemo.persistence.SomeDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataGeneratorService {
    private final SomeDataRepository someDataRepository;
    private final String LOREM_1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
            "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco " +
            "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit " +
            "esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa " +
            "qui officia deserunt mollit anim id est laborum.";

    public String generateData(Long count){
        try {
            Thread.sleep(500L);
            StringBuilder sb = new StringBuilder();
            for (long i = 0; i < count; i++) {
                sb.append(LOREM_1);
                log.debug(LOREM_1);
            }
            log.debug(sb.toString());
            return sb.toString();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String writeDb(Long count) {
        // Anyone doing this in production should be fired!
        for (long i = 0; i < count; i++) {
            SomeDataEntity someDataEntity = SomeDataEntity.builder().myData(i + "").build();
            someDataRepository.save(someDataEntity);
        }
        return null;
    }
}
