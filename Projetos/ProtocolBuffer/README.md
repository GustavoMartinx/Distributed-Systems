# Protocol Buffer

[descrição do projeto]

**Autores:**
- [Christofer Daniel dos Santos](https://github.com/christoferlv)
- [Diogo Rodrigues dos Santos](https://github.com/DiogoRodriguees)
- [Gustavo Zanzin Guerreiro Martins](https://github.com/GustavoMartinx)

## Como instalar o compilador protoc

**1 -** Dentro do diretório `ProtocolBuffer`, crie e entre no diretório `protoc`:

```bash
cd ProtocolBuffer/
mkdir protoc
cd protoc/
```

**2 -** Em seguida, realize o _download_ do compilador e descompacte-o:

```bash
wget https://github.com/protocolbuffers/protobuf/releases/download/v24.4/protoc-24.4-linux-x86_64.zip && unzip protoc-24.4-linux-x86_64.zip
```

**3 -** Se estiver em um sitema GNU/Linux, adicione ao arquivo `.bashrc`:

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

## Como obter a API proto para Java

Obs.: Substitua `server` pelo diretório no qual deseja-se que os arquivos sejam gerados (para este projeto, utilize `server`).

```bash
cd server
wget https://repo1.maven.org/maven2/com/google/protobuf/protobuf-java/3.24.4/protobuf-java-3.24.4.jar
```

## Como obter a API proto para Python

**1 -** Crie um _virtual environment_:

```bash
cd client/
python3 -m venv venv
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
pip install requirements.txt
```

## Como compilar e executar o projeto

Após ter o [compilador protoc instalado](#como-instalar-o-compilador-protoc), o [código-fonte gerado a partir dos arquivos `.proto`](#como-gerar-código-fonte-a-partir-da-estrutura-proto) e a API proto instalada tanto para [Java](#como-obter-a-api-proto-para-java) quanto para [Python](#como-obter-a-api-proto-para-python), é possível compilar e executar o projeto.

**1 -** Compile o servidor Java:

```bash
cd server/
javac -classpath .:protobuf-java-3.24.4.jar *.java
```

**2 -** Execute o servidor Java:
```bash
java -classpath .:protobuf-java-3.24.4.jar ServerTcpMovies
```

**3 -** Compile e execute o cliente Python:

Obs.: Note que o ambiente virtual deve estar ativo ([como ativar?](#como-obter-a-api-proto-para-python)).

```bash
cd client/
python3 client.py
```
