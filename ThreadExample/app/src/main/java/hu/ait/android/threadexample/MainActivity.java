package hu.ait.android.threadexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;
    private int count = 0;
    private boolean enabled = false;
    private Toast myToast;
    private View toastView;

    private class MyCountThread extends Thread {
        public void run() {
            while(enabled) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvData.setText("Counter: "+(++count));
                        // JUST FOR DEMO - custom toast
                        myToast = Toast.makeText(MainActivity.this, "Counter: "+count,
                                Toast.LENGTH_SHORT);
                        Button btnToast = (Button) toastView.findViewById(R.id.button);
                        btnToast.setText("COUNT: "+count);
                        myToast.setView(toastView);
                        myToast.show();

                    }
                });

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class MyResetTask extends TimerTask {
        @Override
        public void run() {
            count = 0;
        }
    }

    private Timer resetTimer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastView = getLayoutInflater().inflate(R.layout.my_toast,null);

        tvData = (TextView)findViewById(R.id.tvData);

        Button btnThread = (Button) findViewById(R.id.btnThread);
        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enabled = true;
                new MyCountThread().start();
                Toast.makeText(MainActivity.this, "HELLO", Toast.LENGTH_SHORT).show();

                resetTimer = new Timer();
                resetTimer.schedule(new MyResetTask(),10000,10000);
            }
        });
    }

    @Override
    protected void onStop() {
        enabled = false;

        if (resetTimer != null) {
            resetTimer.cancel();
        }

        super.onStop();
    }
}
