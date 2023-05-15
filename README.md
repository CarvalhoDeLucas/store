# Store API
Primeiro, crie um banco de dados com o nome "store". 
Depois vá até a classe application.properties e altere o username e o password para o do seu banco de dados.

# Uso

### Categorias
Crie duas categorias utilizando o caminho POST - localhost:8080/categories - Com o Body em row - JSON
* {
  * "typeItem": "Product"
* }
* {
  * "typeItem": "Service"
* }

* DELETE localhost:8080/categories/{id}
* GET localhost:8080/categories/{page}/{size}
* GET localhost:8080/categories/{id}
* PUT localhost:8080/categories/{id}

### Itens
Crie um item utilizando o caminho POST - localhost:8080/items - Com o Body em row - JSON
* {
* "nameItem": "Carro",
* "descriptionItem": "Carro",
* "price": 100.00,
  * "categories": {
    * "id": "categoryId"
  * }
* }

* DELETE localhost:8080/items/{id}
* GET localhost:8080/items/{page}/{size}
* GET localhost:8080/items/{id}
* PUT localhost:8080/items/{id}

### Pedidos
Crie um item utilizando o caminho POST - localhost:8080/orders - Com o Body em row - JSON
* {
  * "situation": "Open",
  * "totalValue": 0
* }

* DELETE localhost:8080/orders/{id}
* GET localhost:8080/orders/{page}/{size}
* GET localhost:8080/orders/{id}
* PUT localhost:8080/orders/{id}
* PUT localhost:8080/orders/{id}/{discount}

### Itens pedidos
Crie um item utilizando o caminho POST - localhost:8080/orders - Com o Body em row - JSON
* {
  * "order": {
    * "id": "orderId" 
  * },
  * "item": {
    * "id": "itemId"
  * },
  * "quantity": 1,
  * "totalValue": 0
* }

* DELETE localhost:8080/orders/{id}
* GET localhost:8080/orders/{page}/{size}