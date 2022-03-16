package asi.projet_asi

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import asi.projet_asi.fournisseur.fournisseur_home

class chooser : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chooser)

        val cli = findViewById<LinearLayout>(R.id.cli)
        val fourn = findViewById<LinearLayout>(R.id.fourn)

        cli.setOnClickListener(View.OnClickListener {
                startActivity(Intent(this,home_client::class.java))
        })

        fourn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this.applicationContext,fournisseur_home::class.java))
        })
    }
}