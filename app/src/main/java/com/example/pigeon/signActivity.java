package com.example.pigeon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signActivity extends AppCompatActivity {
EditText nameet,emailet,passet,conpasset,direcet;
TextView dirtv,errorunametv,erroremailtv,errorpasstv,errorconpasstv,progtexttv;

Button signupbtn;
CheckBox sameasnamecb;
ProgressBar progressBarpb;
    String URL_REGISTER="https://pigeonn.000webhostapp.com/insert.php";
    HttpURLConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        nameet=findViewById(R.id.username_et);
        emailet=findViewById(R.id.useremail_et);
        passet=findViewById(R.id.userpassword_et);
        conpasset=findViewById(R.id.userconpass_et);
        direcet=findViewById(R.id.directory_et);
        sameasnamecb=findViewById(R.id.asname_cb);
        dirtv=findViewById(R.id.dir_tv);
        errorunametv=findViewById(R.id.erroruser_tv);
        erroremailtv=findViewById(R.id.erroremail_tv);
        errorpasstv=findViewById(R.id.errorpassword_tv);
        errorconpasstv=findViewById(R.id.errorconpass_tv);
        signupbtn=findViewById(R.id.signUp_bt);
        progressBarpb=findViewById(R.id.progressBar_pb);
        progtexttv=findViewById(R.id.progtext_tv);
        progressBarpb.setVisibility(View.INVISIBLE);
        progtexttv.setVisibility(View.INVISIBLE);



        nameet.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(sameasnamecb.isChecked()){
                dirtv.setText(nameet.getText().toString());

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });
        emailet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if(emailvalid()){}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (passvalid()){}

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        conpasset.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(conpassvalid()) {

                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }


    public void printerror(TextView t,int error){
        if(error==1) t.setText("Please enter a non empty value");
        else if(error==2) t.setText("Must start with and Contain Alphabets ");
        else if(error==3) t.setText("Invalid E-mail format");
        else if(error==4) t.setText("Password must Contain at least 8 characters");
        else if(error==5) t.setText("Password must Contain Uppercase,Lowercase and digit");
        else t.setText("Doesn't match the password above");

    }
    public boolean enablesignnow(){

        if(nameValid()&&emailvalid()&&passvalid()&&conpassvalid()){
            signupbtn.setEnabled(true);
            return true;
        }
        else{
            signupbtn.setEnabled(false);
            return false;
        }

    }
    public boolean nameValid(){
        char charArray[]=nameet.getText().toString().toCharArray();
        if(nameet.getText().toString().isEmpty()){

            printerror(errorunametv,1);
            return false;
        }
        else if(Character.isDigit(charArray[0])|| charArray[0]==' '){

            printerror(errorunametv,2);
            return false;
        }
        else{
            errorunametv.setText(" ");
            return true;
        }



    }
    public boolean emailvalid(){
        Pattern pattern1 = Pattern.compile( "^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+");

        Matcher matcher1 = pattern1.matcher(emailet.getText().toString());


        if(emailet.getText().toString().isEmpty()){

            printerror(erroremailtv,1);
            return false;
        }
        else if (!matcher1.matches()) {
            printerror(erroremailtv,2);
            return false;
        }
        else {
            erroremailtv.setText(" ");
            return true;
        }
    }
    public boolean passvalid() {
        boolean containdigit = false;
        boolean containlc = false;
        boolean containuc = false;
        if (passet.getText().toString().isEmpty()) {

            printerror(errorpasstv,1);
            return false;
        } else if (passet.getText().toString().length() < 8) {

            printerror(errorpasstv,3);
            return false;
        } else {

            for (int i = 0; i < passet.getText().toString().length(); i++) {

                if (Character.isDigit(passet.getText().toString().charAt(i))) {
                    containdigit = true;
                } else if (Character.isLowerCase(passet.getText().toString().charAt(i))) {
                    containlc = true;
                } else {
                    containuc = true;
                }

            }
            if (containdigit && containlc && containuc) {
                errorpasstv.setText(" ");
                return true;
            }
            else {
                printerror(errorpasstv,4);
                return false;
            }
        }
    }
    public boolean conpassvalid(){

        if(conpasset.getText().toString().isEmpty()){
            printerror(errorconpasstv,1);
            return false;

        }
        else if(!conpasset.getText().toString().equals(passet.getText().toString())){
            printerror(errorconpasstv,5);
            return false;
        }
        else{
            errorconpasstv.setText("");
            return true;
        }
    }
    public void toggleDirectory(View view) {
        if(!sameasnamecb.isChecked()) {
            direcet.setVisibility(View.VISIBLE);
            direcet.setText(nameet.getText().toString());
            dirtv.setVisibility(View.INVISIBLE);
        }
        else{
            direcet.setVisibility(View.INVISIBLE);
            dirtv.setVisibility(View.VISIBLE);
            dirtv.setText(nameet.getText().toString());
        }
    }

    public void signUp(View view) {

// Instantiate the RequestQueue.
        progressBarpb.setVisibility(View.VISIBLE);
        progtexttv.setVisibility(View.VISIBLE);
        progtexttv.setText("Registiring your Account");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url =URL_REGISTER;

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       if(response.contains("Registered")) {
                           progtexttv.setText("Registered");
                           progressBarpb.setIndeterminate(true);
                           progressBarpb.setVisibility(View.INVISIBLE);
                       }
                       else{
                           progtexttv.setText("error in connection");
                           progressBarpb.setIndeterminate(true);
                           progressBarpb.setVisibility(View.INVISIBLE);
                       }
                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progtexttv.setText("error :"+error.getMessage());
                progressBarpb.setIndeterminate(true);
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<String,String>();
                params.put("name",nameet.getText().toString());
                params.put("password",passet.getText().toString());
                params.put("email",emailet.getText().toString());
                params.put("directory",direcet.getText().toString());
                return params;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);



    }

}