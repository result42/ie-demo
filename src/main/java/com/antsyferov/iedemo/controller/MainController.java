package com.antsyferov.iedemo.controller;

import com.antsyferov.iedemo.model.Dummy;
import com.antsyferov.iedemo.service.DummyProvider;
import com.antsyferov.iedemo.service.ImageMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.*;

@Controller
public class MainController {

    @Autowired
    private DummyProvider dummyProvider;

    @Autowired
    private ImageMaker imageMaker;

    @GetMapping(value = "/assignment", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Dummy> assignment() {
        return dummyProvider.fetchReversed();
    }

    @ResponseBody
    @PostMapping(value = "/ingest", consumes = TEXT_PLAIN_VALUE, produces = IMAGE_JPEG_VALUE)
    public byte[] ingest(@RequestBody String input) throws IOException {
        return imageMaker.makeImage(input);
    }
}
