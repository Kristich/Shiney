package com.kristich.shiney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String apiKey = "feba8ae2ba4acc2410552eb8badea616";

        double lat = 9.0765;

        double lon = 7.3986;



        String foreCastUrl = "https://api.forecast.io/forecast/" + apiKey + "/" + lat + "," + lon ;


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(foreCastUrl).build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {



            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                try {

                    Log.v(TAG, response.body().string());


                    if (response.isSuccessful()) {



                    } else {

                        alertUserAboutError();

                    }


                } catch (IOException e) {

                    Log.e(TAG, "Exception caught: ", e);

                }

            }
        });

        Log.d(TAG, "Main UI code is running");


    }

    private void alertUserAboutError() {

        AlertDialogFragment dialog = new AlertDialogFragment();

        dialog.show(getSupportFragmentManager(), "error_dialog");

    }
}
