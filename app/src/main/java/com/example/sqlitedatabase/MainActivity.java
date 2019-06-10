package com.example.sqlitedatabase;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CanBeFinal")
public class MainActivity extends AppCompatActivity implements View.OnClickListener, NoteClickListener {
    private FloatingActionButton mAdd;
    private List<Note> notesList = new ArrayList<>();
    private DatabaseHelper db;
    private NoteAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        //db.deleteAll();
        notesList.addAll(db.getAllNotes());
        initView();
    }

    private void initView() {
        mAdd = findViewById(R.id.add_note);
        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new NoteAdapter(notesList, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DialogBox.showDialog(this, "Add Note", "Save", "Cancel", new DialogBox.OnDialogClickListener() {
            @Override
            public void onPosButtonClick(boolean update, String note, Dialog dialog) {
                if (update && note != null) {

                } else {
                    createNote(note);
                }
                dialog.dismiss();
                Note notes = new Note(0, note, "");
            }

            @Override
            public void onNegButtonClick(Dialog dialog) {

            }
        });
    }

    private void createNote(String note) {
       long id = db.insertNote(note);
       Note n = db.getNote(id);
       if (n != null) {
           notesList.add(n);
           mAdapter.notifyDataSetChanged();
       }
    }

    @Override
    public void onNoteClick() {

    }
}
