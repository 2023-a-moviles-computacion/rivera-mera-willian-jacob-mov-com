import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*

data class Persona(
    private var idPersona: Int,
    private var nombrePersona: String,
    private var domicilio: String,
    private var vecesServicio: Double,
    private var ultimaFecha: Date
) {

    init {
        idPersona
        nombrePersona
        domicilio
        vecesServicio
        ultimaFecha
    }

    fun setIdPersona(idPersona: Int) {
        this.idPersona = idPersona
    }

    fun setNombrePersona(nombrePersona: String) {
        this.nombrePersona = nombrePersona
    }

    fun setDomicilio(domicilio: String){
        this.domicilio= domicilio
    }

    fun setVecesServicio(vecesServicio: Double){
        this.vecesServicio = vecesServicio
    }

    fun setUltimaFecha(ultimaFecha: Date){
        this.ultimaFecha = ultimaFecha
    }

    fun getIdPersona (): Int{
        return idPersona
    }
    fun getNombrePersona (): String{
        return nombrePersona
    }

    fun getDomicilio (): String{
        return domicilio
    }

    fun getVecesServicio (): Double{
        return vecesServicio
    }

    fun getUltimaFecha (): Date{
        return ultimaFecha
    }

    companion object {
        fun selectPersona() {
            // Aqui se leerá el archivo
            val path = Paths.get("src/main/resources/text/persona.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                val valorCadena = it.split(",")
                println(
                    "Número: " + valorCadena[0] + "\n"
                            + "Nombre: " + valorCadena[1] + "\n"
                            + "Domicilio: " + valorCadena[2] + "\n"
                            + "Veces que ha usado el servicio: " + valorCadena[3] + "\n"
                            + "Ultima fecha que fue: " + valorCadena[4] + "\n"
                )
            }
        }

        fun updatePersona(nombrePersona: String) {
            // Leer archivo
            val path = Paths.get("src/main/resources/text/persona.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                val valorCadena = it.split(",")
                if (valorCadena[1] == nombrePersona) {
                    var opcionUpdate = true
                    println(
                        "Número de persona: " + valorCadena[0] + "\n"
                                + "Nombre: " + valorCadena[1] + "\n"
                                + "Domicilio: " + valorCadena[2] + "\n"
                                + "Veces que ha usado el servicio: " + valorCadena[3] + "\n"
                                + "Ultima fecha que fue: " + valorCadena[4] + "\n"
                    )
                    // Ver qué atributo desea modificar
                    val arrayCadena = arrayOf<String>("0", "0", "0", "0")
                    do {
                        println("Que desea modificar: 1) Nombre, 2) Domicilio, 3) Veces que ha usado el servicio , 4) Ultima fecha que fue")
                        val atributoUpdate = readln().toInt()
                        when (atributoUpdate) {
                            1 -> {
                                print("Ingrese el nuevo nombre: ")
                                val nombre = readln()
                                arrayCadena[0] = nombre
                            }

                            2 -> {
                                print("Ingrese el nuevo Domicilio: ")
                                val domicilio = readln()
                                arrayCadena[1] = domicilio
                            }

                            3 -> {
                                print("Ingrese las veces que ha ingresado: ")
                                val ingreso = readln()
                                arrayCadena[2] = ingreso
                            }

                            4 -> {
                                print("Ingrese la ultima fecha que ingreso (AAAA-MM-DD): ")
                                val fecha = readln()
                                val auxFecha = fecha.split("-")
                                val newFecha: Date =
                                    Date(auxFecha[0].toInt() - 1900, auxFecha[1].toInt() - 1, auxFecha[2].toInt())
                                val formato = SimpleDateFormat("yyyy-MM-dd")
                                arrayCadena[3] = formato.format(newFecha)
                            }
                        }
                        // Esperar una nueva actualización
                        println("¿Desea seguir cambiando los datos de la persona? 1) SI - 2) NO")
                        val auxOpcion = readln().toInt()
                        if (auxOpcion == 2) {
                            opcionUpdate = false
                            for (i in 0 until arrayCadena.size) {
                                if (arrayCadena[i] == "0") {
                                    arrayCadena[i] = valorCadena[i + 1]
                                }
                            }
                            archivoUpdate += valorCadena[0] + "," + arrayCadena[0] + "," + arrayCadena[1] + "," + arrayCadena[2] + "," + arrayCadena[3] + "\n"
                        }
                    } while (opcionUpdate)
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("Persona no registrada")
            } else {
                File("src/main/resources/text/persona.txt").printWriter().use { out -> out.print(archivoUpdate) }
                println("Datos actualizados")
            }
        }

        fun deletePersona(nombrePersona: String) {
            // Leer archivo
            val path = Paths.get("src/main/resources/text/persona.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                val valorCadena = it.split(",")
                if (valorCadena[1] == nombrePersona) {
                    println("Persona eliminada!")
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("Persona no encontrada")
            } else {
                File("src/main/resources/text/persona.txt").printWriter().use { out -> out.print(archivoUpdate) }
            }
        }

        fun getNumPersonas(): Int {
            val path = Paths.get("src/main/resources/text/persona.txt")
            var numTotal = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                numTotal += 1
            }
            return numTotal
        }

    }

    fun insertPersona() {
        // Enviar al archivo
        val path = Paths.get("src/main/resources/text/persona.txt")
        val formato = SimpleDateFormat("yyyy-MM-dd")
        val data =
            "$idPersona,$nombrePersona,$domicilio,$vecesServicio,${formato.format(ultimaFecha)}\n"
        try {
            Files.write(path, data.toByteArray(), StandardOpenOption.APPEND)
            println("Persona Agregada!!")
        } catch (e: IOException) {
            println("Fallo al ingresar persona")
        }
    }

}
