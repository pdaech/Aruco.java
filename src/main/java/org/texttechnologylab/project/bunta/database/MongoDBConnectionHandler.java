
package org.texttechnologylab.project.bunta.database;

public class MongoDBConnectionHandler {
  String remote_host = "prg2021.texttechnologylab.org";
  String port = "27020";
  String password = "passwort";
  String username = "PRG_WiSe21_xxx";

  String uri = "mongodb://" + username + ":" + password + "@" + remote_host + ":" + port +  "/" + username + "?authSource=" + username;

  MongoClient client = MongoClients.create(uri);
  MongoDatabase db = client.getDatabase(username);
}

