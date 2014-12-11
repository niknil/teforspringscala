package com.teforspringscala.controllers.control;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.teforspringscala.controllers.domain.Hello;
import com.teforspringscala.controllers.domainresource.HelloResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Bamsen on 2014-12-04.
 */

@Controller
@RequestMapping(value = "/api/ordering")
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class HelloWorldController {

    @RequestMapping(value = "/", produces = "application/hal+json")
    @ResponseBody
    public HelloResource greeting(){

        Hello hello = new Hello("hello",1);
        Link link1 = linkTo(methodOn(HelloWorldController.class).greeting()).withSelfRel();
        HelloResource helloResource = new HelloResource(hello, link1);


        return helloResource;
    }
}
