package mrhoodie.creations.rest_client_v2;

import com.google.gson.JsonObject;
import com.squareup.moshi.Json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import mrhoodie.creations.rest_client_v2.model.Book;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Corentin on 4/2/2018.
 */

public interface iRest {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("bookManager/books")
    Call<String> getAllBooks();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @GET("bookManager/books/{id}")
    Call<String> getBookById(@Path(("id")) String bookId);

    @PUT("bookManager/books")
    Call<JsonObject> updateBook(@Body JsonObject bookToUpdate);

    @POST("bookManager/books")
    Call<JsonObject> postBook(@Body JsonObject bookToPost);

    @DELETE("bookManager/books/{id}")
    Call<String> deleteBook(@Path(("id")) String bookId);
}
