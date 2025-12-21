console.log("Script carregado!");

const forms = document.querySelectorAll("form");

// 1. Funções de Validação (Lógica do Código 2)
function validarCampo(input, erroContainer, tipo) {
    const valor = input.value.trim();
    let mensagem = "";
    
    if (tipo === 'usuario') {
        if (valor === '') {
            mensagem = 'O nome de usuário não pode estar vazio.';
        } else if (valor.length < 5) {
            mensagem = 'O nome de usuário deve ter no mínimo 5 caracteres.';
        }
    } else if (tipo === 'senha') {
        if (input.value === '') { // Senha não usamos trim para permitir espaços se o user quiser
            mensagem = 'A senha não pode estar vazia.';
        } else if (input.value.length < 6) {
            mensagem = 'A senha deve ter no mínimo 6 caracteres.';
        }
    }

    // Aplica o feedback visual (Estilo do Código 1)
    if (mensagem !== "") {
        erroContainer.textContent = mensagem;
        erroContainer.style.color = "red";
        input.style.borderColor = "red";
        return false;
    } else {
        erroContainer.textContent = "";
        input.style.borderColor = "var(--color-input-border)";
        return true;
    }
}

// 2. Eventos nos Formulários
forms.forEach(form => {
    const usuarioInput = form.querySelector('input[type="text"]');
    const senhaInput = form.querySelector('input[type="password"]');
    
    // Busca as divs de erro baseadas na estrutura do Código 1
    const usuarioErro = usuarioInput.parentElement.querySelector('.mensagem-erro');
    const senhaErro = senhaInput.parentElement.querySelector('.mensagem-erro');
    const sucessoGeral = form.querySelector('.mensagem-sucesso');

    // Validação em tempo real (Igual ao Código 2)
    usuarioInput.addEventListener('input', () => validarCampo(usuarioInput, usuarioErro, 'usuario'));
    senhaInput.addEventListener('input', () => validarCampo(senhaInput, senhaErro, 'senha'));

    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const isUserValid = validarCampo(usuarioInput, usuarioErro, 'usuario');
        const isPassValid = validarCampo(senhaInput, senhaErro, 'senha');

        if (isUserValid && isPassValid) {
            // Feedback de Sucesso 
            if (sucessoGeral) {
                sucessoGeral.textContent = "Cadastro realizado com sucesso!";
                sucessoGeral.style.color = "#28a745";
            }
            
            usuarioInput.style.borderColor = "#28a745";
            senhaInput.style.borderColor = "#28a745";

            //o reset após o sucesso
            setTimeout(() => {
                form.reset();
                if (sucessoGeral) sucessoGeral.textContent = "";
                usuarioInput.style.borderColor = "var(--color-input-border)";
                senhaInput.style.borderColor = "var(--color-input-border)";
            }, 3000);
        }
    });
});