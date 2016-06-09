package hu.ait.android.tictactoe;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import hu.ait.android.tictactoe.view.TicTacToeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout layoutRoot = (LinearLayout) findViewById(R.id.layoutRoot);

        final TicTacToeView gameView = (TicTacToeView) findViewById(R.id.gameView);

        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clear the game board
                Snackbar.make(layoutRoot,"Are you sure?", Snackbar.LENGTH_LONG).setAction("Yes",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gameView.clearGameArea();
                            }
                        }).show();
            }
        });
    }

    public void showToastMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
