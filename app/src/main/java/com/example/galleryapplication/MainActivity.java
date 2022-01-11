package com.example.galleryapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView imageListRecyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<ImageModel> imageModels = new ArrayList<ImageModel>();
    String url = "https://api.unsplash.com/search/photos?query=rocket&amp;per_page=40&amp;client_id=3LG8LwNcmXYZQCplMqCw93lAlSu52v2QecSbAe-xWj8";
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        imageListRecyclerView = findViewById(R.id.image_list);

        imageListRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                imageModels.clear();
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    Log.e("Main",""+jsonArray.length());

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);//"urls"
                        JSONObject jsonObject1 = jsonObject.getJSONObject("urls");
                        String thumbUrl = jsonObject1.getString("thumb");
                        String regularUrl = jsonObject1.getString("regular");
                        Log.e("Main thumb",""+thumbUrl);
                        Log.e("Main regular",""+regularUrl);
                        imageModels.add(new ImageModel(thumbUrl,regularUrl));

                    }


                    recyclerViewAdapter = new RecyclerViewAdapter(imageModels,activity);
                    imageListRecyclerView.setAdapter(recyclerViewAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);


    }
}