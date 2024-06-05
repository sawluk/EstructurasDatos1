/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Mundial.java,v 1.10 2007/03/17 15:19:21 jvillalo2 Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_mundial
 * Autor: Milena Vela - 10-may-2006
 * Autor: Daniel Romero - 30-nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.mundial.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Es la clase para representar el mundial de f�tbol <br>
 * <b>inv: </b> <br>
 * equipos != null <br>
 * No hay dos equipos del mismo pa�s
 * 
 */

public class Mundial
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Nombre del archivo de registro de errores del programa
     */
    private static final String LOG_FILE = "./data/error.log";

    /**
     * N�mero de datos a leer para un equipo
     */
    public static final int DATOS_EQUIPO = 4;

    /**
     * N�mero de datos a leer para un jugador
     */
    public static final int DATOS_JUGADOR = 7;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La lista de equipos del mundial
     */
    private ArrayList equipos;

    /**
     * Es el archivo de donde se cargan y salvan los equipos
     */
    private String archivoMundial;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo mundial de f�tbol. <br>
     * Si el archivo indicado no existe, entonces el mundial se crea vac�o y su estado se guardar� en el archivo indicado.<br>
     * Si el archivo existe, entonces de �l se saca la informaci�n de los equipos y jugadores.
     * @param nombreArchivoMundial Es el nombre del archivo que contiene los datos del mundial
     * @throws PersistenciaException Se lanza esta excepci�n si se encuentran problemas cargando los datos del archivo
     */
    public Mundial( String nombreArchivoMundial ) throws PersistenciaException
    {
        archivoMundial = nombreArchivoMundial;
        File archivo = new File( archivoMundial );
        if( archivo.exists( ) )
        {
            // El archivo existe: se debe recuperar de all� el estado del modelo del mundo
            try
            {
                ObjectInputStream ois = new ObjectInputStream( new FileInputStream( archivo ) );
                equipos = ( ArrayList )ois.readObject( );
                ois.close( );
            }
            catch( Exception e )
            {
                // Se atrapan en este bloque todos los tipos de excepci�n
                registrarError( e );
                throw new PersistenciaException( "Error fatal: imposible restaurar el estado del programa (" + e.getMessage( ) + ")" );
            }
        }
        else
        {
            // El archivo no existe: es la primera vez que se ejecuta el programa
            equipos = new ArrayList( );
        }
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna un equipo del mundial dado su pa�s.
     * @param nombreEquipo el nombre del equipo a buscar - nombreEquipo != null
     * @return El equipo cuyo nombre es igual al nombre dado. Si no se encontr� se retorna null.
     */
    public Equipo darEquipo( String nombreEquipo )
    {
        Equipo equipoBuscado = null;
        boolean esta = false;

        for( int i = 0; i < equipos.size( ) && !esta; i++ )
        {
            Equipo e = ( Equipo )equipos.get( i );
            if( e.darPais( ).equalsIgnoreCase( nombreEquipo ) )
            {
                equipoBuscado = e;
                esta = true;
            }
        }
        return equipoBuscado;
    }

    /**
     * Agrega un nuevo equipo al mundial
     * @param pais El nombre del pa�s que representa el equipo - pais != null
     * @param director El nombre del director t�cnico del equipo - director != null
     * @param imagen La imagen con la bandera del pa�s del equipo - imagen != null
     * @throws ElementoExisteException Esta excepci�n se lanza si ya existe un equipo con el mismo nombre
     */
    public void agregarEquipo( String pais, String director, String imagen ) throws ElementoExisteException
    {
        if( darEquipo( pais ) != null )
            throw new ElementoExisteException( "El equipo " + pais + " ya existe en el mundial" );

        Equipo equipo = new Equipo( pais, director, imagen );
        equipos.add( equipo );

        verificarInvariante( );
    }

    /**
     * Retorna un vector con los nombres de los equipos
     * @return Vector con los nombres de los equipos
     */
    public ArrayList darNombresEquipos( )
    {
        ArrayList nombresEquipos = new ArrayList( );
        for( int i = 0; i < equipos.size( ); i++ )
        {
            Equipo e = ( Equipo )equipos.get( i );
            nombresEquipos.add( e.darPais( ) );
        }
        return nombresEquipos;
    }

    /**
     * Agrega un nuevo jugador al equipo
     * @param nombreEquipo El nombre del equipo para adicionar el jugador - hay un equipo con ese nombre en el mundial
     * @param nombreJ El nombre del jugador a crear - nombreJ != null
     * @param edadJ El n�mero de a�os del jugador - edadJ > 0
     * @param posicionJ La posici�n en la que juega el jugador - posicionJ != null
     * @param alturaJ La altura en metros del jugador - alturaJ > 0
     * @param pesoJ El peso en kilogramos del jugador - pesoJ > 0
     * @param salarioJ El salario anual del jugador en millones de d�lares - salarioJ > 0
     * @param imagenJ La ruta de la imagen con la foto del jugador - imagenJ != null
     * @throws ElementoExisteException Esta excepci�n se lanza si ya existe otro jugador en el equipo con el mismo nombre
     */
    public void agregarJugadorAEquipo( String nombreEquipo, String nombreJ, int edadJ, String posicionJ, double alturaJ, double pesoJ, double salarioJ, String imagenJ ) throws ElementoExisteException
    {
        Equipo e = darEquipo( nombreEquipo );
        e.agregarJugador( new Jugador( nombreJ, edadJ, posicionJ, alturaJ, pesoJ, salarioJ, imagenJ ) );
        verificarInvariante( );
    }

    /**
     * Calcula el valor de la n�mina de un equipo y genera el reporte correspondiente
     * @param nombreEquipo El nombre del equipo para el que se va a calcular el valor de la n�mina
     * @param rutaReporte el directorio donde debe generarse el reporte - rutaReporte != null
     * @return Retorna el nombre del archivo en el que se gener� el reporte
     * @throws IOException Se genera esta excepci�n si hay problemas salvando el archivo con el reporte
     */
    public String calcularValorNomina( String nombreEquipo, String rutaReporte ) throws IOException
    {
        // Genera el nombre para el archivo con el reporte de la n�mina
        String strTiempo = Long.toString( System.currentTimeMillis( ) );
        String nombreArchivo = nombreEquipo + "_" + strTiempo + ".nomina";

        // Guarda el archivo con el reporte
        File directorioReportes = new File( rutaReporte );
        if( !directorioReportes.exists( ) )
            directorioReportes.mkdirs( );
        File archivoReporte = new File( directorioReportes, nombreArchivo );
        PrintWriter out = new PrintWriter( archivoReporte );

        Date fecha = new Date( );
        out.println( "Mundial_CUPI2 - Reporte N�mina - Equipo: " + nombreEquipo );
        out.println( "Fecha:            " + fecha.toString( ) );
        out.println( "Total N�mina:          " + darEquipo( nombreEquipo ).calcularValorNomina( ) + " millones anuales" );
        out.close( );

        return nombreArchivo;

    }

    /**
     * Actualiza la informaci�n de los jugadores a partir de la informaci�n contenida en un archivo de texto.<br>
     * El archivo debe tener una l�nea en la cual se encuentra el n�mero de jugadores a los que se les va a modificar la informaci�n, <br>
     * y luego debe haber una l�nea por cada jugador al que se le va a modificar la informaci�n. <br>
     * Cada l�nea tiene el siguiente formato: <nombre pa�s>;<nombre jugador>;<edad>;<posici�n>;<altura>;<peso>;<salario>;<im�gen>
     * @param archivo El archivo que tiene la informaci�n de los jugadores - archivo!= null
     * @throws IOException Se lanza esta excepci�n si hay problemas leyendo el archivo de los jugadores
     * @throws ArchivoJugadoresException Se lanza esta excepci�n si el archivo no cumple con el formato esperado
     */
    public void modificarInformacionJugadores( File archivo ) throws ArchivoJugadoresException, IOException
    {

        String linea = null;
        int numeroJugadores = -1;

        // Abre el archivo con los equipos y jugadores. Si no existe, el constructor del flujo lanza la excepci�n FileNotFoundException
        BufferedReader lector = new BufferedReader( new FileReader( archivo ) );

        // Lee la primera l�nea del archivo (el n�mero de equipos)
        linea = lector.readLine( );

        if( linea != null && !linea.equals( "" ) )
        {
            linea = linea.trim( );

            try
            {
                numeroJugadores = Integer.parseInt( linea );
            }
            catch( NumberFormatException e )
            {
                // Hubo un error al leer el n�mero de jugadores
                throw new ArchivoJugadoresException( "El archivo no tiene el formato esperado. Se esperaba el n�mero de jugadores" );
            }
            int cont = 0;

            linea = lector.readLine( );
            linea = linea.trim( );

            while( cont < numeroJugadores )
            {
                modificarJugador( linea );
                linea = lector.readLine( );
                cont++;

                if( linea != null )
                {
                    linea = linea.trim( );

                }
                else if( cont < numeroJugadores )
                {
                    throw new ArchivoJugadoresException( "El archivo no tiene el formato esperado. Se esperaba la informaci�n de " + numeroJugadores + " jugadores" );
                }
            }

        }
        else
        {
            // Hubo un error al leer el n�mero de jugadores
            throw new ArchivoJugadoresException( "El archivo no tiene el formato esperado. Se esperaba el n�mero de jugadores" );
        }

        lector.close( );

    }

    /**
     * Carga un equipo a partir de la informaci�n contenida en un archivo
     * @param br Es el lector de archivo del cual se lee la informaci�n del equipo - El lector de archivo ya est� listo para leer
     * @throws ArchivoJugadoresException Se lanza esta excepci�n si el archivo no tiene el formato esperado
     * @throws IOException Si hay errores leyendo la siguiente l�nea del archivo
     * @throws ArchivoJugadoresException Si la l�nea del archivo no tiene el formato esperado
     */

    private void modificarJugador( String info ) throws ArchivoJugadoresException, IOException
    {
        String[] nuevosDatos = info.split( ";" );
        // Verificar que se tengan los 8 datos del jugador
        if( nuevosDatos.length == 8 )
        {
            String nombreEquipo = nuevosDatos[ 0 ];
            String nombreJugador = nuevosDatos[ 1 ];
            Equipo equipo = darEquipo( nombreEquipo );

            if( equipo != null )
            {
                if( equipo.darJugador( nombreJugador ) != null )
                {
                    int edad = Integer.parseInt( nuevosDatos[ 2 ] );
                    String posicion = nuevosDatos[ 3 ];
                    double altura = Double.parseDouble( nuevosDatos[ 4 ] );
                    double peso = Double.parseDouble( nuevosDatos[ 5 ] );
                    double salario = Double.parseDouble( nuevosDatos[ 6 ] );
                    String imagen = nuevosDatos[ 7 ];

                    if( edad < 0 || posicion.equals( "" ) || altura < 0 || peso < 0 || salario < 0 || imagen.equals( "" ) )
                    {
                        throw new ArchivoJugadoresException( "La datos del jugador " + nombreJugador + " no son v�lidos" );
                    }

                    equipo.modificarInformacionJugador( nombreJugador, edad, posicion, altura, peso, salario, imagen );
                }
                else
                {
                    throw new ArchivoJugadoresException( "El jugador con nombre " + nombreJugador + " no se encuentra en el campeonato" );
                }
            }
            else
            {
                throw new ArchivoJugadoresException( "El archivo no tiene el formato esperado. El equipo con nombre " + nombreEquipo + " no se encuentra en el campeonato" );
            }
        }
        else
        {
            throw new ArchivoJugadoresException( "El archivo no tiene el formato esperado. Se esperaban los ocho datos del jugador" );
        }
    }
    
    
    // -----------------------------------------------------------------
    // Metodo 1
    // -----------------------------------------------------------------
         public void loadnewteams() throws FileNotFoundException, IOException 
         {
         
             File archivo = new File("./data/nuevose.txt");
             FileReader filereader = new FileReader(archivo);
             BufferedReader reader = new BufferedReader (filereader);
         
             
             String row = reader.readLine();
             
            while (row!=null)
            {
               String[] data = row.split(";");
               String name = data [0];
               String directorName = data [1];
               String teamImage = data [2];
               
               Equipo newteam = new Equipo(name,directorName,teamImage);
               equipos.add(newteam);
               row = reader.readLine();
               
            }
            filereader.close();
            reader.close();
         }
    
    
    
    // -----------------------------------------------------------------
    // Persistencia
    // -----------------------------------------------------------------

    /**
     * Salva el mundial en un archivo binario
     * @throws PersistenciaException Se lanza esta excepci�n si hay problemas guardando la informaci�n del mundial en el archivo
     */
    public void salvarMundial( ) throws PersistenciaException
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( archivoMundial ) );
            oos.writeObject( equipos );
            oos.close( );
        }
        catch( IOException e )
        {
            registrarError( e );
            throw new PersistenciaException( "Error al salvar: " + e.getMessage( ) );
        }
    }

    /**
     * Registra en el archivo de log del programa toda la informaci�n referente a una excepci�n, ocurrida durante el proceso de persistencia
     * @param excepcion Es la excepci�n que contiene la informaci�n del error
     */
    public void registrarError( Exception excepcion )
    {
        try
        {
            FileWriter out = new FileWriter( LOG_FILE, true );
            PrintWriter log = new PrintWriter( out );
            log.println( "---------------------------------------" );
            log.println( "Mundial.java :" + new Date( ).toString( ) );
            log.println( "---------------------------------------" );
            excepcion.printStackTrace( log );
            log.close( );
            out.close( );
        }
        catch( IOException e )
        {
            excepcion.printStackTrace( );
            e.printStackTrace( );
        }
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase: <br>
     * equipos != null <br>
     * No hay dos equipos del mismo pa�s
     */
    private void verificarInvariante( )
    {
        assert equipos != null : "La lista de equipos es null";
        assert !buscarEquiposConElMismoNombre( ) : "Hay dos equipos con el mismo pa�s";
    }

    /**
     * Este m�todo sirve para revisar si hay equipos repetidos dentro del mundial.
     * @return Retorna true si hay un equipo que aparece repetido dentro de la lista de equipos. Retorna false en caso contrario.
     */
    private boolean buscarEquiposConElMismoNombre( )
    {
        for( int i = 0; i < equipos.size( ); i++ )
        {
            Equipo e1 = ( Equipo )equipos.get( i );
            for( int j = 0; j < equipos.size( ); j++ )
            {
                if( i != j )
                {
                    Equipo e2 = ( Equipo )equipos.get( j );
                    if( e1.darPais( ).equals( e2.darPais( ) ) )
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        String result = null;
        try
        {
        
         loadnewteams();
         result = "Se ha cargado los nuevos equipos";
        }
        catch(FileNotFoundException e)
        {
         result = "No encontramos el archivo";
        }
        catch(IOException e){
          result = "No se leyo el archivo";
        }
        return result;
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}