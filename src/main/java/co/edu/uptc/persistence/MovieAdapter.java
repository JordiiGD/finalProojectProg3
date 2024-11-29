package co.edu.uptc.persistence;

import co.edu.uptc.model.Comment;
import co.edu.uptc.model.Movie;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MovieAdapter implements JsonSerializer<Movie>, JsonDeserializer<Movie> {

    @Override
    public Movie deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Movie movie = new Movie();
        if (jsonObject.has("name") && !jsonObject.get("name").isJsonNull()) {
            movie.setName(jsonObject.get("name").getAsString());
        } else {
            movie.setName("");
        }
        if (jsonObject.has("description") && !jsonObject.get("description").isJsonNull()) {
            movie.setDescription(jsonObject.get("description").getAsString());
        } else {
            movie.setDescription("");
        }
        if (jsonObject.has("rating") && !jsonObject.get("rating").isJsonNull()) {
            movie.setRating(jsonObject.get("rating").getAsDouble());
        } else {
            movie.setRating(0.0);
        }
        if (jsonObject.has("poster") && !jsonObject.get("poster").isJsonNull()) {
            movie.setPoster(jsonObject.get("poster").getAsString());
        } else {
            movie.setPoster("");
        }
        if (jsonObject.has("wallpaper") && !jsonObject.get("wallpaper").isJsonNull()) {
            movie.setWallpaper(jsonObject.get("wallpaper").getAsString());
        } else {
            movie.setWallpaper("");
        }
        if (jsonObject.has("comments") && !jsonObject.get("comments").isJsonNull()) {
            List<Comment> commentsList = jsonDeserializationContext.deserialize(jsonObject.get("comments"), new TypeToken<List<Comment>>(){}.getType());
            movie.setCommentsFromList(commentsList);
        }
        if (jsonObject.has("category") && !jsonObject.get("category").isJsonNull()) {
            List<String> categoryList = jsonDeserializationContext.deserialize(jsonObject.get("category"), new TypeToken<List<String>>(){}.getType());
            movie.setCategoryFromList(categoryList);
        }
        if (jsonObject.has("numberOfVisited") && !jsonObject.get("numberOfVisited").isJsonNull()) {
            movie.setNumberOfVisited(jsonObject.get("numberOfVisited").getAsInt());
        } else {
            movie.setRating(0);
        }
        if (jsonObject.has("ratings") && !jsonObject.get("ratings").isJsonNull()) {
            List<Integer> ratingsList = jsonDeserializationContext.deserialize(jsonObject.get("ratings"), new TypeToken<List<Integer>>(){}.getType());
            movie.setRatingsFromList(ratingsList);
        }

        return movie;
    }

    @Override
    public JsonElement serialize(Movie movie, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", movie.getName());
        jsonObject.addProperty("description", movie.getDescription());
        jsonObject.addProperty("rating", movie.getRating());
        jsonObject.addProperty("poster", movie.getPoster());
        jsonObject.addProperty("wallpaper", movie.getWallpaper());
        jsonObject.addProperty("releaseDate", movie.getReleaseDate().toString());
        jsonObject.add("comments", jsonSerializationContext.serialize(movie.getCommentsAsList()));
        jsonObject.add("category", jsonSerializationContext.serialize(movie.getCategoryAsList()));
        jsonObject.addProperty("numberOfVisited", movie.getNumberOfVisited());
        jsonObject.add("ratings", jsonSerializationContext.serialize(movie.getRatingsAsList()));

        return jsonObject;
    }
}
