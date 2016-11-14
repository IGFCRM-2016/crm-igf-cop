package controllers;

import play.mvc.*;

import views.html.cliente.*;


public class ClienteController extends Controller {

    public Result productos() {
		return ok(productos.render()); 
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

}