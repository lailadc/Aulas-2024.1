class Product {
    var name: String = ""
    var price: Double = 0.0

    constructor(name: String, price: Double) {
        this.name = name
        this.price = price
    }
}

fun main() {
    var prod = Product("produto", 50.0)
    println("The product ${prod.name} has price ${prod.price}")
}