<%@page import="java.io.IOException"%>
<%@page import="java.io.ObjectInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.mundo.Video"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar videos</title>
        <style>
            /* Estilo para el cuerpo de la página */
            body {
                font-family: Arial, sans-serif;
                background-image: url('https://img.freepik.com/vector-gratis/fondo-geometrico-plano_23-2148967370.jpg'); /* Ruta de la imagen */
                background-size: cover; /* Escala la imagen para cubrir todo el fondo */
                background-repeat: no-repeat; /* Evita la repetición de la imagen */
                background-attachment: fixed;
                margin: 0;
                padding: 0;
                text-align: center;
            }

            /* Estilo para el contenedor principal */
            .content {
                background-color: white;
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            }

            /* Estilo para los títulos */
            h1 {
                color: #007BFF; /* Color azul */
            }

            /* Estilo para los enlaces */
            a {
                color: #007BFF; /* Color azul */
                text-decoration: none;
            }

            /* Estilo para los elementos de video */
            .video-item {
                margin-bottom: 20px;
                border: 1px solid #ddd;
                padding: 10px;
                border-radius: 5px;
                text-align: left;
                background-color: white;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            }

            /* Estilo para separar los elementos de video */
            .video-item:not(:last-child) {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <div class="content">
            <h1>Listar videos</h1>
            <a href="index.jsp">Volver al inicio</a>

            <%
                // Obtener array list de la solicitud utilizando el método cargarVideosDesdeArchivo
                ArrayList<Video> misVideos = Video.cargarVideosDesdeArchivo(application);

                if (misVideos != null) {
                    System.out.println("Se cargaron " + misVideos.size() + " videos exitosamente.");
                    for (Video v : misVideos) {
            %>
                        <div class="video-item">
                            <strong>IdVideo:</strong> <%= v.getIdVideo() %><br>
                            <strong>Titulo:</strong> <%= v.getTitulo() %><br>
                            <strong>Autor:</strong> <%= v.getAutor() %><br>
                            <strong>Año:</strong> <%= v.getAnio() %><br>
                            <strong>Categoria:</strong> <%= v.getCategoria() %><br>
                            <strong>Url:</strong> <%= v.getUrl() %><br>
                            <strong>Letra:</strong> <%= v.getLetra() %><br>
                        </div>
            <%
                    }
                } else {
                    out.print("No hay videos disponibles.");
                }
            %>
        </div>
    </body>
</html>

