# Para executar a aplicação

### Utilizando o docker-compose
 * Execute o comando: "docker compose up --build -d"
 * Se estiver utilizando o docker compose em uma versão anterior a 2, utilize o seguinte comando: "docker-compose up --build -d"
 * Algumas versões mais antigas do docker podem não vir com suporte ao docker-compose, neste link você pode encontrar os passos para a instalação:https://docs.docker.com/compose/install/

### Utilizando o Minikube
* Caso não tenha o minikube instalado, siga os passos deste link: https://minikube.sigs.k8s.io/docs/start/
* Execute o comando: "minikube start"
* Instale os addons necessários com os comandos:
    - "minikube addons enable ingress"
    - "minikube addons enable metrics-server"
    - "minikube addons enable dashboard
* Para subir a aplicação execute os manifestos com o seguinte comando:
  - "kubectl apply -f kubernetes/manifests"
* Execute o seguinte comando para verificar se todos os componentes do namespace estão rodando corretamente: 
  - "kubectl get all,cm,secrets,ingress,pv,pvc -owide -n fastburger-api"
* Execute o seguinte comando para mapear ao porta do serviço de api para a porta 8080 do seu computador:
  - "kubectl -n fastburger-api  port-forward service/api 8080:8080"
* Agora resta popular o banco de dados com os dados iniciais, para isso execute o seguinte comando:
  - "bash scripts/load_products_table_via_svc.sh"

### Swagger:
* http://localhost:8080/swagger-ui/index.html

## Link video Youtube:
* https://www.youtube.com/watch?v=cpTS392dOiA