package com.teforspringscala.item.domain

import javax.persistence._

/**
 * Created by Bamsen on 2014-12-16.
 */

@Entity
@Table(name = "tefor_order")
class Order() extends AbstractEntity {

  /*
  Java like defaultconstructor as required of
  JPA specification
  */

  @OneToMany
  var items : java.util.List[Item] = new java.util.ArrayList[Item]()

  def getItems = items


  def addItem(item: Item) = {
    items.add(item)
  }

  def addList(listItem: java.util.List[Item]) = {
    items = listItem
  }

  override def toString = id + " "
}


object OrderBuilder {


}
