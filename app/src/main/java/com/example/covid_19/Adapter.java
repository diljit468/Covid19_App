package com.example.covid_19;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyView> {
    MainActivity context;
    ArrayList<Cases> country;
    click click;

    public Adapter(MainActivity context, ArrayList<Cases> country, click click) {
        this.context = context;
        this.country = country;
        this.click = click;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pos_view, parent, false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder,final int i) {
        holder.tv1.setText(country.get(i).getCountry());
        holder.tv2.setText("All Cases : "+country.get(i).getAllCases());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onClick(view,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return country.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        TextView tv1,tv2;
        CardView cv;
        public MyView(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
            cv=itemView.findViewById(R.id.cv);
        }
    }
}
