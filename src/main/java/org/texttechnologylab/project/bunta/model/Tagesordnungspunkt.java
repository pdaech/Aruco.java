package org.texttechnologylab.project.bunta.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tagesordnungspunkt {
  private List<Rede> reden = new ArrayList<>();
  private Sitzung sitzung;

  public List<Rede> getReden() {
    return reden;
  }

  public void setReden(List<Rede> reden) {
    this.reden = reden;
  }

  public Sitzung getSitzung() {
    return sitzung;
  }

  public void setSitzung(Sitzung sitzung) {
    this.sitzung = sitzung;
  }

  public void addRede(Rede rede) {
    if (rede != null) {
      this.reden.add(rede);
      rede.setTagesordnungspunkt(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tagesordnungspunkt that = (Tagesordnungspunkt) o;
    return Objects.equals(reden, that.reden) && Objects.equals(sitzung, that.sitzung);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reden, sitzung);
  }
}
