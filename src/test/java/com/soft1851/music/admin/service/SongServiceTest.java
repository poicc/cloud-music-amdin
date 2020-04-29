package com.soft1851.music.admin.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
class SongServiceTest {

    @Resource
    private SongService songService;

    @Test
    void selectAll() {
        songService.selectAll();
    }

    @Test
    void exportData() throws IOException, InterruptedException {
        songService.exportData();
    }
}