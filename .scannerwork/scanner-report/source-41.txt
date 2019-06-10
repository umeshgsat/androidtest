package com.example.sqlitedatabase;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Note>mNotesList;
    private NoteClickListener mClickListener;

    public NoteAdapter(List<Note> mNotesList, NoteClickListener listener) {
        this.mNotesList = mNotesList;
        this.mClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item, viewGroup, false);
       return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       Note note = mNotesList.get(position);
        ((NoteViewHolder) holder).mDate.setText(note.getTimestamp());
        ((NoteViewHolder) holder).mNote.setText(note.getNote());

        ((NoteViewHolder) holder).mHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onNoteClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotesList.size();
    }

    @SuppressWarnings("CanBeFinal")
    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView mDate;
        private TextView mNote;
        private ConstraintLayout mHolder;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            mDate = itemView.findViewById(R.id.date);
            mNote = itemView.findViewById(R.id.note_item);
            mHolder = itemView.findViewById(R.id.note_holder);
        }
    }
}
