package com.example.travelpakistan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }

    public void sendLink(View view) {
        EditText et = (EditText)findViewById(R.id.email);
        String email = et.getText().toString();

        if (email.equals("")) {
            Toast.makeText(this, "Fill the input field!", Toast.LENGTH_SHORT).show();
        }
        else {
            FirebaseAuth auth = FirebaseAuth.getInstance();

            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                // do something when mail was sent successfully.
                                Toast.makeText(ForgetPassword.this, "Password reset link sent!", Toast.LENGTH_SHORT).show();
                                et.setText("");
                            } else {
                                // ...
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

                                switch (errorCode) {

                                    case "ERROR_INVALID_CUSTOM_TOKEN":
                                        Toast.makeText(ForgetPassword.this, "The custom token format is incorrect. Please check the documentation.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_CUSTOM_TOKEN_MISMATCH":
                                        Toast.makeText(ForgetPassword.this, "The custom token corresponds to a different audience.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_INVALID_CREDENTIAL":
                                        Toast.makeText(ForgetPassword.this, "The supplied auth credential is malformed or has expired.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_INVALID_EMAIL":
//                                    Toast.makeText(ForgetPassword.this, "The email address is badly formatted.", Toast.LENGTH_LONG).show();
                                        et.setError("The email address is badly formatted.");
                                        et.requestFocus();
                                        break;

                                    case "ERROR_WRONG_PASSWORD":
//                                    Toast.makeText(ForgetPassword.this, "The password is invalid or the user does not have a password.", Toast.LENGTH_LONG).show();
//                                    et2.setError("password is incorrect ");
//                                    et2.requestFocus();
//                                    et2.setText("");
                                        break;

                                    case "ERROR_USER_MISMATCH":
                                        Toast.makeText(ForgetPassword.this, "The supplied credentials do not correspond to the previously signed in user.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_REQUIRES_RECENT_LOGIN":
                                        Toast.makeText(ForgetPassword.this, "This operation is sensitive and requires recent authentication. Log in again before retrying this request.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                                        Toast.makeText(ForgetPassword.this, "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_EMAIL_ALREADY_IN_USE":
//                                    Toast.makeText(ForgetPassword.this, "The email address is already in use by another account.   ", Toast.LENGTH_LONG).show();
//                                    et.setError("The email address is already in use by another account.");
//                                    et.requestFocus();
                                        break;

                                    case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                                        Toast.makeText(ForgetPassword.this, "This credential is already associated with a different user account.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_USER_DISABLED":
                                        Toast.makeText(ForgetPassword.this, "The user account has been disabled by an administrator.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_USER_TOKEN_EXPIRED":
                                        Toast.makeText(ForgetPassword.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_USER_NOT_FOUND":
                                        Toast.makeText(ForgetPassword.this, "Incorrect credentials!", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_INVALID_USER_TOKEN":
                                        Toast.makeText(ForgetPassword.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_OPERATION_NOT_ALLOWED":
                                        Toast.makeText(ForgetPassword.this, "This operation is not allowed. You must enable this service in the console.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_WEAK_PASSWORD":
//                                    Toast.makeText(ForgetPassword.this, "The given password is invalid.", Toast.LENGTH_LONG).show();
//                                    et2.setError("The password is invalid it must 6 characters at least");
//                                    et2.requestFocus();
                                        break;

                                }
                            }
                        }
                    });
        }
    }
}