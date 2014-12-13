package com.teforspringscala.web.domainresource;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * Created by Bamsen on 2014-12-04.
 */
public class MainResource extends Resource<String> {

    @JsonCreator
    public MainResource(String content, Link... links) {
        super(content, links);
    }


}
