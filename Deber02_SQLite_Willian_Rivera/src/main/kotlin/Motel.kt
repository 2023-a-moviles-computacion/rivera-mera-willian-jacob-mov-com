import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class Motel(
    private var idMotel: Int,
    private var nombreMotel: String,
    private var ubicacion: String,
    private var fechaApertura: Date,
    private var abierto: Boolean,
    private var listaPersonas: ArrayList<Persona>
){
    init {
        idMotel
        nombreMotel
        ubicacion
        fechaApertura
        abierto
        listaPersonas
    }

    fun setIdMotel (idMotel: Int){
        this.idMotel= idMotel
    }

    fun setNombreMotel (nombreMotel: String){
        this.nombreMotel = nombreMotel
    }
    fun setUbicacion (ubicacion: String){
        this.ubicacion = ubicacion
    }

    fun setFechaApertura (fechaApertura: Date){
        this.fechaApertura = fechaApertura
    }

    fun setAbierto (abierto: Boolean){
        this.abierto= abierto
    }


    fun getIdMotel (): Int{
        return idMotel
    }

    fun getNombreMotel (): String{
        return nombreMotel
    }

    fun getUbicacion (): String{
        return ubicacion
    }
    fun getFechaApertura (): Date{
        return fechaApertura
    }
    fun getAbierto (): Boolean{
        return abierto
    }

    companion object {

        fun selectMotel() {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/motel.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                print(
                    "Número Motel: " + valorCadena[0] + "\n"
                            + "Nombre: " + valorCadena[1] + "\n"
                            + "ubicación: " + valorCadena[2] + "\n"
                            + "Apertura: " + valorCadena[3] + "\n"
                            + "Abierto?: " + valorCadena[4] + "\n"
                )
                println("Lista de Moteles:")
                var path = Paths.get("src/main/resources/text/persona.txt")
                Files.lines(path, Charsets.UTF_8).forEach {
                    var splitMoteles = it.split(",")
                    var idMotel = splitMoteles[0]
                    for (i in 5..valorCadena.size - 1) {
                        if (idMotel == valorCadena[i]) {
                            println("\t" + splitMoteles[0] + ") " + splitMoteles[1] + " - " + splitMoteles[2])
                        }
                    }
                }
            }
            println()
        }

        fun updateMotel(nombreMotel: String) {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/motel.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[1] == nombreMotel) {
                    var opcionUpdate = true
                    print(
                        "Número Motel: " + valorCadena[0] + "\n"
                                + "Nombre: " + valorCadena[1] + "\n"
                                + "ubicación: " + valorCadena[2] + "\n"
                                + "Apertura: " + valorCadena[3] + "\n"
                                + "Abierto?: " + valorCadena[4] + "\n"
                    )
                    println("Lista de Personas:")
                    var path = Paths.get("src/main/resources/text/persona.txt")
                    Files.lines(path, Charsets.UTF_8).forEach {
                        var splitMoteles = it.split(",")
                        var idMotel = splitMoteles[0]
                        for (i in 5..valorCadena.size - 1) {
                            if (idMotel == valorCadena[i]) {
                                println("\t" + splitMoteles[0] + ") " + splitMoteles[1] + " - " + splitMoteles[2])
                            }
                        }
                    }
                    //Ver que atributo desea modificar
                    var newString: String = ""
                    var arrayCadena = arrayOf<String>("0", "0", "0", "0", "0")
                    do {
                        println("Que desea modificar: 1) Nombre, 2) Ubicacion, 3) Apertura, 4) Abierto, 5) Lista Personas")
                        var atributoUpdate = readln().toInt()
                        when (atributoUpdate) {
                            (1) -> {
                                print("Ingrese el nuevo nombre: ")
                                var nombre = readln()
                                arrayCadena.set(0, nombre)
                            }

                            (2) -> {
                                print("Ingrese la nueva ubicación : ")
                                var ubicacion = readln()
                                arrayCadena.set(1, ubicacion)
                            }

                            (3) -> {
                                print("Ingrese la nueva fecha de apertura (AAAA-MM-DD): ")
                                var fecha = readln()
                                var auxFecha = fecha.split("-")
                                val formato = SimpleDateFormat("yyyy-MM-dd")
                                var newFecha: Date =
                                    Date(auxFecha[0].toInt() - 1900, auxFecha[1].toInt() - 1, auxFecha[2].toInt())
                                arrayCadena.set(2, formato.format(newFecha))
                            }

                            (4) -> {
                                print("Ingrese si se encuentra abierto: ")
                                var abierto = readln()
                                arrayCadena.set(3, abierto)
                            }

                            (5) -> {
                                print("Seleccione una acción 1) Agregar un cliente al motel 2) Eliminar un cliente del motel: ")
                                var opcionLista = readln().toInt()
                                if (opcionLista == 1) {
                                    println("LISTA DE PERSONAS")
                                    Persona.selectPersona()
                                    print("Seleccione las personas para agregar (1,2,...): ")
                                    var newListPersonas = readln()
                                    arrayCadena.set(4, updateListaPersonas(newListPersonas, valorCadena[0].toInt()))
                                } else {
                                    print("Seleccione las personas a eliminar (1,2,...): ")
                                    var deleteList = readln()
                                    var auxLista = deleteListaPersonas(deleteList, valorCadena[0].toInt())
                                    arrayCadena.set(4, auxLista)
                                }
                            }
                        }
                        //Ver si desea seguir actualizando la asignatura elegida
                        println("¿Desea seguir actualizando el motel seleccionado? 1) SI - 2) NO")
                        var auxOpcion = readln().toInt()
                        if (auxOpcion == 2) {
                            opcionUpdate = false //Terminar update de asignatura
                            for (i in 0..arrayCadena.size - 1) {
                                if (arrayCadena[i] == "0") {
                                    if (i == 4) { // Tomando lista de temas original de la asignatura
                                        for (j in 5..valorCadena.size - 1) {
                                            if (j == valorCadena.size - 1) {
                                                arrayCadena[i] += valorCadena[j]
                                            } else {
                                                arrayCadena[i] += valorCadena[j] + ","
                                            }
                                        }
                                    } else {
                                        arrayCadena[i] = valorCadena[i + 1]
                                    }
                                }
                            }
                            archivoUpdate += valorCadena[0] + "," + arrayCadena[0] + "," + arrayCadena[1] + "," + arrayCadena[2] + "," + arrayCadena[3] + "," + arrayCadena[4] + "\n"
                        }
                    } while (opcionUpdate == true)
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("MOTEL NO ENCONTRADO")
            } else {
                File("src/main/resources/text/motel.txt").printWriter().use { out -> out.print(archivoUpdate) }
                println("DATOS MOTEL ACTUALIZADOS")
            }
        }

        fun updateListaPersonas(lista: String, id: Int): String {
            var newLista = ""
            var path = Paths.get("src/main/resources/text/motel.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[0].toInt() == id) {
                    for (i in 5..valorCadena.size - 1) {
                        if (i == valorCadena.size - 1) {
                            newLista += valorCadena[i]
                        } else {
                            newLista += valorCadena[i] + ","
                        }
                    }
                }
            }
            return newLista + "," + lista
        }

        fun deleteListaPersonas(lista: String, id: Int): String {
            var newLista = ""
            var path = Paths.get("src/main/resources/text/motel.txt")
            var splitListaParam = lista.split(",")
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[0].toInt() == id) {
                    for (i in 5..valorCadena.size - 1) {
                        var bandera = false
                        for (j in 0..splitListaParam.size - 1) {
                            if (valorCadena[i] != splitListaParam[j]) {
                                bandera = true
                            } else {
                                bandera = false
                                break
                            }
                        }
                        if (bandera == true) {
                            newLista += valorCadena[i] + ","
                        }
                    }
                }
            }
            return removeLastNchars(newLista, 1)
        }


        fun removeLastNchars(str: String, n: Int): String {
            return str.substring(0, str.length - n)
        }

        fun deleteMotel(nombreMotel: String) {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/motel.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[1] == nombreMotel) {
                    println("MOTEL ELIMINADO")
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("DATOS NO ENCONTRADOS")
            } else {
                File("src/main/resources/text/motel.txt").printWriter().use { out -> out.print(archivoUpdate) }
            }
        }

        fun getNumMotel(): Int {
            var path = Paths.get("src/main/resources/text/motel.txt")
            var numTotal = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                numTotal += 1
            }
            return numTotal
        }

        fun setArrayListPersonaMotel(arrayPersonas: Array<Int>): ArrayList<Persona> {
            var path = Paths.get("src/main/resources/text/persona.txt")
            var listaPersonas: ArrayList<Persona> = ArrayList()
            var i = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                var stringSplit = it.split(",")
                if (i < arrayPersonas.size) {
                    if (stringSplit[0] == arrayPersonas[i].toString()) {
                        var splitFecha = stringSplit[4].split("-")
                        var temaAux = Persona(
                            stringSplit[0].toInt(),
                            stringSplit[1],
                            stringSplit[2],
                            stringSplit[3].toDouble(),
                            Date(splitFecha[0].toInt(), splitFecha[1].toInt(), splitFecha[2].toInt())
                        )
                        listaPersonas.add(temaAux)
                        i++
                    }
                }
            }
            return listaPersonas
        }
    }

    fun insertMotel(sizeArrayPersonas: Int) {
        //Enviar al archivo
        var path = Paths.get("src/main/resources/text/motel.txt")
        val formato = SimpleDateFormat("yyyy-MM-dd")
        var data =
            this.idMotel.toString() + "," + this.nombreMotel + "," + this.ubicacion + "," + formato.format(this.fechaApertura) + "," + this.abierto + ","
        var i = 1
        for (item in this.listaPersonas!!) {
            if (i < sizeArrayPersonas) {
                data += item.getIdPersona().toString() + ","
            } else {
                data += item.getIdPersona().toString()
            }
            i++
        }
        data += "\n"
        try {
            Files.write(path, data.toByteArray(), StandardOpenOption.APPEND)
            println("Motel agregado!!!\n")
        } catch (e: IOException) {
            println("No se pudo guardar el motel")
        }
    }


}

