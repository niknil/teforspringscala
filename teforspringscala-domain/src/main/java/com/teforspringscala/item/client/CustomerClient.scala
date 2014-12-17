package com.teforspringscala.item.client

import com.teforspringscala.item.dao.{OrderManager, ItemManager, CustomerManager}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by Bamsen on 2014-12-17.
 */
trait CustomerClient {
  def getCustomerRepo: CustomerManager
}



@Component
class CustomerClientImpl @Autowired()(val customerRepo: CustomerManager) extends CustomerClient{


  def getCustomerRepo: CustomerManager = customerRepo


}