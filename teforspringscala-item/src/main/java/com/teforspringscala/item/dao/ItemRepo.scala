package com.teforspringscala.item.dao

import com.teforspringscala.item.domain.Item
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Bamsen on 2014-12-11.
 */
trait ItemRepo {
  def getAll: java.util.List[Item]

  def save(item: Item): Long

  def update(item: Item)

  def get(entityId: Long): Item

  def delete(entityId: Long)
}

@Repository
@Transactional
class ItemRepoImpl @Autowired() (val sessionFactory: SessionFactory)  extends ItemRepo {

  private def currentSession = sessionFactory.getCurrentSession

  def save(item: Item): Long = Long.unbox(currentSession.save(item).asInstanceOf[Object])


  def update(item: Item): Unit = currentSession.saveOrUpdate(item)


  def delete(entityId: Long): Unit = currentSession.delete(get(entityId))


  def get(entityId: Long): Item = currentSession.get(classOf[Item], Long.box(entityId)).asInstanceOf[Item]


  def getAll: java.util.List[Item] = currentSession.createCriteria(classOf[Item]).list().asInstanceOf[java.util.List[Item]]
}