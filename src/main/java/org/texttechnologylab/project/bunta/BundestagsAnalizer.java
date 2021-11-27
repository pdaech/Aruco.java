package org.texttechnologylab.project.bunta;

import org.texttechnologylab.project.bunta.model.Sitzung;
import org.texttechnologylab.project.bunta.parser.PlenarsitzungParser;

import java.util.ArrayList;
import java.util.List;

public class BundestagsAnalizer {

  private static final int FILE_COUNT = 239;
  private static final List<Sitzung> sitzungen = new ArrayList<>();

  public static void main(String[] args) {
    System.out.println("Lade Sitzungen...");
    parseXmls();
    System.out.println("Sitzungen geladen!");
  }

  private static void parseXmls() {
    for (int i = 1; i < FILE_COUNT + 1; i++) {
      sitzungen.add(PlenarsitzungParser.parseXmlFile(Integer.toString(i)));
    }
  }
}
