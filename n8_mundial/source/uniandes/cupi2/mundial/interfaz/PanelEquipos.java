/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelEquipos.java,v 1.7 2006/12/01 23:11:10 da-romer Exp $
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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.mundial.mundo.Equipo;

/**
 * Panel en el que se muestran los equipos
 */
public class PanelEquipos extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Esta constante indica la altura que debe tener la imagen de un individuo
     */
    private static final int ALTURA = 100;

    /**
     * Esta constante indica el ancho que debe tener la imagen de un individuo
     */
    private static final int ANCHO = 175;

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para cambiar los equipos
     */
    private static final String CAMBIAR_EQUIPO = "CambiarEquipo";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazMundial principal;

    /**
     * Referencia al equipo que se está mostrando actualmente
     */
    private Equipo equipoActual;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Es el comboBox con la lista de equipos
     */
    private JComboBox comboEquipos;

    /**
     * Etiqueta bandera
     */
    private JLabel etiquetaBandera;

    /**
     * Etiqueta director técnico
     */
    private JLabel etiquetaDirector;

    /**
     * Etiqueta del equipo
     */
    private JLabel etiquetaEquipo;

    private JTextField txtDirector;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel con los equipos del mundial
     * @param ventana Ventana principal - ventana!=null
     * @param equipos Una lista con los nombres de los países de los equipos del mundial - equipos!=null
     */

    public PanelEquipos( InterfazMundial ventana, ArrayList equipos )
    {
        principal = ventana;
        setLayout( new GridBagLayout( ) );
        setBorder( new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new TitledBorder( "Equipos" ) ) );

        JPanel panelAuxiliar = new JPanel( );
        panelAuxiliar.setLayout( new GridBagLayout( ) );
        comboEquipos = new JComboBox( equipos.toArray( ) );
        comboEquipos.setEditable( false );
        comboEquipos.addActionListener( this );
        comboEquipos.setActionCommand( CAMBIAR_EQUIPO );

        etiquetaEquipo = new JLabel( "Equipo" );

        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 3, 3, 3, 3 );
        add( etiquetaEquipo, gbc );
        gbc.gridx = 1;
        panelAuxiliar.add( comboEquipos, gbc );

        etiquetaDirector = new JLabel( "Director Técnico" );
        panelAuxiliar.add( etiquetaDirector, gbc );
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelAuxiliar.add( etiquetaDirector, gbc );
        txtDirector = new JTextField( "" );
        txtDirector.setEditable( false );
        txtDirector.setPreferredSize( new Dimension( 160, 20 ) );
        gbc.gridx = 1;
        panelAuxiliar.add( txtDirector, gbc );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets( 24, 3, 3, 30 );
        add( panelAuxiliar, gbc );

        etiquetaBandera = new JLabel( "" );
        etiquetaBandera.setMaximumSize( new Dimension( ANCHO, ALTURA ) );
        etiquetaBandera.setMinimumSize( new Dimension( ANCHO, ALTURA ) );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.insets = new Insets( 3, 3, 3, 3 );
        add( etiquetaBandera, gbc );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la información en el panel con la lista de equipos
     * @param equipos Lista de equipos del mundial - equipos!=null
     */
    public void cargarEquipos( ArrayList equipos )
    {
        comboEquipos.removeAllItems( );

        for( int i = 0; i < equipos.size( ); i++ )
        {
            comboEquipos.addItem( equipos.get( i ) );
        }
    }

    /**
     * Cambia el equipo actual que se muestra en el panel
     * @param equipo El nuevo equipo a mostrar en el panel - evento!=nulll
     */
    public void cambiarEquipo( Equipo equipo )
    {
        equipoActual = equipo;
        if( equipoActual != null )
        {
            BufferedImage imagen;
            try
            {
                imagen = ImageIO.read( new File( equipoActual.darImagen( ) ) );
                Image laImagen = imagen.getScaledInstance( ( int ) ( ANCHO ), ( int ) ( ALTURA ), Image.SCALE_AREA_AVERAGING );
                etiquetaBandera.setIcon( new ImageIcon( laImagen ) );
                txtDirector.setText( equipoActual.darDirector( ) );
            }
            catch( IOException e1 )
            {
                JOptionPane.showMessageDialog( this, "Problemas cargando la bandera del equipo", "Error", JOptionPane.ERROR_MESSAGE );
            }

        }

    }

    /**
     * Actualiza la información en el panel con la lista de equipos
     * @param equipos la lista de equipos a desplegar - equipos!=null
     */
    public void refrescarEquipos( ArrayList equipos )
    {
        comboEquipos.removeAllItems( );

        for( int i = 0; i < equipos.size( ); i++ )
        {
            comboEquipos.addItem( equipos.get( i ) );
        }
    }

    /**
     * Este es el método que se ejecuta cuando se cambia el equipo seleccionado en el combobox
     * @param evento El evento de la selección de un equipo diferente - evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( CAMBIAR_EQUIPO.equals( comando ) )
        {
            String nombreEquipo = ( String )comboEquipos.getSelectedItem( );
            principal.cambiarEquipoSeleccionado( nombreEquipo );

        }

    }

}
