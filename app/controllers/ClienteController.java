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
              
        return redirect(routes.ClienteController.getPage(0));
    }

    public Result ofertas() {
    	return redirect(routes.ClienteController.getOfferPage(0));
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

       public Result getPage(Long page){
        if (page<0){
            return redirect(routes.ClienteController.getPage(0));
        }
       int page_size=9;
       List<Producto> prods = Producto.find.orderBy("id").findPagedList(page.intValue(),page_size).getList();
       List<Categoria> categorias_list = Categoria.find.findList();
       
       return ok(productos.render(prods,categorias_list,page));
        

    }
    
    public Result getOfferImage(Long id){

        try {
            Oferta offer = Oferta.find.byId(id);
            InputStream input = new ByteArrayInputStream(offer.imagen);
            java.awt.image.BufferedImage image =  javax.imageio.ImageIO.read(input);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            javax.imageio.ImageIO.write(image,offer.contentTypeImagen.split("/")[1],baos);

            return ok(baos.toByteArray()).as(offer.contentTypeImagen);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return ok();
    }
    /*Paginador para ofertas*/
     public Result getOfferPage(Long page){
        if (page<0){
            return redirect(routes.ClienteController.getOfferPage(0));
        }
       int page_size=9;
       List<Oferta> offer = Oferta.find.orderBy("id").findPagedList(page.intValue(),page_size).getList();
       List<Categoria> categorias_list = Categoria.find.findList();
       
       return ok(ofertas.render(offer,categorias_list,page));
        

    }
    
}/*Fin de la clase.*/