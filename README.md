 # Spring Auth
  Esse repositorio é  uma api com um  estudo sobre auntenticação do spring com Bearer token jwt.  
 #### disponivel em [render](https://springauth.onrender.com/swagger-ui/index.html)



## Swagger


para facilitar a interação com a api foi incluido o swagger na aplicação. O Swagger é uma linguagem de descrição de interface para descrever APIs RESTful expressas usando JSON. 


![alt text](https://github.com/LucasFreitasRocha/springauth/blob/master/images/Swagger.png)
 
## Usuarios
 Tem 2 usuarios
 - email: user@email.com , senha: 123456
 - email: moderador@email.com, senha: 123456
 
 ## Se Autenticando no [render](https://springauth.onrender.com/swagger-ui/index.html)
 Acessando o link você irá entrar no swagger, basta clicar no auth-controller na rota post-auth e colocar as informações para se autenticar no body da requisição:
 
 
 ![alt text](https://github.com/LucasFreitasRocha/springauth/blob/master/images/RequestAuth.png)
 O Response terá o token e o tipo dele, que será necessário para o resto da aplicação: 
 
 
 ![alt text](https://github.com/LucasFreitasRocha/springauth/blob/master/images/ResponseAuth.png)

## Teste de Autorização no [render](https://springauth.onrender.com/swagger-ui/index.html)


Para fazer os testes de autenticação/autorização criei um crud simples com Entity Message que é composto do conteudo da mensagem e qual usuario criou.

### Cricação de mensagem (POST)
 Para criar uma mensagem é necessario está autenticado, os 2 usuarios estão habilitado a criar. Para se autenticar é só colocar o Bearer e o token como é demonstrado na imagem , e colocar o conteudo da mensagem no corpo da requisição
 
 ![alt text](https://github.com/LucasFreitasRocha/springauth/blob/master/images/RequestMessage.png)


 Se tudo ocorreu corretamente o response da criação terá no corpo a mensagem criada:
 
 
 ![alt text](https://github.com/LucasFreitasRocha/springauth/blob/master/images/ResponseMessage.png)
 
 ### Atualização da mensagem (PUT)
  Para atualizar uma mensagem é necessario ser o usuario criou a mensagem ou ser moderador da aplicação caso não se encaixe nessas situações a aplicação retornará um badrequest com a mensagem "Você não pode atualizar essa mensagem". Caso se encaixe a aplicação retornará com status 204.
  
  
  #### Tentando atualizar: 
  ![alt text](https://github.com/LucasFreitasRocha/springauth/blob/master/images/UpdateMessageRequest.png)
  
  
  ### Caso não esteja nas situações citadas acima: 
  ![alt text](https://github.com/LucasFreitasRocha/springauth/blob/master/images/UpdateMessageBadResponse.png)
 
### Deletar Mensagem (DELETE)
 Somente o Moderador consegue apagar uma mensagem, caso você esteja autenticado com um token de usuario comum a aplicação retornará 403 Forbiden. Caso você tente deletar com o moderador retornará com o 204.
 
 ### Request delete
 
 ![alt text](https://github.com/LucasFreitasRocha/springauth/blob/master/images/DeleteMessageRequest.png)
 
 #### Exemplo de Response  de delete com usuario comun:
 
  ![alt text](https://github.com/LucasFreitasRocha/springauth/blob/master/images/DeleteMessageResponseForbiden.png)
  
 #### Exemplo de Response de delete com usuario Moderador:
 
 ![alt text](https://github.com/LucasFreitasRocha/springauth/blob/master/images/DeleteMessageResponseOk.png)


## Docker 

para utilizar o projeto local com o docker é só baixar a branch e ter docker-compose instalado e rodar o  comando:

    1- docker compose up --build

Para subir o container utilizei docker-compose... e um dockerfile para montar a imagem do spring-java.


### docker-compose - container spring-app
*  linha 5 tem o atributo dockerfile que referencia o dockerfile do spring-java
*  linha 15 tem a string de conexão com o banco de dados e está com o nome(dominio) do container do banco de dados e por conta disso
*  linha 10 e 11 tem o depends_on e o nome do container e por conta disso no fluxo o container do db sobe primeiro
*  linha 19 coloquei restart: unless-stopped por que o container do db pode subir mas não está pronto para conexão ainda e por conta disso quebrar a subida do container do java
![docker-compose](https://github.com/LucasFreitasRocha/springauth/blob/master/images/dockerCompose.png)

### dockerfile - imagem java
![dockerfile](https://github.com/LucasFreitasRocha/springauth/blob/master/images/dockerfile.png)

A imagem é feita com alpine e jdk 17, nessa imagem eu crio um grupo e um usaurio chamado spring, copio o jar construido na etapa 1 do subir.sh e utilizo o comando 

    java -jar app.jar 

para começar o spring.

### docker-compose - postgres/network

A parte do container do banco de dados é bem simples... Uso a imagem oficial do postgres
![dockerCompose-postgres](https://github.com/LucasFreitasRocha/springauth/blob/master/images/dockerCompose2.png)

network é feito para estabelecer a conexão entre os 2 containers

### docker - url de conexão
para url de conexão no container do spring java , além do usar o nome do container do banco de dados pode ser usado o ip da sua maquina. É possivel usar o ip da maquina pois eu externalizo a porta do container postgres
![urlConexao](https://github.com/LucasFreitasRocha/springauth/blob/master/images/urlConexaoBdIp.png)

### ip no windwos
utilize o comando ipconfig:

![ipconfig](https://github.com/LucasFreitasRocha/springauth/blob/master/images/ipconfig.png)
### ip no  linux
utilize o comando ifconfig ou comando certo para sua distribuição linux:

## Endpoints disponiveis somente na branch docker
 
 Na versão master que corresponde ao que está no heroku não está disponiveis esses endpoints para gerenciamento de recursos.
### Create user
 ![createUser](https://github.com/LucasFreitasRocha/springauth/blob/master/images/createUser.png)
 No create user tem o atributo moderador que é um boolean para criar um usuario com o cargo moderador para fazer os testes no endpoint de mensagens
 
 ### All user 
 apenas para mostrar todos os usuarios só é necessario estár autenticado
 ![Allusers](https://github.com/LucasFreitasRocha/springauth/blob/master/images/allUsers.png)
