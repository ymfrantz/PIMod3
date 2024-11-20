
        function validateForm() {
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            const errorMessage = document.getElementById('error-message');
            
            
            if (!username || !password) {
                errorMessage.textContent = "Usuário e senha são obrigatórios.";
                return false;
            }
            
            
            if (username !== "admin" || password !== "12345") {
                errorMessage.textContent = "Usuário ou senha inválidos.";
                return false;
            }
            
           
            return true;
        }
        