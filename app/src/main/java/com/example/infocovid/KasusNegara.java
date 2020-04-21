package com.example.infocovid;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KasusNegara extends AppCompatActivity {
    private RequestQueue negaraQueue;
    private RecyclerView negaraRecyclerView;
    private RecyclerView.Adapter negaraAdapter;
    private RecyclerView.LayoutManager negaraLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negara);

        negaraQueue = Volley.newRequestQueue(this);
        negaraParse();
    }

    private void negaraParse(){
        String url = "https://api.kawalcorona.com/";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    ArrayList<NegaraItem> negaraList = new ArrayList<>();

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject negara = response.getJSONObject(i);
                        JSONObject dataNegara = negara.getJSONObject("attributes");

                        String namaNegara = dataNegara.getString("Country_Region");
                        String textPositif = dataNegara.getString("Confirmed");
                        String textSembuh = dataNegara.getString("Recovered");
                        String textNegatif = dataNegara.getString("Deaths");

                        negaraList.add(new NegaraItem(namaNegara, textPositif, textSembuh, textNegatif));

                    }
                    negaraRecyclerView = findViewById(R.id.recycleNegara);
                    negaraRecyclerView.setHasFixedSize(true);
                    negaraLayoutManager = new LinearLayoutManager(getApplicationContext());
                    negaraAdapter = new NegaraAdapter(negaraList);

                    negaraRecyclerView.setLayoutManager(negaraLayoutManager);
                    negaraRecyclerView.setAdapter(negaraAdapter);
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
        negaraQueue.add(jsonArrayRequest);
    }
}