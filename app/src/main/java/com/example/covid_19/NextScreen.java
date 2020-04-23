package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class NextScreen extends AppCompatActivity {
    TextView name,deathNum,activeNum,recoverNum,allNum,title;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_screen);
        name=findViewById(R.id.name);
        deathNum=findViewById(R.id.deathNum);
        activeNum=findViewById(R.id.activeNum);
        recoverNum=findViewById(R.id.recoverNum);
        allNum=findViewById(R.id.allNum);
        title=findViewById(R.id.title);
        back=findViewById(R.id.back);

        String data = "";

        Bundle bundle=getIntent().getExtras();
        data=bundle.getString("data");
        title.setText(bundle.getString("name"));

        try {
            JSONObject object=new JSONObject(data);

            name.setText(object.getString("cName"));
            deathNum.setText(""+object.getInt("death"));
            activeNum.setText(""+object.getInt("active"));
            recoverNum.setText(""+object.getInt("recover"));
            allNum.setText(""+object.getInt("all"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
