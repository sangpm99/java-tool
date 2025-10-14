package com.tool.controllers.vietnamexploration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VnController {
    private static final Logger logger = LoggerFactory.getLogger(EnController.class);

    @GetMapping("/api/vn/getLayout")
    public Map<String, Object> getLayout() throws Exception {
        logger.info("REQUEST WAS SENT /api/vn/getLayout");
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/static/json/layouts/vn.json");
        return mapper.readValue(is, new TypeReference<Map<String, Object>>() {});
    }

    @GetMapping("/api/vn/getHomePage")
    public Map<String, Object> getContent() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/static/json/pages/home/vn.json");
        return mapper.readValue(is, new TypeReference<Map<String, Object>>() {});
    }
}
