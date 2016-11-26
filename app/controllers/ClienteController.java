package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import java.util.*;
import java.io.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;
//import java.nio.Files;

import models.*;


import views.html.cliente.*;



public class ClienteController extends Controller {

    public Result productos() {
        List<Producto> productos_list = Producto.find.findList();
		List<Categoria> categorias_list = Categoria.find.findList();
        
        return ok(productos.render(productos_list,categorias_list));
    }

    public Result ofertas() {
    	return ok(ofertas.render());
    }

    public Result incidentes() {
    	return ok(incidentes.render());
    }

    public Result carretilla() {
    	return ok(carretilla.render());
    }

    public Result chat() {
        return ok(chat.render());
    }

    public Result getProductImage(Long id){

        try {
            Producto prod = Producto.find.byId(id);
            InputStream input = new ByteArrayInputStream(prod.imagen);
            java.awt.image.BufferedImage image =  javax.imageio.ImageIO.read(input);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            javax.imageio.ImageIO.write(image,prod.contentTypeImagen.split("/")[1],baos);

            return ok(baos.toByteArray()).as(prod.contentTypeImagen);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return ok();
    }
    

}/*Fin de la clase.*/