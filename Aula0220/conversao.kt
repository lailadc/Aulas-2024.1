class ConversaoUnidadesArea {
    private val tipos = mutableListOf("Acres", "Metros", "Milhas", "Pés")
    private var area: Double = 0.0
    private var de: Int = 0
    private var para: Int = 0

    fun inicializar(de: Int): String {
        this.de = de
        val i = this.de - 1
        val unidade: String = this.tipos[i].uppercase()
        var mostrar = when (this.tipos[i]) {
            "Milhas" -> "Quantas "
            else -> "Quantos "
        }
        mostrar += unidade
        mostrar += when (this.tipos[i]) {
            "Acres" -> " "
            "Milhas" -> " QUADRADAS "
            else -> " QUADRADOS "
        }
        mostrar += "deseja converter? "
        return mostrar
    }

    fun finalizar(area: Double, para: Int): String {
        this.area = area
        this.para = para
        return converter()
    }

    private fun opcoes(informacao: Int): String {
        var mostrar = "----------  UNIDADES DE MEDIDA  ----------\n"
        for (i in this.tipos.indices) {
            mostrar += "    ${i+1}. ${this.tipos[i]}"
            mostrar += when (this.tipos[i]) {
                "Acres" -> "\n"
                "Milhas" -> " quadradas\n"
                else -> " quadrados\n"
            }
        }
        mostrar += "Informe a unidade de área "
        mostrar += when (informacao){
            1 -> "que vai converter de: "
            else -> "a ser convertida para: "
        }
        return mostrar
    }

    private fun deAcresParaMetros(): Double {
        return this.area * 4047
    }

    private fun deMetrosParaMilhas(): Double {
        return this.area / 2589988.1
    }

    private fun deMilhasParaPes(): Double {
        return this.area * 27878400
    }

    private fun dePesParaAcres(): Double {
        return this.area / 43560
    }

    fun escolherPartida(): String {
        return opcoes(1)
    }

    fun escolherDestino(): String {
        tipos.removeAt(this.de - 1)
        return opcoes(2)
    }

    private fun converter(): String {
        val resultado: Double
        var mostrar = "${this.area} "
        when (this.de) {
            1 -> {    // CONVERTER DE ACRES
                mostrar += "acre(s)  =  "
                when (this.para) {
                    1 -> {
                        resultado = deAcresParaMetros()
                        mostrar += "$resultado metro(s) quadrado(s)"
                    }
                    2 -> {
                        this.area = deAcresParaMetros()
                        resultado = deMetrosParaMilhas()
                        mostrar += "$resultado milha(s) quadrada(s)"
                    }
                    3 -> {
                        this.area = deAcresParaMetros()
                        this.area = deMetrosParaMilhas()
                        resultado = deMilhasParaPes()
                        mostrar += "$resultado pé(s) quadrado(s)"
                    }
                }
            }

            2 -> {    // CONVERTER DE METROS
                mostrar += "metro(s) quadrado(s)  =  "
                when (this.para) {
                    1 -> {
                        this.area = deMetrosParaMilhas()
                        this.area = deMilhasParaPes()
                        resultado = dePesParaAcres()
                        mostrar += "$resultado acre(s)"
                    }
                    2 -> {
                        resultado = deMetrosParaMilhas()
                        mostrar += "$resultado milha(s) quadrada(s)"
                    }
                    3 -> {
                        this.area = deMetrosParaMilhas()
                        resultado = deMilhasParaPes()
                        mostrar += "$resultado pé(s) quadrado(s)"
                    }
                }
            }

            3 -> {    // CONVERTER DE MILHAS
                mostrar += "milha(s) quadrada(s)  =  "
                when (this.para) {
                    1 -> {
                        this.area = deMilhasParaPes()
                        resultado = dePesParaAcres()
                        mostrar += "$resultado acre(s)"
                    }
                    2 -> {
                        this.area = deMilhasParaPes()
                        this.area = dePesParaAcres()
                        resultado = deAcresParaMetros()
                        mostrar += "$resultado metro(s) quadrado(s)"
                    }

                    3 -> {
                        resultado = deMilhasParaPes()
                        mostrar += "$resultado pé(s) quadrado(s)"
                    }
                }
            }

            4 -> {  // CONVERTER DE PÉS
                mostrar += "pé(s) quadrados"
                when (this.para) {
                    1 -> {
                        resultado = dePesParaAcres()
                        mostrar += "$resultado acre(s)"
                    }
                    2 -> {
                        this.area = dePesParaAcres()
                        resultado = deAcresParaMetros()
                        mostrar += "$resultado metro(s) quadrado(s)"
                    }
                    3 -> {
                        this.area = dePesParaAcres()
                        this.area = deAcresParaMetros()
                        resultado = deMetrosParaMilhas()
                        mostrar += "$resultado milha(s) quadrada(s)"
                    }
                }
            }
        }
        mostrar += "\n\n"

        return mostrar
    }
}

fun main() {
    var continuar = true
    var converterDe: Int
    var converterPara: Int
    var destino: String
    var area: Double
    var prevErros: Char
    while (continuar) {
        val unidades = ConversaoUnidadesArea()
        print(unidades.escolherPartida())
        prevErros = readln()[0]
        converterDe = prevErros.code
        while (converterDe < 49 || converterDe > 52) {
            println("Opção inválida!")
            print(unidades.escolherPartida())
            prevErros = readln()[0]
            converterDe = prevErros.code
        }
        converterDe = prevErros.toString().toInt()

        println(unidades.inicializar(converterDe))
        area = readln().replace(',', '.').toDouble()


        destino = unidades.escolherDestino()
        print(destino)
        prevErros = readln()[0]
        converterPara = prevErros.code
        while (converterPara < 49 || converterPara > 51) {
            println("Opção inválida!")
            print(destino)
            prevErros = readln()[0]
            converterPara = prevErros.code
        }
        converterPara = prevErros.toString().toInt()

        println(unidades.finalizar(area, converterPara))

        // VERIFICANDO SE DESEJA REPETIR AÇÃO
        print("\nDeseja converter mais alguma área?\n(S/N): ")
        prevErros = readln().uppercase()[0]
        while (prevErros != 'S' && prevErros != 'N') {
            println("Não entendi...")
            print("Deseja continuar no programa?\n(S/N): ")
            prevErros = readln().uppercase()[0]
        }
        when (prevErros) {
            'S' -> continuar = true
            'N' -> continuar = false
        }
    }
}