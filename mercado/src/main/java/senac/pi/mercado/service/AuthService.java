package senac.pi.mercado.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.pi.mercado.data.LoginRepository;


@Service
public class AuthService {

    
    private final LoginRepository loginRepository;
    
    @Autowired
    public AuthService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public boolean authenticate(String username, String password) {
        return loginRepository.existsByUsernameAndPassword(username, password);
    }
     
}
