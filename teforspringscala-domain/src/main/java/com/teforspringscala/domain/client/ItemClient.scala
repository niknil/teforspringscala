package com.teforspringscala.domain.client

import com.teforspringscala.domain.dao.ItemManager
import com.teforspringscala.domain.entities.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by Bamsen on 2014-12-17.
 */
trait ItemClient {
  def post(item:Item):Unit
  def get(id: Int): Item
  def getList:java.util.List[Item]
  def delete(orderId: Int): Unit
}


@Component
class ItemClientImpl @Autowired()(val itemRepo: ItemManager) extends ItemClient{

  def post(item:Item):Unit = {
    itemRepo.persist(item)
  }
  def get(id: Int): Item  = {
    itemRepo.get(id)
  }

  def getList:java.util.List[Item] = {
    itemRepo.getAll
  }

  def delete(id: Int): Unit = {
    itemRepo.delete(id)
  }

}