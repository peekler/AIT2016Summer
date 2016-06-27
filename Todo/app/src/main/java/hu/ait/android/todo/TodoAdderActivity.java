package hu.ait.android.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hu.ait.android.todo.model.Todo;

/**
 * Created by Éva on 6/22/2016.
 */
public class TodoAdderActivity extends AppCompatActivity {

    public static final String TODO_KEY = "newTodo";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_adder);

        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newTodoTitleEdittext = (EditText) findViewById(R.id.newTodoTitle);
                String newTodoTitle = newTodoTitleEdittext.getText().toString();
                Todo newTodo = new Todo(newTodoTitle, false);
                Intent result = new Intent();
                result.putExtra(TODO_KEY, newTodo);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }

}
