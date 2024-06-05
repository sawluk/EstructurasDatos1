/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelJugadores.java,v 1.7 2006/12/01 22:29:13 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.mundial.mundo.Equipo;
import uniandes.cupi2.mundial.mundo.Jugador;

/**
 * Es el panel con los jugadores de un equipo
 */
public class PanelJugadores extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para cambiar el jugador
     */
    private static final String CAMBIAR_JUGADOR = "CambiarJugador";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El jugador del que actualmente se presentan los datos en el panel
     */
    private Jugador jugador;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel para presentar los datos del jugador
     */
    private InterfazMundial principal;

    /**
     * Es la etiqueta que muestra la imagen del jugador
     */
    private JLabel etiquetaImagen;

    /**
     * Es el combo con los jugadores del equipo
     */
    private JComboBox comboJugadores;

    /**
     * Etiqueta "Nombre: "
     */
    private JLabel etiquetaNombre;

    /**
     * Etiqueta "Edad: "
     */
    private JLabel etiquetaEdad;

    /**
     * Es la etiqueta con la edad del jugador
     */
    private JLabel etiquetaValorEdad;

    /**
     * Etiqueta "Posición: "
     */
    private JLabel etiquetaPosicion;

    /**
     * Es la etiqueta con la posición del jugador
     */
    private JLabel etiquetaValorPosicion;

    /**
     * Etiqueta "Altura: "
     */
    private JLabel etiquetaAltura;

    /**
     * Es la etiqueta con la altura del jugador
     */
    private JLabel etiquetaValorAltura;

    /**
     * Etiqueta "Peso: "
     */
    private JLabel etiquetaPeso;

    /**
     * Es la etiqueta con el peso del jugador
     */
    private JLabel etiquetaValorPeso;

    /**
     * Etiqueta salario
     */
    private JLabel etiquetaSalario;

    /**
     * Es la etiqueta con el salario del jugador
     */
    private JLabel etiquetaValorSalario;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal - ventana!=null
     */
    public PanelJugadores( InterfazMundial ventana )
    {
        principal = ventana;
        setLayout( new GridBagLayout( ) );
        GridBagConstraints c = new GridBagConstraints( 0, 1, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );

        // Foto del jugador
        etiquetaImagen = new JLabel( "" );
        etiquetaImagen.setPreferredSize( new Dimension( 150, 180 ) );
        etiquetaImagen.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );

        c = new GridBagConstraints( 0, 0, 1, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( etiquetaImagen, c );

        // Nombre
        etiquetaNombre = new JLabel( "Nombre: " );
        c = new GridBagConstraints( 1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( etiquetaNombre, c );

        comboJugadores = new JComboBox( );
        comboJugadores.setEditable( false );
        comboJugadores.addActionListener( this );
        comboJugadores.setActionCommand( CAMBIAR_JUGADOR );
        comboJugadores.setPreferredSize( new Dimension( 230, 20 ) );
        c = new GridBagConstraints( 2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( comboJugadores, c );

        // Edad
        etiquetaEdad = new JLabel( "Edad: " );
        etiquetaValorEdad = new JLabel( "" );
        etiquetaValorEdad.setFont( etiquetaValorEdad.getFont( ).deriveFont( Font.PLAIN ) );
        c = new GridBagConstraints( 1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( etiquetaEdad, c );
        c = new GridBagConstraints( 2, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( etiquetaValorEdad, c );

        // Posición
        etiquetaPosicion = new JLabel( "Posición: " );
        etiquetaValorPosicion = new JLabel( "" );
        etiquetaValorPosicion.setFont( etiquetaValorPosicion.getFont( ).deriveFont( Font.PLAIN ) );
        c = new GridBagConstraints( 1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( etiquetaPosicion, c );
        c = new GridBagConstraints( 2, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( etiquetaValorPosicion, c );

        // Altura
        etiquetaAltura = new JLabel( "Altura: " );
        etiquetaValorAltura = new JLabel( "" );
        etiquetaValorAltura.setFont( etiquetaValorAltura.getFont( ).deriveFont( Font.PLAIN ) );
        c = new GridBagConstraints( 1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( etiquetaAltura, c );
        c = new GridBagConstraints( 2, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( etiquetaValorAltura, c );

        // Peso
        etiquetaPeso = new JLabel( "Peso: " );
        etiquetaValorPeso = new JLabel( "" );
        etiquetaValorPeso.setFont( etiquetaValorPeso.getFont( ).deriveFont( Font.PLAIN ) );
        c = new GridBagConstraints( 1, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( etiquetaPeso, c );
        c = new GridBagConstraints( 2, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( etiquetaValorPeso, c );

        // Salario
        etiquetaSalario = new JLabel( "Salario: " );
        etiquetaValorSalario = new JLabel( "" );
        etiquetaValorSalario.setFont( etiquetaValorSalario.getFont( ).deriveFont( Font.PLAIN ) );
        c = new GridBagConstraints( 1, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( etiquetaSalario, c );
        c = new GridBagConstraints( 2, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( etiquetaValorSalario, c );

        setBorder( new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new TitledBorder( "Jugadores" ) ) );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el jugador actual
     * @param j El nuevo jugador actual - j!=null
     */
    public void cambiarJugador( Jugador j )
    {
        jugador = j;
        etiquetaImagen.setIcon( new ImageIcon( jugador.darImagen( ) ) );
        etiquetaValorEdad.setText( Integer.toString( jugador.darEdad( ) ) + " años" );
        etiquetaValorPosicion.setText( jugador.darPosicion( ) );
        etiquetaValorAltura.setText( Double.toString( jugador.darAltura( ) ) + " mts" );
        etiquetaValorPeso.setText( Double.toString( jugador.darPeso( ) ) + " Kgs" );
        etiquetaValorSalario.setText( principal.formatearValor( jugador.darSalario( ) ) + " millones anuales" );

    }

    /**
     * Cambia los jugadores del equipo que se muestran en el panel
     * @param e El nuevo equipo a mostrar en el panel - e!=null
     */
    public void cambiarJugadores( Equipo e )
    {
        if( e != null )
        {
            cambiarEquipo( e );
        }
        else
        {
            setBorder( new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new TitledBorder( "Jugadores" ) ) );
        }
    }

    /**
     * Cambia el equipo para el cual se muestran los jugadores en el panel y actualiza la información mostrada
     * @param e Es el equipo del que se quieren mostrar los jugadores - e!=null
     */
    private void cambiarEquipo( Equipo e )
    {
        comboJugadores.removeAllItems( );
        ArrayList jugadores = e.darNombresJugadores( );
        for( int i = 0; i < jugadores.size( ); i++ )
        {
            String jugador = ( String )jugadores.get( i );
            comboJugadores.addItem( jugador );
        }
        if( jugadores.size( ) == 0 )
        {
            etiquetaImagen.setIcon( new ImageIcon( "./data/sinFoto.img" ) );
            etiquetaValorEdad.setText( "" );
            etiquetaValorPosicion.setText( "" );
            etiquetaValorAltura.setText( "" );
            etiquetaValorPeso.setText( "" );
            etiquetaValorSalario.setText( "" );
        }
    }

    /**
     * Ejecuta las acciones asociadas a los eventos. Actualiza la información del jugador en el panel de acuerdo al jugador seleccionado en el comboBox
     * @param evento Es el evento del click sobre un botón - evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( CAMBIAR_JUGADOR.equals( comando ) )
        {
            String nombreJugador = ( String )comboJugadores.getSelectedItem( );
            principal.cambiarJugadorSeleccionado( nombreJugador );

        }
    }

}
