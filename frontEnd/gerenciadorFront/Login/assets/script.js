console.log("Script carregado!");

var btnSignup = document.querySelector("#signupLink"); // já existe no HTML
var body = document.querySelector("body");

// Alterna para modo cadastro
if (btnSignup) {
  btnSignup.addEventListener("click", function (e) {
    e.preventDefault(); // evita recarregar a página
    body.className = "sign-up-js";
  });
}

var btnSignin = document.querySelector("#signinLink");
if (btnSignin) {
  btnSignin.addEventListener("click", function (e) {
    e.preventDefault();
    body.className = "sign-in-js";
  });
}

