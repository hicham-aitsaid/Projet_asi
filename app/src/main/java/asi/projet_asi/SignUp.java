package asi.projet_asi;

import static android.graphics.Color.TRANSPARENT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ImageView back,user,fournisseur;
    Button connect;
    TextView t_user,t_fournisseur;
    Spinner wilaya;
    EditText name,email,pswrd,num,adresse;
    private View baseView;
    String nom,em,motpasse,tlphn,lawilaya,adr,id,type;
    CardView c_user,c_fournisseur;
    boolean u,f;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        back=findViewById(R.id.back);
        connect=findViewById(R.id.connect);
        wilaya=findViewById(R.id.wilaya);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        pswrd=findViewById(R.id.pswrd);
        num=findViewById(R.id.num);
        adresse=findViewById(R.id.adresse);
        user=findViewById(R.id.user);
        fournisseur=findViewById(R.id.fournisseur);
        baseView=findViewById(R.id.linear_layout);
        c_user=findViewById(R.id.c_user);
        c_fournisseur=findViewById(R.id.c_fournisseur);
        t_user=findViewById(R.id.user_T);
        t_fournisseur=findViewById(R.id.fournisseur_T);
        firebaseAuth=FirebaseAuth.getInstance();

        u=false;
        f=false;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        c_user.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                u=true;
                f=false;
                type="client";
                t_user.setTextColor(R.color.color1);
                user.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color1), android.graphics.PorterDuff.Mode.MULTIPLY);
                t_fournisseur.setTextColor(R.color.black);
                fournisseur.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);
            }
        });

        c_fournisseur.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                f=true;
                u=false;
                type="fournisseur";
                t_fournisseur.setTextColor(R.color.color1);
                fournisseur.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color1), android.graphics.PorterDuff.Mode.MULTIPLY);
                t_user.setTextColor(R.color.black);
                user.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.wilaya, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wilaya.setAdapter(adapter);
        wilaya.setOnItemSelectedListener(SignUp.this);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom=name.getText().toString();
                em=email.getText().toString();
                motpasse=pswrd.getText().toString();
                tlphn=num.getText().toString();
                adr=adresse.getText().toString();

                if (u==false && f==false){
                    Snackbar snackbar1 = Snackbar.make(baseView, "Sélectionner votre type", Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                }else if (nom.length()<3){
                    Snackbar snackbar1 = Snackbar.make(baseView, "Saisissez votre nom", Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                }else if (!isEmailValid(em)){
                    Snackbar snackbar1 = Snackbar.make(baseView, "Saisissez un email valide", Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                }else if (motpasse.length()<5){
                    Snackbar snackbar1 = Snackbar.make(baseView, "Saisissez un mot passe valide", Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                }else if (tlphn.length()<10){
                    Snackbar snackbar1 = Snackbar.make(baseView, "Saisissez un numero de télphone valide", Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                }else if (adr.length()<5){
                    Snackbar snackbar1 = Snackbar.make(baseView, "Saisissez une adresse valide", Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                }else if (lawilaya.equalsIgnoreCase("wilaya")){
                    Snackbar snackbar1 = Snackbar.make(baseView, "Saisissez votre wilaya", Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                }
                else {
                    Auth_user();
                }
            }
        });

    }

    private void Auth_user() {
        firebaseAuth.createUserWithEmailAndPassword(em,motpasse).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    id=firebaseAuth.getCurrentUser().getUid();
                    adduser(nom,em,lawilaya,adr,id,tlphn,type);
                } else {
                    Snackbar snackbar1 = Snackbar.make(baseView, "Authentification échoué", Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                }
            }
        });
    }

    private void adduser(String nom, String em, String lawilaya, String adr, String id, String tlphn, String type) {

    }

    private boolean isEmailValid(String em) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(em).matches();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        lawilaya = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Snackbar snackbar1 = Snackbar.make(baseView, "Saisissez votre wilaya", Snackbar.LENGTH_SHORT);
        snackbar1.show();
    }
}