/*
Simule um lançamento de dados em Kotlin. Gere um número randômico entre 1 e 6 para
representar o resultado de um dado de seis lados. Imprima o número gerado.
 */
import kotlin.random.Random

fun main() {
    val dado: Int = Random.nextInt(1, 6)

    println("O dado caiu no número $dado")
}