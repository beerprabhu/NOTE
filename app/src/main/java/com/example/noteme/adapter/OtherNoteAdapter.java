package com.example.noteme.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteme.Note;
import com.example.noteme.R;
import com.example.noteme.DetailNoteActivity;

import java.util.List;

public class OtherNoteAdapter extends RecyclerView.Adapter<OtherNoteAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Note> notes;

    public OtherNoteAdapter(Context context ,List<Note> notes) {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public OtherNoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_notes,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherNoteAdapter.ViewHolder holder, int position) {
        Note note = notes.get(position);
        Log.i("position..",notes.get(position).getID()+"");
if(note.getPin().equals("0")) {
    holder.title.setText(note.getTitle());
    holder.date.setText(note.getDate());
    holder.time.setText(note.getTime());
    long idd = note.getID();
    holder.nid.setText(String.valueOf(idd));
    Log.i("idn", idd + "");
    // holder.nid.setText(note.getID());
    holder.lll.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), DetailNoteActivity.class);
            intent.putExtra("ID", notes.get(position).getID());
            // Toast.makeText(view.getContext(), "item clicked"+notes.get(position).getID(), Toast.LENGTH_SHORT).show();

            Log.i("checkid", note.getID() + "");
            view.getContext().startActivity(intent);
        }
    });
}
        else{}
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,date,time,nid;
        LinearLayout lll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lll = itemView.findViewById(R.id.nll);
            nid = itemView.findViewById(R.id.nid);
            title = itemView.findViewById(R.id.ntitle);
            date = itemView.findViewById(R.id.ndate);
            time = itemView.findViewById(R.id.ntime);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(view.getContext(), "item clicked"+notes.get(getAdapterPosition()).getID(), Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(view.getContext(),DetailNoteActivity.class);
//                    intent.putExtra("ID",notes.get(getAdapterPosition()).getID());
//                    Log.i("checkid",notes.get(getAdapterPosition()).getID()+"");
//                    view.getContext().startActivity(intent);
//                }
//            });

        }
    }
}
