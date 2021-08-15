package com.example.databaseappvacwork;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // declaration
    public ListView lv;
    public String[] st;
    int i = 0;
    Handler handler;

    // the array adapter converts an ArrayList of objects
    // into View items filled into the ListView container
    ArrayAdapter<String> arrayAdapter;

    // Initialize list to store data
    public static List<String> ls;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act1);

        // provide id to the layout items
        st = new String[100];
        lv = findViewById(R.id.lt);
        ls = new ArrayList<String>();

        // Initialize Amplify
        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        }
        catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        // retrieve the stored data
        Amplify.API.query(ModelQuery.list(Sensors.class), response -> {
                    for (Sensors sensor : response.getData()) {
                        ls.add(sensor.getTemperature());
                        Log.i("MyAmplifyApp", sensor.getTemperature());
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error));

        handler = new Handler();
        final Runnable r = new Runnable() {
            public void run()
            {
                handler.postDelayed(this, 2000);
                arrayAdapter = new ArrayAdapter<String>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        ls);
                lv.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }
        };
        handler.postDelayed(r, 1000);
    }
}
