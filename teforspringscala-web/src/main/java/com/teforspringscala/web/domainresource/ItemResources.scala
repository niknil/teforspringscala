package com.teforspringscala.web.domainresource

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.{Link, Resources}

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

/**
 * Created by Bamsen on 2014-12-16.
 */
class ItemResources @JsonCreator()(val content: ArrayBuffer[ItemResource], val links: ArrayBuffer[Link]) extends Resources[ItemResource](content.asJava, links.asJava) {}
