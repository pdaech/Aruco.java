package org.texttechnologylab.project.bunta.menu;


import org.texttechnologylab.project.bunta.abstracts.Menu;
import org.texttechnologylab.project.bunta.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class ZurufeMenu_File_Impl extends Menu {
  private final int MENU_LENGTH = 5;

  public ZurufeMenu_File_Impl(List<Sitzung> sitzungen) {
    super(sitzungen);
  }

  @Override
  protected int getMenulength() {
    return MENU_LENGTH;
  }

  @Override
  protected void printMenu() {
    System.out.println("Wie soll die Menge der Zurufe wiedergegeben werden?");
    System.out.println("1 -- Menge an Zurufen die Abgeordnete erhalten haben");
    System.out.println("2 -- Menge an Zurufen die Fraktionen erhalten haben");
    System.out.println("3 -- Menge an Durchschnittlichen Zurufen die Fraktionen pro Rede erhalten haben");
    System.out.println("4 -- TOP X Redebeiträge mit den meisten Zurufen (0 < X < 101)");
    System.out.println("5 -- zurück");
    System.out.println("0 -- Programm beenden");
  }

  @Override
  protected void ausfuehren(int auswahl) {
    setExit(true);
    System.out.println();
    switch (auswahl) {
      case 0:
        System.out.println("Du hast das Programm beendet.");
        break;
      case 1:
        System.out.println("Menge an Zurufen die Abgeordnete erhalten haben: ");
        getBeitraegeAbgeordneter().forEach((k, v) -> System.out.println(k + ": " + v));
        break;
      case 2:
        System.out.println("Menge an Zurufen die Fraktionen erhalten haben: ");
        getZurufeProPartei().forEach((k, v) -> System.out.println(k + ": " + v));
        break;
      case 3:
        System.out.println("Menge an Durchschnittlichen Zurufen die Fraktionen pro Rede erhalten haben: ");
        getDurchschnittlicheZurufeProPartei().forEach((k, v) -> System.out.println(k + ": " + v));
        break;
      case 4:
        int top = getInput(100);
        System.out.println("TOP " + top + " Redebeiträge mit den meisten Zurufen: ");
        Map<String, Integer> topzugriffe = getTopZurufe(top);
        int i = 1;
        for (Map.Entry<String, Integer> entry : topzugriffe.entrySet()) {
          System.out.println((i++) + ". " + entry.getKey() + ": " + entry.getValue());
        }
        break;
      case 5:
        break;
    }
    System.out.println();
  }

  private double getAlleBeitraege() {
    return sitzungen.stream()
        .map(Sitzung::getTagesordnungspunkte).flatMap(List::stream)
        .map(Tagesordnungspunkt::getReden).flatMap(List::stream)
        .map(Rede::getText)
        .mapToInt(String::length)
        .average()
        .orElse(0);
  }

  private Map<String, Integer> getTopZurufe(int top) {
    return sitzungen.stream()
        .map(Sitzung::getTagesordnungspunkte).flatMap(List::stream)
        .map(Tagesordnungspunkt::getReden).flatMap(List::stream)
        .collect(Collectors.toMap((r) -> (("Rede " + r.getTagesordnungspunkt().getReden().indexOf(r) + 1) + ", " +
            r.getTagesordnungspunkt().getTagesOrdnungsUeberschrift()
            + ", Sitzung " + r.getTagesordnungspunkt().getSitzung().getNummer()
            + " vom " + r.getTagesordnungspunkt().getSitzung().getDatum()), (r) -> r.getKommentare().size(), (o1, o2) -> o1))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .limit(top)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o1, LinkedHashMap::new));
  }

  private Map<String, String> getDurchschnittlicheZurufeProPartei() {
    return sitzungen.stream()
        .map(Sitzung::getTagesordnungspunkte).flatMap(List::stream)
        .map(Tagesordnungspunkt::getReden).flatMap(List::stream)
        .filter(r -> r.getRedner() instanceof Abgeordneter)
        .collect(Collectors.groupingBy(r -> ((Abgeordneter) r.getRedner()).getFraktion().getName()))
        .entrySet()
        .stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> {
          Map<Fraktion, Long> kom = new HashMap<>();
          int reden = e.getValue().size();
          e.getValue().stream()
              .map(Rede::getKommentare).flatMap(List::stream)
              .filter(Objects::nonNull)
              .filter(k -> Boolean.TRUE.equals(k.getZuruf()))
              .forEach(v -> {
                v.getZurufsFraktion()
                    .forEach(f -> {
                      if (kom.containsKey(f)) {
                        kom.put(f, kom.get(f) + 1);
                      } else {
                        kom.put(f, 1L);
                      }
                    });
              });
          return kom.entrySet().stream()
              .map(f -> f.getKey().getName() + " - " + getZeichenMitZweiKommastellen((double) f.getValue() / reden))
              .collect(Collectors.joining(", "));
        }));
  }

  private Map<String, String> getZurufeProPartei() {
    Map<String, String> retVal = new HashMap<>();
    sitzungen.stream()
        .map(Sitzung::getTagesordnungspunkte).flatMap(List::stream)
        .map(Tagesordnungspunkt::getReden).flatMap(List::stream)
        .filter(r -> r.getRedner() instanceof Abgeordneter)
        .collect(Collectors.groupingBy(r -> ((Abgeordneter) r.getRedner()).getFraktion().getName()))
        .entrySet()
        .stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e ->
            e.getValue().stream()
                .map(Rede::getKommentare).flatMap(List::stream)
                .filter(Objects::nonNull)
                .filter(k -> Boolean.TRUE.equals(k.getZuruf())).collect(Collectors.toList())
        )).forEach((k, v) -> {
          Map<Fraktion, Long> kom = new HashMap<>();
          v.stream().map(Kommentar::getZurufsFraktion)
              .flatMap(List::stream)
              .forEach(f -> {
                if (kom.containsKey(f)) {
                  kom.put(f, kom.get(f) + 1);
                } else {
                  kom.put(f, 1L);
                }
              });
          retVal.put(k, kom.entrySet().stream().map(e -> e.getKey().getName() + " - " + e.getValue())
              .collect(Collectors.joining(", ")));
        });
    return retVal;
  }

  private Map<String, String> getBeitraegeAbgeordneter() {
    Map<String, String> retVal = new HashMap<>();
    sitzungen.stream()
        .map(Sitzung::getTagesordnungspunkte).flatMap(List::stream)
        .map(Tagesordnungspunkt::getReden).flatMap(List::stream)
        .collect(Collectors.groupingBy(r -> r.getRedner().getName()))
        .entrySet()
        .stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e ->
            e.getValue().stream()
                .map(Rede::getKommentare).flatMap(List::stream)
                .filter(Objects::nonNull)
                .filter(k -> Boolean.TRUE.equals(k.getZuruf())).collect(Collectors.toList())
        ))
        .forEach((k, v) -> {
          Map<Fraktion, Long> kom = new HashMap<>();
          v.stream().map(Kommentar::getZurufsFraktion)
              .flatMap(List::stream)
              .forEach(f -> {
                if (kom.containsKey(f)) {
                  kom.put(f, kom.get(f) + 1);
                } else {
                  kom.put(f, 1L);
                }
              });
          retVal.put(k, kom.entrySet().stream().map(e -> e.getKey().getName() + " - " + e.getValue())
              .collect(Collectors.joining(", ")));
        });
    return retVal;
  }
}