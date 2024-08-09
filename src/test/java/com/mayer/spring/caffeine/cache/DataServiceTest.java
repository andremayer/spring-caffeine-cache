package com.mayer.spring.caffeine.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mayer.spring.caffeine.cache.service.DataService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DataServiceTest {

    @Autowired
    private DataService dataService;

    @Test
    public void testCacheIsUsed() {
        String key = "testKey";

        long startTime = System.currentTimeMillis();
        String firstCall = dataService.getData(key);
        long firstCallDuration = System.currentTimeMillis() - startTime;

        assertThat(firstCallDuration).isGreaterThanOrEqualTo(3000); //should be slower, since first call is not yet on cache!
        assertThat(firstCall).isEqualTo("Data for testKey");

        startTime = System.currentTimeMillis();
        String secondCall = dataService.getData(key);
        long secondCallDuration = System.currentTimeMillis() - startTime;

        assertThat(secondCallDuration).isLessThan(100); //should be faster, because it should hit the cache!
        assertThat(secondCall).isEqualTo("Data for testKey");
    }
}
