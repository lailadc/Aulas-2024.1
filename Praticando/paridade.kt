/*
Crie um programa em Kotlin que gere 10 números inteiros aleatórios entre 1 e 50,
conte quantos deles são pares e quantos são ímpares, e exiba o resultado.
*/
import kotlin.random.Random

fun main() {
    var numero: Int
    var mensagem: String
    var pares = 0
    var impares = 0

    for (i in 1..10) {
        numero = Random.nextInt(1,50)
        mensagem = "O número $numero é "

        if (numero % 2 == 0) {
            mensagem += "par."
            pares++
        } else {
            mensagem += "ímpar."
            impares++
        }

        println(mensagem)
    }

    println("\nForam gerados $pares números pares e $impares ímpares.")
}
