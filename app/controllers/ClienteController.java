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
        //recuparar todos los productos agregados a la carretilla desde las cookies
        //ystem.out.println(request().headers().get("Cookie"));

        //System.out.println(request().cookies().get("prod0").value());
        ArrayList<Long> productos=new ArrayList<Long>();
        ArrayList<Long> ofertas=new ArrayList<Long>();

        String cookies_strings[] = request().headers().get("Cookie");
        if(cookies_strings!=null){
            for (String cookieStr : cookies_strings) {
                //String name = cookieStr.substring(0, cookieStr.indexOf("="));
                System.out.println(cookieStr);
                String cookies[] = cookieStr.split(";");

                for(int i=0;i<cookies.length;i++){
                    cookies[i].trim();
                    String name = cookies[i].substring(0, cookies[i].indexOf("="));
                    String value = cookies[i].substring(cookies[i].indexOf("=")+1);
                    Logger.info("Name of the cookie : " + name);
                    Logger.info("Value of the cookie : " + value);

                    if(value.startsWith("p")){
                        productos.add(Long.valueOf(value.substring(1)));
                    }
                    if(value.startsWith("o")){
                        productos.add(Long.valueOf(value.substring(1)));
                    }
                }

                
                //Logger.info("Name of the cookie : " + name);
               // Cookie cookie = request.cookie(name); // Get the instance of the cookie !
            }
        }

        List<Producto> productos_list= new ArrayList<Producto>();
        List<Oferta> ofertas_list= new ArrayList<Oferta>();
        
       
        for(Long c : productos){
            productos_list.add(Producto.find.byId(c));
        }

        for(Long c : ofertas){
            ofertas_list.add(Oferta.find.byId(c));
        }

        System.out.println(productos_list.size());
        System.out.println(ofertas_list.size());
        
        

    	return ok(carretilla.render(productos_list,ofertas_list));
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