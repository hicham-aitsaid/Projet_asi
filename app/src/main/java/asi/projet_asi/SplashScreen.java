package asi.projet_asi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    ImageView splashLogo;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashLogo = findViewById(R.id.splash_logo);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.anim2);

        splashLogo.startAnimation(anim2);
        firebaseAuth= FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                connexion();
            }
        }, 3000);
    }

    private void connexion() {
        if (firebaseAuth.getCurrentUser()!=null){

        }else {
            Intent intent =new Intent(SplashScreen.this,SignIn.class);
            startActivity(intent);
        }
    }
}
