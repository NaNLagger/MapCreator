package com.nanlagger.mapcreator.main.controllers

import com.nanlagger.mapcreator.main.entities.Field.{IncomeType, TypeField}
import com.nanlagger.mapcreator.main.entities.{Position, FieldEntities}
import com.nanlagger.mapcreator.main.ui.MainForm

/**
 * Created by NaNLagger on 18.05.15.
 * @author Stepan Lyashenko
 */
object Controller {

  def create(): Unit = {
    FieldEntities(20)
  }

  def updateField(position: Position, fieldType: String, incomeType: String): Unit = {
    FieldEntities.getField(position).typeField = fieldType match {
      case "LAND" => TypeField.LAND
      case "OCEAN" => TypeField.OCEAN
      case _ => TypeField.OCEAN
    }

    FieldEntities.getField(position).incomeType = incomeType match {
      case "LAND_GRASS" => IncomeType.LAND_GRASS
      case "LAND_HILL" => IncomeType.LAND_HILL
      case "LAND_MOUNT" => IncomeType.LAND_MOUNT
      case "OCEAN_OCEAN" => IncomeType.OCEAN_OCEAN
      case "OCEAN_REEFS" => IncomeType.OCEAN_REEFS
      case "OCEAN_SEA" => IncomeType.OCEAN_SEA
      case _ => IncomeType.LAND_GRASS
    }
    MainForm.update()
  }

  def parse(): Unit = {
    FieldEntities.parse()
  }
}
