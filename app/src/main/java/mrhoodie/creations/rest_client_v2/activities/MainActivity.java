package mrhoodie.creations.rest_client_v2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import mrhoodie.creations.rest_client_v2.App;
import mrhoodie.creations.rest_client_v2.R;
import mrhoodie.creations.rest_client_v2.converters.StringConverterFactory;
import mrhoodie.creations.rest_client_v2.iRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ipaddrEditText)
    EditText mTextIpAddress;

    @BindView(R.id.connectionButton)
    Button connectionButton;

    @BindView(R.id.portEditText)
    EditText mTextPort;

    URL apiURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        connectionButton = findViewById(R.id.connectionButton);
        mTextIpAddress = findViewById(R.id.ipaddrEditText);
        mTextPort = findViewById(R.id.portEditText);

        connectionButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.connectionButton:
                    if(tryConnection(mTextIpAddress.getText().toString(), mTextPort.getText().toString())){
                        Intent RESTintent = new Intent(MainActivity.this, RESTActivity.class);
                        Bundle args = new Bundle();
                        args.putString("url", apiURL.toString());
                        RESTintent.putExtras(args);
                        App.getInstance().setBASE_URL(apiURL.toString());
                        startActivity(RESTintent);
                    }
                break;

        }
    }

    public boolean tryConnection(String ip, String port){
        try{
            apiURL = new URL("http://" + ip + ":" + port+"/");
            System.out.println(apiURL);
            URLConnection connection = apiURL.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();
            Toast.makeText(this, "Connection succeed !", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e) {
            Toast.makeText(this, "Connection succeed !", Toast.LENGTH_SHORT).show();
            return true;
        }


    }

}
