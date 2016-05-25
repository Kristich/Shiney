package com.kristich.shiney;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private CurrentWeather currentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String apiKey = "feba8ae2ba4acc2410552eb8badea616";

        double lat = 9.0765;

        double lon = 7.3986;



        String foreCastUrl = "https://api.forecast.io/forecast/" + apiKey + "/" + lat + "," + lon ;


        if (isNetworkAvailable()) {


            OkHttpClient client = new OkHttpClient.Builder()
                                    .connectTimeout(10, TimeUnit.SECONDS)
                                    .writeTimeout(10, TimeUnit.SECONDS)
                                    .readTimeout(30, TimeUnit.SECONDS)
                                    .build();
            Request request = new Request.Builder().url(foreCastUrl).build();

            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {


                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {


                    try {

                        String jsonData = response.body().string(); // collects the json data

                        Log.v(TAG, jsonData);


                        if (response.isSuccessful()) {

                            currentWeather = getCurrentDetails(jsonData);


                        } else {

                            alertUserAboutError();

                        }


                    } catch (IOException e) {

                        Log.e(TAG, "Exception caught: ", e);

                    } catch (JSONException e) {

                        Log.e(TAG, "Exception caught: ", e);
                    }

                }
            });


        } else {

            alertUserAboutNetworkError();

        }



        Log.d(TAG, "Main UI code is running");


    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {

        JSONObject forcast = new JSONObject(jsonData);

        String timezone = forcast.getString("timezone");

        Log.i(TAG, "From JSON: " + timezone);



        JSONObject currently = forcast.getJSONObject("currently");

        CurrentWeather currentWeather = new CurrentWeather();

        currentWeather.setHumidity(currently.getDouble("humidity"));

        currentWeather.setTime(currently.getLong("time"));

        currentWeather.setIcon(currently.getString("icon"));

        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));

        currentWeather.setSummary(currently.getString("summary"));

        currentWeather.setTemprature(currently.getDouble("temperature"));

        currentWeather.setTimeZone(timezone);


        Log.d(TAG, currentWeather.getFormattedTime());

        return currentWeather;

    }

    private void alertUserAboutNetworkError() {

        AlertNetworkErrorFragment dialog = new AlertNetworkErrorFragment();

        dialog.show(getSupportFragmentManager(), "error_dialog_network");

    }

    private boolean isNetworkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {

            isAvailable = true;

        }

        return isAvailable;

    }

    private void alertUserAboutError() {

        AlertDialogFragment dialog = new AlertDialogFragment();

        dialog.show(getSupportFragmentManager(), "error_dialog");

    }
}
