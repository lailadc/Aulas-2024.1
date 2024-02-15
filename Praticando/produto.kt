/*
Crie uma classe chamada Produto em Kotlin para representar produtos em uma loja online.
A classe deve ter os seguintes atributos: nome, preço e quantidade em estoque.
Escreva um programa que crie alguns produtos, em seguida crie um menu que contenha as opções de
atualizar o produto respectivamente seus preços e quantidades, e exiba as informações dos produtos na saída.

 */

class Produto(var nome: String, private var preco: Double, private var estoque: Int = 0) {
    fun info(): String {
        var saida = "----------  ${this.nome}  ----------\n"
        saida += "Preço:  R$ ${String.format("%.2f", this.preco)}\n"
        saida += "Estoque:  "
        saida += when(this.estoque) {
            0 -> "VAZIO"
            1 -> "1 unidade"
            else -> "${this.estoque} unidades"
        }
        saida += "\n"
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
            mensagem = "Estoque atualizado com sucesso!\n"
            mensagem += "Estoque atual:  "
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
            mensagem = "Venda realizada com sucesso!\n"
            mensagem += "Estoque "
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
        val mensagem: String = if (vendeu) {
            diminuirEstoque(qtdd)
        } else {
            aumentarEstoque(qtdd)
        }
        return mensagem
    }
}

