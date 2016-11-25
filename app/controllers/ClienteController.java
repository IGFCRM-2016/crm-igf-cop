package controllers;

import play.mvc.*;

import views.html.cliente.*;

import models.*;


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

    public Result chat() {
        return ok(chat.render());
    }

    

}