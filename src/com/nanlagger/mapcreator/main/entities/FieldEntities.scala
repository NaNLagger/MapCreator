package com.nanlagger.mapcreator.main.entities

import java.io.PrintWriter

import scala.util.Random
import scala.util.parsing.json.{JSONArray, JSON}

/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */
object FieldEntities {
  private var fields: Array[Array[Field]] = null

  def apply(size: Int) = {
    fields = Array.ofDim[Field](size, size)
    for(i <- 0 until fields.length; j <- 0 until fields(0).length) {
      fields(i)(j) = Field(i, j, if(Random.nextInt()%2 == 1) Field.TypeField.LAND else Field.TypeField.OCEAN)
    }
  }

  def apply(rows: Int, columns: Int) = {
    fields = Array.ofDim[Field](rows, columns)
    for(i <- 0 until fields.length; j <- 0 until fields(0).length) fields(i)(j) = Field(i, j)
  }

  def rows = fields.length
  def columns = fields(0).length

  def contains(position: Position) = {
    position.row >= 0 && position.row < fields.length && position.column >= 0 && position.column < fields(0).length
  }

  def getAll(): Array[Field] = {
    for(row <- fields; item <- row) yield item
  }

  def getField(position: Position) = {
    if(contains(position))
      fields(position.row)(position.column)
    else
      null;
  }

  def getField(row: Int, column: Int) = fields(row)(column)

  def parse(): Unit = {
    val array = (for(i <- 0 until rows) yield {
      new JSONArray(fields(i).toList)
    }).toList
    val json = new JSONArray(array)

    println(json)

    val out = new PrintWriter("map.json")
    out.print(json)
    out.close()
  }
}

