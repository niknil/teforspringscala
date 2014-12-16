package com.teforspringscala.web.domainresource

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.{Resource, Link}
import scala.collection.JavaConverters._

/**
 * Created by Bamsen on 2014-12-15.
 */
class WelcomeResource @JsonCreator()(val content: String, links: List[Link]) extends Resource[String](content, links.asJava) {}
