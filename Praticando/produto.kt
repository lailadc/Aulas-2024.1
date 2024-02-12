/*
Crie uma classe chamada Produto em Kotlin para representar produtos em uma loja online.
A classe deve ter os seguintes atributos: nome, preço e quantidade em estoque.
Escreva um programa que crie alguns produtos, atualize seus preços e quantidades, e exiba
as informações dos produtos na saída.
 */

class Produto(private val nome: String, private var preco: Double, private var estoque: Int) {
    fun info(): String {
        var saida = "----------  ${this.nome.uppercase()}  ----------\n"
        saida += "Preço:  R$ ${String.format("%.2f", this.preco)}\n"
        saida += "Estoque:  "
        saida += when(this.estoque) {
            0 -> "VAZIO"
            1 -> "1 unidade"
            else -> "${this.estoque} unidades"
        }
        saida += "\n\n"
        return saida
    }

    fun atualizarPreco(novoPreco: Double): String {
        this.preco = novoPreco
        return "Preço atualizado com sucesso!\n\n"
    }

    private fun aumentarEstoque(unidades: Int): String {
        var mensagem: String
        if (unidades <= 0) {
            mensagem = "Quantidade inválida.\n"
        } else {
            this.estoque += unidades
            mensagem = "Estoque atual:  "
            mensagem += when(this.estoque) {
                1 -> "1 unidade"
                else -> "${this.estoque} unidades"
            }
            mensagem += "\n"
        }
        return mensagem
    }

    private fun diminuirEstoque(unidades: Int): String {
        var mensagem: String
        if (unidades > this.estoque) {
            mensagem = "Estoque insuficiente!\n"
        } else if (unidades <= 0) {
            mensagem = "Quantidade inválida.\n"
        } else {
            this.estoque -= unidades
            mensagem = "Estoque "
            mensagem += when(this.estoque) {
                0 -> "atual: VAZIO"
                1 -> "restante:  1 unidade"
                else -> "restante:  ${this.estoque} unidades"
            }
            mensagem += "\n"
        }
        return mensagem
    }

    fun atualizarEstoque(vendeu: Boolean, qtdd: Int = 1): String {
        var mensagem = "Estoque atualizado com sucesso!\n"
        mensagem += if (vendeu) {
            diminuirEstoque(qtdd)
        } else {
            aumentarEstoque(qtdd)
        }
        return mensagem
    }
}

fun main() {
    val garrafa = Produto("Garrafa térmica", 298.97, 0)
    val bandana = Produto("Bandana", 15.0, 100)

    println(bandana.info())
    println(bandana.atualizarEstoque(true))
    println(bandana.info())
    println(bandana.atualizarEstoque(true, 98))
    println(bandana.atualizarPreco(19.98))
    println(bandana.info())
    println("-----------------------------------------------")
    println(garrafa.info())
    println(garrafa.atualizarEstoque(false, 50))
    println(garrafa.info())

}