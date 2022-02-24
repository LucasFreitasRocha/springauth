 # Spring Auth
  Esse repositorio é  uma api com um  estudo sobre auntenticação do spring com Bearer token jwt.  
 #### disponivel em [heroku](https://spring-auth-devrocha.herokuapp.com/swagger-ui.html)

## Docker - branch [aqui](https://github.com/LucasFreitasRocha/springauth/tree/docker)
## Swagger


para facilitar a interação com a api foi incluido o swagger na aplicação. O Swagger é uma linguagem de descrição de interface para descrever APIs RESTful expressas usando JSON. 


![alt text](https://github.com/LucasFreitasRocha/springauth/blob/master/images/Swagger.png)
 
## Usuarios
 Tem 2 usuarios
 - email: user@email.com , senha: 123456
 - email: moderador@email.com, senha: 123456
 
 ## Se Autenticando no [heroku](https://spring-auth-devrocha.herokuapp.com/swagger-ui.html)
 Acessando o link você irá entrar no swagger, basta clicar no auth-controller na rota post-auth e colocar as informações para se autenticar no body da requisição:
 
 
 ![alt text](https://github.com/LucasFreitasRocha/springauth/blob/master/images/RequestAuth.png)
 O Response terá o token e o tipo dele, que será necessário para o resto da aplicação: 
 
 
 ![alt text](https://github.com/LucasFreitasRocha/springauth/blob/master/images/ResponseAuth.png)

## Teste de Autorização no [heroku](https://spring-auth-devrocha.herokuapp.com/swagger-ui.html)


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
