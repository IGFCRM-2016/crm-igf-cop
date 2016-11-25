package controllers;

import play.mvc.*;
import play.libs.*;
import play.libs.F.*;
import java.util.*;
import java.lang.Runnable;//instead of play.libs.F.Callback0
import java.util.function.Consumer;//instead of play.libs.F.Callback
import java.security.SecureRandom;
import java.math.BigInteger;

import views.html.*;


import models.*;



public class ChatController extends Controller {


    public static HashMap<String, CustomSocket> agentes = new HashMap<String, CustomSocket>();//estas son las conexiones de agentes
    public static HashMap<String,CustomSocket> clientes = new HashMap<String,CustomSocket>();//estas son las conexiones de clientes
    //public static HashMap<String, SesionChat> sessions = new HashMap<String,SesionChat>();//estas son las conversaciones
    public static HashMap<String, ArrayList<SesionChat>> user_sessions = new HashMap<String,ArrayList<SesionChat>>();//estas son las conversaciones


    //llamada por el javascript del browser para establecer la conexion
    public  LegacyWebSocket<String> wsInterface(String user) {//cliente con el que se quiere comunicar
        return WebSocket.whenReady((in, out) -> {

            
            String username=user;
           
            if(username!=null){

                //creamos el socket
                CustomSocket cs = new CustomSocket();
                cs.in=in;
                cs.out=out;
                cs.owner=username;

                //guardamos el socket
                if(Cliente.find.where().eq("username",username).findUnique() != null){
                    if(!this.clientes.containsKey(cs.owner)){
                        this.clientes.put(cs.owner,cs);
                        System.out.println("socket cliente guardado: "+cs.owner);
                    }
                }

                if(Empleado.find.where().eq("username",username).findUnique() !=null ){
                    if(!this.agentes.containsKey(cs.owner)){
                        this.agentes.put(cs.owner,cs);
                        System.out.println("socket agente guardado: "+cs.owner);
                    }
                }
                //socket guardado

                System.out.println("iniciando chat");
                iniciarChat(username);
            }
            
        
        });
    }


    public void iniciarChat(String user){

        String username=user;

        if(username!=null){

            

            if(this.clientes.containsKey(username)){
                // final CustomSocket cliente_socket=null;
                // final CustomSocket agente_socket=null;

                final CustomSocket cliente_socket=this.clientes.get(username);

                final CustomSocket agente_socket=(CustomSocket)this.agentes.get("samuel");///OJO ACA ESTO SOLO ES DE PRUEBA

                if(cliente_socket!=null && agente_socket!=null){
                    
                    System.out.println("procedere a crear la sesion");
                    SesionChat session=new SesionChat();
                    session.endpoints.add(cliente_socket);
                    session.endpoints.add(agente_socket);


                    if(!user_sessions.containsKey(cliente_socket.owner)){
                        ArrayList array=new ArrayList();
                        array.add(session);
                        user_sessions.put(cliente_socket.owner,array);
                        System.out.println("Clave de nueva sesion cliente creada");
                    }else{
                        ArrayList  array = user_sessions.get(cliente_socket.owner);
                        array.add(session);
                        System.out.println("Nueva sesion cliente agregada a la clave");
                    }

                    if(!user_sessions.containsKey(agente_socket.owner)){
                        ArrayList array=new ArrayList();
                        array.add(session);
                        user_sessions.put(agente_socket.owner,array);
                        System.out.println("Clave de nueva sesion agente creada");
                    }else{
                        ArrayList  array = user_sessions.get(cliente_socket.owner);
                        array.add(session);
                        System.out.println("Nueva sesion agente agregada a la clave");
                    }


                    cliente_socket.in.onMessage(new Consumer<String>(){
                        public  void accept(String event){
                           session.notifyAll(event);//lo recibiran todos los otros usuarios dentro de notify hayq e restringir a quien le llega los demas deben ignorar el mensaje
                           System.out.println("notificando mensaje de cliente: "+event);
                        }
                    });//fin in.onMessage

                    agente_socket.in.onMessage(new Consumer<String>(){
                        public  void accept(String event){
                           session.notifyAll(event);
                           System.out.println("notificando mensaje de agente: "+event);
                        }
                    });//fin in.onMessage

                    cliente_socket.in.onClose(new Runnable(){
                        public void run(){
                            System.out.println("conversacion interrumpida por cliente: "+cliente_socket.owner);
                            session.notifyAll("conversacion interrumpida");
                            session.endpoints.clear();
                            user_sessions.remove(cliente_socket.owner);//remueve todas las sesiones
                            // ArrayList<SesionChat> array = this.user_sessions.get(cliente_socket.owner))
                            // if(array!=null){
                            //     for(SesionChat ses: array){
                            //         array.remove(ses);
                            //     }
                            // }
                            clientes.remove(cliente_socket.owner);
                        }
                    });//fin in.onClose

                    agente_socket.in.onClose(new Runnable(){
                        public void run(){
                            System.out.println("conversacion interrumpida por agente: "+agente_socket.owner);
                            session.notifyAll("conversacion interrumpida");
                            session.endpoints.clear();
                            user_sessions.remove(agente_socket.owner);//remueve todas las sesiones
                            // ArrayList<SesionChat> array = this.user_sessions.get(agente_socket.owner))
                            // if(array!=null){
                            //     for(SesionChat ses: array){
                            //         array.remove(ses);
                            //     }
                            // }
                            clientes.remove(agente_socket.owner);
                        }
                    });//fin in.onClose


                }
            }


        }

           
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

    }


    public  Result wsJs() {
        return ok(views.js.ws.render());
    }

}