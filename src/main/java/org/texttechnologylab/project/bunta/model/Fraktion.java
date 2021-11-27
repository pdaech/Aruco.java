package org.texttechnologylab.project.bunta.model;

import java.util.Arrays;

public enum Fraktion {
  CDU_CSU("CDU/CSU"),
  SPD("SPD"),
  BUENDNIS_90_DIE_GRUENEN("BÜNDNIS 90/ DIE GRÜNEN"),
  FDP("FDP"),
  AFD("AFD"),
  DIE_LINKE("DIE LINKE"),
  FRAKTIONSLOS("FRAKTIONSLOS");

  private final String name;

  Fraktion(String name) {
    this.name = name;
  }

  public static Fraktion getByNameIgnoreSpacesCaptilization(String name) {
    if(name == null) {
      return null;
    }
    return Arrays.stream(Fraktion.values())
        .filter(p -> p.name.replaceAll(" ", "")
            .equalsIgnoreCase(name.replaceAll(" ", "").replace("\u00a0", "")))
        .findAny()
        .orElse(null);
  }

  public String getName() {
    return this.name;
  }
}
