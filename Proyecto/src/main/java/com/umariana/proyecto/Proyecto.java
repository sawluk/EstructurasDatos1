/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.umariana.proyecto;
import Mundo.Alumno;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Sistemas (David Noguera y Samuel Bolaños)
 */
public class Proyecto {
    
    
    public static void main(String[] args) {
        
        //Duncion que permite leer la opcion del usuario
        Scanner lector= new Scanner(System.in);
        //Bandera para terminar el programa
        boolean activo = true;
        ArrayList<Alumno> misAlumnos = cargarAlumnos();
        
        
        do{
            mostrarMenu();
        // Empleamos un switch para la seleccion de las opciones
        int opcion = lector.nextInt();
        
            switch (opcion) {
                
                case 1:
                    
                    
                    crearNuevoAlumno(misAlumnos, lector);
                    guardarAlumnos(misAlumnos);
                    break;
                    
                case 2:
                    
                    
                    eliminarAlumno(misAlumnos, lector);
                    guardarAlumnos(misAlumnos);
                    break;
                    
                case 3:
                    
                    modificarAlumno(misAlumnos, lector);
                    guardarAlumnos(misAlumnos);
                    break;
                    
                case 4:
                    
                    consultarAlumnos(misAlumnos);
                    
                    break;
                case 5:
                    
                    generarReporteSemestre(misAlumnos, lector);
                    
                    break;
                    
                case 6:
                    guardarAlumnos(misAlumnos);
                    activo = !terminarPrograma(lector);
                    System.out.println("---------------------------");
                    
                    break;
             
            default:
                    System.out.println("Debe seleccionar una opcion del menu");
            }
        
        
        } while (activo);   
    }
    
    // Funcion que despliega el menu con sus opciones
    private static void mostrarMenu() {
        
        System.out.println("--------Menu de opeciones--------");
        System.out.println("1. Insertar alumno");
        System.out.println("2. Eliminar alumno");
        System.out.println("3. Modificar alumno");
        System.out.println("4. Consultar alumno");
        System.out.println("5. Generar reporte por semestre");
        System.out.println("6. Terminar programa");
        System.out.println("----------------------------------");
    }
    
    
    /**
    * A continuacion se presentan las funciones referentes a las opciones del menu
    * @param misAlumnos La lista de alumnos donde se agregará el nuevo alumno.
    * @param lector     El objeto Scanner utilizado para leer la entrada del usuario.
    */
    
    private static void crearNuevoAlumno(ArrayList<Alumno> misAlumnos, Scanner lector) {
        
        System.out.println("--------Crear Nuevo Alumno--------");

        System.out.println("Introduce la cedula del alumno");
        int cedula = lector.nextInt();

        System.out.println("Introduce el nombre del alumno");
        String nombre = lector.next(); 

        System.out.println("Introduce el apellido del alumno");
        String apellido = lector.next(); 

        System.out.println("Introduce el semestre del alumno");
        int semestre = lector.nextInt(); 

        System.out.println("Introduce el correo del alumno");
        String correo = lector.next();

        System.out.println("Introduce el celular del alumno");
        int celular = lector.nextInt();

        //Se crea un objeto para guardar la informacion de cada alumno
        Alumno a = new Alumno();

        a.setCedula(cedula);
        a.setNombre(nombre);
        a.setApellido(apellido);
        a.setSemestre(semestre);
        a.setCorreo(correo);
        a.setCelular(celular);
        // Se agrega el valor del objeto al list array

        misAlumnos.add(a);

        System.out.println("Alumno registrado satisfactoriamente");
        System.out.println("---------------------------");
    }
    
