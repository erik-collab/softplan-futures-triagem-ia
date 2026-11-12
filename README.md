# ⚖️ Assistente de Triagem Jurídica com IA (PoC - Programa Futures)

## 🎯 O Contexto e a Dor Real
Analisando o ecossistema de Justiça e Gestão Pública (onde a Softplan atua fortemente com o SAJ), notei que o alto volume de petições iniciais longas e desestruturadas gera um grande gargalo operacional. O tempo gasto na triagem manual atrasa a priorização de demandas urgentes.

## 💡 A Solução (Prova de Conceito)
Esta **Prova de Conceito (PoC)** demonstra como a **Inteligência Artificial Generativa** pode ser integrada a sistemas corporativos nativos em **Java** para automatizar a leitura, classificação e análise de criticidade de documentos jurídicos. O foco aqui não é o esforço de infraestrutura, mas sim a experimentação rápida e o **resultado de impacto** voltado para o **sucesso do cliente**.

### O que a aplicação entrega instantaneamente:
1. **Classificação Automática:** Identifica a natureza da ação (ex: Saúde, Trabalhista, Consumidor).
2. **Análise de Urgência:** Avalia o risco e classifica a prioridade (Alta, Média ou Baixa) com justificativa.
3. **Resumo Executivo:** Sintetiza documentos de múltiplas páginas em até 3 linhas para tomada de decisão rápida.

## 🛡️ Diferencial Arquitetural: Resiliência e Contingência
No mundo corporativo, APIs de terceiros sofrem instabilidades. Para garantir a continuidade da operação e não travar a tela do usuário do tribunal, esta aplicação conta com **Tolerância a Falhas (Fallback)**. 
* O sistema aguarda a resposta do Google Gemini por no máximo 8 segundos.
* Se a rede oscilar ou a API retornar erro de tráfego (ex: HTTP 503), o código captura a falha de forma elegante, avisa o usuário e ejeta os dados em "Modo de Contingência" (Mock Data) para que o fluxo de trabalho não seja interrompido.

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java 25 SE (usando bibliotecas nativas `HttpURLConnection`, sem dependências de frameworks externos para garantir um Produto Mínimo Viável leve).
* **Modelo de IA:** Google Gemini API (`gemini-flash-latest`).

## 🚀 Como testar localmente
1. Clone este repositório.
2. Obtenha uma chave gratuita no [Google AI Studio](https://aistudio.google.com/).
3. Insira sua chave na variável `API_KEY` dentro do arquivo `AssistenteJuridicoIA.java`.
4. Compile e execute a classe principal na sua IDE (Eclipse/STS/IntelliJ).
5. No console, cole um texto simulando uma petição, pressione `Enter`, digite a palavra `FIM` e pressione `Enter` novamente.

## 🛤️ Roadmap de Evolução (Próximos Passos)
Sendo um MVP para validar o modelo de IA, os próximos passos do ciclo de produto incluiriam:
* **Fase 2:** Suporte a leitura direta de arquivos físicos digitalizados (PDF e DOCX).
* **Fase 3:** Criação de interface gráfica amigável para o servidor do tribunal.
* **Fase 4:** Integração com banco de dados para geração de relatórios de volumetria de processos urgentes.
