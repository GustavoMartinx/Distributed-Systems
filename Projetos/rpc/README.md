# Remote Procedure Call - com gRPC

Com o objetivo de praticar os conceitos acerca de _Remote Procedure Call_ (RPC), este projeto utiliza a biblioteca Google RPC ([gRPC](https://grpc.io/)) para definir uma interface de serviço que proporciona a integração em um sistema distribuído heterogêneo de arquitetura cliente-servidor.

O objetivo foi implementar um serviço de gerenciamento de filmes usando os dados providos pela coleção _movies_ da base de dados 
[Mflix](https://www.mongodb.com/docs/atlas/sample-data/sample-mflix/) do MongoDB.

Através da linguagem neutra [Protocol Buffers](https://developers.google.com/protocol-buffers/), são definidas as estruturas de dados para a comunicação e as interfaces de serviço (métodos gRPC que podem ser chamados).

Dessa forma, o serviço, desenvolvido em Python, implementa as interfaces definidas no arquivo `MoviesRPC.proto` com as funcionalidades para inclusão, alteração, exclusão e consulta de filmes. Além de fazer buscas filtrando por membros do elenco ou por gêneros de filmes.

Enquanto o cliente, implementado em Java, realiza as chamadas das operações como se fossem uma chamada local, no entanto, elas são executadas remotamente. De maneira transparente para o programador, quando um método é chamado, o gRPC envia os parâmetros para o servidor que executa a operação e retorna o resultado para o cliente.

## Sobre o gRPC

O [gRPC](https://grpc.io/) é uma biblioteca RPC universal de código aberto de alto desempenho desenvolvida pelo Google. No gRPC, uma aplicação cliente pode chamar métodos diretamente em uma aplicação servidor de uma outra máquina, como se ele fosse um objeto local, facilitando a criação de aplicações e serviços distribuídos.

O gRPC tem como base a ideia de definir um serviço e especificar os métodos que podem ser chamados remotamente com parâmetros e tipos de retorno deles. Por padrão, o gRPC usa [Protocol Buffers](https://developers.google.com/protocol-buffers/) como a linguagem IDL (_Interface Definition Language_) para descrever a interface do serviço e a estrutura das mensagens de _payload_.

### Índice
1. [Como compilar e executar o projeto](#como-compilar-e-executar-o-projeto) <br>
2. [Como obter o plugin do gRPC para Java](#como-obter-o-plugin-do-grpc-para-java) <br>
3. [Como gerar o código-fonte a partir da estrutura `.proto`](#como-gerar-código-fonte-a-partir-da-estrutura-proto) <br>
4. [Como obter as dependências do gRPC para Python](#como-obter-as-dependências-do-grpc-para-python)
5. [Como instalar o compilador do protobuf](https://github.com/GustavoMartinx/Distributed-Systems/tree/main/Projetos/ProtocolBuffer#como-instalar-o-compilador-do-protobuf)


### Autores
- [Christofer Daniel Rodrigues Santos](https://www.linkedin.com/in/christoferlv/)
- [Diogo Rodrigues dos Santos](https://www.linkedin.com/in/diogorodriguees/)
- [Gustavo Zanzin Gurreiro Martins](https://linkedin.com/in/gustavo-martinx)


<br>

## Como compilar e executar o projeto
Em primeiro lugar, certifique-se que possui o [Apache Maven](https://maven.apache.org/) instalado, o [plugin do gRPC](#como-obter-o-plugin-do-grpc-para-java) para Java e o [ambiente Python](#como-obter-as-dependências-do-grpc-para-python) configurado.

**1 -** Execute o servidor Python:

```bash
cd server/
python3 MainRPC.py
```

Obs.: Lembre-se de alterar sua `uri` do MongoDB no arquivo `Database.py`.


**2 -** Compile e execute o cliente Java:

```bash
cd client/
mvn clean compile exec:java
```

Caso já tenha compilado e queria apenas executá-lo, é possível com:
```bash
mvn exec:java -D"exec.mainClass"="MovieClient"
```

## Como obter o plugin do gRPC para Java
O plugin é usado para gerar o código-fonte, a partir da estrutura `.proto`, não só acerca dos _stubs_ mas também para o gRPC. Para este projeto ser executado, não é necessário realizar novamente o _download_ do plugin. Dessa forma o seguinte passo-a-passo é útil se você estiver criando um novo projeto.

1- Baixe o _plugin_ do grpc em: https://central.sonatype.com/artifact/io.grpc/protoc-gen-grpc-java/1.62.2/versions

2 - Clique em "Browse". Você será redirecionado para: https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java/1.62.2/

3 - Selecione a versão desejada e aguarde o _download_. Para este projeto, foi utilizado ``protoc-gen-grpc-java-1.62.2-linux-x86_64.exe``.

4 - Inclua o _plugin_ no mesmo diretório do protoc, ou configure um _alias_ com o caminho.


## Como gerar código-fonte a partir da estrutura `.proto`

Ao compilar o arquivo de IDL Protocol Buffer (`MoviesRPC.proto`), no qual se definiram as estruturas de dados para a comunicação e as interfaces de serviço, o compilador gera tanto os _stubs_ de mensagens, responsáveis pelo  _marshall_ e _unmarshall_ dos tipos de dados, quanto o código-fonte relacionado ao serviço gRPC propriamente dito, que permite enviar e receber dados através das chamadas de procedimento remoto.

Por esse âmbito, utilize os comandos abaixo para gerar esses componentes:

**Para Python:**
```bash
cd server/

python3 -m grpc_tools.protoc -I. --python_out=. --grpc_python_out=. MoviesRPC.proto
```

**Para Java:**
```bash
cd client/src/main/java

protoc --java_out=. --grpc-java_out=. --plugin=protoc-gen-grpc-java=<CAMINHO/DO/PLUGIN/GRPC> MoviesRPC.proto
```


## Como obter as dependências do gRPC para Python

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

**3 -** Instale as dependências - que incluem o gRPC para o Python:

```bash
pip install -r requirements.txt
```