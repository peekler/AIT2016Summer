package hu.ait.android.multiactivitydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import hu.ait.android.multiactivitydemo.data.UserDataManager;

public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        TextView tvData = (TextView) findViewById(R.id.tvData);

        /*if (getIntent().hasExtra(MainActivity.KEY_USER)) {
            User user = (User) getIntent().getSerializableExtra(MainActivity.KEY_USER);

            tvData.setText("Hello "+user.getUserName()+
                    " you are living in "+user.getAddress());
        }*/

        tvData.setText("Hello "+ UserDataManager.getInstance().getMainUser().getUserName()+
                " you are living in "+UserDataManager.getInstance().getMainUser().getAddress());


        /*if (getIntent().hasExtra(MainActivity.KEY_USERNAME) &&
                getIntent().hasExtra(MainActivity.KEY_ADDRESS)) {

            String userName = getIntent().getStringExtra(MainActivity.KEY_USERNAME);
            String address = getIntent().getStringExtra(MainActivity.KEY_ADDRESS);

            tvData.setText("Hello "+userName+" you are living in "+address);
        }*/

    }
}
