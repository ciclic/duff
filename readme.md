# Duff

Descrição breve do projeto.

## How to run

Para executar o projeto localmente, siga as seguintes instruções:

1. Clone o repositório para sua máquina
2. Preencha as credencias no arquivo .env
3. Inicie o projeto com o `make up`
4. Pra desenvolvimento podes usar o `make up` para subir o banco de dados e acessar normalmente do seu
    ambiente dev (npm install && npm  run dev)
5. Com o servidor e o banco de dados ativo, execute os endpoints localmente conforme a porta indicada no env
6. Para iniciar a base de dados com o seed use o endpoint POST `/init`

## Endpoints

Abaixo está a lista de todos os endpoints disponíveis no projeto:

| Endpoint | Descrição | Exemplo de entrada | Exemplo de saída |
| --- | --- | --- | --- |
| GET `/` | Retorna lista des cervejas a partir de um filtro ou nenhum | `style='Dunkel'`, `minTemperature=-1`, `maxTemperatureskip=2`, `limit=1`, `skip=1`  | `[{"_id": "643b34194b60163f3f469ff5","minTemperature": -1,"maxTemperature": 1"style": "Dunkel","__v": 0},]` |
| GET `/suitable | Retorna a  cerveja mais adequada de acordo com a temperatura informada | `temperature=1` |  |
| GET `/playlist | Retorna a playlist mais adequada para um estilo de cerveja | `temperature=1` | `[]` |
| `/beer-playlist | Calcula a cerveja e playlist mais adequada | `temperature=1` |  |
| `/delete | Delete uma cerveja | `style=Dunkel` |  |
| `/update | Atualiza uma cerveja |  |  |
| `/init | Executa um seed para o banco de dados |  |  |



## How to deploy