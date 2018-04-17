package mrhoodie.creations.rest_client_v2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import mrhoodie.creations.rest_client_v2.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView text = findViewById(R.id.text);
        text.setMovementMethod(new ScrollingMovementMethod());

        Intent passedIntent = getIntent();
        Bundle extrasBundle = passedIntent.getExtras();
        if(!extrasBundle.isEmpty()){
            if(extrasBundle.getString("list_books") != null){
                try{
                    JSONArray data = new JSONArray(extrasBundle.getString("list_books").toString());
                    //text.setText(extrasBundle.getString("list_books").toString());
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < data.length(); i++) {
                        sb.append("BOOK " + (i+1)+"\n");
                        JSONObject explrObject = data.getJSONObject(i);
                        Log.e("obj", explrObject.toString());
                        sb.append(explrObject.toString());
                        sb.append("\n\n");
                    }
                    text.setText(sb.toString());
                    Log.e("list_books", extrasBundle.get("list_books").toString());

                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }else if(extrasBundle.getString("book") != null){
                try{
                    text.setText(extrasBundle.getString("book").toString());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }



        }
        }

}
