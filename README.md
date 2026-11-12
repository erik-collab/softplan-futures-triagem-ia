# ⚖️ Assistente de Triagem Jurídica com IA (PoC - Programa Futures)

> **[▶️ Clique aqui para assistir ao vídeo de demonstração (2 min)](COLE_O_LINK_DO_SEU_VIDEO_DO_LOOM_AQUI)**

## 🎯 O Problema (Dor Real)
No ecossistema de Justiça e Gestão Pública, o alto volume de petições iniciais e documentos extensos gera gargalos operacionais na triagem e priorização de demandas urgentes.

## 💡 A Solução
Esta **Prova de Conceito (PoC)** demonstra como a **Inteligência Artificial Generativa** pode ser integrada a sistemas corporativos em **Java** para automatizar a leitura, classificação e análise de criticidade de documentos jurídicos em segundos, gerando **resultado de impacto** e foco no **sucesso do cliente**.

### O que a aplicação entrega:
1. **Classificação Automática:** Identifica a natureza da ação (ex: Saúde, Trabalhista, Consumidor).
2. **Análise de Urgência:** Avalia o risco e classifica a prioridade (Alta, Média ou Baixa).
3. **Resumo Executivo:** Sintetiza documentos extensos em até 3 linhas para rápida tomada de decisão.

## 🛠️ Arquitetura e Tecnologias
* **Linguagem:** Java 25 SE (usando bibliotecas nativas `HttpURLConnection`, sem dependências externas para focar em experimentação rápida).
* **Modelo de IA:** Google Gemini API (`gemini-flash-latest`).

## 🚀 Como Executar
1. Clone este repositório.
2. Obtenha uma chave gratuita no [Google AI Studio](https://aistudio.google.com/).
3. Insira sua chave na variável `API_KEY` dentro de `AssistenteJuridicoIA.java`.
4. Compile e execute a classe principal via terminal ou IDE (Eclipse/STS/IntelliJ).
5. Cole o texto da petição no console, pressione `Enter`, digite `FIM` e pressione `Enter` novamente.

## 🛤️ Roadmap de Evolução (Próximos Passos)
Por ser uma Prova de Conceito (PoC) para experimentação rápida, o escopo inicial foi focado na integração nativa. As próximas fases de evolução do produto incluiriam:
* **Fase 2:** Suporte a leitura direta de arquivos PDF e DOCX.
* **Fase 3:** Interface visual para os analistas dos tribunais.
* **Fase 4:** Integração com banco de dados para gerar dashboards de volumetria de processos.
