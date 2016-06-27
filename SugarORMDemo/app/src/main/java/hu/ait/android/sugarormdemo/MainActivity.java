package hu.ait.android.sugarormdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import hu.ait.android.sugarormdemo.model.Note;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tvData = (TextView) findViewById(R.id.tvData);
        final EditText etNote = (EditText) findViewById(R.id.etNote);
        
        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note newNote = new Note(etNote.getText().toString(),
                        new Date(System.currentTimeMillis()).toString());
                newNote.save();


                refreshData();
            }
        });
        
        Button btnQuery = (Button) findViewById(R.id.btnQuery);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });

        Button btnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note.deleteAll(Note.class);
                refreshData();
            }
        });

        refreshData();
    }

    private void refreshData() {
        List<Note> notes = Note.listAll(Note.class);

        tvData.setText("");
        for (Note note : notes) {
            tvData.append(note.getDescription()+"\n"+note.getCreateDate()+"\n\n");
        }
    }
}
