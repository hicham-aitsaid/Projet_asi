package asi.projet_asi.fournisseur

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asi.projet_asi.R

class ProductsAdapter (private val mProducts: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>()
{
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val nameTextView = itemView.findViewById<TextView>(R.id.contact_name)

    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val ProductView = inflater.inflate(R.layout.product_layout, parent, false)
        // Return a new holder instance
        return ViewHolder(ProductView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ProductsAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val Product: Product = mProducts.get(position)
        // Set item views based on your views and data model
        val textView = viewHolder.nameTextView
        textView.setText(Product.name)


    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mProducts.size
    }
}