    private static void eliminarAlumno(ArrayList<Alumno> misAlumnos, Scanner lector) {
        System.out.println("--------Eliminar Alumno--------");

        System.out.println("Ingrese el número de cédula del alumno que desea eliminar");
        int cedulaE = lector.nextInt();
        boolean buscar = false; 

        Iterator<Alumno> iterador = misAlumnos.iterator();

        while (iterador.hasNext()) {
            Alumno alumno = iterador.next();

            if (alumno.getCedula() == cedulaE) {
                iterador.remove();
                System.out.println("Se eliminó el alumno con la siguiente cédula: " + cedulaE);
                buscar = true;
                break;
            }
        }

        if (!buscar) {
            System.out.println("No se encontró un alumno con la cédula: " + cedulaE);
        }

        if (misAlumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        }

        System.out.println("---------------------------");
    }
    
    
    private static void modificarAlumno(ArrayList<Alumno> misAlumnos, Scanner lector) {
        System.out.println("--------Modificar datos de alumno--------");

        System.out.println("Ingrese el número de cédula del alumno que desea modificar");
        int cedulaM = lector.nextInt();
        boolean modificar = false; 

        Iterator<Alumno> iterador = misAlumnos.iterator();

        while (iterador.hasNext()) {
            Alumno alumnoModificado = iterador.next();

            if (alumnoModificado.getCedula() == cedulaM) {
                System.out.println("Seleccione el dato que desea modificar:");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. Semestre");
                System.out.println("4. Correo");
                System.out.println("5. Celular");
                int opcionDato = lector.nextInt();

                switch (opcionDato) {
                    case 1:
                        System.out.println("Introduzca nuevo nombre del alumno:");
                        String nuevoNombre = lector.next();
                        alumnoModificado.setNombre(nuevoNombre);
                        break;
                    case 2:
                        System.out.println("Introduzca nuevo apellido del alumno:");
                        String nuevoApellido = lector.next();
                        alumnoModificado.setApellido(nuevoApellido);
                        break;
                    case 3:
                        System.out.println("Introduzca nuevo semestre del alumno:");
                        int nuevoSemestre = lector.nextInt();
                        alumnoModificado.setSemestre(nuevoSemestre);
                        break;
                    case 4:
                        System.out.println("Introduzca nuevo correo del alumno:");
                        String nuevoCorreo = lector.next();
                        alumnoModificado.setCorreo(nuevoCorreo);
                        break;
                    case 5:
                        System.out.println("Introduzca el nuevo celular del alumno:");
                        int nuevoCelular = lector.nextInt();
                        alumnoModificado.setCelular(nuevoCelular);
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }

                System.out.println("Se modificaron los datos del alumno con la siguiente cédula: " + cedulaM);
                modificar = true;
                break;
            }
        }

        if (!modificar) {
            System.out.println("No se encontró un alumno con la cédula: " + cedulaM);
        }

        if (misAlumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        }

        System.out.println("---------------------------");
    }
    
    
    private static void consultarAlumnos(ArrayList<Alumno> misAlumnos) {
    System.out.println("--------Consultar Alumnos--------");
    
        if (misAlumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        } else {
            for (Alumno alumno : misAlumnos) {
                System.out.println("Cédula: " + alumno.getCedula());
                System.out.println("Nombre: " + alumno.getNombre());
                System.out.println("Apellido: " + alumno.getApellido());
                System.out.println("Semestre: " + alumno.getSemestre());
                System.out.println("Correo: " + alumno.getCorreo());
                System.out.println("Celular: " + alumno.getCelular());
                System.out.println("---------------------------");
            }
        }
    }
    
    private static void generarReporteSemestre(ArrayList<Alumno> misAlumnos, Scanner lector) {
        System.out.println("--------Generar reporte por semestre--------");
        System.out.println("Ingrese el semestre del cual necesita una lista");

        int semestreS = lector.nextInt();

        try {
            // El archivo se va a almacenar en la carpeta "data"
            File archivo = new File("./data/reporteEstudiantes" + semestreS + ".txt");

            PrintWriter pluma = new PrintWriter(archivo);
            pluma.println("Reporte de estudiantes semestre: " + semestreS);
            pluma.println("-----------------------");

            Iterator<Alumno> iterator = misAlumnos.iterator();
            while (iterator.hasNext()) {
                Alumno alumno = iterator.next();
                if (alumno.getSemestre() == semestreS) {
                    pluma.println("Cédula: " + alumno.getCedula());
                    pluma.println("Nombre: " + alumno.getNombre());
                    pluma.println("Apellido: " + alumno.getApellido());
                    pluma.println("Semestre: " + alumno.getSemestre());
                    pluma.println("Correo: " + alumno.getCorreo());
                    pluma.println("Celular: " + alumno.getCelular());
                    pluma.println("---------------------------");
                }
            }

            pluma.close();
            System.out.println("Archivo generado exitosamente: reporteEstudiantes" + semestreS + ".txt");
        } catch (IOException e) {
            System.out.println("Error al generar el archivo: " + e.getMessage());
        }

        System.out.println("---------------------------");
    }

