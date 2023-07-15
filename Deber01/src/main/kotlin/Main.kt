import java.util.Date

fun main(args: Array<String>) {

    do {
        var opcionUsuario = false
        println(
            "BOHEMIO- MOTELES!! SELECCIONE EL TEMA:" +
                    "\n1) Personas" +
                    "\n2) Moteles" +
                    "\n3) Salir"
        )
        var opTabla = readln().toInt()
        if (opTabla != 3) {
            var opcionAux = false
            var textoConsola = ""
            if (opTabla == 1) {
                textoConsola += "Que desea:" +
                        "\n1) Ingresar nuevos datos de personas" +
                        "\n2) Ver clientes" +
                        "\n3) Actualizar cliente" +
                        "\n4) Eliminar cliente" +
                        "\n5) Volver"
            } else {
                textoConsola += "Elija una opción:" +
                        "\n1) Ingresar un nuevo motel" +
                        "\n2) Ver moteles" +
                        "\n3) Actualizar moteles" +
                        "\n4) Eliminar moteles" +
                        "\n5) Volver"
            }
            while (!opcionAux) {
                println(textoConsola)
                var opcionCrud = readln().toInt()
                when (opcionCrud) {
                    (1) -> {
                        if (opTabla == 1) {
                            print("Nombre Persona: ")
                            var nombre = readln()
                            print("Domicilio: ")
                            var domicilio = readln()
                            print("Veces que se ha usado el servicio: ")
                            var ingreso = readln().toDouble()
                            print("Utima fecha que fue (AAAA-MM-DD): ")
                            var fecha = readln()
                            var fechaSplit = fecha.split("-")
                            var fechaAux: Date =
                                Date(fechaSplit[0].toInt() - 1900, fechaSplit[1].toInt() - 1, fechaSplit[2].toInt())

                            var newPersona =
                                Persona(Persona.getNumPersonas() + 1, nombre, domicilio, ingreso, fechaAux)
                            newPersona.insertPersona()

                        } else {
                            print("Nombre del  Motel: ")
                            var nombre = readln()
                            print("Ubicación: ")
                            var ubicacion = readln()
                            print("Fecha de Apertura (AAAA-MM-DD): ")
                            var fecha = readln()
                            var fechaSplit = fecha.split("-")
                            var fechaAux: Date =
                                Date(fechaSplit[0].toInt() - 1900, fechaSplit[1].toInt() - 1, fechaSplit[2].toInt())
                            print("Se encuentra abierto? 1)SI 2)NO: ")
                            var abiertoAux = readln().toInt()
                            var abierto: Boolean

                            if (abiertoAux == 1) {
                                abierto = true
                            } else {
                                abierto = false
                            }
                            println("\n LISTA DE PERSONAS PARA AGENDAR")
                            Persona.selectPersona()

                            print("Seleccione las personas para agendar (1,2,...): ")
                            var stringPersonas = readlnOrNull().toString()
                            var splitPersonas = stringPersonas.split(",")
                            var intPersonasArray = splitPersonas.map { it.toInt() }.toTypedArray()
                            var listaMotel: ArrayList<Persona> = Motel.setArrayListPersonaMotel(intPersonasArray)

                            var newMotel = Motel(Motel.getNumMotel() + 1, nombre, ubicacion, fechaAux, abierto, listaMotel)
                            newMotel.insertMotel(intPersonasArray.size)

                        }
                    }

                    (2) -> {
                        if (opTabla == 1) {
                            println("Lista de personas:")
                            Persona.selectPersona()
                        } else {
                            println("Lista de moteles")
                            Motel.selectMotel()
                        }
                    }

                    (3) -> {
                        if (opTabla == 1) {
                            print("Ingrese el nombre de la persona a cambiar: ")
                            var searchPersona = readln()
                            Persona.updatePersona(searchPersona)
                        } else {
                            print("Ingrese el nombre del Motel a cambiar: ")
                            var searchMotel = readln()
                            Motel.updateMotel(searchMotel)
                        }
                    }

                    (4) -> {
                        if (opTabla == 1) {
                            print("Ingrese el nombre de la persona que desee eliminar: ")
                            var searchPersona = readln()
                            Persona.deletePersona(searchPersona)
                        } else {
                            print("Ingrese el nombre del motel que desee eliminar: ")
                            var searchMotel = readln()
                            Motel.deleteMotel(searchMotel)
                        }
                    }

                    (5) -> {
                        opcionAux = true
                    }
                }
            }
        } else {
            opcionUsuario = true
        }
    } while (!opcionUsuario)


}

