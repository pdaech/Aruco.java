package org.texttechnologylab.project.bunta.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Kommentar {
  private String inhalt;
  private Boolean isZuruf;
  private List<Fraktion> zurufsFraktionen = new ArrayList<>();

  public String getInhalt() {
    return inhalt;
  }

  public void setInhalt(String inhalt) {
    this.inhalt = inhalt;
  }

  public Boolean getZuruf() {
    return isZuruf;
  }

  public void setZuruf(Boolean zuruf) {
    isZuruf = zuruf;
  }

  public List<Fraktion> getZurufsFraktion() {
    return zurufsFraktionen;
  }

  public void setZurufsFraktion(List<Fraktion> zurufsFraktion) {
    this.zurufsFraktionen = zurufsFraktion;
  }

  public void addZurufsFraktion(Fraktion fraktion) {
    if (fraktion != null) {
      this.zurufsFraktionen.add(fraktion);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Kommentar kommentar = (Kommentar) o;
    return Objects.equals(inhalt, kommentar.inhalt) && Objects.equals(isZuruf, kommentar.isZuruf) && Objects.equals(zurufsFraktionen, kommentar.zurufsFraktionen);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inhalt, isZuruf, zurufsFraktionen);
  }
}
