/*
 Crie uma classe chamada Pessoa em Kotlin que tenha os seguintes atributos: nome, idade e profissão.
 Em seguida, escreva um programa que crie três instâncias da classe Pessoa com informações
 diferentes e as imprima na saída.
 */

class Pessoa(var nome: String, var idade: Int, var profissao: String) {

    fun informacoes(): String {
        return "${this.nome} tem ${this.idade} anos e é ${this.profissao}."
    }
}

fun main() {
    val eu = Pessoa("Rebeca Laila", 24, "estudante")
    val namorado = Pessoa("Igor Lima", 24, "pesquisador")
    val irma = Pessoa("Ingrid Laís", 25, "concurseira")

    println(eu.informacoes())
    println(namorado.informacoes())
    println(irma.informacoes())
}