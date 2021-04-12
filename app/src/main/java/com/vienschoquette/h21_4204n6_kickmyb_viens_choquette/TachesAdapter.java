package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.TachesItemBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TachesAdapter extends RecyclerView.Adapter<TachesAdapter.MyViewHolder> {
    public List<Taches> list;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TacheNom;
        public TextView TacheAvencement;
        public TextView TacheDateLimite;
        public TextView TacheTempsEcouler;
        public MyViewHolder(LinearLayout v) {
            super(v);
            TacheNom = v.findViewById(R.id.TacheNom);
            TacheAvencement = v.findViewById(R.id.TacheAvencement);
            TacheDateLimite = v.findViewById(R.id.TacheDateLimite);
            TacheTempsEcouler = v.findViewById(R.id.TacheTempsEcouler);
        }
    }


    public TachesAdapter()
    {
        list = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TachesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.taches_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        Log.i("DEBOGAGE", "apple a onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Taches t = list.get(position);

        holder.TacheNom.setText("Activitée: " + t.nom);
        holder.TacheAvencement.setText("Progrès: " + t.avencementFait.toString() );
        holder.TacheDateLimite.setText("Date limite: " + t.dateLimite.toString());
        holder.TacheTempsEcouler.setText("Temps écouler: " + t.TimeSpent.toString() );

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent( holder.itemView.getContext(),InscriptionActivity.class);
                Intent i = new Intent( v.getContext() ,ConsultationActivity.class);
                i.putExtra("Position", position);
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

}
