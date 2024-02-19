package MongoDB.MongoDB;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bson.Document;

public class App 
{
    public static void main( String[] args )
    {
		String connectionString = "mongodb+srv://miguelpardonavarrooo:ciNe7vDapnJuVY5F@cluster0.f33r7hk.mongodb.net/?retryWrites=true&w=majority";
		        
        try (MongoClient mongoClient =  MongoClients.create(new ConnectionString(connectionString))) {
            
            MongoDatabase database = mongoClient.getDatabase("mi_base_de_datos");
            
            MongoCollection<Document> collection = database.getCollection("mi_coleccion");
            
            Document document = new Document("nombre","Miguel")
                                    .append("edad",24)
                                    .append("ciudad","Albacete");
            collection.insertOne(document);
            
            MongoCursor<Document> cursor = collection.find().iterator();
            
            try {
                while (cursor.hasNext()) {                    
                    System.out.println(cursor.next().toJson());
                }
            } finally {
                cursor.close();
            }
        }
    }
}
