package com.mongodb.starter.controllers;

import com.mongodb.starter.exception.EntityNotFoundException;
import com.mongodb.starter.services.MovieAtlasSearchService;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/movies")
public class MovieAtlasSearchController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MovieAtlasSearchController.class);
    private final MovieAtlasSearchService movieAtlasSearchService;

    public MovieAtlasSearchController(MovieAtlasSearchService movieAtlasSearchService) {
        this.movieAtlasSearchService = movieAtlasSearchService;
    }

    @GetMapping("with/{keywords}")
    Collection<Document> getMoviesWithKeywords(@PathVariable String keywords,
                                               @RequestParam(value = "limit", defaultValue = "5") int limit) {
        return movieAtlasSearchService.moviesByKeywords(keywords, limit);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "MongoDB didn't find any document.")
    public final void handleNotFoundExceptions(EntityNotFoundException e) {
        LOGGER.info("=> Movie not found: {}", e.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
    public final void handleAllExceptions(RuntimeException e) {
        LOGGER.error("=> Internal server error.", e);
    }
}
