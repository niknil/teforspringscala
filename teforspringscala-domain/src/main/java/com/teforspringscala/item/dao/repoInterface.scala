package com.teforspringscala.item.dao

import java.util

import com.teforspringscala.item.domain.Item
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Bamsen on 2014-12-11.
 */
trait RepoInterface[T] {

  def getAll: java.util.List[T]

  def persist(item: T): Int

  def update(item: T)

  def get(entityId: Int): T

  def delete(entityId: Int)
}

@Repository
@Transactional
class ItemRepoImpl @Autowired() (val sessionFactory: SessionFactory)  extends RepoInterface[Item] {

  private def currentSession = sessionFactory.getCurrentSession

  def persist(item: Item): Int = Int.unbox(currentSession.save(item).asInstanceOf[Object])


  def update(item: Item): Unit = currentSession.saveOrUpdate(item)


  def delete(entityId: Int): Unit = currentSession.delete(get(entityId))


  def get(entityId: Int): Item = currentSession.get(classOf[Item], Int.box(entityId)).asInstanceOf[Item]


  def getAll: java.util.List[Item] = currentSession.createCriteria(classOf[Item]).list().asInstanceOf[java.util.List[Item]]


}