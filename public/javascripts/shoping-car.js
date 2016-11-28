function shopingCountCarNotify(){
    var cookies =document.cookie.split (';').filter(function(el) {return el.length != 0});
    document.getElementById('car_notification').innerHTML = cookies.length;
}


function addToShopingCar(producto_id) {
    var cookies = document.cookie.split (';').filter(function(el) {return el.length != 0});

    var agregado = false;
    for(var i = 0; i<cookies.length; i++){
        var cookie = cookies[i];
        var key = cookie.split('=')[0];
        var value = cookie.split('=')[1];
        if(value==producto_id){
            agregado=true; 
        }
    }

    if(!agregado){//si no esta agregado lo agregamos
        var cookie_name = "prod"+cookies.length;
        document.cookie =  cookie_name + "=" + encodeURIComponent( producto_id );
        shopingCountCarNotify();

        $.notify("Elemento agregado a la carretilla :).", "success");
    }else{
        $.notify("Elemento agregado a carretilla previamente ;).", "info");
    }
}

function removeToShopingCar(producto_id) {
    var cookies = document.cookie.split (';').filter(function(el) {return el.length != 0});

    for(var i = 0; i<cookies.length; i++){
        var cookie = cookies[i];
        var key = cookie.split('=')[0];
        var value = cookie.split('=')[1];
        if(value==producto_id){
            document.cookie = key + "=;" + "max-age=0";
            $.notify("Elemento retirado de la carretilla.", "info");
        }
    }
    shopingCountCarNotify();
}

function eliminarFila(tr_id) {
    //no clue what to put here?
    var fil = document.getElementById(tr_id);
     fil.parentNode.removeChild(fil);
}

function calcularSubtotal(resultado_id,cantidad_id,precio_id){
    document.getElementById(resultado_id).value='$'+parseInt(document.getElementById(cantidad_id).value)*parseFloat(document.getElementById(precio_id).value.substring(1));
}

function recalcularTotal(){
    var subtotales = document.getElementsByClassName('subtotal');

    var total=0;
    for(var i=0; i<subtotales.length;i++ ){
        total=total+parseFloat(subtotales[i].value.substring(1));
    }
    document.getElementById('total').value="$"+total;
}


window.onload=function(){
    shopingCountCarNotify();
}

