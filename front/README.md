Frontend - Sistema Gerenciador de Eventos (SGE)
Este diretório contém o código-fonte do frontend do Sistema Gerenciador de Eventos (SGE). Desenvolvido com Angular, ele oferece a interface de usuário para interação com o backend da aplicação.

🚀 Tecnologias Utilizadas
Angular CLI: Versão 20.1.0

Angular Core: Versão 20.1.0

Angular Material: Utilizado para componentes de UI ricos e responsivos, incluindo:

Toolbar, Ícones

Tabelas, Paginação

Botões, Formulários (campos de texto, inputs)

Cards, Indicadores de Progresso (spinner, barra)

Seletores de Data

Snack Bar para notificações

TypeScript: Linguagem de programação principal.

RxJS: Para programação reativa.

Angular Router: Para navegação entre as diferentes seções da aplicação.

Angular Forms (Reactive Forms): Para gerenciamento de formulários complexos.

📦 Estrutura do Projeto (Frontend)
.
├── src/

│   ├── app/                      # Módulos e componentes da aplicação

│   │   ├── core/                 # Módulo de funcionalidades interceptor e services

│   │   ├── features/evento/      # Módulo de gerenciamento de eventos

│   │   │   ├── components/       # Componentes específicos de evento

│   │   ├── shared/               # Módulo de models

│   │   └── app-routing-module.ts # Configuração de rotas principais

│   │   └── app.ts                # Componente raiz da aplicação

│   │   └── app-module.ts         # Módulo principal da aplicação

│   │   └── app.html              # Html principal da aplicação

│   │   └── app.scss              # CSS principal da aplicação

│   ├── assets/                   # Ativos estáticos (imagens, etc.)

│   ├── environments/             # Arquivos de configuração de ambiente

│   │   ├── environment.ts        # Ambiente de desenvolvimento

│   │   └── environment.prod.ts   # Ambiente de produção

│   └── main.ts                   # Ponto de entrada da aplicação

├── angular.json                  # Configurações do projeto Angular CLI

├── package.json                  # Dependências e scripts do projeto

├── tsconfig.json                 # Configurações do TypeScript

└── Dockerfile                    # Dockerfile para construir a imagem do frontend

└── nginx.conf                    # Arquivo de configuração do nginx

⚙️ Configuração e Execução (Desenvolvimento)
Siga os passos abaixo para configurar e executar o frontend em modo de desenvolvimento.

Pré-requisitos
Certifique-se de ter o Node.js e o npm (ou Yarn) instalados em sua máquina.

1. Instalar Dependências
Navegue até a pasta front/ no seu terminal e instale as dependências do projeto:

cd front
npm install
# ou yarn install (se estiver usando Yarn)

2. Executar o Servidor de Desenvolvimento
Após a instalação das dependências, você pode iniciar o servidor de desenvolvimento:

ng serve

Este comando iniciará o servidor de desenvolvimento e a aplicação estará disponível em http://localhost:4200 (porta padrão do Angular CLI). Qualquer alteração no código-fonte será automaticamente recarregada no navegador.

3. Acessar a Aplicação
O frontend estará acessível em seu navegador no endereço:

http://localhost:4200

Nota: Quando executado via Docker Compose (como configurado no docker-compose.yml na raiz do projeto), o frontend será exposto na porta 80 do seu host.

🛠️ Scripts Disponíveis
No diretório front/, você pode executar os seguintes scripts:

npm start ou ng serve: Inicia o servidor de desenvolvimento.

npm run build ou ng build: Compila o projeto para produção.

Para construir para produção: ng build --configuration production

npm run watch ou ng build --watch --configuration development: Compila o projeto em modo de observação para desenvolvimento.

npm test ou ng test: Executa os testes unitários via Karma.

🌍 Variáveis de Ambiente
O projeto utiliza arquivos de ambiente para gerenciar configurações específicas de desenvolvimento e produção.

src/environments/environment.ts: Para configurações de desenvolvimento.

src/environments/environment.prod.ts: Para configurações de produção.

Esses arquivos são usados para armazenar variáveis como URLs de API, chaves de API, etc., que podem variar entre os ambientes. O Angular CLI substitui automaticamente o arquivo de ambiente correto durante o processo de build, dependendo da configuração (--configuration).

🧩 Módulos Principais
Com base nos seus arquivos app-module.ts e evento.module.ts:

AppModule (app-module.ts):

Módulo raiz da aplicação.

Configura o ambiente de navegação (AppRoutingModule).

Importa BrowserModule e BrowserAnimationsModule para funcionalidades básicas do navegador e animações.

Integra o CoreModule, que provavelmente contém serviços e componentes compartilhados.

Utiliza MatToolbarModule e MatIconModule para a barra de ferramentas e ícones globais da aplicação.

EventoModule (evento.module.ts):

Módulo de recurso dedicado ao gerenciamento de eventos.

Define rotas específicas para eventos (EventoRoutingModule).

Declara e gerencia os componentes relacionados a eventos: EventoListComponent, EventoFormComponent, EventoDetailComponent, e MeuComponente.

Importa uma vasta gama de módulos do Angular Material para construir a UI de gerenciamento de eventos, incluindo tabelas, formulários, botões, cards, seletores de data e indicadores de progresso.

Utiliza ReactiveFormsModule para o gerenciamento de formulários complexos.

Inclui MatSnackBarModule para exibir mensagens de notificação ao usuário.