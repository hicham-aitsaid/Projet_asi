package asi.projet_asi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import asi.projet_asi.fournisseur.fournisseur_home
import asi.projet_asi.fournisseur.fournisseur_profil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, fournisseur_home::class.java))
    }
}