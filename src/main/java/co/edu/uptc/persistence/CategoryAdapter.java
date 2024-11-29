package co.edu.uptc.persistence;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CategoryAdapter implements JsonSerializer<Category>, JsonDeserializer<Category> {
    @Override
    public Category deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Category category = new Category(jsonObject.get("name").getAsString());
        List<Movie> moviesList = jsonDeserializationContext.deserialize(jsonObject.get("movies"), new TypeToken<List<Movie>>() {}.getType());
        category.setMoviesFromList(moviesList);

        return category;
    }

    @Override
    public JsonElement serialize(Category category, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", category.getName());
        jsonObject.add("movies", jsonSerializationContext.serialize(category.getMoviesAsList()));

        return jsonObject;
    }
}
