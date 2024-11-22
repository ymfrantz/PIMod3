async function validateForm() {
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();
    const errorMessage = document.getElementById('error-message');

    // Limpa mensagens de erro anteriores
    errorMessage.textContent = '';

    if (!username || !password) {
        errorMessage.textContent = 'Usuário e senha são obrigatórios.';
        return false;
    }

    try {
        // Faz a requisição para o back-end
        const response = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password }),
        });

        const result = await response.json();

        if (response.ok && result.success) {
            // Redireciona para a página estoque.html
            window.location.href = 'estoque.html';
            return false; // Evita o envio padrão do formulário
        } else {
            // Exibe a mensagem de erro retornada pelo back-end
            errorMessage.textContent = result.message || 'Erro ao fazer login.';
        }
    } catch (error) {
        console.error('Erro ao validar login:', error);
        errorMessage.textContent = 'Erro inesperado. Tente novamente mais tarde.';
    }

    return false; // Impede o envio padrão do formulário
}