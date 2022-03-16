package asi.projet_asi.fournisseur

class Product(val name: String, val isOnline: Boolean) {

    companion object {
        private var lastProductId = 0
        fun createProductsList(numProducts: Int): ArrayList<Product> {
            val Products = ArrayList<Product>()
            for (i in 1..numProducts) {
                Products.add(Product("Produit " + ++lastProductId, i <= numProducts / 2))
            }
            return Products
        }
    }
}