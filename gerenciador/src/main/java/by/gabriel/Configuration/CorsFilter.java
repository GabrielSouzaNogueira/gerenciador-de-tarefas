package by.gabriel.Configuration;

import static spark.Spark.*;

public class CorsFilter {

    public static void enableCORS() {
        
        // Permite que o navegador faça requisições OPTIONS (pré‑flight)
        options("/*", (req, res) -> {
            String headers = req.headers("Access-Control-Request-Headers");
            
            if (headers != null) {
                res.header("Access-Control-Allow-Headers", headers);
            }

            String methods = req.headers("Access-Control-Request-Method");
            if (methods != null) {
                res.header("Access-Control-Allow-Methods", methods);
            }

            return "OK";
        });

        // Antes de cada requisição, adiciona os headers de CORS
        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*"); // Permite qualquer origem
            res.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS"); //permite todos os Metodos
            res.header("Access-Control-Allow-Headers", "Content-Type,Authorization"); //Permite todos os cabeçalhos do Navegador
        });
    }
}