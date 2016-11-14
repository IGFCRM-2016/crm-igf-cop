package controllers;

import play.mvc.*;
import java.util.*;

import views.html.agente_marketing.*;

import models.*;


public class AgenteMarketingController extends Controller {

	public Result ofertas(){
		List<Oferta> ofertas_list = Oferta.find.findList();
		return ok(ofertas.render(ofertas_list));
	}
	

  //   public Result index() {
		// if(session("connected")==null){
		// 	return ok(index.render());
		// }else{
		// 	return redirect(routes.HomeController.home());
		// }     
  //   }

}