package com.teforspringscala.item.domain

import javax.persistence.{Embeddable, Entity}

/**
 * Created by Bamsen on 2014-12-21.
 */
@Embeddable
class ItemInfo(private val description:String,private val storage:Int){


  /*
  Java like defaultconstructor as required of
  JPA specification
  */

  private def this() = this(null,0)


  def getDescription = description

  def getStorage = storage

}
