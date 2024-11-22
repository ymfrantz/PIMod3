
package senac.pi.mercado.controller;


import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senac.pi.mercado.model.LoginEntity;
import senac.pi.mercado.service.LoginService;


@RestController // como um manipulador de solicitação HTTP
// e permite que o Spring o reconheça como um serviço REST.
@CrossOrigin(origins = "*") 
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    LoginService loginService;
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllLogins(){
        
        List<LoginEntity> logins = loginService.listarLogins();
        
        return new ResponseEntity<>(logins, HttpStatus.OK);
    }
    
    @PostMapping("/adicionar")
    public ResponseEntity<LoginEntity> addLogin(@Valid @RequestBody LoginEntity login){
        
        var novoLogin = loginService.criarLogin(login);
        
        return new ResponseEntity<>(novoLogin, HttpStatus.CREATED);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<LoginEntity> atualizarLogin(@PathVariable Integer id, @RequestBody LoginEntity login){
        
        var loginAtualizado = loginService.atualizarLogin(id, login);
        
        return new ResponseEntity<>(loginAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarLogin(@PathVariable Integer id){
        
        loginService.deletarLogin(id);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

