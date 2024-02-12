import kotlin.random.Random

fun main () {
    var nota = Random.nextInt(0, 11)
    println("nota sorteada $nota")
    if (nota >= 6)
        print("Aprovado(a)")
    else
        print("Reprovado(a)")
}