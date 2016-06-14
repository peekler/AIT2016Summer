package hu.ait.android.highlowgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnStart) Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "START", Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.btnStart)
    public void submit(View view) {
        Toast.makeText(MainActivity.this, "DEMO", Toast.LENGTH_SHORT).show();
    }
}
