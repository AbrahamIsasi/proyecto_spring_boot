function eliminarContacto(elemento){
    var ok = confirm('Estas seguro que desea eliminar');

    if(ok){
        elemento.nextElementSibling.submit();
    }

}