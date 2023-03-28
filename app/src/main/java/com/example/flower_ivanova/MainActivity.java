package com.example.flower_ivanova;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    /*private ProgressBar progBar;*/
    private Button showButton;
    ListView listView;

    List<Flower> flowersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        /*progBar = findViewById(R.id.progressBar);*/
        listView = findViewById(R.id.listView);
        showButton = findViewById(R.id.button);

        /*progBar.setVisibility(View.INVISIBLE);*/

        flowersList = new ArrayList<>();

        showButton.setOnClickListener(view -> {
            setFlowers();
        });
    }

    public void setFlowers() {
        FlowerAPI flowerAPI = FlowerAPI.retrofit.create(FlowerAPI.class);
        final Call<List<Flower>> call = flowerAPI.getData();

        call.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                if (response.isSuccessful()){
                    flowersList.addAll(response.body());
                    FlowerAdapter adapter = new FlowerAdapter(MainActivity.this, flowersList);
                    listView.setAdapter(adapter);
                    /*progBar.setVisibility(View.INVISIBLE);*/
                } else {
                    ResponseBody errorBody = response.errorBody();
                    try {
                        Toast.makeText(MainActivity.this, errorBody.string(),
                                Toast.LENGTH_SHORT).show();
                        /*progBar.setVisibility(View.INVISIBLE);*/
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Что-то пошло не так. ",
                        Toast.LENGTH_SHORT).show();
                /*progBar.setVisibility(View.INVISIBLE);*/

            }
        });
    }
}