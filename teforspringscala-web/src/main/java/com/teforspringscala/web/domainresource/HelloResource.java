package com.teforspringscala.web.domainresource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.teforspringscala.web.domain.Hello;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * Created by Bamsen on 2014-12-04.
 */
public class HelloResource extends Resource<Hello> {

    @JsonCreator
    public HelloResource(Hello content, Link... links) {
        super(content, links);
    }
}