    private static boolean terminarPrograma(Scanner lector) {
        System.out.println("--------Terminar Programa--------");
        System.out.println("¿Está seguro de querer terminar el programa? (Si/No)");

        String respuesta = lector.next();

        if (respuesta.equalsIgnoreCase("Si")) {
            System.out.println("Fin del programa");
            return true;
        } else if (respuesta.equalsIgnoreCase("No")) {
            System.out.println("Continuando");
            return false;
        } else {
            System.out.println("Respuesta inválida. Continuando");
            return false;
        }
    }
    
    
    /**
    * "cargarAlumnos "Carga los datos de los alumnos al iniciar el programa. (Restaura la persistencia)
    * Trabaja en conjunto con "cargarAlumnos" pero este se agrega inicio del proceso
    * @param misAlumnos La lista de alumnos cuyos datos se van leer y cargar al iniciar el programa.
    */
    
    private static ArrayList<Alumno> cargarAlumnos() {
        ArrayList<Alumno> misAlumnos = new ArrayList<>();

        try {
            //Se define la ruta de donde se cargaran los datos de los Alumnos
            File archivo = new File("./data/alumnos.txt");
            FileReader fr = new FileReader(archivo);
            // "lector" se encarga de leer los datos del archivo alumnos.txt
            // Se encarga de restaurar los datos contribuyendo a mantener la persistencias
            BufferedReader lector = new BufferedReader(fr);

            String linea = lector.readLine();

            while (linea != null) {
                String[] datos = linea.split(",");
                
                int cedula = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String apellido = datos[2];
                int semestre = Integer.parseInt(datos[3]);
                String correo = datos[4];
                int celular = Integer.parseInt(datos[5]);

                Alumno nuevoAlumno = new Alumno();
                nuevoAlumno.setCedula(cedula);
                nuevoAlumno.setNombre(nombre);
                nuevoAlumno.setApellido(apellido);
                nuevoAlumno.setSemestre(semestre);
                nuevoAlumno.setCorreo(correo);
                nuevoAlumno.setCelular(celular);

                misAlumnos.add(nuevoAlumno);

                linea = lector.readLine();
            }
               //Se cierra lector y se arroja un mensaje en caso de error al cargar los datos
                lector.close();
                fr.close();
         } catch (IOException e) {
             System.out.println("Error al cargar los datos de alumnos: " + e.getMessage());
        } 

        return misAlumnos;
    }
    
    /**
    * "guardarAlumnos "Guarda los datos de los alumnos en un archivo para persistencia.
    * Trabaja en conjunto con "cargarAlumnos" pero este se añade en case 1 y 6
    * @param misAlumnos La lista de alumnos cuyos datos se van a guardar.
    */
    
    
    private static void guardarAlumnos(ArrayList<Alumno> misAlumnos) {
        try {
            
            //Se define la ruta donde se guardaran los datos de los Alumnos
            File archivo = new File("./data/alumnos.txt");
            FileWriter fw = new FileWriter(archivo);
            //"escritor" se encarga de registrar los datos de los alumnos
            // y el metodo que se emplea en este caso "Filewriter" y "BufferedWriter"
            BufferedWriter escritor = new BufferedWriter(fw);
            
            // Este "for each" recorre la lista de alumnos y se guarda cada alumno en una línea del archivo
            for (Alumno alumno : misAlumnos) {
                String linea = alumno.getCedula() + "," +
                               alumno.getNombre() + "," +
                               alumno.getApellido() + "," +
                               alumno.getSemestre() + "," +
                               alumno.getCorreo() + "," +
                               alumno.getCelular();
                escritor.write(linea);
                escritor.newLine(); // Se agrega una nueva línea al final de cada registro
            }
            
            // Se cierra "escritor" y se lanzan mensajes de exito en el guardado o error
            escritor.close();
            fw.close();
            System.out.println("Datos de alumnos guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos de alumnos: " + e.getMessage());
        }
    }
}
        
