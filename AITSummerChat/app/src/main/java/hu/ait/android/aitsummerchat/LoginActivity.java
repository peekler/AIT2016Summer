package hu.ait.android.aitsummerchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.dd.CircularProgressButton;

public class LoginActivity extends AppCompatActivity {

    private EditText etMail;
    private EditText etUserName;
    private EditText etPassword;
    private Button btnLogin;
    private CircularProgressButton btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMail = (EditText) findViewById(R.id.etMail);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPwd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (CircularProgressButton) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void registerUser() {
        btnRegister.setIndeterminateProgressMode(true);
        btnRegister.setProgress(50);

        BackendlessUser newUser = new BackendlessUser();
        newUser.setEmail(etMail.getText().toString());
        newUser.setProperty("userName", etUserName.getText().toString());
        newUser.setPassword(etPassword.getText().toString());

        Backendless.UserService.register(newUser, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Toast.makeText(LoginActivity.this, "REGISTRATION OK", Toast.LENGTH_SHORT).show();

                btnRegister.setProgress(100);
                btnRegister.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnRegister.setProgress(0);
                    }
                }, 2000);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(LoginActivity.this,
                        "REGISTRATION FAILED: " + fault.getMessage(),
                        Toast.LENGTH_SHORT).show();

                btnRegister.setProgress(-1);
                btnRegister.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnRegister.setProgress(0);
                    }
                }, 2000);
            }
        });
    }

    private void loginUser() {
        Backendless.UserService.login(etMail.getText().toString(),
                etPassword.getText().toString(),
                new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        startActivity(new Intent(LoginActivity.this,
                                ForumActivity.class));
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(LoginActivity.this,
                                "Login failed: "+fault.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
