
package org.texttechnologylab.project.bunta.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.texttechnologylab.project.bunta.util.PropertyUtil;

public class MongoDBConnectionHandler {
  public static void connect() {
    String host = PropertyUtil.getRemoteHost();
    String database = PropertyUtil.getRemoteDatabase();
    String user = PropertyUtil.getRemoteUser();
    String password = PropertyUtil.getRemotePassword();
    String port = PropertyUtil.getRemotePort();
    String uri = "mongodb://" + database + ":" + password + "@" + host + ":" + port + "/" + database + "?authSource=" + user;
    try {
      MongoClient client = MongoClients.create(uri);
      MongoDatabase db = client.getDatabase(user);
    } catch (Exception e) {
      System.out.println("connection error");
    }

  }
}

