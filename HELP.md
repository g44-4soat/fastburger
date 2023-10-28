# Para executar a aplicação 

### Utilizando o docker-compose
 * Execute o comando: "docker compose up --build -d"
 * Se estiver utilizando o docker compose em uma versão anterior a 2, utilize o seguinte comando: "docker-compose up --build -d"
 * Algumas versões mais antigas do docker podem não vir com suporte ao docker-compose, neste link você pode encontrar os passos para a instalação:https://docs.docker.com/compose/install/


### Variáveis de ambiente
* No arquivo docker-compose.yaml você encontrará as seguintes variáveis que podend ser alteradas conforme sua necessidade:
  SPRING_DATASOURCE_URL: "url do banco de dados"
  SPRING_DATASOURCE_USERNAME: "usuário do banco de dados"
  SPRING_DATASOURCE_PASSWORD: "senha do banco de dados"
  SPRING_PORT: "porta da aplicação"


