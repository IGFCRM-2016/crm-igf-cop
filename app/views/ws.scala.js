$(function(){

    // get websocket class, firefox has a different way to get it
    var WS = window['MozWebSocket'] ? window['MozWebSocket'] : WebSocket;

    // open pewpew with websocket
    var socket = new WS('@routes.ChatController.wsInterface("asdf").webSocketURL(request)');

    var writeMessages = function(event){
        $('#socket-messages').prepend(

            '<!--message item-->'+
            '<li class="media">'+
                '<div class="media-body">'+
                    '<div class="media">'+
                        '<a class="pull-left" href="#">'+
                            '<img class="media-object img-circle " src="@routes.Assets.versioned("images/agent.png")" />'+
                        '</a>'+
                        '<div class="media-body" >'+
                            event.data+
                            '<br />'+
                           '<small class="text-muted">Jhon Rexa | 23rd June at 5:00pm</small>'+
                           '<hr />'+
                        '</div>'+
                    '</div>'+
                '</div>'+
            '</li>'+
            '<!--fin message item-->'
        );
    }

    socket.onmessage = writeMessages;

    $('#socket-input').keyup(function(event){
        var charCode = (event.which) ? event.which : event.keyCode ;

        // if enter (charcode 13) is pushed, send message, then clear input field
        if(charCode === 13){
            socket.send($(this).val());
            $(this).val('');
        }
    });
});