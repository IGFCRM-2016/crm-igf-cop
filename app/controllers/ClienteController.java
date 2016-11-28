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
    	return ok(ofertas.render());
    }

    public Result incidentes() {
    	return ok(incidentes.render());
    }

    public Result carretilla() {
        //recuparar todos los productos agregados a la carretilla desde las cookies
        //ystem.out.println(request().headers().get("Cookie"));

        //System.out.println(request().cookies().get("prod0").value());

        String cookies_strings[] = request().headers().get("Cookie");
        if(cookies_strings!=null){
            for (String cookieStr : cookies_strings) {
                //String name = cookieStr.substring(0, cookieStr.indexOf("="));

                String cookies[] = cookieStr.split(";");

                for(int i=0;i<cookies.length;i++){
                    cookies[i].trim();
                    String name = cookies[i].substring(0, cookies[i].indexOf("="));
                    Logger.info("Name of the cookie : " + name);
                }

                
                //Logger.info("Name of the cookie : " + name);
               // Cookie cookie = request.cookie(name); // Get the instance of the cookie !
            }
        }
        

        //System.out.println(request().cookies().size());
        // for(play.mvc.Http.Cookie c: response().cookies()){
        //     System.out.println(c.value());
        // }
        //System.out.println(request().queryString());
        List<Producto> productos_list=Producto.find.findList();
        List<Oferta> ofertas_list=Oferta.find.findList();

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
    

}/*Fin de la clase.*/