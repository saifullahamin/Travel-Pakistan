package com.example.travelpakistan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void signupFunction(View view) {
        EditText et = (EditText)findViewById(R.id.email);
        EditText et2 = (EditText)findViewById(R.id.password);
        EditText et3 = (EditText)findViewById(R.id.name);
        EditText et4 = (EditText)findViewById(R.id.number);
        EditText et5 = (EditText)findViewById(R.id.country);

        String email = et.getText().toString();
        String password = et2.getText().toString();
        String name = et3.getText().toString();
        String number = et4.getText().toString();
        String country = et5.getText().toString();


        if (email.equals("") && password.equals("") && name.equals("") && number.equals("") && country.equals("")) {
            Toast.makeText(this, "Fill all the fields!", Toast.LENGTH_SHORT).show();
        }
        else {

        Intent i = new Intent(this, Home.class);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String userId = user.getUid();

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("Users").child(userId);

                            myRef.child("Name").setValue(name);
                            myRef.child("Number").setValue(number);
                            myRef.child("Email").setValue(email);
                            myRef.child("Country").setValue(country);

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                            user.updateProfile(profileUpdates);

                            Toast.makeText(SignUp.this, "User signed up!", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            et.setText("");
                            et2.setText("");
                            et3.setText("");
                            et4.setText("");
                            et5.setText("");

                        } else {
                            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

                            switch (errorCode) {

                                case "ERROR_INVALID_CUSTOM_TOKEN":
                                    Toast.makeText(SignUp.this, "The custom token format is incorrect. Please check the documentation.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_CUSTOM_TOKEN_MISMATCH":
                                    Toast.makeText(SignUp.this, "The custom token corresponds to a different audience.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_INVALID_CREDENTIAL":
                                    Toast.makeText(SignUp.this, "The supplied auth credential is malformed or has expired.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_INVALID_EMAIL":
//                                    Toast.makeText(SignUp.this, "The email address is badly formatted.", Toast.LENGTH_LONG).show();
                                    et.setError("The email address is badly formatted.");
                                    et.requestFocus();
                                    break;

                                case "ERROR_WRONG_PASSWORD":
//                                    Toast.makeText(SignUp.this, "The password is invalid or the user does not have a password.", Toast.LENGTH_LONG).show();
//                                    et2.setError("password is incorrect ");
//                                    et2.requestFocus();
//                                    et2.setText("");
                                    break;

                                case "ERROR_USER_MISMATCH":
                                    Toast.makeText(SignUp.this, "The supplied credentials do not correspond to the previously signed in user.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_REQUIRES_RECENT_LOGIN":
                                    Toast.makeText(SignUp.this, "This operation is sensitive and requires recent authentication. Log in again before retrying this request.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                                    Toast.makeText(SignUp.this, "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_EMAIL_ALREADY_IN_USE":
//                                    Toast.makeText(SignUp.this, "The email address is already in use by another account.   ", Toast.LENGTH_LONG).show();
                                    et.setError("The email address is already in use by another account.");
                                    et.requestFocus();
                                    break;

                                case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                                    Toast.makeText(SignUp.this, "This credential is already associated with a different user account.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_USER_DISABLED":
                                    Toast.makeText(SignUp.this, "The user account has been disabled by an administrator.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_USER_TOKEN_EXPIRED":
                                    Toast.makeText(SignUp.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_USER_NOT_FOUND":
                                    Toast.makeText(SignUp.this, "There is no user record corresponding to this identifier. The user may have been deleted.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_INVALID_USER_TOKEN":
                                    Toast.makeText(SignUp.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_OPERATION_NOT_ALLOWED":
                                    Toast.makeText(SignUp.this, "This operation is not allowed. You must enable this service in the console.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_WEAK_PASSWORD":
//                                    Toast.makeText(SignUp.this, "The given password is invalid.", Toast.LENGTH_LONG).show();
                                    et2.setError("The password is invalid it must 6 characters at least");
                                    et2.requestFocus();
                                    break;

                            }
                        }
                    }
                });
        }
    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}