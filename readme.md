# Duff

Descrição breve do projeto.

## How to run

Para executar o projeto localmente, siga as seguintes instruções:

1. Clone o repositório para sua máquina.
2. Preencha as credencias no arquivo .env
3. Inicie o projeto com o `make up`
4. Execute os endpoints localmente conforme a porta indicada no env

## Endpoints

Abaixo está a lista de todos os endpoints disponíveis no projeto:

| Endpoint | Descrição | Exemplo de entrada | Exemplo de saída |
| --- | --- | --- | --- |
| `/users` | Retorna a lista de todos os usuários | N/A | `[{"id": 1, "nome": "Fulano", "email": "fulano@exemplo.com"}, {"id": 2, "nome": "Ciclano", "email": "ciclano@exemplo.com"}]` |
| `/users/:id` | Retorna os detalhes do usuário especificado pelo ID | `id=1` | `{"id": 1, "nome": "Fulano", "email": "fulano@exemplo.com"}` |

## How to deploy

