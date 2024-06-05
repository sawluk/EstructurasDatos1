<%-- 
    Document   : agregarvideo
    Created on : 29/08/2023, 5:45:41 p. m.
    Author     : Sistemas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rockola</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        
    </head>
    <body>
        
        <div class = "container text-center">  <!-- clase contenedora --> 
            <h1>Sistema reproductor de videos</h1>
            <div class = "row"  > <!-- clase division por 4 columnas -->
                <div class = "col"  >
                <div class = "card card-body"> <!-- targeta de trabajo -->
                   <form action="SvVideo" method="POST">         
            
                    <label for="idVideo">idVideo:</label>
                    <input type="text" name="idVideo" class = "form-control"><br>

                    <label for="titulo">Titulo:</label>
                    <input type="text" name="titulo" class = "form-control"><br>

                    <label for="autor">Autor:</label>
                    <input type="text" name="autor" class = "form-control"><br>

                    <label for="anio">Año:</label>
                    <input type="text" name="anio" class ="form-control"><br>

                    <label for="url">Url:</label>
                    <input type="text" name="url" class ="form-control"><br>

                    <label for="categoria">Categoria:</label>
                    <input type="text" name="categoria" class ="form-control"><br>

                    <label for="letra">Letra:</label>
                    <textarea id="id" name="letra" rows="5" cols="10" class ="form-control"></textarea>

                    <input type="submit" value="Agregar video" class ="form-control">
                </form>  
                <a href="index.jsp">Volver al menu</a> 
                </div>
            </div>
            
                
                
            <div class = "col-md-8"  >
                <table class="table table-bordered">
                    <thead><!-- comment -->
                        <tr><!-- comment -->
                            <th> id </th>
                            <th> Titulo </th>
                            <th> Autor </th>
                            <th> Acciones </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td> 1 </td>
                            <td> No dejes que </td>
                            <td> Caifanes </td>
                            <td> Editar Borrar </td>
                            
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        
    </body>
</html>
