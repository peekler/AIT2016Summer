package hu.ait.android.todo;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import hu.ait.android.todo.items.TodosAdapter;
import hu.ait.android.todo.model.Todo;
import hu.ait.android.todo.swipeanddrag.TodoItemTouchHelperCallback;

public class TodosActivity extends AppCompatActivity {

    public static final int NEW_ITEM_REQUEST_CODE = 1;
    private TodosAdapter todosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);

        initTodosList();

        startItemAdderActivityWhenAddButtonClicked();
    }

    private void initTodosList() {
        RecyclerView todosRecyclerView = (RecyclerView) findViewById(R.id.todos);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        todosRecyclerView.setLayoutManager(layoutManager);

        todosAdapter = new TodosAdapter();
        todosRecyclerView.setAdapter(todosAdapter);

        ItemTouchHelper.Callback callback =
                new TodoItemTouchHelperCallback(todosAdapter, todosRecyclerView);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(todosRecyclerView);
    }

    private void startItemAdderActivityWhenAddButtonClicked() {
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(TodosActivity.this, TodoAdderActivity.class), NEW_ITEM_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_ITEM_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                Todo newTodo = (Todo) data.getSerializableExtra(TodoAdderActivity.TODO_KEY);
                todosAdapter.addItem(newTodo);
            }else{
                Toast.makeText(this, R.string.sorry, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
