Schedule API

A Schedule API é uma aplicação para gerenciamento de estoque de produtos.
Como Começar

Siga estas instruções para obter uma cópia do projeto e executá-lo em sua máquina local para desenvolvimento e teste.
Pré-requisitos

Certifique-se de ter o seguinte instalado em sua máquina:

    Java
    Maven
    MySQL (ou outro banco de dados suportado)

Instalação

    Clone o repositório:

    bash

git clone https://github.com/seu-usuario/schedule-api.git

Acesse o diretório do projeto:

bash

cd schedule-api

Configure as propriedades do banco de dados no arquivo application.properties:

properties

spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco_de_dados
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

Execute a aplicação:

bash

    mvn spring-boot:run

    Acesse a API em http://localhost:8080

Endpoints

    POST /produtos: Cadastra um novo produto.
    PUT /produtos: Atualiza um produto existente.
    DELETE /produtos/{codigo}: Deleta um produto pelo código.
    POST /movimentacoes-estoque/entrada: Registra uma entrada de estoque para um produto.
    POST /movimentacoes-estoque/saida: Registra uma saída de estoque para um produto.

Documentação da API

Acesse http://localhost:8080/swagger-ui.html para explorar a documentação interativa da API.
Construído Com

    Spring Boot - Framework Java para criação de aplicativos autônomos.
    Maven - Gerenciador de dependências.

Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.
Autores

    Heber - Desenvolvedor

Licença

Este projeto está licenciado sob a Licença MIT - consulte o arquivo LICENSE para obter detalhes.
