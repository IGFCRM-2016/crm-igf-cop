package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import java.util.*;
import java.io.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.*;
//import views.html.*;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;
//import java.nio.Files;

import models.*;


import views.html.cliente.*;



public class ClienteController extends Controller {

    public Result productos() {
              
        return redirect(routes.ClienteController.getPage(0,null,null,null));
    }

    public Result ofertas() {
    	return redirect(routes.ClienteController.getOfferPage(0,null,null,null));
    }

    public Result incidencias() {
        List<Incidencia> incidencias_list =null;

        Map<String, String[]> values = request().queryString();

        String fecha = null;
        
        if(values.containsKey("fecha")){
            fecha = values.get("fecha")[0];
        }
    

        if(fecha!=null){
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
            fecha="01-"+fecha;
            try{

                Date inicio = dateFormat.parse(fecha);
                System.out.println(inicio);
                Calendar c = Calendar.getInstance();
                c.setTime(inicio);
                c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
                Date fin = c.getTime();
                System.out.println(fin);
               incidencias_list=Incidencia.find.where().conjunction().eq("cliente.username",session("username")).ge("fecha",inicio).le("fecha",fin).findList();
                
                flash("fecha",fecha);
            }catch(Exception e){
                e.printStackTrace();
            }

        }else{
            Calendar inicio = Calendar.getInstance();
            Calendar fin = Calendar.getInstance();

            inicio.set(Calendar.DAY_OF_MONTH, inicio.getActualMinimum(Calendar.DAY_OF_MONTH));
            fin.set(Calendar.DAY_OF_MONTH, fin.getActualMaximum(Calendar.DAY_OF_MONTH));


           incidencias_list=Incidencia.find.where().conjunction().eq("cliente.username",session("username")).ge("fecha",inicio.getTime()).le("fecha",fin.getTime()).findList();
            
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
            
            flash("fecha",dateFormat.format(new Date()) );

        }

        
        return ok(incidencias.render(incidencias_list));

    }


    public Result incidencia_new(Long compra) {
        //solo se podran levantar insidencias para las compras del mes

        Form<Incidencia> incidencia_form = Form.form(Incidencia.class).bindFromRequest();


        if( incidencia_form.hasErrors() ){


            Calendar inicio = Calendar.getInstance();
            Calendar fin = Calendar.getInstance();
            inicio.set(Calendar.DAY_OF_MONTH, inicio.getActualMinimum(Calendar.DAY_OF_MONTH));
            fin.set(Calendar.DAY_OF_MONTH, fin.getActualMaximum(Calendar.DAY_OF_MONTH));
            List<Compra> compras_list=Compra.find.where().conjunction().eq("cliente.username",session("username")).ge("fecha",inicio.getTime()).le("fecha",fin.getTime()).findList();
            

            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
            flash("fecha",dateFormat.format(new Date()) );
            
            flash("modal","mod-incident-compra-"+compra);
            return badRequest(compras.render(compras_list,incidencia_form));

        }//fin if form has errors


        Incidencia nueva = incidencia_form.get();
        Compra comp = Compra.find.byId(compra);
        nueva.compra = comp;

        nueva.cliente = Cliente.find.where().eq("username",session("username")).findUnique();
        
        nueva.save();
        comp.incidencia=nueva;
        comp.update();

        flash("exito", "Tu incidencia ha sido guardada con exito, puedes ver los detalles de incidencias ");
        return redirect(routes.ClienteController.compras());
    }


    public Result showIncidencia(Long id){

        Incidencia in = Incidencia.find.byId(id);

        if(in==null){
            flash("global_error","Incidencia solicitada no disponible");
            return redirect(routes.ClienteController.incidencias());
        }

        List<MensajeIncidencia> mensajes_noleidos = MensajeIncidencia.find.where().conjunction().eq("incidencia",in).eq("cliente",null).eq("leido",false).findList();

        for(MensajeIncidencia mensaje : mensajes_noleidos){
            mensaje.leido=true;
            mensaje.update();
        }

        Form<MensajeIncidencia> mensaje_form = Form.form(MensajeIncidencia.class);

        return ok(incidencia.render(in,mensaje_form));

    }

