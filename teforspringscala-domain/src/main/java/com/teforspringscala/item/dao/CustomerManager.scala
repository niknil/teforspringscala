package com.teforspringscala.item.dao

import com.teforspringscala.item.domain.{Item, Customer}
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Bamsen on 2014-12-17.
 */
trait CustomerManager {

  def getAll: java.util.List[Customer]

  def persist(item: Customer): Int

  def update(item: Customer)

  def get(entityId: Int): Customer

  def delete(entityId: Int)
}

@Repository
@Transactional
class CustomerRepoImpl @Autowired() (val sessionFactory: SessionFactory)  extends CustomerManager {

  private def currentSession = sessionFactory.getCurrentSession

  def persist(customer: Customer): Int = Int.unbox(currentSession.save(customer).asInstanceOf[Object])


  def update(customer: Customer): Unit = currentSession.saveOrUpdate(customer)


  def delete(customerId: Int): Unit = currentSession.delete(get(customerId))


  def get(customerId: Int): Customer = currentSession.get(classOf[Customer], Int.box(customerId)).asInstanceOf[Customer]


  def getAll: java.util.List[Customer] = currentSession.createCriteria(classOf[Customer]).list().asInstanceOf[java.util.List[Customer]]


}