Sistema Gerenciador de Eventos
Bem-vindo ao Sistema Gerenciador de Eventos (SGE)! Este projeto é uma aplicação completa para gerenciar eventos, composta por um backend em Spring Boot, um frontend web e um banco de dados MySQL, tudo orquestrado via Docker Compose.

🚀 Tecnologias Utilizadas
Backend: Spring Boot (Java) - verificar README.md na pasta back/

Frontend: Angular - verificar README.md na pasta front/

Banco de Dados: MySQL 8.0

Containerização: Docker e Docker Compose

📦 Estrutura do Projeto
A estrutura principal do seu projeto deve ser a seguinte:

.
├── back/                 # Contém o código-fonte do backend (Spring Boot)

│   └── Dockerfile        # Dockerfile para construir a imagem do backend

├── front/                # Contém o código-fonte do frontend (Angular)

│   └── Dockerfile        # Dockerfile para construir a imagem do frontend

├── docker-compose.yml    # Arquivo de configuração do Docker Compose

└── .env                  # Arquivo de variáveis de ambiente para o banco de dados

└── README.md             # Este arquivo

⚙️ Configuração e Execução
Siga os passos abaixo para configurar e executar o projeto em sua máquina local.

Pré-requisitos
Certifique-se de ter o Docker e o Docker Compose instalados em seu sistema.

Docker Desktop: Instalação do Docker

1. Clonar o Repositório
Primeiro, clone este repositório para sua máquina local:

git clone https://github.com/edilsonbsfilho/sge.git
cd sge 

2. Criar o Arquivo .env
Na pasta raiz do projeto (a mesma onde está o docker-compose.yml), crie um arquivo chamado .env e adicione as seguintes variáveis de ambiente para a configuração do banco de dados:

DB_ROOT_PASSWORD=123456
DB_URL=jdbc:mysql://db:3306/sgedb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USERNAME=sge_user
DB_PASSWORD=123456

3. Executar com Docker Compose
Com o arquivo .env criado, você pode iniciar todos os serviços (banco de dados, backend e frontend) usando o Docker Compose. Navegue até a pasta raiz do projeto no seu terminal e execute o seguinte comando:

docker-compose up --build

O comando --build garante que as imagens do backend e frontend sejam construídas a partir dos seus Dockerfiles antes de iniciar os contêineres.

A primeira execução pode demorar um pouco, pois o Docker fará o download das imagens necessárias (MySQL) e construirá as imagens personalizadas.

4. Acessar a Aplicação
Após todos os serviços estarem em execução, você pode acessar a aplicação nos seguintes endereços:

Frontend: http://localhost:80

Backend (API): http://localhost:8080

O serviço de banco de dados MySQL estará disponível na porta 3306 dentro da rede Docker, e o backend se conectará a ele usando o nome do serviço db.

🛑 Parar a Aplicação
Para parar todos os serviços e remover os contêineres, execute o seguinte comando na pasta raiz do projeto:

docker-compose down

Se você também quiser remover os volumes (dados do banco de dados), use:

docker-compose down -v

