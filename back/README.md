Backend - Sistema Gerenciador de Eventos (SGE)
Este diretório contém o código-fonte do backend do Sistema Gerenciador de Eventos (SGE). Desenvolvido com Spring Boot, ele é responsável por fornecer a API RESTful para o gerenciamento de eventos, persistência de dados e lógica de negócio.

🚀 Tecnologias Utilizadas
Spring Boot: Versão 3.5.3

spring-boot-starter-data-jpa: Para persistência de dados com JPA e Hibernate.

spring-boot-starter-validation: Para validação de dados de entrada.

spring-boot-starter-web: Para construção de aplicações web e APIs RESTful.

spring-boot-devtools: Ferramentas de desenvolvimento para recarga automática e outras funcionalidades de produtividade.

spring-boot-starter-test: Para testes unitários e de integração.

Java: Versão 21

Maven: Ferramenta de automação de build.

MySQL Connector/J: Driver JDBC para conexão com o banco de dados MySQL.

Flyway: Para gerenciamento de migrações de banco de dados, garantindo que o esquema do banco esteja sempre atualizado.

Springdoc OpenAPI UI: Para geração automática de documentação da API (Swagger UI), facilitando o teste e a compreensão dos endpoints.

Lombok: Biblioteca para reduzir o código boilerplate (getters, setters, construtores, etc.).

H2 Database: Banco de dados em memória, usado principalmente para testes.

📦 Estrutura do Projeto (Backend)
.
├── src/

│   ├── main/

│   │   ├── java/                 # Código-fonte Java

│   │   │   └── br/

│   │   │       └── com/

│   │   │           └── mirante/

│   │   │               └── sge/             # Pacote principal da aplicação

│   │   │                   ├── config/      # Configurações do OpenAPI

│   │   │                   ├── controller/  # Endpoints da API REST

│   │   │                   ├── dto/         # DTOs para entrada e saída de dados da API

│   │   │                   ├── entity/      # Entidades de domínio

│   │   │                   ├── exception/   # Classes de exceções e classe de Handle de exceções da API REST

│   │   │                   ├── repository/  # Interfaces de acesso a dados

│   │   │                   ├── service/     # Regras de negócio

│   │   │                   └── SgeApplication.java # Classe principal da aplicação

│   │   └── resources/            # Arquivos de configuração, templates, etc.

│   │       ├── application.properties # Configurações da aplicação

│   │       ├── db/migration/     # Scripts de migração do Flyway

│   │       └── ...

│   └── test/

│       └── java/                 # Código-fonte dos testes

├── pom.xml                       # Arquivo de configuração do Maven

└── Dockerfile                    # Dockerfile para construir a imagem do backend

⚙️ Configuração e Execução (Desenvolvimento)
Siga os passos abaixo para configurar e executar o backend em modo de desenvolvimento.

Pré-requisitos
Java Development Kit (JDK) 21

Maven

Docker e Docker Compose (para executar com o banco de dados MySQL externo)

1. Clonar o Repositório
Certifique-se de ter o repositório principal clonado e navegue até a pasta back/:

cd back

2. Compilar o Projeto
Para compilar o projeto e gerar o arquivo JAR executável:

mvn clean install

Este comando irá baixar as dependências, compilar o código e empacotar a aplicação em um arquivo JAR na pasta target/.

3. Executar a Aplicação (Standalone)
Você pode executar o backend diretamente a partir do JAR gerado:

java -jar target/sge-0.0.1-SNAPSHOT.jar

Nota: Para esta execução, você precisará configurar as propriedades de conexão com o banco de dados (MySQL) em seu application.properties ou como variáveis de ambiente, ou garantir que um banco de dados H2 esteja configurado para uso em desenvolvimento.

4. Executar com Docker Compose (Recomendado)
A forma mais recomendada de executar o backend é utilizando o docker-compose.yml na raiz do projeto. Este arquivo orquestra o backend, frontend e o banco de dados MySQL, garantindo que todas as dependências estejam disponíveis.

Certifique-se de estar na pasta raiz do projeto (um nível acima de back/) e execute:

docker-compose up --build

Para mais detalhes sobre a execução completa com Docker Compose, consulte o README.md principal na raiz do projeto.

📄 Documentação da API (Swagger UI)
Uma vez que o backend esteja em execução, a documentação interativa da API estará disponível através do Swagger UI.

URL da Documentação: http://localhost:8080/swagger-ui.html

Nesta interface, você poderá visualizar todos os endpoints disponíveis, seus parâmetros, modelos de dados e testar as requisições diretamente no navegador.

🧪 Testes
Para executar os testes unitários e de integração do projeto:

mvn test