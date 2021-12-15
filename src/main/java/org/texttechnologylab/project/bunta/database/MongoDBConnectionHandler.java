
package org.texttechnologylab.project.bunta.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBConnectionHandler {
  String remote_host = "prg2021.texttechnologylab.org";
  String remote_user = "PRG_WiSe21_195";
  String remote_password = "4XCBb741";
  String remote_port = "27020";
  String uri = "mongodb://" + remote_user + ":" + remote_password + "@" + remote_host + ":" + remote_port +  "/" + remote_user + "?authSource=" + remote_user;

  try (MongoClient client = MongoClients.create(uri)) {
      List<Document> databases = client.listDatabases().into(new ArrayList<>());
      databases.forEach(db -> System.out.println(db.toJson()));
  }
  MongoDatabase db = client.getDatabase(remote_user);
}

