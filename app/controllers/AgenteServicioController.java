package controllers;

import play.mvc.*;
import java.util.*;
import play.data.*;


import views.html.agente_servicio.*;

import models.*;


public class AgenteServicioController extends Controller {
	

	public Result incidencias(){
		List<Incidencia> incidencias_list = Incidencia.find.findList();
		Empleado empleado = Empleado.find.where().eq("username",session("username")).findUnique();

		return ok(incidencias.render(incidencias_list,empleado));
	}

	public Result showIncidencia(Long id){

		Empleado empleado = Empleado.find.where().eq("username",session("username")).findUnique();

        Incidencia in = Incidencia.find.byId(id);

        if(in==null){
            flash("global_error","Incidencia solicitada no disponible");
            return redirect(routes.ClienteController.incidencias());
        }

        //unicamente el dueño de la incidencia hara que se muevan los contadores de mensajes no leidos
        if(in.empleado!=null){

        	if(in.empleado.equals(empleado)){
        		List<MensajeIncidencia> mensajes_noleidos = MensajeIncidencia.find.where().conjunction().eq("incidencia",in).eq("empleado",null).eq("leido",false).findList();

		        for(MensajeIncidencia mensaje : mensajes_noleidos){
		            mensaje.leido=true;
		            mensaje.update();
		        }
        	}
        }
        

        Form<MensajeIncidencia> mensaje_form = Form.form(MensajeIncidencia.class);

        return ok(incidencia.render(in,mensaje_form,in.getForm(),empleado));

    }


    public Result cerrarIncidencia(Long id){


        Incidencia in = Incidencia.find.byId(id);
        if(in==null){
            flash("global_error","Incidencia solicitada no disponible");
            return redirect(routes.ClienteController.incidencias());
        }
		Empleado empleado = Empleado.find.where().eq("username",session("username")).findUnique();

        Form<Incidencia> incidencia_form = Form.form(Incidencia.class).bindFromRequest();

        Form<MensajeIncidencia> mensaje_form = Form.form(MensajeIncidencia.class);

        Map<String,String[]> values = request().body().asFormUrlEncoded();
        if(values.containsKey("resolucion")){
        	if(values.get("resolucion")[0].isEmpty()){
        		incidencia_form.reject("resolucion","Debe ingresar la resolucion del problema para poder cerrar la incidencia");
        		return badRequest(incidencia.render(in,mensaje_form,incidencia_form,empleado));
        	}
        }else{
       		incidencia_form.reject("resolucion","Debe ingresar la resolucion del problema para poder cerrar la incidencia");
        	return badRequest(incidencia.render(in,mensaje_form,incidencia_form,empleado));
        }
        
        


        return ok(incidencia.render(in,mensaje_form,incidencia_form,empleado));

    }


    public Result atenderIncidencia(Long id){


        Incidencia in = Incidencia.find.byId(id);
        if(in==null){
            flash("global_error","Incidencia solicitada no disponible");
            return redirect(routes.ClienteController.incidencias());
        }
		Empleado empleado = Empleado.find.where().eq("username",session("username")).findUnique();

		in.empleado=empleado;
		in.estado=2;
		in.update();

		return redirect(routes.AgenteServicioController.showIncidencia(id));
    }


    public Result enviarMensaje(Long incidencia_id){

        Form<MensajeIncidencia> mensaje_incidencia_form = Form.form(MensajeIncidencia.class).bindFromRequest();

        Empleado empleado = Empleado.find.where().eq("username",session("username")).findUnique();


        if(mensaje_incidencia_form.hasErrors()){
        	Form<Incidencia> incidencia_form = Form.form(Incidencia.class);

            return badRequest(incidencia.render(Incidencia.find.byId(incidencia_id),mensaje_incidencia_form,incidencia_form,empleado));
        }

        MensajeIncidencia mi=mensaje_incidencia_form.get();
        mi.fecha=new Date();
        mi.empleado = empleado;
        mi.incidencia = Incidencia.find.byId(incidencia_id);

        mi.save();

        return redirect(routes.AgenteServicioController.showIncidencia(incidencia_id));

    }

	public Result chat() {
        return ok(chat.render());
    }

}