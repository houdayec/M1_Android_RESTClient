package mrhoodie.creations.rest_client_v2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import mrhoodie.creations.rest_client_v2.App;
import mrhoodie.creations.rest_client_v2.R;
import mrhoodie.creations.rest_client_v2.converters.StringConverterFactory;
import mrhoodie.creations.rest_client_v2.iRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.getAllBooks)
    Button getBooksButton;

    @BindView(R.id.RESTpanel)

    Button getBookByIdButton;
    Button putBookButton;
    Button postBookButton;
    Button deleteBookButton;

    String BASE_URL;
    Retrofit retrofit;
    Retrofit retrofit2;

    iRest service;
    iRest serviceSend;

    EditText bookId;

    JsonObject bookPut;
    JsonObject bookPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        getBooksButton = findViewById(R.id.getAllBooks);
        getBookByIdButton = findViewById(R.id.getBookById);
        putBookButton = findViewById(R.id.putBook);
        postBookButton = findViewById(R.id.postBook);
        deleteBookButton = findViewById(R.id.deleteBook);
        bookId = findViewById(R.id.bookId);

        postBookButton.setOnClickListener(this);
        deleteBookButton.setOnClickListener(this);
        putBookButton.setOnClickListener(this);
        getBooksButton.setOnClickListener(this);
        getBookByIdButton.setOnClickListener(this);

        Intent passedIntent = getIntent();
        Bundle extrasBundle = passedIntent.getExtras();
        if(extrasBundle != null){
            if(!extrasBundle.isEmpty()){
                BASE_URL = extrasBundle.getString("url");
            }
        }else{
            BASE_URL = App.getInstance().getBASE_URL();
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(new StringConverterFactory())
                .build();

        retrofit2 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        service = retrofit.create(iRest.class);
        serviceSend = retrofit2.create(iRest.class);

        bookPost = new JsonObject();
        bookPut = new JsonObject();


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.getAllBooks:
                getAllBooks();
                break;
            case R.id.getBookById:
                if(!bookId.getText().toString().equals(null))
                    getBookById(bookId.getText().toString());
                else
                    Toast.makeText(this, "You need to provide a book ID", Toast.LENGTH_SHORT).show();
                break;
            case R.id.putBook:
                updateBook();
                break;
            case R.id.postBook:
                postBook();
                break;
            case R.id.deleteBook:
                if(!bookId.getText().toString().equals(null))
                    deleteBook(bookId.getText().toString());
                else
                    Toast.makeText(this, "You need to provide a book ID", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }

    public void getAllBooks(){

        //Toast.makeText(this, BASE_URL, Toast.LENGTH_SHORT).show();

        Call<String> books = service.getAllBooks();
        books.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                System.out.println(response.body());
                String bookList = response.body();
                if(bookList != null){
                    if(bookList != null){
                        //Toast.makeText(MainActivity.this, bookList.toString(), Toast.LENGTH_SHORT).show();
                        Bundle fetchedInfo = new Bundle();
                        fetchedInfo.putString("list_books",bookList);
                        Intent infoIntent = new Intent(RESTActivity.this, InfoActivity.class);
                        infoIntent.putExtras(fetchedInfo);
                        startActivity(infoIntent);
                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RESTActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
        if(!books.isExecuted()){
            try {
                books.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getBookById(String id){

        final Call<String> book = service.getBookById(id);
        book.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String book = response.body();
                System.out.println(book);
                if(book != null){
                    Bundle fetchedInfo = new Bundle();
                    fetchedInfo.putString("book",book);
                    Intent infoIntent = new Intent(RESTActivity.this, InfoActivity.class);
                    infoIntent.putExtras(fetchedInfo);
                    startActivity(infoIntent);
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    public void updateBook(){

        bookPut.addProperty("id", "bk110");
        bookPut.addProperty("listAuthor", "O'Brien, Tim");
        bookPut.addProperty("type", "Computer");
        bookPut.addProperty("title","THIS IS A NEW TITLE");
        bookPut.addProperty("price", 36.95);
        bookPut.addProperty("publishedDate", "2000-12-09");
        bookPut.addProperty("description","Microsoft's .NET initiative is explored in\n" +
                "            detail in this deep programmer's reference.");

        Log.e("bookPut : ", bookPut.toString());

        final Call<JsonObject> putCall = serviceSend.updateBook(bookPut);
        putCall.enqueue(new Callback<JsonObject>() {
                         @Override
                         public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                             Log.d("onResponse","Getting response from server : "+response.body());
                             Toast.makeText(RESTActivity.this, "Book has been updated successfully !", Toast.LENGTH_SHORT).show();
                         }

                         @Override
                         public void onFailure(Call<JsonObject> call, Throwable t) {
                             Log.d("onFailure","Getting response from server : "+t.getMessage());
                         }
                     }
        );

    }


    public void postBook(){

        Log.d("BOOK POST", "being created");
        bookPost.addProperty("listAuthor", "");
        bookPost.addProperty("type", "Drama");
        bookPost.addProperty("title","Comment post un book depuis un client Android ?");
        bookPost.addProperty("price", 9.99);
        bookPost.addProperty("publishedDate", "21/05/96");
        bookPost.addProperty("description","Et bien comme ça !");
        bookPost.addProperty("catalog", "cata");

        final Call<JsonObject> postCall = serviceSend.postBook(bookPost);
        postCall.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                Log.d("onResponse","Getting response from server : "+response.body());
                                Toast.makeText(RESTActivity.this, "Book has been posted successfully !", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                Log.d("onFailure","Getting response from server : "+t.getMessage());
                            }
                        }
        );

    }

    public void deleteBook(String id){

        final Call<String> deleteCall = serviceSend.deleteBook(id);
        deleteCall.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.d("onResponse","Getting response from server : "+response.body());
                                Toast.makeText(RESTActivity.this, "Book has been deleted successfully !", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("onFailure","Getting response from server : "+t.getMessage());
                            }
                        }
        );

    }
}
