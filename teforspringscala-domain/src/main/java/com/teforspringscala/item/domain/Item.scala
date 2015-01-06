package com.teforspringscala.item.domain

import javax.persistence._

/**
 * Created by Bamsen on 2014-12-09.
 */
@Entity
class Item(private val name:String, private val itemInfo:ItemInfo) extends AbstractEntity {

  /*
  Java like defaultconstructor as required of
  JPA specification
  */
  private def this() = this(null,null)


  @ManyToOne
  @JoinColumn
  var order : Order = _

  def getName = name

  @Embedded
  def getDetail = itemInfo

  override def toString = name
}

object ItemBuilder {

}
