package com.teforspringscala.domain.entities

import javax.persistence.{MappedSuperclass, GenerationType, GeneratedValue, Id}

/**
 * Created by Bamsen on 2014-12-18.
 */
@MappedSuperclass
abstract class AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Int = 0

  def getId:Int = id

}
