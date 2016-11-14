package controllers;

import play.mvc.*;
import java.util.*;

import views.html.agente_servicio.*;

import models.*;


public class AgenteServicioController extends Controller {
	

	public Result incidencias(){
		List<Incidencia> incidencias_list = Incidencia.find.findList();
		return ok(incidencias.render(incidencias_list));
	}
  //   public Result index() {
		// if(session("connected")==null){
		// 	return ok(index.render());
		// }else{
		// 	return redirect(routes.HomeController.home());
		// }     
  //   }

}