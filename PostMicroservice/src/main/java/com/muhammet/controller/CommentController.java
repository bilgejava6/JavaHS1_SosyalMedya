package com.muhammet.controller;

import com.muhammet.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.muhammet.config.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMMENT)
public class CommentController {
    private final CommentService commentService;
}
