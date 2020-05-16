package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView global_number,corono_number, recoverd_number, deaths_number,active_number,critical_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        global_number = findViewById(R.id.global_number);
        corono_number = findViewById(R.id.corona_number);
        recoverd_number = findViewById(R.id.recovered_number);
        deaths_number = findViewById(R.id.deaths_number);
        active_number = findViewById(R.id.active_number);
        critical_number = findViewById(R.id.critical_number);

        fetchData();
    }

    private void fetchData() {

        String url = "https://disease.sh/v2/all";

        StringRequest request = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                                global_number.setText(jsonObject.getString("updated"));
                                corono_number.setText(jsonObject.getString("cases"));
                                recoverd_number.setText(jsonObject.getString("recovered"));
                                deaths_number.setText((jsonObject.getString("deaths")));
                                active_number.setText((jsonObject.getString("active")));
                                critical_number.setText((jsonObject.getString("critical")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}