package servlets;

import com.umariana.mundo.Video;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

@WebServlet(name = "SvVideo", urlPatterns = {"/SvVideo"})
public class SvVideo extends HttpServlet {
    ArrayList<Video> misVideos = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        // Obtener el ServletContext correctamente
        ServletContext servletContext = getServletContext();

        try {
            // Cargar los videos serializados al iniciar la aplicación
            misVideos = Video.cargarVideosDesdeArchivo(servletContext);
        } catch (IOException ex) {
            Logger.getLogger(SvVideo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Aquí vienen los datos por doPost
        // Manda las variables pero no las muestra por motivos de seguridad
        String idVideo = request.getParameter("idVideo");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String anio = request.getParameter("anio");
        String url = request.getParameter("url");
        String categoria = request.getParameter("categoria");
        String letra = request.getParameter("letra");   

        //Añadimos un try n catch donde realizamos un parseInt a la ID
        //También hay una excepción en caso de errores
        try {
            int idVideoInt = Integer.parseInt(idVideo);
            // Ingresar datos al objeto
            Video miVideo = new Video(idVideoInt, titulo, autor, anio, categoria, url, letra);
            misVideos.add(miVideo);
        } catch (NumberFormatException e) {
            // Manejo de la excepción si idVideo no es un número válido
            e.printStackTrace();
            System.out.println("Error al convertir idVideo a entero: " + e.getMessage());
        }

        // Guardar la lista de videos en un archivo .ser
        Video.guardarVideosEnArchivo(misVideos, getServletContext());

        // Agregar el ArrayList al objeto de solicitud
        request.setAttribute("misVideos", misVideos);

        // Redireccionar a la página web destino
        request.getRequestDispatcher("listarVideos.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}


