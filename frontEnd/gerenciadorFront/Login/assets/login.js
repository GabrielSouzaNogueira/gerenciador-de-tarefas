// Aguarda o carregamento completo do DOM antes de executar o script
document.addEventListener("DOMContentLoaded", function() {
  
  // Seleciona o formulário de login pelo ID
  const form = document.getElementById("loginForm");

  // Adiciona um listener para o evento de "submit" do formulário
  form.addEventListener("submit", async function(e) {
    e.preventDefault(); // Impede o comportamento padrão (recarregar a página)

    // Captura os valores digitados nos inputs de usuário e senha
    const usuario = document.getElementById("usuarioInput").value;
    const senha = document.getElementById("passwordInput").value;

    // Limpa mensagens anteriores de erro/sucesso
    document.getElementById("usuarioError").textContent = "";
    document.getElementById("passwordError").textContent = "";

    try {
      // Faz a requisição POST para o back-end (Spark em http://localhost:4567/login)
      const resposta = await fetch("http://localhost:4567/login", {
        method: "POST", // método HTTP
        headers: { "Content-Type": "application/json" }, // indica que o corpo é JSON
        body: JSON.stringify({ nome: usuario, senha: senha }) // envia os dados do login
      });

      // Converte a resposta do servidor para JSON
      const dados = await resposta.json();

      if (resposta.ok) {
        // Se o status HTTP for 200 (OK), exibe mensagem de sucesso
        document.getElementById("usuarioError").textContent = dados.mensagem;
        document.getElementById("usuarioError").style.color = "green";
      } else {
        // Se o status for diferente de 200 (ex: 401, 400, 500), exibe mensagem de erro
        document.getElementById("usuarioError").textContent = dados.mensagem;
        document.getElementById("usuarioError").style.color = "red";
      }
    } catch (erro) {
      // Caso ocorra erro de conexão (ex: servidor fora do ar), mostra mensagem genérica
      document.getElementById("usuarioError").textContent = "Erro de conexão com o servidor";
      document.getElementById("usuarioError").style.color = "red";
    }
  });
});