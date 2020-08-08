package com.example.pigeon;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
EditText usern,passw;
TextView userer,passer;
MediaPlayer mp,ep;
private String name,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         usern=findViewById(R.id.username_et);
         passw=findViewById(R.id.pass_et);
        userer=findViewById(R.id.errortext1_tv);
        passer=findViewById(R.id.errortext2_tv2);
        mp=MediaPlayer.create(this,R.raw.welcome);
        ep=MediaPlayer.create(this,R.raw.error);


        passw.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  passwordCorrect();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });
        usern.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nameCorrect(getName(usern));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });

    }


    private String getName(EditText net){
        return net.getText().toString();
    }
    private String getPassword(EditText pet){
        return pet.getText().toString();
    }

    private boolean passwordCorrect(){
        if(passw.getText().toString().isEmpty()){
            showPasserror();
            return false;
        }
        else {
            showPasswordcorrect();
            return true;}

    }

    private boolean  nameCorrect(String n){
        if(n.isEmpty()){
            showNameerror();
            return false;

        }
        else {
            showNamecorrect();
            return true;}

    }

    private void showNameerror(){
       userer.setText("Empty Username !");

    }
    private void showPasserror(){
        passer.setText("Empty Password  !");

    }
    private void showNameincorrect(){
        userer.setText("Incorrect Username !");
    }
    private void showPasswordincorrect(){
        passer.setText("Incorrect Password !");
    }
    private void showNamecorrect(){
        userer.setText(" ");
    }
    private void showPasswordcorrect(){
        passer.setText(" ");
    }
    private boolean credentialsCorrect(){

        if(name.equals("s")){
            userer.setText(" ");
            if(pass.equals("123")){
                startNewintent();
                return true;
            }
            else{
            showPasswordincorrect();
            playAudio(ep);
            return false;
            }
        }
        else{
            showNameincorrect();
            playAudio(ep);
            if(!pass.equals("123")){
                showPasswordincorrect();
                playAudio(ep);
                return false;
            }
            else{return false;}


        }

    }

    public void startNewintent(){
        playAudio(mp);
        Toast t=makeText(getApplicationContext(),"welcome"+name,Toast.LENGTH_SHORT);
        t.show();
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("value",name);
        startActivity(intent);
    }

    private void playAudio(MediaPlayer mpp){
        if(!mpp.isPlaying()){

            mpp.start();


        }


    }
    private void stopaudio(MediaPlayer mpp){
        if(mpp!=null){

            mpp.release();
            System.out.println("released");
        }


        }

        public void loginCheck(View view) {

        name=getName(usern);
        pass=getPassword(passw);

        if (nameCorrect(name)){
            if (passwordCorrect()){
             if(credentialsCorrect()) {
                finish();
            }
        }
        }
        else{
            passwordCorrect();
        }





    }


    public void toggleSound(View view) {
    }

    public void goTosignUp(View view) {

        Intent intent=new Intent(this,signActivity.class);
        startActivity(intent);

    }
}
