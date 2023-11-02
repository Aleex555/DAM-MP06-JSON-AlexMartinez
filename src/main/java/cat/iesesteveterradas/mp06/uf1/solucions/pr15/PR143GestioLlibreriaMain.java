package cat.iesesteveterradas.mp06.uf1.solucions.pr15;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;

public class PR143GestioLlibreriaMain {
    public static void main(String[] args) {
        try (JsonReader jsonReader = Json.createReader(new FileReader("data/llibres_input.json"))) {
            JsonArray jsonArray = jsonReader.readArray();
            System.out.println(jsonArray);

            // Crear un JsonArrayBuilder para construir el nuevo arreglo JSON
            JsonArrayBuilder newArrayBuilder = Json.createArrayBuilder();

            for (JsonValue jsonValue : jsonArray) {
                if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                    JsonObject jsonObject = jsonValue.asJsonObject();
                    if (jsonObject.getInt("id") == 1) {
                        JsonObject modifiedObject = modifyJsonObject(jsonObject);
                        newArrayBuilder.add(modifiedObject);
                    } else if (jsonObject.getInt("id") == 2) {
                        continue;
                    } else {
                        newArrayBuilder.add(jsonObject);
                    }
                }
            }
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("id", 4);
            objectBuilder.add("títol", "Històries de la ciutat");
            objectBuilder.add("autor", "Miquel Soler");
            objectBuilder.add("any", 2022);
            newArrayBuilder.add(objectBuilder);

            JsonArray modifiedJsonArray = newArrayBuilder.build();

            try (JsonWriter jsonWriter = Json.createWriter(new FileWriter("data/llibres_output.json"))) {
                jsonWriter.writeArray(modifiedJsonArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonObject modifyJsonObject(JsonObject originalObject) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder(originalObject);
        objectBuilder.add("any", 1995);
        return objectBuilder.build();
    }
}
