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

    if (password.length < 8) {
        feedbackMessage += 'A senha deve ter pelo menos 8 caracteres. ';
    }

    feedbackElement.textContent = feedbackMessage; // Exibir mensagem de erro
}

function clearFeedback(feedbackId) {
    document.getElementById(feedbackId).textContent = ''; // Limpa a mensagem de feedback
}