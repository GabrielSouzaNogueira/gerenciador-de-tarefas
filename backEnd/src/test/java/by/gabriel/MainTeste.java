// package by.gabriel;

// import by.gabriel.Model.Usuario;
// import by.gabriel.Repository.UserDAO;
// import by.gabriel.ConexaoDB.ConexaoDB;

// public class MainTeste {
//     public static void main(String[] args) {
//         // Primeiro: testar conexão
//         ConexaoDB.testarConexao();

//         // Criar um usuário de teste
//         Usuario usuario = new Usuario();
//         usuario.setNome("Gabriel Souza Nogueira");
//         usuario.setSenha("123456");

//         // Testar login direto no DAO
//         UserDAO dao = new UserDAO();
//         try {
//             boolean autenticado = dao.realizarLogin(usuario);
//             if (autenticado) {
//                 System.out.println("Login realizado com sucesso!");
//             } else {
//                 System.out.println("Usuário ou senha incorretos!");
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }