package com.umariana.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.servlet.ServletContext;

public class Video implements Serializable {
        
    private int idVideo;
    private String titulo;
    private String autor; 
    private String anio;
    private String categoria;
    private String url;
    private String letra;

    public Video() {
    }

    public Video(int idVideo, String titulo, String autor, String anio, String categoria, String url, String letra) {
        this.idVideo = idVideo;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.categoria = categoria;
        this.url = url;
        this.letra = letra;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
    
    // Método para guardar la lista de videos en un archivo videos.ser
    public static void guardarVideosEnArchivo(ArrayList<Video> videos, ServletContext context) throws IOException {
        String relativePath = "/data/videos.ser";
        String absPath = context.getRealPath(relativePath);
        File archivo = new File(absPath);
        
        try {
            // Crear un archivo para guardar la lista de videos serializada
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(videos);
            oos.close();
            System.out.println("Datos de videos guardados exitosamente en: videos.ser");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos de videos: " + e.getMessage());
        }
    }

    // Método para cargar los videos desde el archivo deserializándolo
    public static ArrayList<Video> cargarVideosDesdeArchivo(ServletContext context) throws IOException, ClassNotFoundException {
        ArrayList<Video> listaDeVideos = new ArrayList<>();
        
        String relativePath = "/data/videos.ser";
        String absPath = context.getRealPath(relativePath);
        File archivo = new File(absPath);
        try {
            // Cargar la lista de videos desde el archivo
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            listaDeVideos = (ArrayList<Video>) ois.readObject();
            ois.close();
            System.out.println("Datos de videos cargados exitosamente desde: videos.ser");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al cargar los datos de videos: " + e.getMessage());
        }
        return listaDeVideos;
    }
}

