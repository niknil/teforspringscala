package com.teforspringscala.item.domain

import java.util
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

  @OneToMany(fetch = FetchType.EAGER)
  var items : java.util.List[Item] = new util.ArrayList[Item]()

  def getItems = items

  def addItem(item: Item) = {
    items.add(item)
  }

  override def toString = id + " "
}


object OrderBuilder {


}
