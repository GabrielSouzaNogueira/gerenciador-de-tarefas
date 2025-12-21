const form = document.getElementById('loginForm');
const usernameInput = document.getElementById('usernameInput');
const usernameError = document.getElementById('usernameError');

const passwordInput = document.getElementById('passwordInput');
const passwordError = document.getElementById('passwordError');

function validarNomeDeUsuario(){
    const usernameValue = usernameInput.value.trim();
    let isValid = true;

    usernameError.textContent = '';
    usernameInput.classList.remove('input-error');

    if (usernameValue === ''){
        usernameError.textContent = 'O nome de usuário não pode estar vazio.';
        isValid = false;
    } else if (usernameValue.length < 5){
        usernameError.textContent = 'O nome de usuário deve ter no mínimo 5 caracteres.';
        isValid = false;
    }
    return isValid;
}

function validarSenha(){
    const password = passwordInput.value;
    let isValid = true;

    passwordError.textContent = '';
    passwordInput.classList.remove('input-error');

    if (password === ''){
        passwordError.textContent = 'A senha não pode estar vazia.';
        isValid = false;
    } else if (password.length < 6){
        passwordError.textContent = 'A senha deve ter no mínimo 6 caracteres.';
        isValid = false;
    }
    return isValid;
}

form.addEventListener('submit', function(event){
    event.preventDefault();

    const isUsernameValid = validarNomeDeUsuario();
    const isPasswordValid = validarSenha();

    if (isUsernameValid && isPasswordValid){
        alert('Cadastro realizado com sucesso!');
    } else {
        alert('Por favor, corrija os erros nos campos de usuário e senha.');
    }
});

usernameInput.addEventListener('input', validarNomeDeUsuario);
passwordInput.addEventListener('input', validarSenha);