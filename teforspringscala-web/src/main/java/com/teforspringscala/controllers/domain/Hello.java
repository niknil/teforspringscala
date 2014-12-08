package com.teforspringscala.controllers.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Bamsen on 2014-12-04.
 */

public class Hello {

    private String name;
    private int id;

    @JsonCreator
    public Hello(@JsonProperty("name")String name,@JsonProperty("id") int id){
        this.setName(name);
        this.setId(id);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
