package com.nanlagger.mapcreator.main.entities

import java.awt.Point

/**
 * Created by NaNLagger on 01.04.15.
 * @author Stepan Lyashenko
 */
object Utils {
  val SCREEN_WIDTH = 1024
  val SCREEN_HEIGHT = 578
  val widthField: Int = 50
  val heightField: Int = 25
  val CONS_SCALE: Float = 1
  val CONS_SCALE_Y: Float = 1

  def positionToPoint(pos: Position): Point = {
    val x = pos.column * widthField + (if(pos.row%2 == 1) widthField/2 else 0)
    val y = pos.row * heightField
    new Point(x.toInt, y.toInt)
  }

  def pointToPosition(point: Point): Position = {
    val row: Int = point.y / (heightField * CONS_SCALE) toInt
    val column: Int = (point.x - (if(row%2 == 1) widthField/2 else 0)) / widthField toInt;
    Position(row, column)
  }
}
