package models.chat;

import akka.actor.*;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.chat.*;


public class Conversacion  {

    public String id;
    public Cliente cliente;
    public Agente agente;

    List<Mensaje> mensajes;

    public Conversacion(String id, Agente agente, Cliente cliente){
        this.id=id;
        this.agente=agente;
        this.cliente=cliente;
        mensajes = new ArrayList<Mensaje>();
    }

    public void agregarMensaje(Mensaje mensaje){
        this.mensajes.add(mensaje);
        this.cliente.notificarMensaje(mensaje);
        this.agente.notificarMensaje(mensaje);
    }

}