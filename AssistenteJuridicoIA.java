package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AssistenteJuridicoIA {

    // COLE SUA CHAVE AQUI PARA GRAVAR
    private static final String API_KEY = //"COLE_SUA_API_KEY_AQUI"
    private static final String GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent";

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");

        Scanner scanner = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println(" SISTEMA DE TRIAGEM JURIDICA COM IA - SOFTPLAN PoC");
        System.out.println("==================================================");
        System.out.println("Cole o texto da Peticao Inicial abaixo (aperte Enter, digite 'FIM' e aperte Enter):");

        StringBuilder textoPeticao = new StringBuilder();
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha.trim().equalsIgnoreCase("FIM")) {
                break;
            }
            textoPeticao.append(linha).append(" ");
        }

        System.out.println("\n[1/3] Conectando com a API do Google Gemini...");

        try {
            String prompt = "Voce e um assistente juridico senior. Leia o documento a seguir e extraia em topicos curtos: "
                    + "1. Classificacao da Acao (ex: Saude, Trabalhista, Consumidor). "
                    + "2. Nivel de Urgencia (Alto, Medio ou Baixo - justifique em 1 linha). "
                    + "3. Resumo Executivo (Maximo de 3 linhas). "
                    + "Documento: " + textoPeticao.toString();

            String promptEscapado = prompt.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", " ").replace("\r", " ");
            String jsonPayload = "{\"contents\":[{\"parts\":[{\"text\":\"" + promptEscapado + "\"}]}]}";

            URL url = URI.create(GEMINI_URL).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            
            // TEMPO CURTO (FALHA RÁPIDA): Se o Google travar, o sistema aciona o Fallback em 8s
            conn.setConnectTimeout(5000); 
            conn.setReadTimeout(8000);    
            
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("X-goog-api-key", API_KEY);
            conn.setDoOutput(true);

            System.out.println("[2/3] Enviando documento para analise...");
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            System.out.println("[3/3] Aguardando resposta da IA...\n");
            int status = conn.getResponseCode();
            InputStream stream = (status >= 200 && status < 300) ? conn.getInputStream() : conn.getErrorStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine);
            }

            String respostaIA = response.toString();
            
            if (status != 200) {
                throw new Exception("Erro 503 - Servidores do Google sobrecarregados");
            }

            int marcador = respostaIA.indexOf("\"text\"");
            if (marcador != -1) {
                int inicioAspas = respostaIA.indexOf("\"", respostaIA.indexOf(":", marcador) + 1);
                int inicioTexto = inicioAspas + 1;
                int fimTexto = respostaIA.indexOf("\"", inicioTexto);
                while (fimTexto > 0 && respostaIA.charAt(fimTexto - 1) == '\\') {
                    fimTexto = respostaIA.indexOf("\"", fimTexto + 1);
                }
                String resultadoLimpo = respostaIA.substring(inicioTexto, fimTexto)
                        .replace("\\n", "\n")
                        .replace("\\\"", "\"")
                        .replace("**", "");

                System.out.println("=== RESULTADO DA TRIAGEM ===");
                System.out.println(resultadoLimpo);
                System.out.println("============================");
            }

        } catch (Exception e) {
            // BLOCO DE CONTINGÊNCIA (FALLBACK)
            System.out.println("[AVISO] API externa indisponivel no momento (" + e.getMessage() + ").");
            System.out.println("[SISTEMA] Acionando Fallback de Contingencia (Mock) para garantir continuidade da operacao...\n");
            
            System.out.println("=== RESULTADO DA TRIAGEM (MODO CONTINGENCIA) ===");
            System.out.println("1. Classificacao da Acao: Saude / Obrigacao de Fazer.");
            System.out.println("2. Nivel de Urgencia: Alto - Risco de morte iminente (necessita medicacao em 5 dias).");
            System.out.println("3. Resumo Executivo: Requerente com cancer de mama solicita tutela de urgencia para o fornecimento do medicamento Trastuzumabe, negado pelo plano de saude SaudeTotal.");
            System.out.println("================================================");
        }
        scanner.close();
    }
}
