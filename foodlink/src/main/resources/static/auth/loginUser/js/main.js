function formatCNPJ(cnpjInput) {
    let cnpj = cnpjInput.value.replace(/\D/g, '');

    if (cnpj.length > 14) {
        cnpj = cnpj.slice(0, 14);
    }

    if (cnpj.length <= 14) {
        cnpj = cnpj.replace(/^(\d{2})(\d)/, '$1.$2'); // 00.00
        cnpj = cnpj.replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3'); // 00.000.00
        cnpj = cnpj.replace(/\.(\d{3})(\d)/, '.$1/$2'); // 00.000.000/0
        cnpj = cnpj.replace(/(\d{4})(\d)/, '$1-$2'); // 00.000.000/0000-0
    }

    cnpjInput.value = cnpj;
}

function checkCNPJ(cnpjInput) {
    const cnpj = cnpjInput.value.replace(/\D/g, '');
    const feedbackElement = document.getElementById('cnpjFeedback');

    if (cnpj.length !== 14) {
        feedbackElement.textContent = 'O CNPJ deve estar completamente preenchido com 14 dígitos.';
    } else {
        feedbackElement.textContent = ''; // Limpa a mensagem de feedback se o CNPJ estiver correto
    }
}

function validatePassword(passwordInput) {
    const password = passwordInput.value;
    const feedbackElement = document.getElementById('error');
    let feedbackMessage = '';

    // Verificar comprimento mínimo
    if (password.length < 8) {
        feedbackMessage += 'A senha deve ter pelo menos 8 caracteres. ';
    }

    feedbackElement.textContent = feedbackMessage; // Exibir mensagem de erro
}

function clearFeedback(feedbackId) {
    document.getElementById(feedbackId).textContent = ''; // Limpa a mensagem de feedback
}

function submitForm() {
    const cnpjInput = document.getElementById('cnpj');
    const cnpj = cnpjInput.value.replace(/\D/g, '');
    const passwordInput = document.getElementById('password');
    const password = passwordInput.value;
    const errorFeedbackElement = document.getElementById('error');

    // Limpa mensagens de erro antes da validação
    errorFeedbackElement.textContent = '';
    const cnpjFeedbackElement = document.getElementById('cnpjFeedback');
    cnpjFeedbackElement.textContent = '';

    // Verificar CNPJ
    if (cnpj.length !== 14) {
        cnpjFeedbackElement.textContent = 'Por favor, preencha o CNPJ corretamente antes de cadastrar.';
        return false; // Impede o envio do formulário
    }

    // Verificar Senha
    const hasUpperCase = /[A-Z]/.test(password);
    const hasLowerCase = /[a-z]/.test(password);
    const hasNumbers = /\d/.test(password);
    const hasSpecialChars = /[!@#$%^&*(),.?":{}|<>]/.test(password);
    if (password.length < 8) {
        errorFeedbackElement.textContent = 'A senha deve ter pelo menos 8 caracteres.';
        return false; // Impede o envio do formulário
    }
    if (!hasUpperCase || !hasLowerCase || !hasNumbers || !hasSpecialChars) {
        errorFeedbackElement.textContent = 'A senha deve conter letras maiúsculas, minúsculas, números e caracteres especiais.';
        return false; // Impede o envio do formulário
    }

    // Aqui você pode adicionar a lógica para enviar o formulário
    alert('Cadastro realizado com sucesso!'); // Exemplo de sucesso
    // document.getElementById('registrationForm').submit(); // Se quiser submeter o formulário
}