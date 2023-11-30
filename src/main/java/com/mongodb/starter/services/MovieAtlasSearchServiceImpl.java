package com.mongodb.starter.services;

import com.mongodb.client.MongoCollection;
import com.mongodb.starter.exception.EntityNotFoundException;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.search.SearchOperator.text;
import static com.mongodb.client.model.search.SearchOptions.searchOptions;
import static com.mongodb.client.model.search.SearchPath.fieldPath;

@Service
public class MovieAtlasSearchServiceImpl implements MovieAtlasSearchService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MovieAtlasSearchServiceImpl.class);
    private final MongoCollection<Document> collection;
    @Value("${spring.data.mongodb.atlas.search.index}")
    private String index;

    public MovieAtlasSearchServiceImpl(MongoTemplate mongoTemplate) {
        this.collection = mongoTemplate.getCollection("movies");
    }

    public Collection<Document> moviesByKeywords(String keywords, int limit) {
        LOGGER.info("=> Searching movies by keywords: {} with limit {}", keywords, limit);
        Bson searchStage = search(text(fieldPath("fullplot"), keywords), searchOptions().index(index));
        Bson projectStage = project(fields(excludeId(), include("title", "year", "fullplot", "imdb.rating")));
        Bson limitStage = limit(limit);
        List<Bson> pipeline = List.of(searchStage, projectStage, limitStage);
        List<Document> docs = collection.aggregate(pipeline).into(new ArrayList<>());
        if (docs.isEmpty()) {
            throw new EntityNotFoundException("moviesByKeywords", keywords);
        }
        return docs;
    }

}
