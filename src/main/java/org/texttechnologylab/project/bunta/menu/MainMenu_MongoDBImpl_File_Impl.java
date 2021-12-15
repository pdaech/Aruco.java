package org.texttechnologylab.project.bunta.menu;

import org.texttechnologylab.project.bunta.abstracts.Menu_MongoDB_Impl;
import org.texttechnologylab.project.bunta.model.Sitzung;

import java.util.List;

public class MainMenu_MongoDBImpl_File_Impl extends Menu_MongoDB_Impl {
  private final int MENU_LENGTH = 3;

  public MainMenu_MongoDBImpl_File_Impl(List<Sitzung> sitzungen) {
    super(sitzungen);
  }

  @Override
  protected int getMenulength() {
    return MENU_LENGTH;
  }

  @Override
  protected void printMenu() {
    System.out.println("Bitte gib eine der folgenden Zahlen ein um eine Kategorie zu wählen:");
    System.out.println("1 -- Auflistung von Rednern");
    System.out.println("2 -- Länge der durchschnittlichen Redebeiträge");
    System.out.println("3 -- Redebeiträge mit den meisten Zurufen");
    System.out.println("0 -- Programm beenden");
  }

  @Override
  protected void ausfuehren(int auswahl) {
    switch (auswahl) {
      case 0:
        setExit(true);
        System.out.println("Du hast das Programm beendet.");
        break;
      case 1:
        RednerMenu_MongoDBImpl_File_Impl rednerMenu = new RednerMenu_MongoDBImpl_File_Impl(sitzungen);
        rednerMenu.runMenu();
        break;
      case 2:
        AvgRede_File_Impl avgRedeMenu = new AvgRede_File_Impl(sitzungen);
        avgRedeMenu.runMenu();
        break;
      case 3:
        ZurufeMenu_MongoDBImpl_File_Impl zurufeMenu = new ZurufeMenu_MongoDBImpl_File_Impl(sitzungen);
        zurufeMenu.runMenu();
        break;
    }
  }
}