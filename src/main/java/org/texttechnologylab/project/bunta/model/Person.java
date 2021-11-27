package org.texttechnologylab.project.bunta.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
  private String id;
  private String vorname;
  private String nachname;
  private String namenszusatz;
  private String ortszusatz;
  private List<Rede> reden = new ArrayList<>();
  private List<Kommentar> kommentare = new ArrayList<>();
  private Rolle rolle;
  private String titel;

  public Person() {
  }

  public Person(String id, String vorname, String nachname, String namenszusatz, String ortszusatz, Rolle rolle, String titel) {
    this.id = id;
    this.vorname = vorname;
    this.nachname = nachname;
    this.namenszusatz = namenszusatz;
    this.ortszusatz = ortszusatz;
    this.rolle = rolle;
    this.titel = titel;
  }

  public void addRede(Rede rede) {
    if (rede != null) {
      this.reden.add(rede);
      rede.setRedner(this);
    }
  }

  public void addKommentar(Kommentar kommentar) {
    if (kommentar != null) {
      this.kommentare.add(kommentar);
    }
  }

  public String getNamenszusatz() {
    return namenszusatz;
  }

  public void setNamenszusatz(String namenszusatz) {
    this.namenszusatz = namenszusatz;
  }

  public String getOrtszusatz() {
    return ortszusatz;
  }

  public void setOrtszusatz(String ortszusatz) {
    this.ortszusatz = ortszusatz;
  }

  public String getTitel() {
    return titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getVorname() {
    return vorname;
  }

  public void setVorname(String vorname) {
    this.vorname = vorname;
  }

  public String getNachname() {
    return nachname;
  }

  public void setNachname(String nachname) {
    this.nachname = nachname;
  }

  public List<Rede> getReden() {
    return reden;
  }

  public void setReden(List<Rede> reden) {
    this.reden = reden;
  }

  public List<Kommentar> getKommentare() {
    return kommentare;
  }

  public void setKommentare(List<Kommentar> kommentare) {
    this.kommentare = kommentare;
  }

  public Rolle getRolle() {
    return rolle;
  }

  public void setRolle(Rolle rolle) {
    this.rolle = rolle;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return Objects.equals(id, person.id) && Objects.equals(vorname, person.vorname) && Objects.equals(nachname, person.nachname) && Objects.equals(namenszusatz, person.namenszusatz) && Objects.equals(ortszusatz, person.ortszusatz) && Objects.equals(reden, person.reden) && Objects.equals(kommentare, person.kommentare) && Objects.equals(rolle, person.rolle) && Objects.equals(titel, person.titel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vorname, nachname, namenszusatz, ortszusatz, reden, kommentare, rolle, titel);
  }
}
