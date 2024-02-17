package org.example;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Map;

public class MongoDB {
    public static void mongoDB(String ipAddress, String sslDetails, String pageSource, Map<String, String> content) {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase("UrlScanDb");

            MongoCollection<Document> collection = database.getCollection("webCollection");

            Document doc = new Document("ipAddress", ipAddress)
                    .append("sslDetails", sslDetails)
                    .append("pageSource", pageSource)
                    .append("locale", content.get("locale"))
                    .append("language", content.get("language"));

            collection.insertOne(doc);
            System.out.println("Document inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
