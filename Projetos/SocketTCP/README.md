# Sockets TCP

Este projeto implementa dois protocolos de comunicação TCP, a saber, [Protocolo Binário](README.md#protocolo-binário) e [Protocolo Textual](README.md#protocolo-textual).

**Autores:**

- [Diogo Rodrigues dos Santos](https://github.com/DiogoRodriguees)
- [Gustavo Zanzin Guerreiro Martins](https://github.com/GustavoMartinx)

## Protocolo Binário

No protocolo binário os dados são transmitidos byte a byte. O objetivo foi implementar um sistema de arquitetura cliente-servidor (cuja comunicação ocorre por um _socket_ TCP) a fim de gerenciar um conjunto de arquivos remotos. As operações disponíveis incluem a listagem, o _upload_, o _download_ e a exclusão de arquivos.

Para tanto, foi utilizada a linguagem de programação Java 21.0, as bibliotecas `io`, `nio`, `net` e `util.logging` para o uso das classes `ByteBuffer`, `Socket` e `Logger`.

Além das funcionalidades padrões de transmissão de dados entre cliente e servidor, o sistema implementa o registro das operações realizadas em um _log_. Para mais detalhes sobre a definição do protocolo de comunicação, acesse a [especificação do trabalho](https://github.com/GustavoMartinx/Distributed-Systems/blob/main/Projetos/SocketTCP/Especifica%C3%A7%C3%A3o-Lab01TCP.pdf).

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

Já no protocolo textual os dados são transmitidos no formato de _string_. O objetivo foi implementar um sistema de arquitetura cliente-servidor (cuja comunicação também ocorre por um _socket_ TCP) para manipular remotamente um espaço de usuário através de um protocolo textual.

Por meio do cliente, um usuário pode fazer _login_ no sistema e ter acesso ao seu espaço no servidor. Feito isso, é possível tanto criar quanto listar arquivos e diretórios, navegar pela estrutura de diretórios e obter o caminho corrente.

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

### Exemplos de uso

**CONNECT user password:** Conecta o usuário _user_ a sua área no servidor se a senha _password_ conferir e o usuário _user_ existir. A área dos usuários no servidor fica no diretório ``src/users/``.
```bash
CONNECT diogo senha
```
- Obs.: Atualmente no sistema existem apenas os usuários 'diogo' e 'gustavo', ambos podendo serem acessados através da senha 'senha'. Não foi desenvolvido um método de criação de usuários, pois esse não era o objetivo da atividade.


**PWD:** Devolve o caminho corrente (_path_) usando String UTF separando os diretórios por barra (``/``).
```bash
PWD
```
Exemplo de saída:
```console
src/users/diogo
```

**TOUCH:** Cria um arquivo no espaço do usuário no servidor, dentro do diretório corrente, com o nome e tipo (extensão) informados.
```bash
TOUCH myfile.txt
```

**MKDIR:** Cria um diretório no espaço do usuário no servidor, dentro do diretório corrente, com o nome informado.
```bash
MKDIR mydir
```

**GETFILES:** Devolve os arquivos do diretório corrente no espaço do usuário no servidor.
```bash
GETFILES
```

**GETDIRS:** Devolve os diretórios do diretório corrente no espaço do usuário no servidor.
```bash
GETDIRS
```

**EXIT:** Finaliza a conexão do cliente com o servidor.
```bash
EXIT
```