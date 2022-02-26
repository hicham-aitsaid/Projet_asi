package asi.projet_asi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    Button login,signup;
    EditText email,pswrd;
    private View baseView;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        firebaseAuth=FirebaseAuth.getInstance();
        login=findViewById(R.id.connect);
        signup=findViewById(R.id.creer);
        email=findViewById(R.id.email);
        pswrd=findViewById(R.id.pswrd);
        baseView=findViewById(R.id.relative);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=email.getText().toString();
                String motpasse=pswrd.getText().toString();

                if (!isEmailValid(mail)){
                    Snackbar snackbar1 = Snackbar.make(baseView, "Saisissez un email valide", Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                }else if (motpasse.isEmpty()){
                    Snackbar snackbar1 = Snackbar.make(baseView, "Saisissez votre mot passe", Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                }else {
                    connect(mail,motpasse);
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
    }

    private boolean isEmailValid(String mail) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches();

    }

    private void connect(String mail, String motpasse) {
        firebaseAuth.signInWithEmailAndPassword(mail, motpasse)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            getTypeuser();
                           /* Intent intent=new Intent(getApplicationContext(),home.class);
                            startActivity(intent);
                            finish(); */
                        } else {
                            // If sign in fails, display a message to the user.
                            Snackbar snackbar1 = Snackbar.make(baseView, "Authentication échoué.", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }


                    }
                });
    }

    private void getTypeuser() {
    }
}