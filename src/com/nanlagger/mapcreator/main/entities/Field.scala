package com.nanlagger.mapcreator.main.entities

import com.nanlagger.mapcreator.main.entities.Field.{IncomeType, TypeField}

import scala.util.Random

/**
 * Created by NaNLagger on 31.03.15.
 * @author Stepan Lyashenko
 */

object Field {
  def apply(position: Position): Field = {
    new Field(position)
  }

  def apply(row: Int, column: Int): Field = {
    new Field(new Position(row, column))
  }

  def apply(row: Int, column: Int, typeField: TypeField.Value): Field = {
    new Field(new Position(row, column), typeField)
  }

  def apply = new Field(new Position)

  object TypeField extends Enumeration {
    val LAND, OCEAN = Value
  }

  object IncomeType extends Enumeration {
    val LAND_GRASS, LAND_HILL, LAND_MOUNT = Value
    val OCEAN_OCEAN, OCEAN_SEA, OCEAN_REEFS = Value
  }
}

class Field(val position: Position, var typeField: TypeField.Value = TypeField.OCEAN) {
  var incomeType: IncomeType.Value = if(typeField == TypeField.LAND) {
    val random = new Random()
    (Math.abs(random.nextInt()) % 3) match {
      case 0 => IncomeType.LAND_GRASS
      case 1 => IncomeType.LAND_HILL
      case 2 => IncomeType.LAND_MOUNT
      case _ => IncomeType.LAND_GRASS
    }
  } else {
    val random = new Random()
    (Math.abs(random.nextInt()) % 3) match {
      case 0 => IncomeType.OCEAN_OCEAN
      case 1 => IncomeType.OCEAN_SEA
      case 2 => IncomeType.OCEAN_REEFS
      case _ => IncomeType.OCEAN_OCEAN
    }
  }
  var visionFlag = false
  var income = 1

  override def toString(): String = {
    "{ \"position\": \"" + position + "\"," + "\"typeField\": \"" + typeField + "\"," + "\"incomeType\": \"" + incomeType + "\" }"
  }
}

