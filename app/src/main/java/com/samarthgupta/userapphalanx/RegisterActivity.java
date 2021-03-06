package com.samarthgupta.userapphalanx;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by samarthgupta on 13/02/17.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputfname, inputlname, inputmno , inputcity,inputicode;
    private Button btnRegister;
   // private ProgressBar progressBar;
    private FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference ref ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);

        //Get Firebase auth instance
            auth = FirebaseAuth.getInstance();
            ref = firebaseDatabase.getReference();
            user=auth.getCurrentUser();

            btnRegister = (Button)findViewById(R.id.btn1);
            inputEmail = (EditText) findViewById(R.id.editText3);
            inputPassword = (EditText) findViewById(R.id.editText5);
          //  progressBar = (ProgressBar) findViewById(R.id.progressBar);
            inputcity = (EditText)findViewById(R.id.editText8);
            inputfname=(EditText)findViewById(R.id.editText1);
            inputlname= (EditText)findViewById(R.id.editText2);
            inputmno = (EditText)findViewById(R.id.editText7);
            inputicode = (EditText)findViewById(R.id.editText9);


            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String email = inputEmail.getText().toString().trim();
                    final String password = inputPassword.getText().toString().trim();
                    final String fname = inputfname.getText().toString().trim();
                    final String lname = inputlname.getText().toString().trim();
                    final String mno = inputmno.getText().toString().trim();
                    final String city = inputcity.getText().toString().trim();
                    final String icode = inputicode.getText().toString().trim();



                    //  CHECKING ALL EDIT TEXT FIELDS
                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    else if (TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                   else if (password.length() < 6) {
                        Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                   else if (TextUtils.isEmpty(fname)||TextUtils.isEmpty(lname)) {
                        Toast.makeText(getApplicationContext(), "Enter First and Last name", Toast.LENGTH_SHORT).show();
                        return;
                    }

                   else if (TextUtils.isEmpty(mno)) {
                        Toast.makeText(getApplicationContext(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                   else if (mno.length()!=10) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    else if (TextUtils.isEmpty(city)) {
                        Toast.makeText(getApplicationContext(), "Enter your city", Toast.LENGTH_SHORT).show();
                        return;
                    }
                   // progressBar.setVisibility(View.VISIBLE);
                    //create user
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                  //  progressBar.setVisibility(View.GONE);
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),Toast.LENGTH_SHORT).show();
                                    } else {

                                        UserInfo userInfo = new UserInfo(email,password,fname,lname,mno,city,icode);
                                        ref.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userInfo);
                                        startActivity(new Intent(RegisterActivity.this, SigninActivity.class));
                                        finish();
                                    }
                                }
                            });

                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //progressBar.setVisibility(View.GONE);
    }


}
