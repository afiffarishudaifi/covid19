package com.example.infocovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{
    private RequestQueue mQueue;
    ImageView negara, provinsi, pencegahan, gejala;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        negara = (ImageView) findViewById(R.id.pindahnegara);
        negara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KasusNegara.class);
                startActivity(intent);
            }
        });

        provinsi = findViewById(R.id.pindahprovinsi);
        provinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KasusProvinsi.class);
                startActivity(intent);
            }
        });

        pencegahan = (ImageView) findViewById(R.id.pindahpencegahan);
        pencegahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Pencegahan.class );
                startActivity(intent);
            }
        });

        gejala = (ImageView) findViewById(R.id.pindahgejala);
        gejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Gejala.class);
                startActivity(intent);
            }
        });

        mQueue = Volley.newRequestQueue(this);

        getData();

    }
    void getData(){
        String url = "https://api.kawalcorona.com/indonesia";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject indonesia = response.getJSONObject(i);

                        String positif = indonesia.getString("positif");
                        String sembuh = indonesia.getString("sembuh");
                        String meninggal = indonesia.getString("meninggal");

                        TextView tvPositif = findViewById(R.id.positif);
                        TextView tvSembuh = findViewById(R.id.sembuh);
                        TextView tvMeninggal = findViewById(R.id.meninggal);

                        tvPositif.setText(positif);
                        tvSembuh.setText(sembuh);
                        tvMeninggal.setText(meninggal);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(jsonArrayRequest);
    }

}
