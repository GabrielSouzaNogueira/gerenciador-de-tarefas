console.log("Script carregado!");

const body = document.querySelector("body"); // body definido no CSS.s
const forms = document.querySelectorAll("form");
const btnSignin = document.querySelector("#signinLink");
const btnSignup = document.querySelector("#signupLink");


if (btnSignup) {
    btnSignup.addEventListener("click", (e) => {
        e.preventDefault();
        body.classList.replace("sign-in-js", "sign-up-js");
    });
}

if (btnSignin) {
    btnSignin.addEventListener("click", (e) => {
        e.preventDefault();
        body.classList.replace("sign-up-js", "sign-in-js");
    });
}

function validarCampo(input, erroContainer, tipo) {
    const valor = input.value.trim();
    let mensagem = ""; 
    
    if (tipo === 'usuario') {
        if (valor === '') mensagem = 'O nome de usuário não pode estar vazio.';
        else if (valor.length < 5) mensagem = 'Mínimo de 5 caracteres.';
    } 
    else if (tipo === 'senha') {
        if (input.value === '') mensagem = 'A senha não pode estar vazia.';
        else if (input.value.length < 6) mensagem = 'Mínimo de 6 caracteres.';
    }

    if (mensagem !== "") {
        erroContainer.textContent = mensagem;
        erroContainer.style.color = "red";
        input.style.borderColor = "red";
        return false;
    } else {
        erroContainer.textContent = "";
        input.style.borderColor = "#28a745"; // Feedback de OK
        return true;
    }
}

forms.forEach(form => {
    // Busca dentro do formulário atual para não misturar login com cadastro
    const usuarioInput = form.querySelector('input[name*="username"]');
    const senhaInput = form.querySelector('input[name*="password"]');
    const usuarioErro = usuarioInput.nextElementSibling; // Pega a div mensagem-erro logo abaixo
    const senhaErro = senhaInput.closest('.input-group').querySelector('.mensagem-erro');
    const sucessoGeral = form.querySelector('.mensagem-sucesso');

    usuarioInput.addEventListener('input', () => validarCampo(usuarioInput, usuarioErro, 'usuario'));
    senhaInput.addEventListener('input', () => validarCampo(senhaInput, senhaErro, 'senha'));

    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const isUserValid = validarCampo(usuarioInput, usuarioErro, 'usuario');
        const isPassValid = validarCampo(senhaInput, senhaErro, 'senha');

        if (isUserValid && isPassValid) {
            const isLogin = form.id === "loginForm";
            sucessoGeral.textContent = isLogin ? "Login realizado!" : "Cadastro realizado!";
            sucessoGeral.style.color = "#28a745";

            setTimeout(() => {
                form.reset();
                sucessoGeral.textContent = "";
                // Reseta as bordas para a cor padrão (verifique se sua variável CSS existe)
                usuarioInput.style.borderColor = ""; 
                senhaInput.style.borderColor = "";
            }, 3000);
        }
    });
});
document.querySelectorAll(".toggle-password").forEach(icon => {
    icon.addEventListener("click", function() {
        const input = this.previousElementSibling; // Pega o input que vem antes do ícone
        
        if (input.type === "password") {
            input.type = "text";
            this.classList.replace("fa-eye", "fa-eye-slash");
        } else {
            input.type = "password";
            this.classList.replace("fa-eye-slash", "fa-eye");
        }
    });
});