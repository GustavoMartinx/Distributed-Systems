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

Chat _peer-to-peer_ que possibilita os pares trocarem mensagens entre si em diferentes formatos (normal, emoji, URL ou ECHO). Um sistema _peer-to-peer_ é aquele em que todos os processos possuem capacidades equivalentes, podendo tanto consumir quanto fornecer recursos.

Este trabalho utilizou ``DatagramSocket`` (UDP) para o envio e recebimento de mensagens, além de uma _thread_ para o recebimento das mensagens dos outros _peers_.

### Como compilar e executar

1 - Compile o código-fonte:

```bash
cd SocketUDP/ChatP2P
chmod +x compile.sh
./compile.sh
```

2 - Execute o processo _peer_ 1:

```bash
./peer1.sh
```

3 - Execute o processo _peer_ 2:

```bash
./peer2.sh
```

4 - (Opcional) Exclua os arquivos gerados durante a execução:

```bash
./clean.sh
```


### Exemplos de uso
De forma geral, a comunicação é feita através do seguinte formato, onde o identificador `<tipo-da-mensagem>` é um número inteiro de 1 a 4 respectivo ao tipo da mensagem a ser enviada (normal, emoji, URL ou ECHO):
```bash
<tipo-da-mensagem> <conteudo-da-mensagem>
```

**Tipo ``1`` -** Mensagem normal (textual):
```bash
1 qual a resposta para a vida, o universo e tudo mais?
```

**Tipo ``2`` -** Emoji:
```bash
2 :)
```

**Tipo ``3`` -** URL:
```bash
3 https://wood-viscose-668.notion.site/Redes-2-37b839239507467a8d925eea1abcefa9
```

**Tipo ``4`` -** ECHO:
```bash
4 qualquer mensagem para teste de comunicação
```

5 - Para finalizar qualquer processo _peer_, digite:
```bash
exit
```