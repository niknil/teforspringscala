package com.teforspringscala.controllers.control;

import com.teforspringscala.controllers.domainresource.MainResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Bamsen on 2014-12-04.
 */

@Controller
@RequestMapping(value ="/api")
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class MainController {

    @RequestMapping(value ="/")
    @ResponseBody
    public MainResource showLinks(){

        Link link = linkTo(methodOn(HelloWorldController.class).greeting()).withRel("ordering");

        MainResource mainResource = new MainResource("",link);



        return mainResource;
    }



}
