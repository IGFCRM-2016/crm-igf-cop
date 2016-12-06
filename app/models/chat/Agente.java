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
import play.libs.Json;

import controllers.ChatController;
import models.chat.*;



public class Agente  {

    public  String nombre=null;
    public  String username=null;

    public  WebSocket.In<JsonNode> in=null;
    public  WebSocket.Out<JsonNode> out=null;

    private  Agente me = null; 
    
    public  HashMap<String, Conversacion> conversaciones=null;//estas son las conexiones de agentes;


    public Agente(String username, String nombre, WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out){
    	
    	in.onMessage(new Consumer<JsonNode>(){
            public  void accept(JsonNode event){
            	//this.out.write("I receive your message: "+event.textValue());
                ObjectNode on = (ObjectNode)event;

                switch(event.get("evento").textValue()){
                    case "mensaje":
                        //el mensaje solo lo reenviamos, pero le incluimos la fecha
                        try{
                            Conversacion conversation = conversaciones.get(event.get("to_username").textValue());
                        Mensaje mensaje = new Mensaje(me.username, me.nombre, event.get("to_username").textValue(), event.get("to_nombre").textValue(), event.get("message").textValue(), conversation);
                        conversation.agregarMensaje(mensaje);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                        
                    break;
                    case "atender":
                        //Sacamos al cliente de la cola y lo agregamos a la lista de conversaciones del agente
                        String uname = event.get("cliente").textValue();
                        if(ChatController.clientes.containsKey(uname)){

                            //removemos al cliente de la cola
                            Cliente cliente = ChatController.clientes.remove(uname);
                            //le notificamos ese evento a los agentes
                            on = Json.newObject();
                            on.put("evento","remove_from_queue");
                            on.put("username",cliente.username);
                            me.out.write((JsonNode)on);
                            for(Agente agent : ChatController.agentes.values()){
                                agent.out.write((JsonNode)on);
                            }

                            //creamos la nueva conversacion con el cliente
                            Conversacion conversacion = new Conversacion(cliente.username,me,cliente);
                            cliente.conversacion=conversacion;
                            conversaciones.put(cliente.username,conversacion);
                            
                            //le notificamos a cliente y a agente este evento
                            on = Json.newObject();
                            on.put("evento","add_conversation");
                            on.put("username",cliente.username);
                            on.put("nombre",cliente.nombre);
                            me.out.write((JsonNode)on);
                            
                            on = Json.newObject();
                            on.put("evento","add_conversation");
                            on.put("username",me.username);
                            on.put("nombre",me.nombre);
                            cliente.out.write((JsonNode)on);

                            //le decimos al cliente que puede empezar a escribir
                            on = Json.newObject();
                            on.put("evento","begin");
                            cliente.out.write((JsonNode)on);
                            
                        }
                    break;
                    case "solicitar_conversacion":
                        Conversacion to_show = conversaciones.get(event.get("username"));
                        if(to_show!=null){
                            on = Json.newObject();
                            on.put("evento","show_conversation");
                            on.put("conversacion", Json.toJson(to_show));
                            me.out.write((JsonNode)on);
                        }
                    break;
                }

            	
                System.out.println("agente '"+username+"' dice '"+event.toString()+"'");
            } 
        });
                
	    in.onClose(new Runnable(){
	        public void run(){
            	Agente agente = ChatController.agentes.remove(username);

                //si esta en conversacion con clientes le notificamos ese evento a esos clientes
                for(Conversacion conv : agente.conversaciones.values()){
                    Cliente client = conv.cliente;
                    ObjectNode on = Json.newObject();
                    on.put("evento","remove_conversation");
                    on.put("conversacion",Json.toJson(conv));
                    client.out.write((JsonNode)on);

                    Mensaje mensaje = new Mensaje("error", "Internal Server", client.username, client.nombre, "Usuario remoto se ha desconectado...", conv);
                    conv.agregarMensaje(mensaje);
                }
                System.out.println("agente '"+username+"' se ha desconectado");
	        }
	    });

    	this.nombre=nombre;
    	this.username=username;
    	this.in = in;
    	this.out=out;
    	this.conversaciones=new HashMap<String, Conversacion>();
        this.me=this;
    	
    }


    public void notificarMensaje(Mensaje mensaje){
        ObjectNode on = (ObjectNode)(Json.toJson(mensaje));
        on.put("evento","mensaje");
        this.out.write((JsonNode)on);
    }
    
}