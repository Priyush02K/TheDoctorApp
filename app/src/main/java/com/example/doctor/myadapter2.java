package com.example.doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class myadapter2 extends FirebaseRecyclerAdapter<model2,myadapter2.myviewholder>
{
    String c,e,user;
    private Context mcontext;
    public myadapter2(@NonNull FirebaseRecyclerOptions<model2> options, String u,Context context) {

        super(options);
        mcontext=context;
        user=u;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model2 model)
    {
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.number.setText(model.getNumber());
        holder.cat.setText(model.getCat());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow2,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name,email,number,cat;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.drname);
            email=(TextView)itemView.findViewById(R.id.drmail);
            number=(TextView)itemView.findViewById(R.id.drphone);
            cat= itemView.findViewById(R.id.drcat);

        }
    }
}
