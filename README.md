<h1 align=center> Produtos Frescos - Projeto Integrador </h1>
<p align=center> TRANSFORMELI Grupo 2 </p>
<br>

## ✅ Testes
* Unitários [`src/test/java/br/com/dh/meli/projeto_integrador`](src/test/java/br/com/dh/meli/projeto_integrador)

## 🚩 Postman collection
* Variveis do Postman [`src/main/resources/collection/workspace.postman_globals.json`](src/main/resources/collection/workspace.postman_globals.json)
* Exemplos de Uso [`src/main/resources/collection/Projeto_Integrador.postman_collection.json`](src/main/resources/collection/Projeto_Integrador.postman_collection.json)

## 📝 Proposta
Criar uma API REST que faz o gerenciamento de estoque e venda de produtos frescos.

* API DE PRODUTOS FRESCOS

## 💣 Requisito 6
* Gerenciador de vendedores
* <a href="https://drive.google.com/file/d/1YAgjXLTfXWWIpHxZKFwE9UInseWDDd3_/view">User Story</a>
* Collection Postman [`src/main/resources/collection/PI_Req6.postman_collection.json`]
* Teste unitário: [`src/test/java/br/com/dh/meli/projeto_integrador/service/SellerServiceTest.java`]
* Endpoints:

. GET
```
api/v1/seller/all
```
```
api/v1/seller/{id}
```

. POST
```
api/v1/seller/register
```

. PUT
```
api/v1/seller/update
```

. DELETE
```
api/v1/seller/{id}
```



## 🚀 Como clonar e iniciar a aplicação

- Abra seu terminal e digite o seguinte comando:

```
git clone git@github.com:rebecccruz/projeto_integrador.git
```

- Ainda em seu terminal digite o comando abaixo para entrar na pasta do projeto:

```
cd projeto_integrador
```

- Instale as dependências do maven:

```
mvn install
```

ou
<br>

```
maven install
```

- Rode localmente:

```
mvn spring-boot:run
```

## 👥 Membros do grupo

- <a href="https://github.com/aborgssouzameli">Alexandre Borges Souza</a>
- <a href="https://github.com/evycoliveira">Evelyn Cristini Oliveira</a>
- <a href="https://github.com/isaiasfmeli">Isaias Finger</a>
- <a href="https://github.com/laridevmeli">Larissa Navarro</a>
- <a href="https://github.com/lucaspinheirorocha">Lucas Pinheiro Rocha</a>
- <a href="https://github.com/rebecccruz">Rebecca da Cunha Cruz</a>
