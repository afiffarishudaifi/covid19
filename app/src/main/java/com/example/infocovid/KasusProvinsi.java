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

public class KasusProvinsi extends AppCompatActivity {
    private RequestQueue provinsiQueue;
    private RecyclerView provinsiRecyclerView;
    private RecyclerView.Adapter provinsiAdapter;
    private RecyclerView.LayoutManager provinsiLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provinsi);

        provinsiQueue = Volley.newRequestQueue(this);
        provinsiParse();
    }

    private void provinsiParse() {
        String url = "https://api.kawalcorona.com/indonesia/provinsi/";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    ArrayList<ProvinsiItem> provinsiList = new ArrayList<>();

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject provinsi = response.getJSONObject(i);
                        JSONObject dataProvinsi = provinsi.getJSONObject("attributes");

                        String namaProvinsi = dataProvinsi.getString("Provinsi");
                        String positifProvinsi = dataProvinsi.getString("Kasus_Posi");
                        String sembuhProvinsi = dataProvinsi.getString("Kasus_Semb");
                        String meninggalProvinsi = dataProvinsi.getString("Kasus_Meni");

                        provinsiList.add(new ProvinsiItem(namaProvinsi, positifProvinsi, sembuhProvinsi, meninggalProvinsi));
                    }
                    provinsiRecyclerView = findViewById(R.id.recycleProvinsi);
                    provinsiRecyclerView.setHasFixedSize(true);
                    provinsiLayoutManager = new LinearLayoutManager(getApplicationContext());
                    provinsiAdapter = new ProvinsiAdapter(provinsiList);

                    provinsiRecyclerView.setLayoutManager(provinsiLayoutManager);
                    provinsiRecyclerView.setAdapter(provinsiAdapter);
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
        provinsiQueue.add(jsonArrayRequest);
    }
}
