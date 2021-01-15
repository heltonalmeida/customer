## CUSTOMER

## Descrição do projeto

Esse projeto consiste na manutenção de dados dos clientes. Podendo realizar ações de consulta, inserção, atualização e remoção de dados.

## Pré requisitos

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [MySQL](https://www.mysql.com/)
- [Lombok](https://projectlombok.org/)

## Dependências utilizadas

- Spring Boot Framework: Controlar o projeto e diminuir a necessidade de configurações.
- Apache commons: Facilitar a escrita do código com classes utilitarias
- MySQL: Integração com o SGBD
- Swagger: Documentação da API
- Flyway: Automatizar  a criação de scripts SQL 
- Junit: Criação de testes unitários
- Mockito: Criação de testes unitários
- Lombok: Evitar a repetição de código
- OpenFeign: Facilitar a comunicação entre microsserviços.

## Para funcionar 

- Download do projeto

No terminal, clone o projeto:

```
https://github.com/heltonalmeida/customer.git
```

- Configurando SGBD

Visto a aplicação utilizar o MySQL, e caso ainda não tenha o mesmo instalado, podemos obter seu instalador atraves do [Download](https://www.mysql.com/downloads/) ou tendo o [Docker](https://www.docker.com/) configurado, para obter uma instância execute o comando abaixo.  

```
docker run -it -d --name mysql8-customer -p 3306:3306 hpalmeida/helton-mysql8-customer
```
Para facilitar o acesso ou caso queira fazer alguma mudança na imagem, o arquivo Dockerfile que originou a imagem hpalmeida/helton-mysql8-customer encontra-se no caminho infra\docker a partir da raiz do projeto.

Quanto a configuração das credencias para acesso ao banco, no arquivo application.yml já contém as configurações necessárias para a aplicação se conectar ao MySQL.

Na primeira vez que estiver executando o projeto, através do Flayway será executado automaticamente no banco os scripts contidos no caminho src\main\resources\db para criação de tabela e massa de dados. Para que isso aconteça é necessário já ter criado o database tb_customer. Podendo ser pelo comando docker acima ou manualmente.

- Comunicação com microsserviço CITY

Pelo fato do endpoint de persistir cliente precisar validar a cidade informada, existe uma comunicação com o microsserviço CITY.
Visto isso, para o funcionamento correto do endpoint é necessário que o microsserviço [CITY](https://github.com/heltonalmeida/city) esteja em execução. 

- Configurando Lombok

Além da dependência contida no projeto, é necessário uma [Configuração](https://projectlombok.org/setup/overview) adicional na sua respectiva IDE.

## Para apreciação

- Swagger 

```
http://localhost:4000/api/swagger-ui.html
```
- Postman

Na raiz do projeto esta contido o arquivo CUSTOMER_API.postman_collection.json





