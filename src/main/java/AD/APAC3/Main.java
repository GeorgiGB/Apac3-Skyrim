package AD.APAC3;

import Peticiones.APAC3.ClasesConnect;
import Peticiones.APAC3.FaccionesConnect;
import Peticiones.APAC3.JugadoresConnect;
import Peticiones.APAC3.RazasConnect;

public class Main {
    public static void main(String[] args) {
        Utilidades.espacioVacio(15);
        Utilidades.liniadoble(32);

        //String res = Utilidades.leerTexto("Dime quieres hacer: ");
        String res;
        System.out.println("APAC 3 'BASE DE DATOS DE SKYRIM'");
        Utilidades.liniadoble(32);
        do {
            //Utilidades.Menu();
            res = Utilidades.leerTexto("Dime quieres hacer: ");

            switch (res) {
                case "help":
                    Utilidades.Menu();
                    break;
                default://poder identificar si usamos el -c o no
                    String[] orden = res.split(" ");
                    switch (orden[0]) {
                        case "show"://Enseñara las clases en cascade
                            if (orden.length > 2 && orden[1].equals("-c")) {
                                if (orden[2].equals("Jugadores") || orden[2].equals("jugadores")) {
                                    JugadoresConnect.showObjectsFromJugadores();
                                }
                                if (orden[2].equals("Facciones") || orden[2].equals("facciones")) {
                                    FaccionesConnect.showObjectsFromFaccionesCascade();
                                }
                                if (orden[2].equals("Razas") || orden[2].equals("razas")) {
                                    RazasConnect.showObjectsFromRazasCascade();
                                }
                                if (orden[2].equals("Clases") || orden[2].equals("clases")) {
                                    ClasesConnect.showObjectsFromClasesCascade();
                                }
                            }//sin la Cascade
                            if (orden[1].equals("Jugadores") || orden[1].equals("jugadores")) {
                                JugadoresConnect.showObjectsFromJugadores();
                            }
                            if (orden[1].equals("Facciones") || orden[1].equals("facciones")) {
                                FaccionesConnect.showObjectsFromFacciones();
                            }
                            if (orden[1].equals("Razas") || orden[1].equals("razas")) {
                                RazasConnect.showObjectsRazasTable();
                            }
                            if (orden[1].equals("Clases") || orden[1].equals("clases")) {
                                ClasesConnect.showObjectsFromClases();
                            }
                            break;
                        case "add":
                            if (orden[1].equals("Jugadores") || orden[1].equals("jugadores")) {
                                JugadoresConnect.createObjectsFromTable();
                            }
                            if (orden[1].equals("Facciones") || orden[1].equals("facciones")) {
                                FaccionesConnect.createObjectsFromTable();
                            }
                            if (orden[1].equals("Razas") || orden[1].equals("razas")) {
                                RazasConnect.createObjectsFromTable();
                            }
                            if (orden[1].equals("Clases") || orden[1].equals("clases")) {
                                ClasesConnect.createObjectsFromTable();
                            }
                            break;
                        case "delete":
                            if (orden[1].equals("Jugadores") || orden[1].equals("jugadores")) {
                                JugadoresConnect.deleteObjectsFromJugadors();
                            }
                            if (orden[1].equals("Facciones") || orden[1].equals("facciones")) {
                                FaccionesConnect.deleteObjectsFromFacciones();
                            }
                            if (orden[1].equals("Razas") || orden[1].equals("razas")) {
                                RazasConnect.deleteObjectsFromRazas();
                            }
                            if (orden[1].equals("Clases") || orden[1].equals("clases")) {
                                ClasesConnect.deleteObjectsFromClases();
                            }
                            break;
                        case "update":
                            if (orden[1].equals("Jugadores") || orden[1].equals("jugadores")) {
                                JugadoresConnect.updateObjectsFromJugadors();
                            }
                            if (orden[1].equals("Facciones") || orden[1].equals("facciones")) {
                                FaccionesConnect.updateObjectsFromFacciones();
                            }
                            if (orden[1].equals("Razas") || orden[1].equals("razas")) {
                                RazasConnect.updateObjectsFromRazas();
                            }
                            if (orden[1].equals("Clases") || orden[1].equals("clases")) {
                                ClasesConnect.updateObjectsFromClases();
                            }
                            break;
                        default:
                            Utilidades.espacioVacio(2);
                            System.out.println("El comando '" + res + "' es incorrecto, escribe <help> para obtener mas ayuda.");
                    }
            }//Mientras la respuesta no sea igual a 'quit' el programa seguira
        } while (!res.equals("quit"));

        System.out.println("Fin del programa.");
        Utilidades.liniadoble(25);
    }
}



/*
Problema de este modo en el show -c y el show normal siempre hace las 2 funciones ya que se cumplen las dos condiciones
                if (res.contains("show")) {
                    if (res.contains("Jugador")) {
                        JugadoresConnect.showObjectsFromTable();

                    } else if (res.contains("Facciones")) {
                        FaccionesConnect.showObjectsFromTableCascade();
                    } else if (res.contains("Razas")) {

                    } else if (res.contains("Clases")) {

                    } else {
                        System.out.println("No existe esa tabla.");
                    }
                }

                if (res.contains("show -c")) {
                    if (res.contains("Jugador")) {
                        JugadoresConnect.showObjectsFromJugadorsCascade();
                    } else if (res.contains("Facciones")) {
                        FaccionesConnect.showObjectsFromTableCascade();
                    } else if (res.contains("Razas")) {
                        RazasConnect.showObjectsFromTableCascade();

                    } else if (res.contains("Clases")) {

                    } else {
                        System.out.println("No existe esa tabla.");
                    }
                }
*/



