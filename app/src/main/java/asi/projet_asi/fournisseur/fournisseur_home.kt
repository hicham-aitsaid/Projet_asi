package asi.projet_asi.fournisseur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import asi.projet_asi.R

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class fournisseur_home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fournisseur_home)



        val button = findViewById<FloatingActionButton>(R.id.fab)
        val button2 = findViewById<FloatingActionButton>(R.id.profile)


        button.setOnClickListener {
            startActivity(Intent(this,add_product::class.java))
        }
        button2.setOnClickListener {

        }


            lateinit var Products: ArrayList<Product>

                // ...
                // Lookup the recyclerview in activity layout
                val rvProducts = findViewById<View>(R.id.rvContacts) as RecyclerView
                // Initialize Products
                Products = Product.createProductsList(20)
                // Create adapter passing in the sample user data
                val adapter = ProductsAdapter(Products)
                // Attach the adapter to the recyclerview to populate items
                rvProducts.adapter = adapter
                // Set layout manager to position the items
                rvProducts.layoutManager = LinearLayoutManager(this)
                // That's all!

        }




}