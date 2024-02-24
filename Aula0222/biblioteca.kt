class Livro {
    private var titulo: String = ""
    private var autor: String = ""
    private var paginas: Int = 0

    fun cadastrarLivro(titulo: String, autor: String, paginas: Int): String {
        this.titulo = titulo
        this.autor = autor
        this.paginas = paginas
        return "Livro cadastrado com sucesso!"
    }

    fun solicitarTitulo(): String {
        return "Informe o título do livro que deseja cadastrar: "
    }

    fun solicitarAutor(titulo: String): String {
        return "Informe o nome do(a) autor(a) de $titulo: "
    }

    fun solicitarPaginas(titulo: String, autor: String): String {
        return "Informe quantas páginas possui $titulo de $autor: "
    }
}

class Biblioteca {
    val livrosEmprestados = ArrayList<Livro>()
    val livrosDisponiveis = ArrayList<Livro>()
    val livrosCadastrados = ArrayList<Livro>()

    private fun verificardisponibilidade(livro: Livro): Map<Boolean, String> {
        var possuiLivro = false
        var disponivel: Boolean
        val mensagem: String
        val retorno: Map<Boolean, String>
        for (i in this.livrosCadastrados.indices) {
            if (livro == this.livrosCadastrados[i]) {
                possuiLivro = true
                break
            }
        }

        if (possuiLivro) {
            if (this.livrosDisponiveis.size > this.livrosEmprestados.size) {
                disponivel = true
                for (i in this.livrosEmprestados.indices) {
                    if (livro == this.livrosEmprestados[i]) {
                        disponivel = false
                        break
                    }
                }
            } else {
                disponivel = false
                for (i in this.livrosDisponiveis.indices) {
                    if (livro == this.livrosDisponiveis[i]) {
                        disponivel = true
                        break
                    }
                }
            }

            mensagem = if (disponivel) {
                "Livro disponível!"
            } else {
                "Livro alugado..."
            }
            retorno = mapOf(disponivel to mensagem)
        } else {
            retorno = mapOf(false to "Livro não cadastrado!")
        }
        return retorno
    }

    fun emprestarLivro(livro: Livro): String{
        val verificacao = this.verificardisponibilidade(livro)
        if (verificacao.containsKey(true)) {
            this.livrosDisponiveis.remove(livro)
            this.livrosEmprestados.add(livro)
            return "Livro emprestado com sucesso!"
        }
        return verificacao.getValue(false)
    }

    fun devolverLivro(livro: Livro): String {
        val verificacao = this.verificardisponibilidade(livro)
        val mensagem = verificacao.getValue(false)
        val engano = "Livro não pertence à esta biblioteca..."
        if (verificacao.containsKey(false)) {
            if (mensagem == "Livro alugado...") {
                this.livrosEmprestados.remove(livro)
                this.livrosDisponiveis.add(livro)
            } else {
                return engano
            }
        }
        return "Livro devolvido com sucesso!"
    }
}