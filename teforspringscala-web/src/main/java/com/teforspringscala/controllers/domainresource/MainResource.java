package com.teforspringscala.controllers.domainresource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.teforspringscala.controllers.domain.Hello;
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
