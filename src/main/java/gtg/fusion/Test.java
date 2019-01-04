package gtg.fusion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @author rylynn
 * @version 2019-01-02
 * @classname Test
 * @discription
 */

public class Test {
    private static final String DB = "gtg";
    private static final String COLLECTION = "fusion";

    public static void main(String[] args) {
        MongoClient client = new MongoClient("10.113.3.1", 20000);
        JsonParser jsonParser = new JsonParser();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.err.println("data center database list: ");
        client.listDatabaseNames().iterator().forEachRemaining(System.out::println);
        System.err.println("database %s's collections list: ");
        MongoDatabase database = client.getDatabase(DB);
        database.listCollectionNames().iterator().forEachRemaining(System.out::println);
        MongoCollection<Document> collection = database.getCollection(COLLECTION);
        collection.find().limit(5).iterator().forEachRemaining(document -> {
            JsonElement element = jsonParser.parse(document.toJson());
            System.out.println(gson.toJson(element));
        });
    }
}
