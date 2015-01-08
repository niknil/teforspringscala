package com.teforspringscala.domain.entities

import java.util
import javax.persistence._

/**
 * Created by Bamsen on 2014-12-16.
 */

@Entity
@Table(name = "tefor_order")
class Order() extends AbstractEntity {



  @ManyToMany(cascade = Array(CascadeType.ALL))
  @JoinColumn(name = "tefor_order_id")
  var items: java.util.List[Item] = new java.util.ArrayList[Item]

  def getItems = items

  def addItem(item: Item) = {
    if(items == null){
      items = new util.ArrayList[Item]()
    }

    items.add(item)
  }

  def addList(itemsList: java.util.List[Item]) = {
    items = itemsList
  }
  override def toString = id + " "
}


object OrderBuilder {
  private var accName: Option[String] = None
  private val items: java.util.List[Item] = null

  def withAccName(s: String) = {
    accName = Some(s); this
  }

  def withItem(item: Item) = {
    items.add(item); this
  }


}



