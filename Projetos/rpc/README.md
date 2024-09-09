# GRPC
Reimplementação do projeto (https://github.com/GustavoMartinx/Distributed-Systems/tree/comentarios_protobuff/Projetos/ProtocolBuffer) usando GRPC.

**Autores**
- [Christofer Daniel Rodrigues Santos](https://github.com/christoferlv)
- [Diogo Rodrigues dos Santos](https://github.com/DiogoRodriguees)
- [Gustavo Zanzin Guerreiro Martins](https://github.com/GustavoMartinx)


## Como compilar e executar o projeto

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
mvn clean compile exec:java
```

**3 -** Execute o servidor Python:

Obs.: Note que o ambiente virtual deve estar ativo ([como ativar?](#como-obter-a-api-proto-para-python)).

```bash
cd server/
python3 MainRPC.py
```

Obs.: Lembre-se de alterar sua `uri` do MongoDB no arquivo `Database.py`.
