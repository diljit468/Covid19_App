package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    String data = "";
    Spinner spinnerName;
    click click;
    ArrayList<ContinentModel> continentModels=new ArrayList<>();
    ArrayList<Cases> country = new ArrayList<>();
    ArrayList<String> Categories=new ArrayList<>();
    RecyclerView recyclerView;
    int item=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
        try {
            init();
            getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        click=new click() {
            @Override
            public void onClick(View view, int postion) {
                try {
                    Log.e(TAG, "onClick: "+continentModels.get(item).getName() );
                    Log.e(TAG, "onClick: "+continentModels.get(item).getArray().getJSONObject(postion));
                    Intent intent=new Intent(MainActivity.this,NextScreen.class);
                    intent.putExtra("data",continentModels.get(item).getArray().getJSONObject(postion).toString());
                    intent.putExtra("name",continentModels.get(item).getName());
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }
    private void init() throws JSONException{
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        spinnerName=findViewById(R.id.spinnerName);
        spinnerName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e(TAG, "onItemSelected: "+continentModels.get(i).getArray());
                item=i;
                country.clear();
                JSONArray array=continentModels.get(i).getArray();
                for(int a=0;a<array.length();a++){
                    try {
                        JSONObject object=(JSONObject)array.get(a);
                        Cases cases=new Cases(object.getString("cName"),object.getInt("all"));
                        country.add(cases);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Adapter adapter=new Adapter(MainActivity.this,country,click);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void getData() throws JSONException {
        JSONObject object=new JSONObject(data);
        JSONArray array=object.getJSONArray("data");
        for (int i=0;i<array.length();i++){
            JSONObject data=(JSONObject)array.get(i);
            ContinentModel model=new ContinentModel(data.getString("name"),data.getJSONArray("list"));
            Categories.add(data.getString("name"));
            continentModels.add(model);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, Categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerName.setAdapter(dataAdapter);
    }

    public void fillData(){
        data="{\"data\":[\n" +
                "{\"name\":\"North America\",\"list\":\n" +
                "[{\n" +
                "\"cName\":\"Canada\",\n" +
                "\"all\":40190,\n" +
                "\"death\":2505,\n" +
                "\"active\":24230,\n" +
                "\"recover\":13789\n" +
                "},\n" +
                "{\n" +
                "\"cName\":\"USA\",\n" +
                "\"all\":848779,\n" +
                "\"death\":47230,\n" +
                "\"active\":717456,\n" +
                "\"recover\":84723\n" +
                "},\n" +
                "{\n" +
                "\"cName\":\"Mexico\",\n" +
                "\"all\":10592,\n" +
                "\"death\":970,\n" +
                "\"active\":6947,\n" +
                "\"recover\":2627\n" +
                "}]},\n" +
                "\n" +
                "{\"name\":\"South America\",\n" +
                "\"list\":\n" +
                "[{\n" +
                "\"cName\":\"Brazil\",\n" +
                "\"all\":40190,\n" +
                "\"death\":2005,\n" +
                "\"active\":24230,\n" +
                "\"recover\":13789\n" +
                "},\n" +
                "{\n" +
                "\"cName\":\"Peru\",\n" +
                "\"all\":40190,\n" +
                "\"death\":2005,\n" +
                "\"active\":24230,\n" +
                "\"recover\":13789\n" +
                "}]},\n" +
                "\n" +
                "{\"name\":\"Europe\",\n" +
                "\"list\":\n" +
                "[{\n" +
                "\"cName\":\"UK\",\n" +
                "\"all\":133456,\n" +
                "\"death\":18100,\n" +
                "\"active\":115051,\n" +
                "\"recover\":22567\n" +
                "},{\n" +
                "\"cName\":\"France\",\n" +
                "\"all\":159315,\n" +
                "\"death\":21340,\n" +
                "\"active\":79880,\n" +
                "\"recover\":40657\n" +
                "},{\n" +
                "\"cName\":\"Germany\",\n" +
                "\"all\":150234,\n" +
                "\"death\":5315,\n" +
                "\"active\":99400,\n" +
                "\"recover\":45560\n" +
                "},{\n" +
                "\"cName\":\"Spain\",\n" +
                "\"all\":208455,\n" +
                "\"death\":21717,\n" +
                "\"active\":107345,\n" +
                "\"recover\":85912\n" +
                "},{\n" +
                "\"cName\":\"Italy\",\n" +
                "\"all\":187652,\n" +
                "\"death\":25085,\n" +
                "\"active\":107668,\n" +
                "\"recover\":545376\n" +
                "}\n" +
                "]},\n" +
                "\n" +
                "{\"name\":\"Asia\",\n" +
                "\"list\":\n" +
                "[{\n" +
                "\"cName\":\"China\",\n" +
                "\"all\":82345,\n" +
                "\"death\":4632,\n" +
                "\"active\":959,\n" +
                "\"recover\":77207\n" +
                "},{\n" +
                "\"cName\":\"Japan\",\n" +
                "\"all\":11950,\n" +
                "\"death\":299,\n" +
                "\"active\":10227,\n" +
                "\"recover\":1424\n" +
                "},{\n" +
                "\"cName\":\"India\",\n" +
                "\"all\":21370,\n" +
                "\"death\":681,\n" +
                "\"active\":16319,\n" +
                "\"recover\":4370\n" +
                "},{\n" +
                "\"cName\":\"South Korea\",\n" +
                "\"all\":10702,\n" +
                "\"death\":240,\n" +
                "\"active\":2051,\n" +
                "\"recover\":8233\n" +
                "}]}\n" +
                "]}";
    }
}