    public Result enviarMensaje(Long incidencia_id){

        Form<MensajeIncidencia> mensaje_incidencia_form = Form.form(MensajeIncidencia.class).bindFromRequest();

        if(mensaje_incidencia_form.hasErrors()){
            return badRequest(incidencia.render(Incidencia.find.byId(incidencia_id),mensaje_incidencia_form));
        }

        MensajeIncidencia mi=mensaje_incidencia_form.get();
        mi.fecha=new Date();
        mi.cliente = Cliente.find.where().eq("username",session("username")).findUnique();
        mi.incidencia = Incidencia.find.byId(incidencia_id);

        mi.save();

        return redirect(routes.ClienteController.showIncidencia(incidencia_id));

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
                        ofertas.add(Long.valueOf(value.substring(1)));
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

    public Result realizarCompra(){
        Map<String, String[]> values = request().body().asFormUrlEncoded();

        ArrayList<String> elements_id = new ArrayList<String>();
        for(String key : values.keySet()){
            if(!key.equals("total")){
                System.out.println(key);
                String id = key.substring(key.indexOf("[") + 1, key.indexOf("]"));
                System.out.println("Id generado: "+ id);
                if(!elements_id.contains(id)){
                    elements_id.add(id);
                }
            }
        }

        Compra compra = new Compra();
        compra.fecha = Calendar.getInstance().getTime();

        Cliente cliente = Cliente.find.where().eq("username",session("username")).findUnique();

        if(cliente!=null){
            compra.cliente = cliente;
        }

        for(String id_code : elements_id){
            if(id_code.startsWith("o")){
                Long real_id = Long.valueOf(id_code.substring(1));
                LineaOferta lo = new LineaOferta();
                lo.oferta=Oferta.find.byId(real_id);
                lo.compra = compra;
                lo.cantidad = Integer.valueOf(values.get("cantidad["+id_code+"]")[0]);
                lo.subtotal = Double.valueOf(values.get("subtotal["+id_code+"]")[0].substring(1));
                lo.precio_compra = Double.valueOf(values.get("precio["+id_code+"]")[0].substring(1));
                compra.lineas_oferta.add(lo);
            }

            if(id_code.startsWith("p")){
                Long real_id = Long.valueOf(id_code.substring(1));
                LineaProducto lp = new LineaProducto();
                lp.producto=Producto.find.byId(real_id);
                lp.compra = compra;
                lp.cantidad = Integer.valueOf(values.get("cantidad["+id_code+"]")[0]);
                lp.subtotal = Double.valueOf(values.get("subtotal["+id_code+"]")[0].substring(1));
                lp.precio_compra = Double.valueOf(values.get("precio["+id_code+"]")[0].substring(1));
                compra.lineas_producto.add(lp);
            }
        }

        compra.total=Double.valueOf(values.get("total")[0].substring(1));
        compra.save();

        //eliminando cookies
        String cookies_strings[] = request().headers().get("Cookie");
        ArrayList<String> cookies_name = new ArrayList<String>();
        if(cookies_strings!=null){
            for (String cookieStr : cookies_strings) {
                //String name = cookieStr.substring(0, cookieStr.indexOf("="));
                System.out.println("cookie de entrada para eliminacion de claves: \n"+cookieStr);
                String cookies[] = cookieStr.split(";");

                for(int i=0;i<cookies.length;i++){
                    cookies[i].trim();
                    String name = cookies[i].substring(0, cookies[i].indexOf("="));
                    name=name.trim();
                    if(name.startsWith("prod")){
                        System.out.println("estoy listo para eliminar cookie:"+name);
                        response().discardCookie(name);
                        System.out.println("cookie >"+name+"< descartada");
                    }
                }
            }
        }
        //fin eliminiacion cookies

        flash("exito","Tu compra se ha registrado exitosamente, puedes ver los detalles ");

        return redirect(routes.ClienteController.carretilla());
    }


    public Result compras(){
        List<Compra> compras_list =null;

        Map<String, String[]> values = request().queryString();

        String fecha = null;
        
        if(values.containsKey("fecha")){
            fecha = values.get("fecha")[0];
        }
    

        if(fecha!=null){
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
            fecha="01-"+fecha;
            try{

                Date inicio = dateFormat.parse(fecha);
                System.out.println(inicio);
                Calendar c = Calendar.getInstance();
                c.setTime(inicio);
                c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
                Date fin = c.getTime();
                System.out.println(fin);
                compras_list=Compra.find.where().conjunction().eq("cliente.username",session("username")).ge("fecha",inicio).le("fecha",fin).findList();
                

                Date hoy = new Date();
                if( hoy.compareTo(fin) >= 0){
                    flash("report_disabled","disabled");
                }
                flash("fecha",fecha);
            }catch(Exception e){
                e.printStackTrace();
            }

        }else{
            Calendar inicio = Calendar.getInstance();
            Calendar fin = Calendar.getInstance();

            inicio.set(Calendar.DAY_OF_MONTH, inicio.getActualMinimum(Calendar.DAY_OF_MONTH));
            fin.set(Calendar.DAY_OF_MONTH, fin.getActualMaximum(Calendar.DAY_OF_MONTH));


            compras_list=Compra.find.where().conjunction().eq("cliente.username",session("username")).ge("fecha",inicio.getTime()).le("fecha",fin.getTime()).findList();
            
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
            
            flash("fecha",dateFormat.format(new Date()) );

        }

        System.out.println("tam: "+Cliente.find.where().eq("username",null).findList().size());
        
        Form<Incidencia> incidencia_form = Form.form(Incidencia.class);
        return ok(compras.render(compras_list,incidencia_form));
    }



    public Result chat() {
        String username = session("username");
        Cliente cliente = Cliente.find.where().eq("username",username).findUnique();

        if(cliente==null){
            return redirect(routes.HomeController.login());
        }
        String nombre=cliente.nombre;
        return ok(chat.render(username,nombre,"cliente"));
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
  public Result getPage(Long page,String n,java.lang.Long c, java.lang.Long g){

        int page_size=9;

        String nombre= n;
        Long categoria= c;
        Long genero = g;



        List<Producto> prods = null;

        if (page<0){
            return redirect(routes.ClienteController.getPage(Long.valueOf(0),n,c,g));
        }

        if (genero!=null) {
            if (categoria!=null ) {
                if (nombre!=null ) {
                    //filtrar por genero categoria y nombre
                    com.avaje.ebean.Query<Producto> query = Ebean.find(Producto.class);
                    com.avaje.ebean.ExpressionList<models.Producto> el = query.where().conjunction().disjunction();
                    for(String word : nombre.split(" ")){
                        el.icontains("nombre",word);
                    }
                    el.endJunction().eq("categoria.id",categoria).eq("genero",genero).endJunction();
                    prods = el.orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }else{
                    /*Filtrar por genero y categoria*/
                    prods = Producto.find.where().conjunction().eq("categoria.id",categoria).eq("genero",genero).orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }
            }else{
                if (nombre!=null) {
                    /*Filtrar por genero y nombre*/
                    com.avaje.ebean.Query<Producto> query = Ebean.find(Producto.class);
                    com.avaje.ebean.ExpressionList<models.Producto> el = query.where().conjunction().disjunction();
                    for(String word : nombre.split(" ")){
                        el.icontains("nombre",word);
                    }
                    el.endJunction().eq("genero",genero).endJunction();
                    prods = el.orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }else{
                    /*Filtrar por genero*/
                    prods = Producto.find.where().eq("genero",genero).orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }
            }   
        }else{
            if (categoria!=null ) {
                if (nombre!=null ) {
                    //filtrar por categoria y nombre
                    com.avaje.ebean.Query<Producto> query = Ebean.find(Producto.class);
                    com.avaje.ebean.ExpressionList<models.Producto> el = query.where().conjunction().disjunction();
                    for(String word : nombre.split(" ")){
                        el.icontains("nombre",word);
                    }
                    el.endJunction().eq("categoria.id",categoria).endJunction();
                    prods = el.orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }else{
                    /*Filtrar por categoria*/
                    prods = Producto.find.where().eq("categoria.id",categoria).orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }
            }else{
                if (nombre!=null) {
                /*Filtrar por nombre*/
                    com.avaje.ebean.Query<Producto> query = Ebean.find(Producto.class);
                    com.avaje.ebean.ExpressionList<models.Producto> el = query.where().disjunction();
                    for(String word : nombre.split(" ")){
                        System.out.println(word);
                        el.icontains("nombre",word);
                    }
                    prods = el.endJunction().orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }else{
                    /*No hay filtro*/
                    prods = Producto.find.orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }
            } 
        }

        List<Categoria> categorias_list = Categoria.find.findList();
       //offer = Oferta.find.where().eq("nombre","Juego de sala").orderBy("id").findPagedList(page.intValue(),page_size).getList();
       if(nombre!=null){
            flash("filter","?");
            flash("nom",nombre);
       }
       if(categoria!=null){
            flash("filter","?");
            flash("cat",Long.valueOf(categoria).toString());
            flash("catn",Categoria.find.where().eq("id",categoria).findUnique().nombre);
       }
       if(genero!=null){
            flash("filter","?");
            flash("gen",Long.valueOf(genero).toString());
            switch(genero.intValue()){
                case 1:
                    flash("genn","Masculino");
                break;
                case 2:
                    flash("genn","Femenino");
                break;
                case 3:
                    flash("genn","Unisex");
                break;
            }
       }

       
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
    public Result getOfferPage(Long page,String n,java.lang.Long c, java.lang.Long g){
       int page_size=9;

       String nombre= n;
       Long categoria= c;
       Long genero = g;
     
       List<Oferta> offer = null;

        if (page<0){
            return redirect(routes.ClienteController.getOfferPage(Long.valueOf(0),n,c,g));
        }

        if (genero!=null) {
            if (categoria!=null ) {
                if (nombre!=null ) {
                    //filtrar por genero categoria y nombre
                    com.avaje.ebean.Query<Oferta> query = Ebean.find(Oferta.class);
                    com.avaje.ebean.ExpressionList<models.Oferta> el = query.where().conjunction().disjunction();
                    for(String word : nombre.split(" ")){
                        el.icontains("nombre",word);
                    }
                    el.endJunction().eq("aplicaciones_oferta.categoria.id",categoria).eq("genero",genero).endJunction();
                    offer = el.orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }else{
                    /*Filtrar por genero y categoria*/
                    offer = Oferta.find.where().conjunction().eq("aplicaciones_oferta.categoria.id",categoria).eq("genero",genero).orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }
            }else{
                if (nombre!=null) {
                    /*Filtrar por genero y nombre*/
                    com.avaje.ebean.Query<Oferta> query = Ebean.find(Oferta.class);
                    com.avaje.ebean.ExpressionList<models.Oferta> el = query.where().conjunction().disjunction();
                    for(String word : nombre.split(" ")){
                        el.icontains("nombre",word);
                    }
                    el.endJunction().eq("genero",genero).endJunction();
                    offer = el.orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }else{
                    /*Filtrar por genero*/
                    offer = Oferta.find.where().eq("genero",genero).orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }
            }   
        }else{
            if (categoria!=null ) {
                if (nombre!=null ) {
                    //filtrar por categoria y nombre
                    com.avaje.ebean.Query<Oferta> query = Ebean.find(Oferta.class);
                    com.avaje.ebean.ExpressionList<models.Oferta> el = query.where().conjunction().disjunction();
                    for(String word : nombre.split(" ")){
                        el.icontains("nombre",word);
                    }
                    el.endJunction().eq("aplicaciones_oferta.categoria.id",categoria).endJunction();
                    offer = el.orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }else{
                    /*Filtrar por categoria*/
                    offer = Oferta.find.where().eq("aplicaciones_oferta.categoria.id",categoria).orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }
            }else{
                if (nombre!=null) {
                /*Filtrar por nombre*/
                    com.avaje.ebean.Query<Oferta> query = Ebean.find(Oferta.class);
                    com.avaje.ebean.ExpressionList<models.Oferta> el = query.where().disjunction();
                    for(String word : nombre.split(" ")){
                        System.out.println(word);
                        el.icontains("nombre",word);
                    }
                    offer = el.endJunction().orderBy("id").findPagedList(page.intValue(),page_size).getList();
                    System.out.println("entro aqui");
                }else{
                    /*No hay filtro*/
                    offer = Oferta.find.orderBy("id").findPagedList(page.intValue(),page_size).getList();
                }
            } 
        }

       List<Categoria> categorias_list = Categoria.find.findList();

       if(nombre!=null){
            flash("filter","?");
            flash("nom",nombre);
       }
       if(categoria!=null){
            flash("filter","?");
            flash("cat",Long.valueOf(categoria).toString());
            flash("catn",Categoria.find.where().eq("id",categoria).findUnique().nombre);
       }
       if(genero!=null){
            flash("filter","?");
            flash("gen",Long.valueOf(genero).toString());
            switch(genero.intValue()){
                case 1:
                    flash("genn","Masculino");
                break;
                case 2:
                    flash("genn","Femenino");
                break;
                case 3:
                    flash("genn","Unisex");
                break;
            }
       }
       return ok(ofertas.render(offer,categorias_list,page));
        

    }
    
}/*Fin de la clase.*/