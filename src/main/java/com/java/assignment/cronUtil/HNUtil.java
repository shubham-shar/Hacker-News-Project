package com.java.assignment.cronUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.java.assignment.config.HackerNewsConfig;
import com.java.assignment.model.Update;
import com.java.assignment.utils.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

/**
 * @author Shubham Sharma
 * @date 25/5/20
 */
@Slf4j
@Service
public class HNUtil {

    @Autowired
    HackerNewsConfig hackerNewsConfig;

    @Autowired
    RestTemplate restTemplate;

    public List<Long> fetchLatestStories() {
        URI storiesUrl = null;
        try {

            storiesUrl = URIUtils.createURI(hackerNewsConfig.getScheme(), hackerNewsConfig.getHost(),
                    hackerNewsConfig.getPort(), hackerNewsConfig.getPath("newstories"),
                    null, null);


            log.info("Calling top stories api - {}", storiesUrl.toString());

            ResponseEntity<String> hnResponse = restTemplate.getForEntity(storiesUrl, String.class);

            log.info("Fetched top stories with status: {}", hnResponse.getStatusCode());

            return ObjectMapperUtil.getInstance()
                    .getObjectMapper()
                    .readValue(hnResponse.getBody(), new TypeReference<List<Long>>(){});
        } catch (URISyntaxException ex) {
            log.error("Error in building top stories api", ex.getStackTrace());
        } catch (IOException ex){
            log.error("Error in fetching list of stories", ex.getStackTrace());
        }
        return Collections.emptyList();
    }

    public Update fetchUpdatedStories() {
        URI storiesUrl = null;
        try {

            storiesUrl = URIUtils.createURI(hackerNewsConfig.getScheme(), hackerNewsConfig.getHost(),
                    hackerNewsConfig.getPort(), hackerNewsConfig.getPath("updates"),
                    null, null);


            log.info("Calling update api - {}", storiesUrl.toString());

            ResponseEntity<String> hnResponse = restTemplate.getForEntity(storiesUrl, String.class);

            log.info("Fetched Updated events with status: {}", hnResponse.getStatusCode());

            return ObjectMapperUtil.getInstance()
                    .getObjectMapper()
                    .readValue(hnResponse.getBody(), Update.class);
        } catch (URISyntaxException ex) {
            log.error("Error in building top stories api", ex.getStackTrace());
        } catch (IOException ex){
            log.error("Error in fetching list of stories", ex.getStackTrace());
        }
        return null;
    }
}
