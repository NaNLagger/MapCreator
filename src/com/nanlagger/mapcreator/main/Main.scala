package com.nanlagger.mapcreator.main

import javax.swing.UIManager

import com.nanlagger.mapcreator.main.ui.MainForm

/**
 * Created by NaNLagger on 18.05.15.
 * @author Stepan Lyashenko
 */
object Main extends App {
  UIManager.getInstalledLookAndFeels.find(_.getName == "GTK+").headOption match {
    case Some(info) => UIManager.setLookAndFeel(info.getClassName)
    case None => UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName)
  }
  MainForm
}
