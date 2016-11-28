package controllers;
import models.*;
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
import views.html.*;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

	   return redirect(routes.HomeController.getProductPage(0,null,null,null));
        
    }

    public Result login(){

        return ok(login.render());

    	//session("conected","ssssaaamm");
    	//return redirect(routes.ClienteController.productos());
    }

    public Result login_post(){

        Map<String, String[]> values = request().body().asFormUrlEncoded();

        String username=values.get("username")[0];
        String password=values.get("password")[0];


        Cliente c = Cliente.find.where().eq("username",username).findUnique();
        Empleado e = Empleado.find.where().eq("username",username).findUnique();

        if(c == null && e ==null ){
            flash("no_registered","Usuario '"+username+"' no registrado");
            return redirect(routes.HomeController.login());
        }else{
            if(c!=null){
                if( c.password.equals(password) ){
                    session("username",username);
                    return redirect(routes.ClienteController.productos());
                    //return ok("cliente registrado y password concuerda");
                }else{
                    flash("no_password","La contraseña es invalida");
                    return redirect(routes.HomeController.login());
                    //return ok("cliente registrado y password no concuerda");
                }
            }else{
                if( e.password.equals(password) ){
                    //return ok("empleado registrado y password concuerda");
                    if(e.tipo.codigo == 1){
                        session("username",username);
                        return redirect(routes.AdministradorController.empleados());
                    }

                    if(e.tipo.codigo == 2){
                        session("username",username);
                        return redirect(routes.AgenteServicioController.incidencias());
                    }

                    if(e.tipo.codigo == 3){
                        session("username",username);
                        return redirect(routes.AgenteMarketingController.ofertas());
                    }
                }else{
                    return ok("empleado registrado y password no concuerda");
                }
            }
        }

        return null;

        //return ok(login.render());

        //session("conected","ssssaaamm");
        //return redirect(routes.ClienteController.productos());
    }


    public Result logout(){
    	session().clear();
    	return redirect(routes.HomeController.index());
    }

    public Result register(){
        
        Form<Cliente> cliente_form = Form.form(Cliente.class);
        //Form<Tarjeta> tarjeta_form = Form.form(Tarjeta.class);
    	return ok(register.render(cliente_form));
    	//return redirect(routes.HomeController.index());
    }


    public Result register_post(){
        //dos formas de obtener los datos del formulario
        Form<Cliente> cliente_form = Form.form(Cliente.class).bindFromRequest();
        Map<String, String[]> values = request().body().asFormUrlEncoded();

        if(cliente_form.hasErrors()){

            return badRequest(register.render(cliente_form));
        }

        //preparamos todo para guardar en la BD
        Cliente cliente = cliente_form.get();
        Tarjeta tarjeta = cliente.tarjeta;
        ArrayList<Gusto> gustos = new ArrayList();

        cliente.save();
        tarjeta.save();


        tarjeta.cliente=cliente;
        cliente.tarjeta.id=tarjeta.id;


        tarjeta.save();
        cliente.save();


        //obtenemos y guardamos los gustos o intereses del cliente
        for( int i =0; i<values.get("categorias[]").length; i++ ){
            Gusto g= new Gusto();
            g.categoria=Categoria.find.where().idEq(Long.valueOf(values.get("categorias[]")[i])).findUnique();
            g.cliente=cliente;
            g.save();
        }





        

        flash("registered","Usuario registrado con exito!. En 5 seg sera redirigido...");
        return redirect(routes.HomeController.register());

        //return ok(register_success.render(usuario.username));
    }

    public Result about(){
    	session().clear();
    	return redirect(routes.HomeController.index());
    }

   /* public Result productos() {
            List<Producto> productos_list = Producto.find.findList();
        
        return ok(productos.render(productos_list));

    }
    */

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

    public Result productos(){
        return redirect(routes.HomeController.getProductPage(0,null,null,null));
    }

    public Result getProductPage(Long page,String n,java.lang.Long c, java.lang.Long g){

        int page_size=9;

        String nombre= n;
        Long categoria= c;
        Long genero = g;



        List<Producto> prods = null;

        if (page<0){
            return redirect(routes.HomeController.getProductPage(Long.valueOf(0),n,c,g));
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

       
       return ok(index.render(prods,categorias_list,page));
        

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

    public Result ofertas(){
        return redirect(routes.HomeController.getOfferPage(0,null,null,null));
    }

    /*Paginador para ofertas*/
    public Result getOfferPage(Long page,String n,java.lang.Long c, java.lang.Long g){
       int page_size=9;

       String nombre= n;
       Long categoria= c;
       Long genero = g;


       // try{
       //      categoria = Long.valueOf(c);
       // }catch(Exception e){
       //      Long categoria=null;
       // }
       // try{
       //      genero = Long.valueOf(g);
       // }catch(Exception e){
       //      Long genero =null;
       // }
       

       // final Set<Map.Entry<String,String[]>> entries = request().queryString().entrySet();
       
       // for (Map.Entry<String,String[]> entry:entries) {
       //     final String key = entry.getKey();

       //     if (key.equals("nombre")) {
       //         nombre =entry.getValue()[0];
       //     }
       //     if (key.equals("genero")) {
       //          if(!entry.getValue()[0].trim().equals("")){
       //              try{
       //                  genero =Long.valueOf(entry.getValue()[0]);
       //              }catch(Exception e){
       //                  genero = null;
       //              }
       //          }
       //     }
       //     if (key.equals("categoria")) {
       //          if(!entry.getValue()[0].trim().equals("")){
       //              try{
       //                  categoria =Long.valueOf(entry.getValue()[0]);
       //              }catch(Exception e){
       //                  categoria=null;
       //              }
       //          }
       //     }
       // }



       List<Oferta> offer = null;

        if (page<0){
            return redirect(routes.HomeController.getOfferPage(Long.valueOf(0),n,c,g));
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

        
    
       //List<Oferta> offer = Oferta.find.orderBy("id").findPagedList(page.intValue(),page_size).getList();
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

       
       return ok(ofertas.render(offer,categorias_list,page));
        

    }
}
