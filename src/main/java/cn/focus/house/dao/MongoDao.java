package cn.focus.house.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import cn.focus.house.utils.ConfigurationUtil;

@Repository
public class MongoDao implements InitializingBean {

    private static MongoDatabase db;

    public MongoCollection<Document> getCollection(String name) {
        return db.getCollection(name);
    }
 
    public long count(String collectionName, Document query) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        return collection.count(query);
    }
    
    public List<Document> find(String collectionName, Document query, Document key, Document order, int skip, int limit) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        List<Document> result = new ArrayList<Document>();
        if (query == null) {
            return result;
        }
        FindIterable<Document> iterable = collection.find(query);
        if (order != null) {
            iterable = iterable.sort(order);
        }
        if (skip > 0) {
            iterable = iterable.skip(skip);
        }
        if (limit > 0) {
            iterable = iterable.limit(limit);
        }
        if (key != null) {
            iterable = iterable.projection(key);
        }
        iterable.into(result);
        return result;
    }

    public void deleteById(String collectionName, String id) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        collection.deleteOne(new Document("_id", new ObjectId(id)));
    }

    public void delete(String collectionName, Document query) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        collection.deleteMany(query);
    }

    public Document findOne(String collectionName, Document query) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        return collection.find(query).first();
    }

    public void insert(String collectionName, Document document) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        collection.insertOne(document);
    }

    public void updateById(String collectionName, ObjectId id, Document document) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        collection.updateOne(new Document("_id",id), new Document("$set", document));
    }

    //@Override
    public void afterPropertiesSet() throws Exception {
        
        ConfigurationUtil config = new ConfigurationUtil("/mongo.properties");
        
        MongoClientOptions mongoClientOptions = new MongoClientOptions.Builder()
                
                .connectionsPerHost(config.getInt("mongoDB.option.activeConnectionCount", 10))
                .connectTimeout(config.getInt("mongoDB.option.connectTimeout"))
                .socketTimeout(config.getInt("mongoDB.option.socketTimeout"))
                .maxWaitTime(config.getInt("mongoDB.option.maxWaitTime"))
                .threadsAllowedToBlockForConnectionMultiplier(
                        config.getInt("mongoDB.option.threadsAllowedToBlockForConnectionMultiplier"))
                .build();
        String address = config.get("mongoDB.address");
        String database = config.get("mongoDB.database");
        MongoClient mongoClient = null;
        try {
            List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
            for (String add : address.split(" ")) {
                serverAddresses.add(new ServerAddress(add));
            }
            
            List<MongoCredential> credentialsLists = new ArrayList<MongoCredential>();  
            MongoCredential mongoCredential = MongoCredential.createCredential("house", database, "house123".toCharArray());
            credentialsLists.add(mongoCredential);
            mongoClient = new MongoClient(serverAddresses, credentialsLists, mongoClientOptions);
         //   mongoClient = new MongoClient(serverAddresses, mongoClientOptions);
            db = mongoClient.getDatabase(database);
        } catch (Exception e) {
            mongoClient.close();
        }
    }
}
