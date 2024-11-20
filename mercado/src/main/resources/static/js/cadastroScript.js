document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('produtoForm');
    
    form.addEventListener('submit', function(event) {
        
        if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }
        
        
        form.classList.add('was-validated');
    });
});