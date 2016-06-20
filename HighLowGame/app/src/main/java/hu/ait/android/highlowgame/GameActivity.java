package hu.ait.android.highlowgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity {


    public static final String KEY_GENERATED = "KEY_GENERATED";
    @BindView(R.id.etGuess)
    EditText etGuess;

    @BindView(R.id.tvData)
    TextView tvData;

    private int generatedNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);

        if (savedInstanceState != null &&
                savedInstanceState.containsKey(KEY_GENERATED)) {
            generatedNum = savedInstanceState.getInt(KEY_GENERATED);
        } else {
            generateNewNumber();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_GENERATED, generatedNum);

        super.onSaveInstanceState(outState);
    }

    private void generateNewNumber() {
        Random rand = new Random(System.currentTimeMillis());
        generatedNum = rand.nextInt(100); // random number between 0 and 99
    }

    @OnClick(R.id.btnGuess)
    public void guessButtonPressed() {
        int guessNum = Integer.parseInt(etGuess.getText().toString());
        if (guessNum < generatedNum) {
            tvData.setText("Your number is smaller.");
        } else if (guessNum > generatedNum) {
            tvData.setText("Your number is larger.");
        } else {
            tvData.setText("You have won, the number is: "+guessNum);

            startActivity(new Intent(this,WinActivity.class));
        }
    }
}