fun main() {
    var produtos = mutableListOf(
        Produto("GARRAFA TÉRMICA", 149.98, 20),
        Produto("BANDANA", 15.0, 100)
    )
    var produto: Produto
    var nome: String
    var preco: Double
    var estoque: Int
    var inicializarEstoque: Char
    var prevErros: Char
    var opcao: Char
    var existe: Boolean
    var continuar: Boolean
    var i: Int
    val menu: String = "--------------- MENU ---------------\n" +
            "   1.  Adicionar produto\n" +
            "   2.  Modificar produto\n" +
            "   3.  Excluir produto\n" +
            "   X.  Encerrar programa\n" +
            "Informe o número correspondente à opção desejada:  "
    var instrucao: String
    var escolhido: Int
    val modificar: String = "---------- OPÇÕES ----------\n" +
            "   1.  Atualizar preço\n" +
            "   2.  Atualizar estoque\n" +
            "   V.  Voltar ao menu principal\n" +
            "   X. Encerrar programa"



    principal@ while (true) {
        for (prod: Produto in produtos) {
            println(prod.info())
        }
        println()
        print(menu)
        opcao = readln().uppercase()[0]
        when (opcao) {
            '1' -> {
                continuar = true
                while (continuar) {
                    existe = false
                    print("\n\n\nInforme o nome do novo produto:  ")
                    nome = readln().uppercase()
                    for (prod: Produto in produtos) {  // VERIFICANDO SE EXISTE
                        if (prod.nome == nome) {
                            println("\nProduto já existente!\n")
                            existe = true
                            break
                        }
                    }
                    if (!existe) {   // ADICIONANDO PRODUTO
                        print("Informe o preço de $nome:  R$ ")
                        preco = readln().replace(",", ".").toDouble()
                        print("Já possui $nome para o estoque?\n(S/N): ")
                        inicializarEstoque = readln().uppercase()[0]
                        while (inicializarEstoque != 'S' && inicializarEstoque != 'N') {
                            println("Não entendi...")
                            print("Já possui $nome para o estoque?\n(S/N): ")
                            inicializarEstoque = readln().uppercase()[0]
                        }
                        when (inicializarEstoque) {
                            'S' -> {
                                print("Qual a quantidade de $nome que há no estoque? ")
                                estoque = readln().toInt()
                                produtos.add(Produto(nome, preco, estoque))
                            }

                            'N' -> produtos.add(Produto(nome, preco))
                        }
                    }

                    // VERIFICANDO SE DESEJA REPETIR AÇÃO
                    print("Deseja ADICIONAR mais algum produto?\n(S/N): ")
                    prevErros = readln().uppercase()[0]
                    while (prevErros != 'S' && prevErros != 'N') {
                        println("Não entendi...")
                        print("Deseja adicionar MAIS ALGUM produto?\n(S/N): ")
                        prevErros = readln().uppercase()[0]
                    }
                    when(prevErros) {
                        'S' -> continuar = true
                        'N' -> continuar = false
                    }
                }
            }
            '2' -> {  // MODIFICAR PRODUTO
                continuar = true
                while (continuar) {
                    i = 1
                    instrucao = "\n\n\nQual produto deseja MODIFICAR?\n"
                    for (prod: Produto in produtos) {
                        instrucao += "    $i.  ${prod.nome}\n"
                        i++
                    }
                    instrucao += "    V.  Voltar ao menu principal\n"
                    instrucao += "    X.  Encerrar programa\n"
                    instrucao += "Informe o número correspondente ao produto:  "
                    print(instrucao)

                    prevErros = readln().uppercase()[0]
                    when (prevErros) {
                        'V' -> continue@principal
                        'X' -> break@principal
                        else -> {
                            escolhido = prevErros.code
                            if (escolhido in 48..57) {
                                escolhido = prevErros.toString().toInt()
                                if (escolhido in 1..produtos.size) {
                                    escolhido--
                                    produto = produtos[escolhido]
                                    println(modificar)
                                    println("O que deseja modificar em ${produto.nome}?")
                                    print("Informe o número correspondente à opção desejada:  ")
                                    prevErros = readln().uppercase()[0]
                                    when (prevErros) {
                                        '1' -> {    // ATUALIZAR PREÇO
                                            println(produto.info())
                                            print("\n\nInforme o novo preço de ${produto.nome}:  R$ ")
                                            preco = readln().replace(',', '.').toDouble()
                                            println(produto.atualizarPreco(preco))
                                        }

                                        '2' -> {    // VERIFICAÇÃO PARA ATUALIZAR ESTOQUE
                                            println("Escolha:")
                                            println("   1.  ${produto.nome} foi vendido para um(a) cliente")
                                            println("   2.  ${produto.nome} foi comprado para repor estoque")
                                            println("   V.  Voltar ao menu principal")
                                            println("   X. Encerrar programa")
                                            print("Informe o número correspondente à ação realizada:  ")
                                            prevErros = readln().uppercase()[0]
                                            instrucao = "Quantos produtos foram "
                                            when (prevErros) {
                                                '1' -> instrucao += "vendidos?  "
                                                '2' -> instrucao += "comprados?  "
                                                'V' -> continue@principal
                                                'X' -> break@principal
                                            }
                                            print(instrucao)
                                            estoque = readln().toInt()
                                            when (prevErros) {  // ATUALIZAR ESTOQUE
                                                '1' -> println(produto.atualizarEstoque(true, estoque))
                                                '2' -> println(produto.atualizarEstoque(vendeu = false, estoque))
                                            }
                                        }

                                        'V' -> continue@principal
                                        'X' -> break@principal
                                        else -> println("Opção inválida!\n")
                                    }
                                } else {
                                    println("Opção inválida!\n")
                                    continue
                                }
                            } else {
                                println("Opção inválida!\n")
                                continue
                            }

                            // VERIFICANDO SE DESEJA REPETIR AÇÃO
                            print("Deseja MODIFICAR mais algum produto?\n(S/N): ")
                            prevErros = readln().uppercase()[0]
                            while (prevErros != 'S' && prevErros != 'N') {
                                println("Não entendi...")
                                print("Deseja modificar MAIS ALGUM produto?\n(S/N): ")
                                prevErros = readln().uppercase()[0]
                            }
                            when(prevErros) {
                                'S' -> continuar = true
                                'N' -> continuar = false
                            }
                        }
                    }
                }
            }
            '3' -> {
                continuar = true
                while (continuar){
                    i = 1
                    instrucao = "\n\n\nQual produto deseja EXCLUIR?\n"
                    for (prod: Produto in produtos) {
                        instrucao += "    $i.  ${prod.nome}\n"
                        i++
                    }
                    instrucao += "    V.  Voltar ao menu principal\n"
                    instrucao += "    X.  Encerrar programa\n"
                    instrucao += "Informe o número correspondente ao produto:  "
                    print(instrucao)
                    prevErros = readln().uppercase()[0]
                    when (prevErros) {
                        'V' -> continue@principal
                        'X' -> break@principal
                        else -> {
                            escolhido = prevErros.code
                            if (escolhido in 48..57){
                                escolhido = prevErros.toString().toInt()
                                if (escolhido in 1..produtos.size) {
                                    escolhido--
                                    produto = produtos[escolhido]
                                    produtos.removeAt(escolhido)
                                    println("${produto.nome} excluído com sucesso!\n")
                                } else {
                                    println("Opção inválida!\n")
                                    continue
                                }
                            } else {
                                println("Opção inválida!\n")
                                continue
                            }

                            // VERIFICANDO SE DESEJA REPETIR AÇÃO
                            print("\nDeseja EXCLUIR mais algum produto?\n(S/N): ")
                            prevErros = readln().uppercase()[0]
                            while (prevErros != 'S' && prevErros != 'N') {
                                println("Não entendi...")
                                print("Deseja excluir MAIS ALGUM produto?\n(S/N): ")
                                prevErros = readln().uppercase()[0]
                            }
                            when (prevErros) {
                                'S' -> continuar = true
                                'N' -> continuar = false
                            }
                        }
                    }
                }
            }
            'X' -> break@principal
        }
        println("\n\n\n\n")
    }
    println("Programa encerrado...")
}