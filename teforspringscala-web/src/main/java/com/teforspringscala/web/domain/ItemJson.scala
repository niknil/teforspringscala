package com.teforspringscala.web.domain

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import org.springframework.hateoas.{ResourceSupport}
import scala.annotation.meta.field


/**
 * Created by Bamsen on 2014-12-14.
 */

class ItemJson @JsonCreator()(@(JsonProperty)val names:String)
{
  @(JsonProperty@field)("name")
  val name:String = names

  def getString = name

}
