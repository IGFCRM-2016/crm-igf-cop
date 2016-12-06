@(username: String, nombre: String, tipo: String)
$(function(){

    // get websocket class, firefox has a different way to get it
    var WS = window['MozWebSocket'] ? window['MozWebSocket'] : WebSocket;

    // open pewpew with websocket
    var socket = new WS('@routes.ChatController.communication_socket().webSocketURL(request)');


    function handleEvent(event){
        var data = JSON.parse(event.data);
        switch(data.evento){
            case "mensaje":
                writeMessage(data);
            break;
            case "cola"://enviada a todos los agentes cuando se conectan al chat
                paintQueue(data.cola);
            break;
            case "wait"://enviada al cliente
                disableInputs();
            break;
            case "remove_from_queue"://enviada a todos los agentes
                removeFromQueue(data.username);
            break;
            case "add_conversation"://enviada a solo un agente cuando el lo solicite
                addConversation(data);
            break;
            case "begin"://eviada a los clientes
                enableInputs();
            break;
            case "new_client"://enviada a todos los agentes
                addToQueue(data.cliente);
            break;
            case "show_conversation"://enviada a solo un agente cuando el lo solicite
                showConversation(data.conversacion);
            break;
            case "remove_conversation":
                removeConversation(data.conversacion)
            break;
        }
    }


    function writeMessage(data){
        if(data.from_username=="@username"){
            $('#socket-messages').append(
                '<!--message item-->'+
                '<li>'+
                            '<blockquote>'+
                              '<small>'+
                                '<cite title="Source Title">'+
                                  '<span>'+
                                    '<i class="glyphicon glyphicon-user"></i>'+ 
                                        data.from_nombre +
                                  '</span>'+
                                '</cite>'+
                                '&nbsp;|&nbsp;'+
                                '<span>'+
                                    data.fecha +
                                '</span>'+
                              '</small>'+
                              '<p class="text-success">'+
                                    data.texto+
                              '</p>'+
                            '</blockquote>'+
                '</li>'+
                '<!--fin message item-->'
            );
        }else{
            $('#socket-messages').append(
                '<!--message item-->'+
                '<li>'+
                            '<blockquote>'+
                              '<small>'+
                                '<cite title="Source Title">'+
                                  '<span>'+
                                    '<i class="glyphicon glyphicon-user"></i>'+ 
                                        data.from_nombre +
                                  '</span>'+
                                '</cite>'+
                                '&nbsp;|&nbsp;'+
                                '<span>'+
                                    data.fecha +
                                '</span>'+
                              '</small>'+
                              '<p class="text-primary">'+
                                    data.texto+
                              '</p>'+
                            '</blockquote>'+
                '</li>'+
                '<!--fin message item-->'
            );
        }
        
        var objDiv = document.getElementById("message-box");
        objDiv.scrollTop = objDiv.scrollHeight;
    }

    // function atenderCliente(cliente){
    //     var object = new Object();
    //     object.evento="antender";
    //     object.cliente=cliente;
    //     socket.send(JSON.stringify(object));
    //     alert('si me ejecuto')
    // }    

    function paintQueue(cola){



        var clientes = cola;
        for( var i=0; i<clientes.length ;i++){
            var cliente = clientes[i];
            addToQueue(cliente);
        }

    }

    function disableInputs(){
        document.getElementById('socket-input').disabled=true;
        document.getElementById('send-button').disabled=true;
        $("#socket-messages").prop( "disabled", true );
    }

    function enableInputs(){
        document.getElementById('socket-input').disabled=false;
        document.getElementById('send-button').disabled=false;
        $("#socket-messages").prop( "disabled", false );
        if($("#loading").length ) {
            $("#loading").hide();
        }
    }

    function removeFromQueue(username){
        if($("#tr-"+username)!=null){
           $("#tr-"+username).remove(); 
        }
    }

    function addConversation(data){
        $('#conversation-list').append(
            '<li id="li-'+data.username+'"><a href="#" id="a-'+data.username+'">'+data.nombre+'&nbsp;&nbsp;('+data.username+')</a></li>'
        );

        $("#a-"+data.username).click(function(e){
            // Special stuff to do when this link is clicked...
            // Cancel the default action
            e.preventDefault();
        });

        $("#li-"+data.username).on("click", 
            function (){
                var object = new Object();
                object.evento="solicitar_conversacion";
                object.cliente=data.username;
                socket.send(JSON.stringify(object));
            }    
        );

        $("#actual-username-conversation-indicator").html(data.username);
        $("#actual-nombre-conversation-indicator").html(data.nombre);


        $("#li-"+data.username).trigger('click');

    }

    function addToQueue(cliente){
        var tabla = document.getElementById('tabla-cola');
        $('#tabla-cola tr:last').after(
            '<tr id="tr-'+cliente.username+'">'+
                '<td><center>'+cliente.username+'</center></td>'+
                '<td><center>'+cliente.nombre+'</center></td>'+
                '<td><center><button class="btn btn-danger btn-xs" id="atender-'+cliente.username+'"><span class="glyphicon glyphicon-headphones"></span> Atender</button></center></td>'+
            '</tr>'
        );

        $("#atender-"+cliente.username).on("click", 
            function (){
                var object = new Object();
                object.evento="atender";
                object.cliente=cliente.username;
                socket.send(JSON.stringify(object));
            }    
        );
    }

    function showConversation(conversacion){
        var cliente = conversacion.cliente;
        var agente = conversacion.agente;

        @if(tipo=="agente"){
            $("#actual-username-conversation-indicator").html(data.username);
            $("#actual-nombre-conversation-indicator").html(data.nombre);
        }

        @if(tipo=="cliente"){
            $("#actual-username-conversation-indicator").html(data.username);
            $("#actual-nombre-conversation-indicator").html(data.nombre);
        }

        $('#socket-messages').empty();
        
        var mensajes = conversacion.mensajes;
        for(var i=0; i<mensajes.length; i++){
            writeMessage(mensajes[i]);
        }
    }

    function removeConversation(conversacion){
        if(conversacion.cliente.username=="@username"){
            $("#li-"+conversacion.agente.username).remove();
            if($('#actual-username-conversation-indicator').html()==conversacion.agente.username){
                disableInputs();
            }
        }else{
            $("#li-"+conversacion.cliente.username).remove();
            if($('#actual-username-conversation-indicator').html()==conversacion.cliente.username){
                disableInputs();
            }
        }
    }

    

    socket.onmessage = handleEvent;

    //eventos para enviar mensajes
    $('#socket-input').keyup(function(event){
        var charCode = (event.which) ? event.which : event.keyCode ;

        // if enter (charcode 13) is pushed, send message, then clear input field
        if(charCode === 13){
            //but sends it only if text is entered
            if($(this).val() != ''){
                var object = new Object();
                object.evento="mensaje";
                object.from_username="@username";
                object.from_nombre="@nombre";
                object.to_username=$('#actual-username-conversation-indicator').html();
                object.to_nombre=$('#actual-nombre-conversation-indicator').html();
                object.tipo_usuario="@tipo";
                object.message=$(this).val();
                socket.send(JSON.stringify(object));
                $(this).val('');
            }
        }
    });


    document.getElementById('send-button').onclick=function (event){
        if(document.getElementById('socket-input').value != ''){
            var object = new Object();
            object.evento="mensaje";
            object.from_username="@username";
            object.from_nombre="@nombre";
            object.to_username=$('#actual-username-conversation-indicator').html();
            object.to_nombre=$('#actual-nombre-conversation-indicator').html();
            object.tipo_usuario="@tipo";
            object.message=document.getElementById('socket-input').value;
            socket.send(JSON.stringify(object));
            document.getElementById('socket-input').value='';
        }
    };
});