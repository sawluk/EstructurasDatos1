/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Jugador.java,v 1.8 2006/12/01 22:29:13 da-romer Exp $
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

package uniandes.cupi2.mundial.mundo;

import java.io.Serializable;

/**
 * Es la clase que representa un jugador que hace parte de un equipo <br>
 * <b>inv: </b> <br>
 * nombre != null <br>
 * edad > 0 <br>
 * posicion != null <br>
 * altura > 0 <br>
 * peso > 0 <br>
 * salario > 0 <br>
 * imagen != null <br>
 */
public class Jugador implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versión para la serialización
     */
    private static final long serialVersionUID = 100L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el nombre del jugador
     */
    private String nombre;

    /**
     * Es la edad del jugador
     */
    private int edad;

    /**
     * Es la posición en la que juega el jugador
     */
    private String posicion;

    /**
     * Es la altura del jugador en metros
     */
    private double altura;

    /**
     * Es el peso en kilogramos del jugador
     */
    private double peso;

    /**
     * Es el salario anual del jugador en millones de pesos
     */
    private double salario;

    /**
     * Es la ruta a la imagen con la foto del jugador
     */
    private String imagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un jugador con los datos suministrados
     * @param nombreJ El nombre del jugador - nombreJ != null
     * @param edadJ La edad del jugador - edadJ > 0
     * @param posicionJ La posición en la que juega el jugador - posicionJ != null
     * @param alturaJ Es la altura del jugador en metros - alturaJ > 0
     * @param pesoJ Es el peso del jugador en kilogramos - pesoJ > 0
     * @param salarioJ Es el salario del jugador - salarioJ > 0
     * @param imagenJ Es el salario del jugador - imagenJ != null
     */
    public Jugador( String nombreJ, int edadJ, String posicionJ, double alturaJ, double pesoJ, double salarioJ, String imagenJ )
    {
        nombre = nombreJ;
        edad = edadJ;
        posicion = posicionJ;
        altura = alturaJ;
        peso = pesoJ;
        salario = salarioJ;
        imagen = imagenJ;

        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del jugador
     * @return Se retornó el nombre del jugador
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la edad del jugador
     * @return Se retornó la edad del jugador
     */
    public int darEdad( )
    {
        return edad;
    }

    /**
     * Retorna la posición del jugador
     * @return Se retornó la posición del jugador
     */
    public String darPosicion( )
    {
        return posicion;
    }

    /**
     * Retorna la altura del jugador
     * @return Se retornó la altura del jugador
     */
    public double darAltura( )
    {
        return altura;
    }

    /**
     * Retorna el peso del jugador
     * @return Se retornó el peso del jugador
     */
    public double darPeso( )
    {
        return peso;
    }

    /**
     * Retorna el salario del jugador
     * @return Se retornó el salario del jugador
     */
    public double darSalario( )
    {
        return salario;
    }

    /**
     * Retorna la ruta de la imagen del jugador
     * @return Se retornó la ruta de la imagen del jugador
     */
    public String darImagen( )
    {
        return imagen;
    }

    /**
     * Cambia la altura del jugador
     * @param nAltura Nueva altura del jugador - altura>0
     */
    public void cambiarAltura( double nAltura )
    {
        altura = nAltura;
    }

    /**
     * Cambia la edad del jugador
     * @param nEdad Nueva edad del jugador - nEdad>0
     */
    public void cambiarEdad( int nEdad )
    {
        edad = nEdad;
    }

    /**
     * Cambia la imagen del jugador
     * @param nImagen Nueva imagen del jugador - imagen!=null
     */
    public void cambiarImagen( String nImagen )
    {
        imagen = nImagen;
    }

    /**
     * Cambia el peso del jugador
     * @param nPeso Nuevo peso del jugador - nPeso>0
     */
    public void cambiarPeso( double nPeso )
    {
        peso = nPeso;
    }

    /**
     * Cambia la posición del jugador
     * @param nPosicion Nueva posición del jugador - nPosicion!=null
     */
    public void cambiarPosicion( String nPosicion )
    {
        posicion = nPosicion;
    }

    /**
     * Cambia el salario del jugador
     * @param nSalario Nueva salario del jugador - nSalario>0
     */
    public void cambiarSalario( double nSalario )
    {
        salario = nSalario;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------
    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv: </b> <br>
     * nombre != null <br>
     * edad > 0 <br>
     * posicion != null <br>
     * altura > 0 <br>
     * peso > 0 <br>
     * salario > 0 <br>
     * imagen != null <br>
     */
    private void verificarInvariante( )
    {
        assert nombre != null : "El nombre es inválido";
        assert edad > 0 : "La edad deben ser un entero positivo";
        assert posicion != null : "La posición es inválida";
        assert altura > 0 : "La altura debe ser un double positivo";
        assert peso > 0 : "El peso debe ser un double positivo";
        assert salario > 0 : "El salario debe ser un double positivo";
        assert imagen != null : "La ruta de la imagen es inválida";
    }

}