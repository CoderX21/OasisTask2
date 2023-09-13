package com.example.mytodolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    RealmResults<Note> notesList;

    public MyAdapter(Context context, RealmResults<Note> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Note note=notesList.get(position);
    holder.titleoutput.setText(note.getTitle());
    holder.descoutput.setText(note.getDescription());
    String formattedTime= DateFormat.getDateTimeInstance().format(note.createdTime);
    holder.timeoutput.setText(formattedTime);

    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            PopupMenu menu=new PopupMenu(context,v);
            menu.getMenu().add("DELETE");
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if(item.getTitle().equals("DELETE")){
                        //delete the note
                        Realm realm=Realm.getDefaultInstance();
                        realm.beginTransaction();
                        note.deleteFromRealm();
                        realm.commitTransaction();
                        Toast.makeText(context,"Note Deleted",Toast.LENGTH_LONG).show();
                    }
                    return true;
                }
            });
            menu.show();
            return true;
        }
    });


    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView descoutput;
        TextView titleoutput;
        TextView timeoutput;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleoutput=itemView.findViewById(R.id.titleoutput);
            descoutput=itemView.findViewById(R.id.descoutput);
            timeoutput=itemView.findViewById(R.id.timeoutput);

        }
    }
}
