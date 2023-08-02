$(document).ready(function() {
   // on ready
});

async function registrarUsuario(){

    let datos={};
    datos.nombre=document.getElementById("FirstName").value;
    datos.apellido=document.getElementById("LastName").value;
    datos.telefono=document.getElementById("PhoneUser").value;
    datos.email=document.getElementById("InputEmail").value;
    datos.password=document.getElementById("Password").value;

    let repetirpassoword=document.getElementById("RepeatPassword").value;
    if(repetirpassoword!=datos.password){
        alert("La contraseña que escribiste es diferente");
        return
    }
    const request= await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(datos)

    });
    alert("La cuenta fue creada con exito");
    window.location.href='login.html';
}
