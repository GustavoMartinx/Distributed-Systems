# Sockets UDP

Este trabalho implementa dois projetos envolvendo comunicação com sockets UDP, a saber, um [Sistema de Backup de Arquivos](README.md#sistema-de-backup-de-arquivos) e um [Chat Peer-to-Peer](README.md#chat-peer-to-peer).

**Autores:**

- [Diogo Rodrigues dos Santos](https://github.com/DiogoRodriguees)
- [Gustavo Zanzin Guerreiro Martins](https://github.com/GustavoMartinx)


## Sistema de Backup de Arquivos

Neste projeto, o cliente envia os dados de um arquivo a ser  transmitido para um servidor remoto. O usuário informa o nome do arquivo e este será transferido em blocos de 1024 bytes, através de datagramas para o servidor. O servidor recebe os dados e salva o arquivo no diretório `Documents/`.

Para tanto, foi utilizada a linguagem de programação Java 21.0, a biblioteca `java.security.MessageDigest` para a criação dos _checksums_ dos arquivos enviados, através do algoritmo `SHA-1`.

### Como compilar e executar

1 - Compile o código-fonte:

```bash
cd SocketUDP/BackupSystem/
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

### Exemplo de uso

Para realizar o _upload_ de um arquivo para a pasta `Documents/` no servidor, basta informar o nome do arquivo no terminal. Note que o arquivo passado como argumento deve existir localmente dentro da pasta `BackupSystem/`.
```bash
$ <NomeDeUmFileDentroDeBackupSystem>.<extension>
```


<br>

## Chat Peer-to-Peer

Chat _peer-to-peer_ que possibilita os pares trocarem mensagens entre si em diferentes formatos (normal, emoji, URL ou ECHO).

### Como compilar e executar

1 - Compile o código-fonte:

```bash
cd SocketUDP/ChatP2P
chmod +x compile.sh
./compile.sh
```

2 - Execute o processo _peer_ 1:

```bash
chmod +x peer1.sh
./peer1.sh
```

3 - Execute o processo _peer_ 2:

```bash
chmod +x peer2.sh
./peer2.sh
```

4 - (Opcional) Exclua os arquivos gerados durante a execução:

```bash
chmod +x clean.sh
./clean.sh
```
