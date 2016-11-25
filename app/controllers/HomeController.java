package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import java.util.*;
//import java.util.Map;
//import static play.data.Form.*;



import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;



import views.html.*;

import models.*;




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
    	if(session("connected")==null){
    		return ok(index.render());
    	}else{
    		return redirect(routes.ClienteController.productos());
    	}
        
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


}
