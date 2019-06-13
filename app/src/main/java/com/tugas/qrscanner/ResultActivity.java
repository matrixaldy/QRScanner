package com.tugas.qrscanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultActivity extends AppCompatActivity {
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        Intent i = getIntent();
        String UniqueID = i.getStringExtra(MainActivity.CODE);

        int result = Integer.parseInt(UniqueID);

        textViewResult = (TextView) findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://test.riandyfadly.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Fri> friCall = jsonPlaceHolderApi.showFri(result);

        friCall.enqueue(new Callback<Fri>() {
            @Override
            public void onResponse(Call<Fri> call, Response<Fri> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText(("code: " + response.code()));
                    return;
                }

                Fri fri = response.body();
                String content = "";
                content += "Unique ID \t\t\t: " + fri.getUniqueId() + "\n";
                content += "Airline Code \t\t: " + fri.getAirlineCode() + "\n";
                content += "Flight Number \t: " + fri.getFlightNum() + "\n";
                content += "Depart Gate \t\t: " + fri.getDepartGate() + "\n";


                textViewResult.append(content);

            }

            @Override
            public void onFailure(Call<Fri> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
