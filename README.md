# 🤖 Discord Invite Bot

Um bot desenvolvido em **Java** para automatizar a criação de convites personalizados para um servidor do Discord, com integração ao **Google Sheets** para organizar os convites associados aos e-mails dos usuários.

---

## 📌 Funcionalidades

- 🔗 Geração automática de **convites únicos e permanentes** para um servidor do Discord.
- 📧 Cada convite é **associado a um e-mail** específico, extraído de uma planilha do Google.
- 🧾 Os convites gerados são escritos diretamente na mesma planilha, ao lado do e-mail correspondente.
- ✅ Evita duplicações verificando se o convite já foi criado para aquele e-mail.
- 🔒 Convites com **tempo ilimitado** e **número de usos personalizado** (ou ilimitado).

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Descrição |
|------------|-----------|
| **Java 17+** | Linguagem principal usada no desenvolvimento do bot |
| **JDA (Java Discord API)** | Biblioteca para interagir com a API do Discord |
| **Google Sheets API** | Para ler e escrever dados diretamente em uma planilha do Google |
| **Gradle / Maven** | Gerenciamento de dependências (dependendo da sua escolha) |
| **dotenv-java (opcional)** | Leitura de variáveis de ambiente do `.env` |

---

## 📋 Pré-requisitos

- Conta no [Discord Developer Portal](https://discord.com/developers/applications)
- Planilha no Google Drive com uma lista de e-mails
- Projeto criado no [Google Cloud Console](https://console.cloud.google.com/) com a API do Google Sheets ativada
- Arquivo de credenciais JSON da conta de serviço

---

## 📁 Estrutura do Projeto (Exemplo)

discord-invite-bot/
├── src/ 
│ ├── main/ │ 
│ ├── java/com/seuusuario/bot/ 
│ │ │ ├── Main.java │
│ │ ├── DiscordService.java 
│ │ │ ├── GoogleSheetsService.java 
│ │ │ └── InviteManager.java 
│ └── resources/ 
│ └── credentials.json 
├── .env 
├── build.gradle / pom.xml 
└── README.md


---

## ⚙️ Configuração

### 1. `.env` (ou variáveis de ambiente)

```dotenv
DISCORD_TOKEN=seu_token_discord
GUILD_ID=seu_id_do_servidor
GOOGLE_SHEET_ID=id_da_sua_planilha
GOOGLE_SERVICE_EMAIL=seu_email_de_serviço@projeto.iam.gserviceaccount.com
GOOGLE_PRIVATE_KEY="sua_chave_privada"
https://discord.com/oauth2/authorize?client_id=SEU_CLIENT_ID&scope=bot&permissions=268435456

Clone o repositório:
git clone https://github.com/seu-usuario/discord-invite-bot.git
cd discord-invite-bot

Instale as dependências:
Gradle -
./gradlew build
./gradlew run
Maven - 
mvn install
mvn exec:java -Dexec.mainClass="com.seuusuario.bot.Main"


Possíveis Melhorias Futuras
Integração com API de e-mail para envio automático dos convites

Painel Web para controle dos convites (com Spring Boot + Thymeleaf ou React)

Limitar o número de usos do convite por usuário

Log de erros detalhado em arquivos locais

Desenvolvido com 💻 por João Cunha
🔗 https://www.linkedin.com/in/joãocunhabackend/
🐙 https://github.com/joaomauro0
