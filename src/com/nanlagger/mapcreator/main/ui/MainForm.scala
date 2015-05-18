package com.nanlagger.mapcreator.main.ui

import java.awt.{Color, Button}
import java.awt.event.{ActionEvent, ActionListener, WindowEvent, WindowAdapter}
import javax.swing.{JMenuItem, JMenuBar, JPanel, JFrame}

import com.nanlagger.mapcreator.main.controllers.Controller
import com.nanlagger.mapcreator.main.entities.Field.{IncomeType, TypeField}
import com.nanlagger.mapcreator.main.entities.{FieldEntities, Position, Utils}

import scala.collection.mutable.ArrayBuffer


/**
 * Created by NaNLagger on 18.05.15.
 * @author Stepan Lyashenko
 */
object MainForm extends JFrame {
  setTitle("MapCreator")
  setSize(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT)
  setVisible(true)
  addWindowListener(new WindowAdapter() {
      override def windowClosing(e: WindowEvent) {
      super.windowClosing(e)
      System.exit(0)
    }
  })
  val arrayButton = new ArrayBuffer[PositionButton]()
  initTopMenu()
  Controller.create
  createButtons
  class PositionButton(val position: Position, label: String) extends Button(label) {

  }

  def initTopMenu() {
    val topMenu = new JMenuBar();
    setJMenuBar(topMenu);
    requestFocus();
    val export = new JMenuItem("Export");
    topMenu.add(export);
    export.addActionListener(new ActionListener {
      override def actionPerformed(actionEvent: ActionEvent): Unit = {
        Controller.parse()
      }
    })
  }

  def createButtons() {
    for (i <- 0 until FieldEntities.rows; j <- 0 until FieldEntities.columns) {
      Thread.sleep(100)
      val field = FieldEntities.getField(i, j)
      val button: PositionButton = new PositionButton(Position(i, j), field.incomeType + "")
      val position = Utils.positionToPoint(field.position)
      println(position.toString)
      button.addActionListener(new ActionListener {
        override def actionPerformed(actionEvent: ActionEvent): Unit = {
          createDialog(Position(i, j))
        }
      })
      field.typeField match {
        case TypeField.LAND => button.setBackground(new Color(0, 255, 0))
        case TypeField.OCEAN => button.setBackground(new Color(0, 0, 255))
      }

      button.setBounds(position.x, position.y, Utils.widthField, Utils.heightField)
      arrayButton += button
      add(button)
    }
  }

  def createDialog(position: Position): Unit = {
    val dialog: EditorDialog = new EditorDialog(position)
    dialog.pack
    dialog.setVisible(true)
  }

  def update(): Unit = {
    arrayButton.map((x) => {
      val field = FieldEntities.getField(x.position)
      x.setLabel(field.incomeType + "")
      field.incomeType match {
        case IncomeType.LAND_GRASS => x.setBackground(new Color(0, 255, 0))
        case IncomeType.LAND_HILL => x.setBackground(new Color(80, 60, 0))
        case IncomeType.LAND_MOUNT => x.setBackground(new Color(0, 0, 0))
        case IncomeType.OCEAN_OCEAN => x.setBackground(new Color(0, 0, 255))
        case IncomeType.OCEAN_SEA => x.setBackground(new Color(100, 100, 255))
        case IncomeType.OCEAN_REEFS => x.setBackground(new Color(255, 0, 0))
        case _ => x.setBackground(new Color(255, 255, 255))
      }
    })
  }
}
