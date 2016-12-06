package controllers;

import play.mvc.*;
import play.libs.*;
import play.libs.F.*;
import java.util.*;
import java.lang.Runnable;//instead of play.libs.F.Callback0
import java.util.function.Consumer;//instead of play.libs.F.Callback
import java.security.SecureRandom;
import java.math.BigInteger;
// import org.json.simple.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import com.fasterxml.jackson.databind.node.ObjectNode;


import views.html.*;


import models.*;



public class ChatController extends Controller {

    public static HashMap<String,models.chat.Cliente> clientes = new HashMap<String,models.chat.Cliente>();//estas son las conexiones de clientes
    public static HashMap<String, models.chat.Agente> agentes = new HashMap<String, models.chat.Agente>();//estas son las conexiones de agentes
    
    // public static HashMap<String, CustomSocket> agentes = new HashMap<String, CustomSocket>();//estas son las conexiones de agentes
    // public static HashMap<String,CustomSocket> clientes = new HashMap<String,CustomSocket>();//estas son las conexiones de clientes
    // //public static HashMap<String, SesionChat> sessions = new HashMap<String,SesionChat>();//estas son las conversaciones
    // public static HashMap<String, ArrayList<SesionChat>> user_sessions = new HashMap<String,ArrayList<SesionChat>>();//estas son las conversaciones


    // //llamada por el javascript del browser para establecer la conexion
    // public  LegacyWebSocket<String> wsInterface() {//cliente con el que se quiere comunicar
        
    //     final String username=session().get("username");
    //     return WebSocket.whenReady((in, out) -> {
            
    //         if(username!=null){

    //             //creamos el socket
    //             CustomSocket cs = new CustomSocket();
    //             cs.in=in;
    //             cs.out=out;
    //             cs.owner=username;

    //             //guardamos el socket
    //             Cliente c = Cliente.find.where().eq("username",username).findUnique();
    //             if(c != null){
    //                 if(!this.clientes.containsKey(cs.owner)){
    //                     this.clientes.put(cs.owner,cs);
    //                     System.out.println("Cliente conectado: "+cs.owner);
    //                     System.out.println("Total clientes: "+clientes.size());
    //                 }
    //             }

    //             Empleado e=Empleado.find.where().eq("username",username).findUnique();
    //             if(e !=null ){
    //                 if(!this.agentes.containsKey(cs.owner)){
    //                     this.agentes.put(cs.owner,cs);
    //                     System.out.println("Agente conectado: "+cs.owner);
    //                     System.out.println("Total agentes: "+agentes.size());
    //                     cs.out.write(""+clientes.size());
    //                 }
    //             }
    //             //socket guardado

    //             //System.out.println("iniciando chat...");
    //             //iniciarChat(username);
    //         }
            
        
    //     });
    // }


    public  LegacyWebSocket<JsonNode> communication_socket() {//medio de comunicacion
        
        //falta validacion de cuando este logueado
        final String username=session().get("username");

        return WebSocket.whenReady((in, out) -> {

                //si es cliente lo agregamos a la cola de clientes
                Cliente c = Cliente.find.where().eq("username",username).findUnique();
                if(c != null){
                    if(!clientes.containsKey(username)){
                        models.chat.Cliente client = new models.chat.Cliente(username,c.nombre,in,out);
                        clientes.put(username,client);
                        //le decimos al cliente que espere
                        ObjectNode on = Json.newObject();
                        on.put("evento","wait");
                        out.write((JsonNode)on);

                        //le decimos a los agentes que se ha conectado un cliente mas
                        on = Json.newObject();
                        on.put("evento","new_client");
                        on.put("cliente",Json.toJson(client));
                        for(models.chat.Agente agent : ChatController.agentes.values()){
                            agent.out.write((JsonNode)on);
                        }
                        System.out.println("cliente '"+username+"' added to queue");
                    }else{
                         clientes.get(username).in = in;
                         clientes.get(username).out = out;
                         System.out.println("cliente '"+username+"' already in queue");
                    }
                }

                //si es agente lo agregamos a la cola de agentes
                Empleado e=Empleado.find.where().eq("username",username).findUnique();
                if(e !=null ){
                    if(!clientes.containsKey(username)){
                        models.chat.Agente agent = new models.chat.Agente(username,e.nombre,in,out);
                        agentes.put(username,agent);

                        //inicialmente le mandamos la cola de usuarios
                        ArrayList<Cliente> cola = new ArrayList(clientes.values());
                        
                        ObjectNode on = Json.newObject();
                        on.put("evento","cola");
                        on.put("cola", Json.toJson(cola) );

                        out.write((JsonNode)on);
                        System.out.println(((JsonNode)on).toString());

                        System.out.println("agente '"+username+"' added to list");
                    }else{
                         clientes.get(username).in = in;
                         clientes.get(username).out = out;
                         System.out.println("agente '"+username+"' already in list");
                    }
                }

        });
    }


