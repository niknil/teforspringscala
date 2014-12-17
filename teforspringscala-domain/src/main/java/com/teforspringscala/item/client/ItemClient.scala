package com.teforspringscala.item.client

import com.teforspringscala.item.dao.{CustomerManager, OrderManager, ItemManager}
import com.teforspringscala.item.domain.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by Bamsen on 2014-12-17.
 */
trait ItemClient {
  def post(item:Item):Unit
  def get(id:Int):Item
  def getList:java.util.List[Item]
  def update(item:Item):Unit
  def delete(id:Int): Unit
}


@Component
class ItemClientImpl @Autowired()(val itemRepo: ItemManager) extends ItemClient{

  def post(item:Item):Unit = {
    itemRepo.persist(item)
  }
  def get(id:Int):Item = {
    itemRepo.get(id)
  }
  def getList:java.util.List[Item] = {
    itemRepo.getAll
  }
  def update(item:Item):Unit = {
    itemRepo.update(item)
  }
  def delete(id:Int): Unit = {
    itemRepo.delete(id)
  }


}