package com.example.curso.Controllers;

import com.example.curso.dao.UsuarioDao;
import com.example.curso.models.Usuario;
import com.example.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;
    @RequestMapping(value="api/login", method= RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){
        Usuario usuarioLogeado=usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if(usuarioLogeado!=null){
            String tokenJwt=jwtUtil.create(String.valueOf(usuarioLogeado.getId()), usuarioLogeado.getEmail());
            return tokenJwt;
        };
        return "fail";
    }
}
