/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazMundial.java,v 1.14 2006/12/01 23:20:25 da-romer Exp $
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

package uniandes.cupi2.mundial.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.mundial.mundo.ArchivoJugadoresException;
import uniandes.cupi2.mundial.mundo.ElementoExisteException;
import uniandes.cupi2.mundial.mundo.Equipo;
import uniandes.cupi2.mundial.mundo.Jugador;
import uniandes.cupi2.mundial.mundo.Mundial;
import uniandes.cupi2.mundial.mundo.PersistenciaException;

/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazMundial extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * La ruta donde deben guardarse los reportes
     */
    private static final String RUTA_REPORTES = "./data/reportes";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Mundial mundial;

    /**
     * Es una referencia al equipo del cual se est�n mostrando los datos
     */
    private Equipo equipoSeleccionado;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con los equipos
     */
    private PanelEquipos panelEquipos;

    /**
     * Panel con los jugadores
     */
    private PanelJugadores panelJugadores;

    /**
     * Panel con los botones
     */
    private PanelBotones panelBotones;

    /**
     * Panel con una imagen decorativa
     */
    private PanelImagen panelImagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz de la aplicaci�n
     * @param m Es el mundial de f�tbol que se va a mostrar - m!=null
     */
    public InterfazMundial( Mundial m )
    {

        // Crea la clase principal
        mundial = m;

        // Construye la forma
        setTitle( "Mundial de f�tbol CUPI2" );
        setLayout( new GridBagLayout( ) );
        setSize( 560, 600 );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setResizable(false);

        // Creaci�n de los paneles aqu�
        panelImagen = new PanelImagen( );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add( panelImagen, gbc );

        panelEquipos = new PanelEquipos( this, mundial.darNombresEquipos( ) );
        gbc.gridy = 1;
        add( panelEquipos, gbc );

        panelJugadores = new PanelJugadores( this );
        gbc.gridy = 2;
        add( panelJugadores, gbc );

        ArrayList equipos = mundial.darNombresEquipos( );
        if( equipos.size( ) > 0 )
        {
            cambiarEquipoSeleccionado( ( ( String )equipos.get( 0 ) ) );
        }

        panelBotones = new PanelBotones( this );
        gbc.gridy = 3;
        add( panelBotones, gbc );
        centrar( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Centra la ventana en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }

    /**
     * Cambia el equipo seleccionado en el panel de detalles del equipo
     * @param nombreEquipo El nombre del equipo a mostrar los detalles - nombreEquipo!=null
     */
    public void cambiarEquipoSeleccionado( String nombreEquipo )
    {
        equipoSeleccionado = mundial.darEquipo( nombreEquipo );
        panelEquipos.cambiarEquipo( equipoSeleccionado );
        panelJugadores.cambiarJugadores( equipoSeleccionado );
    }

    /**
     * Cambia el jugador seleccionado en el panel de detalles del equipoSeleccionado
     * @param nombreJugador El nombre del jugador - nombreJugador!=null
     */
    public void cambiarJugadorSeleccionado( String nombreJugador )
    {
        Jugador jugador = equipoSeleccionado.darJugador( nombreJugador );

        if( jugador != null )
        {
            panelJugadores.cambiarJugador( jugador );
        }

    }

    /**
     * Muestra el di�logo para agregar un nuevo equipoSeleccionado al mundial
     */
    public void mostrarDialogoAgregarEquipo( )
    {
        DialogoCrearEquipo dialogo = new DialogoCrearEquipo( this );
        dialogo.setVisible( true );
    }

    /**
     * Muestra el di�logo para agregar un nuevo jugador al equipoSeleccionado en el panel de jugadores
     */
    public void mostrarDialogoAgregarJugador( )
    {
        DialogoCrearJugador dialogo = new DialogoCrearJugador( this );
        dialogo.setVisible( true );

    }

    /**
     * Crea un nuevo equipo en el mundial y actualiza el panel con la lista de equipos <br>
     * <b>pre: <b>No debe haber otro equipo representando al mismo pa�s en el mundial
     * @param pais Es el pa�s del equipoSeleccionado - pais != null
     * @param director Es el nombre del director t�cnico del equipoSeleccionado - director != null
     * @param imagen Es la ruta de la imagen con la bandera del equipoSeleccionado - imagen != null
     * @return Retorna true si el equipo se pudo agregar. Esto sirve para saber si se debe cerrar el di�logo.
     */
    public boolean crearEquipo( String pais, String director, String imagen )
    {
        boolean ok = false;
        try
        {
            mundial.agregarEquipo( pais, director, imagen );
            panelEquipos.refrescarEquipos( mundial.darNombresEquipos( ) );
            ok = true;
        }
        catch( ElementoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ) );
        }

        return ok;
    }

    /**
     * Crea un nuevo jugador en el equipo que se muestra en los detalles de equipo del mundial <br>
     * <b>pre: <b>No debe haber otro jugador con el mismo nombre en el equipo
     * @param nombre El nombre del jugador - nombre != null
     * @param edad La edad del jugador - edad > 0
     * @param posicion La posici�n en la que juega el jugador - posicion != null
     * @param altura Es la altura del jugador en metros - altura > 0
     * @param peso Es el peso del jugador en kilogramos - peso > 0
     * @param salario Es el salario del jugador - salario > 0
     * @param imagen Es la ruta a la imagen con la foto del jugador - imagen != null
     * @return Retorna true si el jugador se pudo agregar. Esto sirve para saber si se debe cerrar el di�logo.
     */
    public boolean crearJugador( String nombre, int edad, String posicion, double altura, double peso, double salario, String imagen )
    {
        boolean ok = false;

        if( equipoSeleccionado != null )
        {
            try
            {
                mundial.agregarJugadorAEquipo( equipoSeleccionado.darPais( ), nombre, edad, posicion, altura, peso, salario, imagen );
                equipoSeleccionado = mundial.darEquipo( equipoSeleccionado.darPais( ) );
                panelJugadores.cambiarJugadores( equipoSeleccionado );
                ok = true;
            }
            catch( ElementoExisteException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ) );
            }
        }

        return ok;
    }

    /**
     * Calcula el valor de la n�mina del equipo seleccionado
     */
    public void calcularValorNomina( )
    {

        try
        {
            String archivoReporte = mundial.calcularValorNomina( equipoSeleccionado.darPais( ), RUTA_REPORTES );
            JOptionPane.showMessageDialog( this, "El reporte se guard� en el archivo: " + archivoReporte, "Reporte Guardado", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog( this, "Se present� un problema guardando el archivo con el reporte:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Este m�todo se encarga de modificar la informaci�n de los jugadores a partir de un archivo
     */
    public void modificarInformacionJugadores( )
    {
        JFileChooser fc = new JFileChooser( "./data" );
        fc.setDialogTitle( "Modificar informaci�n de jugadores" );
        int resultado = fc.showOpenDialog( this );
        if( resultado == JFileChooser.APPROVE_OPTION )
        {
            File archivo = fc.getSelectedFile( );
            if( archivo != null )
            {
                try
                {
                    mundial.modificarInformacionJugadores( archivo );
                    JOptionPane.showMessageDialog( this, "La informaci�n de los jugadores fue modificada", "Modificar Informaci�n Jugadores", JOptionPane.INFORMATION_MESSAGE );
                }
                catch( FileNotFoundException e )
                {
                    JOptionPane.showMessageDialog( this, "Se present� un problema leyendo el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
                catch( IOException e )
                {
                    JOptionPane.showMessageDialog( this, "Se present� un problema leyendo el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
                catch( ArchivoJugadoresException e )
                {
                    JOptionPane.showMessageDialog( this, "Se present� un problema debido al formato del archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }

        panelEquipos.refrescarEquipos( mundial.darNombresEquipos( ) );
    }

    /**
     * Formatea un valor num�rico para presentar en la interfaz <br>
     * @param valor El valor num�rico a ser formateado
     * @return Cadena con el valor formateado con puntos y signos.
     */
    public String formatearValor( double valor )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "$ ###,###.##" );
        df.setMinimumFractionDigits( 2 );
        return df.format( valor );
    }

    /**
     * Este m�todo se encarga de salvar la informaci�n del mundial, justo antes de cerrar la aplicaci�n
     */
    public void dispose( )
    {
        try
        {
            mundial.salvarMundial( );
            super.dispose( );
        }
        catch( Exception e )
        {
            setVisible( true );
            int respuesta = JOptionPane.showConfirmDialog( this, "Problemas salvando la informaci�n del mundial:\n" + e.getMessage( ) + "\n�Quiere cerrar el programa sin salvar?", "Error", JOptionPane.YES_NO_OPTION );
            if( respuesta == JOptionPane.YES_OPTION )
            {
                super.dispose( );
            }
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = mundial.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
        panelEquipos.refrescarEquipos(mundial.darNombresEquipos());
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = mundial.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args Argumentos para la ejecuci�n de la aplicaci�n. No deben usarse
     */
    public static void main( String[] args )
    {
        Mundial mundial = null;

        try
        {
            mundial = new Mundial( "./data/mundial.equipos" );
        }
        catch( PersistenciaException e )
        {
            e.printStackTrace( );
            System.exit( 1 );
        }

        InterfazMundial id = new InterfazMundial( mundial );
        id.setVisible( true );
    }

}