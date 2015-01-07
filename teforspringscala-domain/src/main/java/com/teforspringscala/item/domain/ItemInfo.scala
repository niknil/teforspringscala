package com.teforspringscala.item.domain

import javax.persistence.{Embeddable, Entity}

/**
 * Created by Bamsen on 2014-12-21.
 */
@Embeddable
class ItemInfo(private val stock:Int){


  /*
  Java like defaultconstructor as required of
  JPA specification
  */

  private def this() = this(0)

  def getStorage = stock

}
