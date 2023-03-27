import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        
        // fazer uma conexão HTTP e buscar os top 10 filmes
        String urlApi = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(urlApi);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        
        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir e manipular os dados
        System.out.println("⭐ TOP 10 MOVIES ⭐");
        for (int i = 0; i < 3; i++) {
            Map<String,String> filme = listaDeFilmes.get(i); 
            System.out.println("\u001b[1m\u001b[3mFilme: \u001b[m" + "\u001b[38;2;255;255;255m \u001b[48;2;255;126;249m" + filme.get("title") + "\u001b[0m");
            System.out.println("\u001b[1m\u001b[3mImagem: \u001b[m" + filme.get("image"));
            double classific = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelinhas = (int) classific;
            if (classific <= 5) {
                System.out.println("\u001b[38;2;255;255;255m \u001b[48;2;255;0;0mclsClassificação: (" + classific + ")\u001b[0m");
            } else {
                System.out.println("\u001b[38;2;255;255;255m \u001b[48;2;42;255;0mClassificação: (" + classific + ")\u001b[0m");
            }
            
            for (int estrelinhas = 1; estrelinhas <= numeroEstrelinhas; estrelinhas++) {
                System.out.print("⭐");
            }
            System.out.println("\n");

         }
     }
}
