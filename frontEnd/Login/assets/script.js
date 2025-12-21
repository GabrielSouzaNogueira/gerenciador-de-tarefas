// Exibe no console que o script foi carregado
console.log("Script carregado!");

// Seleciona todos os formulários da página
const forms = document.querySelectorAll("form");

// ------------------------------
// 1. Função de Validação
// ------------------------------
function validarCampo(input, erroContainer, tipo) {
    const valor = input.value.trim(); // pega o valor do campo (sem espaços extras)
    let mensagem = ""; // mensagem de erro que pode ser exibida
    
    // Validação do campo usuário
    if (tipo === 'usuario') {
        if (valor === '') {
            mensagem = 'O nome de usuário não pode estar vazio.';
        } else if (valor.length < 5) {
            mensagem = 'O nome de usuário deve ter no mínimo 5 caracteres.';
        }
    } 
    // Validação do campo senha
    else if (tipo === 'senha') {
        if (input.value === '') { // aqui não usamos trim para permitir espaços
            mensagem = 'A senha não pode estar vazia.';
        } else if (input.value.length < 6) {
            mensagem = 'A senha deve ter no mínimo 6 caracteres.';
        }
    }

    // Feedback visual: se houver mensagem de erro
    if (mensagem !== "") {
        erroContainer.textContent = mensagem; // mostra mensagem
        erroContainer.style.color = "red";    // cor vermelha
        input.style.borderColor = "red";      // borda vermelha no input
        return false; // campo inválido
    } else {
        erroContainer.textContent = ""; // limpa mensagem
        input.style.borderColor = "var(--color-input-border)"; // volta borda padrão
        return true; // campo válido
    }
}

// ------------------------------
// 2. Eventos nos Formulários
// ------------------------------
forms.forEach(form => {
    // Seleciona os inputs de usuário e senha dentro do formulário
    const usuarioInput = form.querySelector('input[type="text"]');
    const senhaInput = form.querySelector('input[type="password"]');
    
    // Seleciona as divs de erro e sucesso
    const usuarioErro = usuarioInput.parentElement.querySelector('.mensagem-erro');
    const senhaErro = senhaInput.parentElement.querySelector('.mensagem-erro');
    const sucessoGeral = form.querySelector('.mensagem-sucesso');

    // Botão de "sign in" (troca de tela)
    var btnSignin = document.querySelector("#signinLink");
    if (btnSignin) {
      btnSignin.addEventListener("click", function (e) {
        e.preventDefault(); // evita comportamento padrão do link
        body.className = "sign-in-js"; // troca a classe do body (provavelmente muda o layout)
      });
    }

    // Validação em tempo real: cada vez que o usuário digita
    usuarioInput.addEventListener('input', () => validarCampo(usuarioInput, usuarioErro, 'usuario'));
    senhaInput.addEventListener('input', () => validarCampo(senhaInput, senhaErro, 'senha'));

    // Evento de submit do formulário
    form.addEventListener("submit", function (e) {
        e.preventDefault(); // evita envio padrão

        // Valida os dois campos
        const isUserValid = validarCampo(usuarioInput, usuarioErro, 'usuario');
        const isPassValid = validarCampo(senhaInput, senhaErro, 'senha');

        // Se ambos forem válidos
        if (isUserValid && isPassValid) {
            // Mostra mensagem de sucesso
            if (sucessoGeral) {
                sucessoGeral.textContent = "Cadastro realizado com sucesso!";
                sucessoGeral.style.color = "#28a745"; // verde
            }
            
            // Bordas verdes nos inputs
            usuarioInput.style.borderColor = "#28a745";
            senhaInput.style.borderColor = "#28a745";

            // Reset do formulário após 3 segundos
            setTimeout(() => {
                form.reset(); // limpa os campos
                if (sucessoGeral) sucessoGeral.textContent = ""; // limpa mensagem
                usuarioInput.style.borderColor = "var(--color-input-border)";
                senhaInput.style.borderColor = "var(--color-input-border)";
            }, 3000);
        }
    });
});