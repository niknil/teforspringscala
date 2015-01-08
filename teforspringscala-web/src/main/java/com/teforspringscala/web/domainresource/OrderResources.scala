package com.teforspringscala.web.domainresource

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.{Link, Resources}

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

/**
 * Created by Bamsen on 2014-12-18.
 */
class OrderResources@JsonCreator()(val content: ArrayBuffer[OrderResource], val links: ArrayBuffer[Link]) extends Resources[OrderResource](content.asJava, links.asJava) {}

