package models;
import play.mvc.*;
import play.libs.*;
import play.libs.F.*;
import java.util.*;
import java.lang.Runnable;//instead of play.libs.F.Callback0
import java.util.function.Consumer;//instead of play.libs.F.Callback


public class CustomSocket{

	public WebSocket.Out<String> out; //salida del socket
	public WebSocket.In<String> in; //entrada del socket
	public String owner; // dueño del socket

}