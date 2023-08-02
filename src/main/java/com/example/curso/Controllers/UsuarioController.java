package com.example.curso.Controllers;

import com.example.curso.dao.UsuarioDao;
import com.example.curso.models.Usuario;
import com.example.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value="api/usuarios/{id}", method= RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario=new Usuario();
        usuario.setId(id);
        usuario.setNombre("Irving");
        usuario.setApellido("Rios");
        usuario.setEmail("riosirving04@gmail.com");
        usuario.setPassword("12345");
        return usuario;
    }

    @RequestMapping(value="api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token){

        if(!validarToken(token)){
            return null;
        }
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value="api/usuarios/{id}", method= RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value="Authorization") String token, @PathVariable Long id){
        if(!validarToken(token)){
            return ;
        }
        usuarioDao.eliminar(id);
    }

    private boolean validarToken(String token){
        String UsuarioId=jwtUtil.getKey(token);
        return UsuarioId!=null;

    }

    @RequestMapping(value="api/usuarios", method= RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash=argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);
    }

  /* @RequestMapping(value="usuario63")
    public Usuario editar(@PathVariable Long id){
        Usuario usuario=new Usuario();
        usuario.setId(id);
        usuario.setNombre("Irving");
        usuario.setApellido("Rios");
        usuario.setEmail("riosirving04@gmail.com");
        usuario.setPassword("123456");
        return usuario;
    }

    @RequestMapping(value="usuario85")
    public Usuario buscar(@PathVariable Long id){
        Usuario usuario=new Usuario();
        usuario.setId(id);
        usuario.setNombre("Irving");
        usuario.setApellido("Rios");
        usuario.setEmail("riosirving04@gmail.com");
        usuario.setPassword("123456");
        return usuario;
    }*/

}
