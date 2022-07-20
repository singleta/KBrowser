package com.example.kbrowser.api;

import com.example.kbrowser.configuration.PathsConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface KBrowserApi {
    public static final String APPLICATION_JSON = "application/json";

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping( value = PathsConfig.READ_TOPIC, method = RequestMethod.GET, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    @ResponseBody
    ResponseEntity<String> readTopic(@RequestParam Map<String, String> params, @RequestHeader Map<String, String> headers);

}
