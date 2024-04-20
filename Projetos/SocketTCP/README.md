# Sockets TCP

Este projeto implementa dois protocolos de comunicação TCP, a saber, [Protocolo Binário](README.md#protocolo-binário) e [Protocolo Textual](README.md#protocolo-textual).

## Protocolo Binário

No protocolo binário os dados são transmitidos byte a byte. Para tanto, foi utilizada a linguagem de programação Java 21.0, as bibliotecas `io`, `nio`, `net` e `util.logging` para o uso das classes `ByteBuffer`, `Socket` e `Logger`.

### Como compilar e executar

- Terminal 1 (Processo Servidor):

```bash
cd BinaryProtocol/
javac src/*.java
cd src/
java TCPServer.java
```

- Terminal 2 (Processo Cliente):

```bash
cd BinaryProtocol/src/
java TCPClient.java
```

### Exemplos de uso

1. **ADDFILE:** Faz _upload_ de um arquivo para a pasta `Documents/` no servidor. O arquivo passado como argumento deve existir localmente dentro da pasta `BinaryProtocol/src/`.
```bash
    ADDFILE <NomeDeUmFileDentroDeSRC>.<extension>
```

2. **DELETE:**  Exclui um arquivo da pasta `Documents/` do servidor. O arquivo passado como argumento deve existir remotamente dentro da pasta `Documents/`.
```bash
    DELETE <NomeDeUmFileDentroDeDocuments>.<extension>
```


3. **GETFILESLIST:** Obtém a lista dos arquivos existentes remotamente na pasta `Documents/` do servidor.
```bash
    GETFILESLIST
```

4. **GETFILE:** Realiza o _download_ de um arquivo disponível na pasta `Documents/` do servidor para a pasta local `Downloads/`.
```bash
    GETFILE <NomeDeUmFileDentroDeDocuments>.<extension>
```

<br>

## Protocolo Textual

Já no protocolo textual os dados são transmitidos no formato de string.

### Como compilar e executar

1. Compile o código-fonte:

```bash
cd TextualProtocol/src/
./compile.sh
```
2. Execute o processo servidor:

```bash
./server.sh
```
3. Execute o processo cliente:

```bash
./client.sh
```

4. (Opcional) Exclua os arquivos gerados durante a execução:

```bash
./clear.sh
```