    // public void iniciarChat(String un){

    //     String username=un;

    //     if(username!=null){

            

    //         if(this.clientes.containsKey(username)){
    //             // final CustomSocket cliente_socket=null;
    //             // final CustomSocket agente_socket=null;

    //             final CustomSocket cliente_socket=this.clientes.get(username);

    //             final CustomSocket agente_socket=(CustomSocket)this.agentes.get("ray93");///OJO ACA ESTO SOLO ES DE PRUEBA

    //             if(cliente_socket!=null && agente_socket!=null){
                    
    //                 System.out.println("procedere a crear la sesion");
    //                 SesionChat session=new SesionChat();
    //                 session.endpoints.add(cliente_socket);
    //                 session.endpoints.add(agente_socket);


    //                 if(!user_sessions.containsKey(cliente_socket.owner)){
    //                     ArrayList array=new ArrayList();
    //                     array.add(session);
    //                     user_sessions.put(cliente_socket.owner,array);
    //                     System.out.println("Clave de nueva sesion cliente creada");
    //                 }else{
    //                     ArrayList  array = user_sessions.get(cliente_socket.owner);
    //                     array.add(session);
    //                     System.out.println("Nueva sesion cliente agregada a la clave");
    //                 }

    //                 if(!user_sessions.containsKey(agente_socket.owner)){
    //                     ArrayList array=new ArrayList();
    //                     array.add(session);
    //                     user_sessions.put(agente_socket.owner,array);
    //                     System.out.println("Clave de nueva sesion agente creada");
    //                 }else{
    //                     ArrayList  array = user_sessions.get(cliente_socket.owner);
    //                     array.add(session);
    //                     System.out.println("Nueva sesion agente agregada a la clave");
    //                 }


    //                 cliente_socket.in.onMessage(new Consumer<String>(){
    //                     public  void accept(String event){
    //                        session.notifyAll(event);//lo recibiran todos los otros usuarios dentro de notify hayq e restringir a quien le llega los demas deben ignorar el mensaje
    //                        System.out.println("notificando mensaje de cliente: "+event);
    //                     }
    //                 });//fin in.onMessage

    //                 agente_socket.in.onMessage(new Consumer<String>(){
    //                     public  void accept(String event){
    //                        session.notifyAll(event);
    //                        System.out.println("notificando mensaje de agente: "+event);
    //                     }
    //                 });//fin in.onMessage

    //                 cliente_socket.in.onClose(new Runnable(){
    //                     public void run(){
    //                         System.out.println("conversacion interrumpida por cliente: "+cliente_socket.owner);
    //                         session.notifyAll("conversacion interrumpida");
    //                         session.endpoints.clear();
    //                         user_sessions.remove(cliente_socket.owner);//remueve todas las sesiones
    //                         // ArrayList<SesionChat> array = this.user_sessions.get(cliente_socket.owner))
    //                         // if(array!=null){
    //                         //     for(SesionChat ses: array){
    //                         //         array.remove(ses);
    //                         //     }
    //                         // }
    //                         clientes.remove(cliente_socket.owner);
    //                     }
    //                 });//fin in.onClose

    //                 agente_socket.in.onClose(new Runnable(){
    //                     public void run(){
    //                         System.out.println("conversacion interrumpida por agente: "+agente_socket.owner);
    //                         session.notifyAll("conversacion interrumpida");
    //                         session.endpoints.clear();
    //                         user_sessions.remove(agente_socket.owner);//remueve todas las sesiones
    //                         // ArrayList<SesionChat> array = this.user_sessions.get(agente_socket.owner))
    //                         // if(array!=null){
    //                         //     for(SesionChat ses: array){
    //                         //         array.remove(ses);
    //                         //     }
    //                         // }
    //                         clientes.remove(agente_socket.owner);
    //                     }
    //                 });//fin in.onClose


    //             }
    //         }


    //     }

           
        // if(username!=null){
        //     if(Cliente.find.where().eq("username",username).findUnique() != null){
        //         return redirect(routes.ClienteController.chat());
        //     }
        // }

        // if(username!=null){
        //     if(Empleado.find.where().eq("username",username).findUnique() != null){
        //         return redirect(routes.AgenteServicioController.chat());
        //     }
        // }

        // return ok("solo para callar al compilador");

    //}


    // public  Result wsJs() {
    //     return ok(views.js.ws.render());
    // }


    public  Result communication_api(String username,String nombre,String tipo) {

        //esta es la interfaz de comunicacion el javascript que manejara el cliente
        return ok(views.js.communication_api.render(username,nombre,tipo));

    }


}