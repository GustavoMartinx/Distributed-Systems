# Google Protocol Buffers (protobuf)

A fim de colocar em prática os conceitos de Representação Externa de Dados, este projeto utiliza [Google Protocol Buffers](https://developers.google.com/protocol-buffers/) para gerar as estruturas e APIs que viabilizam a comunicação em um sistema distribuído heterogêneo de arquitetura cliente-servidor.

Para tal, optou-se por desenvolver o servidor e o cliente, respectivamente, nas linguagens de programação Python e Java.

Nesse sentido, o [compilador do _protobuf_](https://github.com/protocolbuffers/protobuf) gera o código-fonte responsável pelo gerenciamento das estruturas e das interfaces de _marshalling_ e _unmarshalling_, tanto para o servidor quanto para o cliente.

A definição das estruturas é realizada no arquivo `.proto` que é utilizado pelo compilador do _protobuf_.

O objetivo foi implementar um serviço de gerenciamento de filmes usando os dados providos pela coleção _movies_ da base de dados 
[Mflix](https://www.mongodb.com/docs/atlas/sample-data/sample-mflix/) do MongoDB.

O serviço implementa as funcionalidades para inclusão, alteração, exclusão e consulta de filmes. Além de fazer buscas filtrando por membros do elenco ou por gêneros de filmes.

**Índice**
1. [Como instalar o compilador do protobuf](#como-instalar-o-compilador-do-protobuf)
2. [Como gerar o código-fonte a partir da estrutura `.proto`](#como-gerar-código-fonte-a-partir-da-estrutura-proto)
3. [Como obter a API proto para Java](#como-obter-a-api-proto-para-java)
4. [Como obter a API proto para Python](#como-obter-a-api-proto-para-python)
5. [Como compilar e executar o projeto](#como-compilar-e-executar-o-projeto)

**Autores**
- [Christofer Daniel Rodrigues Santos](https://github.com/christoferlv)
- [Diogo Rodrigues dos Santos](https://github.com/DiogoRodriguees)
- [Gustavo Zanzin Guerreiro Martins](https://github.com/GustavoMartinx)

## Como instalar o compilador do protobuf

**1 -** Dentro do diretório `ProtocolBuffer`, crie e entre no diretório `protoc`:

```bash
cd ProtocolBuffer/
mkdir protoc
cd protoc/
```

**2 -** Em seguida, se estiver em um sitema GNU/Linux, realize o _download_ do compilador e descompacte-o com:

```bash
wget https://github.com/protocolbuffers/protobuf/releases/download/v24.4/protoc-24.4-linux-x86_64.zip && unzip protoc-24.4-linux-x86_64.zip
```

<details><summary>Caso estiver em um sitema Windows:</summary>

- Mude para um sistema GNU/Linux e tente novamente.
</details>
<br>

**3 -** Se estiver em um sitema GNU/Linux, adicione ao arquivo `.bashrc` para facilitar a utilização do compilador:

```bash
alias protoc=/home/user/PathTo/ProtocolBuffer/protoc/bin/protoc
```

## Como gerar código-fonte a partir da estrutura `.proto`

Obs.: Para ambos exemplos abaixo, substitua `destiny_language/` pelo diretório no qual se deseja que os arquivos sejam gerados (para este projeto, `client` e `server`, respectivamente) e `file.proto` pelo nome do arquivo `.proto` que contém a estrutura.

**Para Python:**
```bash
protoc --python_out=destiny_py/ file.proto
```

**Para Java:**
```bash
protoc --java_out=destiny_java/ file.proto
```

Ou, se preferir, gere o código-fonte de forma automatizada com:

```bash
./create-interfaces.sh
```

## Como obter a API proto para Java

Obs.: Substitua `client` pelo diretório no qual deseja-se que os arquivos sejam gerados (para este projeto, utilize `client`).

```bash
cd client/
wget https://repo1.maven.org/maven2/com/google/protobuf/protobuf-java/3.24.4/protobuf-java-3.24.4.jar
```

## Como obter a API proto para Python

**1 -** Crie um _virtual environment_:

```bash
cd server/
python3 -m venv nome-do-venv
```

<details><summary><b>2 -</b> Em seguida, ative-o.</summary>
<p>

- Linux

    ```
    source ./nome-do-venv/bin/activate
    ```

- Windows (Prompt de Comando)

    ```
    .\nome-do-venv\Scripts\activate
    ```

- Windows (Terminal Integrado do VSCode)

    ```
    source ./nome-do-venv/Scripts/activate
    ```

</p>
</details>

<br>

**3 -** Instale as dependências (a API proto para o Python):

```bash
pip install -r requirements.txt
```

## Como compilar e executar o projeto

Após ter o [compilador protoc instalado](#como-instalar-o-compilador-do-protobuf), o [código-fonte gerado a partir dos arquivos `.proto`](#como-gerar-código-fonte-a-partir-da-estrutura-proto) e a API proto instalada tanto para [Java](#como-obter-a-api-proto-para-java) quanto para [Python](#como-obter-a-api-proto-para-python), é possível compilar e executar o projeto.

**1 -** Compile o cliente Java:

```bash
cd client/
javac -classpath .:protobuf-java-3.24.4.jar *.java
```

**2 -** Execute o cliente Java:
```bash
java -classpath .:protobuf-java-3.24.4.jar Main
```

Ou, se preferir, compile e execute o cliente de forma automatizada:

```bash
cd client/
./run.sh
```

**3 -** Execute o servidor Python:

Obs.: Note que o ambiente virtual deve estar ativo ([como ativar?](#como-obter-a-api-proto-para-python)).

```bash
cd server/
python3 Main.py
```

Ou, se preferir, execute o servidor de forma automatizada:

```bash
cd server/
./run.sh
```

Obs.: Lembre-se de alterar sua `uri` do MongoDB no arquivo `Database.py`.