# Sockets TCP

Este projeto implementa dois protocolos de comunicação TCP, a saber, [Protocolo Binário](README.md#protocolo-binário) e [Protocolo Textual](README.md#protocolo-textual).

**Autores:**

- [Diogo Rodrigues dos Santos](https://github.com/DiogoRodriguees)
- [Gustavo Zanzin Guerreiro Martins](https://github.com/GustavoMartinx)

## Protocolo Binário

No protocolo binário os dados são transmitidos byte a byte. Para tanto, foi utilizada a linguagem de programação Java 21.0, as bibliotecas `io`, `nio`, `net` e `util.logging` para o uso das classes `ByteBuffer`, `Socket` e `Logger`.

Além das funcionalidades padrões de transmissão de dados entre cliente e servidor, o sistema implementa o registro das operações realizadas em um _log_.

### Como compilar e executar

1 - Compile o código-fonte:

```bash
cd BinaryProtocol/
chmod +x compile.sh
./compile.sh
```

2 - Execute o processo servidor:
```bash
cd src/
java TCPServer
```

3 - Execute o processo cliente:

```bash
cd BinaryProtocol/src/
java TCPClient
```

4 - (Opicional) Exclua os arquivos gerados durante a execução:

```bash
cd BinaryProtocol/
chmod +x clear.sh
./clear.sh
```


### Exemplos de uso

**ADDFILE:** Faz _upload_ de um arquivo para a pasta `Documents/` no servidor. O arquivo passado como argumento deve existir localmente dentro da pasta `BinaryProtocol/src/`.
```bash
ADDFILE <NomeDeUmFileDentroDeSrc>.<extension>
```

**DELETE:**  Exclui um arquivo da pasta `Documents/` do servidor. O arquivo passado como argumento deve existir remotamente dentro da pasta `Documents/`.
```bash
DELETE <NomeDeUmFileDentroDeDocuments>.<extension>
```


**GETFILESLIST:** Obtém a lista dos arquivos existentes remotamente na pasta `Documents/` do servidor.
```bash
GETFILESLIST
```

**GETFILE:** Realiza o _download_ de um arquivo disponível na pasta `Documents/` do servidor para a pasta local `Downloads/`.
```bash
GETFILE <NomeDeUmFileDentroDeDocuments>.<extension>
```

<br>

## Protocolo Textual

Já no protocolo textual os dados são transmitidos no formato de string.

### Como compilar e executar

1 - Compile o código-fonte:

```bash
cd TextualProtocol/
chmod +x compile.sh
./compile.sh
```

2 - Execute o processo servidor:

```bash
./server.sh
```

3 - Execute o processo cliente:

```bash
./client.sh
```

4 - (Opcional) Exclua os arquivos gerados durante a execução:

```bash
./clear.sh
```