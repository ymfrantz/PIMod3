package senac.pi.mercado.service;

import com.api.atividade02.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.pi.mercado.data.LoginRepository;
import senac.pi.mercado.model.LoginEntity;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;
    
public LoginEntity criarLogin(LoginEntity login){
        
        login.setId(null);
        loginRepository.save(login);
        return login;
    }

    public List<LoginEntity> listarLogins(){
        
        return loginRepository.findAll();   
    }

public void deletarLogin(Integer loginId){
        
        LoginEntity login = getLoginId(loginId);
        loginRepository.deleteById(login.getId());
    } 

   public List<LoginEntity> getLoginTitulo(String user){
     
        return loginRepository.findByUserContaining(user);
    }
   
     public LoginEntity getLoginId(Integer id){
        
        return loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Login não encontrado " + id));
    }
     
        public LoginEntity atualizarLogin(Integer id, LoginEntity loginRequest){
        LoginEntity produto = getLoginId(id);
        produto.setUser(loginRequest.getUser());
        produto.setSenha(loginRequest.getSenha());

        
        loginRepository.save(produto);
        return produto;
    }
     
}
