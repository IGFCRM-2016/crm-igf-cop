package models.chat;

import akka.actor.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.lang.Runnable;//instead of play.libs.F.Callback0
import java.util.function.Consumer;//instead of play.libs.F.Callback
import play.mvc.WebSocket;
import play.mvc.LegacyWebSocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.annotation.*;
import play.libs.Json;


import controllers.ChatController;
import models.chat.*;

public class Cliente  {

    public  String nombre=null;
    public  String username=null;
    private Cliente me=null;

    @JsonIgnore
    public  WebSocket.In<JsonNode> in=null;
    @JsonIgnore
    public  WebSocket.Out<JsonNode> out=null;
    @JsonIgnore
    public  Conversacion conversacion=null;


    public Cliente(String username, String nombre, WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out){
    	
    	in.onMessage(new Consumer<JsonNode>(){
            public  void accept(JsonNode event){
                /*aqui debe haber un case segun el tipo de operacion
                  las operaciones que pueden provenir del cliente son:
                    a) mensajes
                    b) solo mensajes... :S
                */

                /*este evento debe ser notificado a:
                    a) el cliente que envia el mensaje, es decir a este cliente
                    b) al agente que lo atiende en caso de que lo esten atendiendo
                */


                switch(event.get("evento").textValue()){
                    case "mensaje":
                        //el mensaje solo lo reenviamos, pero le incluimos la fecha
                        Mensaje mensaje = new Mensaje(me.username, me.nombre, event.get("to_username").textValue(), event.get("to_nombre").textValue(), event.get("message").textValue(), me.conversacion);
                        me.conversacion.agregarMensaje(mensaje);
                        
                    break;
                }

                System.out.println("cliente '"+username+"' dice '"+event.toString()+"'");
            } 
        });
                
        in.onClose(new Runnable(){
            public void run(){
                ObjectNode on = Json.newObject();
                Cliente client = ChatController.clientes.remove(username);

                if(client == null){
                    //el cliente no esta en cola esta en alguna conversacion
                    Agente atendiendome = me.conversacion.agente;
                    Conversacion conver = atendiendome.conversaciones.remove(me.username);
                    //notificarle este evento al agente
                    on = Json.newObject();
                    on.put("evento","remove_conversation");
                    on.put("conversacion",Json.toJson(conver));
                    atendiendome.out.write((JsonNode)on);

                    Mensaje mensaje = new Mensaje("error", "Internal Server", atendiendome.username, atendiendome.nombre, "Usuario remoto se ha desconectado...", me.conversacion);
                    me.conversacion.agregarMensaje(mensaje);

                }else{
                    //el usuario si estaba en cola pero ya lo eliminamos
                    //le notificamos ese evento a los agentes
                    on = Json.newObject();
                    on.put("evento","remove_from_queue");
                    on.put("username",client.username);
                    for(Agente agent : ChatController.agentes.values()){
                        agent.out.write((JsonNode)on);
                    }
                }
                
                System.out.println("cliente '"+username+"' se ha desconectado");
            }
        });

    	this.nombre=nombre;
    	this.username=username;
    	this.in = in;
    	this.out=out;
    	this.conversacion=null;
        this.me=this;
    }

    public void notificarMensaje(Mensaje mensaje){
        ObjectNode on = (ObjectNode)(Json.toJson(mensaje));
        on.put("evento","mensaje");
        this.out.write((JsonNode)on);
    }

}