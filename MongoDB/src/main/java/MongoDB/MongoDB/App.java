package MongoDB.MongoDB;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bson.Document;

public class App 
{
    public static void main( String[] args )
    {
    	//String connectionString = "mongodb+srv://miguelpardonavarrooo:ciNe7vDapnJuVY5F@cluster0.f33r7hk.mongodb.net/?retryWrites=true&w=majority";
		String connectionString = "mongodb://localhost:27017";
		        
        try (MongoClient mongoClient =  MongoClients.create(new ConnectionString(connectionString))) {
            
        	//MongoDatabase database = mongoClient.getDatabase("mi_base_de_datos");
            MongoDatabase database = mongoClient.getDatabase("databasePruebas");
            
            //MongoCollection<Document> collection = database.getCollection("mi_coleccion");
            MongoCollection<Document> collection = database.getCollection("collectionPruebas");
            
            //insertDocument(collection, "Gominola", 50, "Chuchelandia");

            updateDocument(collection, "Miguel", new Document("edad", 24));

            //deleteDocument(collection, "Gominola");
            
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
    
    private static void insertDocument(MongoCollection<Document> collection, String nombre, int edad, String ciudad) {
        Document document = new Document("nombre", nombre)
                .append("edad", edad)
                .append("ciudad", ciudad);
        collection.insertOne(document);
        System.out.println("Documento insertado: " + document.toJson());
    }

    private static void updateDocument(MongoCollection<Document> collection, String nombre, Document updatedValues) {
        Document filter = new Document("nombre", nombre);
        collection.updateOne(filter, new Document("$set", updatedValues));
        System.out.println("Documento actualizado: " + filter.toJson());
    }

    private static void deleteDocument(MongoCollection<Document> collection, String nombre) {
        Document filter = new Document("nombre", nombre);
        collection.deleteOne(filter);
        System.out.println("Documento eliminado: " + filter.toJson());
    }
    
    
}
