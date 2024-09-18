# Projetos Práticos

Este diretório contém alguns dos projetos práticos de codificação desenvolvidos durante a disciplina de Sistemas Distribuídos (OPT021) ministrada pelo professor [Dr. Rodrigo Campiolo](http://paginapessoal.utfpr.edu.br/rcampiolo).

Neste documento, é dada uma visão geral sobre cada projeto. Nesse sentido, dentro de cada pasta respectiva a um projeto, encontra-se não só a especificação daquele trabalho, mas também um arquivo `README.md` com mais detalhes a respeito dele.

## Sockets TCP
Este projeto implementa dois protocolos de comunicação TCP, a saber, Protocolo Binário e Protocolo Textual. Ambos, por meio da linguagem de programação Java, suportam a conexão de multiplos processos clientes ao processo servidor com a utilização de _threads_.

- **Protocolo Binário:** A transmição dos dados entre os processos cliente e servidor é realizada byte a byte. O servidor implementa a gerência de um conjunto de arquivos remotos entre multiplos processos clientes.
- **Protocolo Textual:** A transmição dos dados entre os processos cliente e servidor é feita no formato de _string_. O servidor implementa o controle de mensagens e áreas de usuários distintos, através de um "_login_" com usuário e senha.

## Sockets UDP
Este trabalho contempla dois projetos. Ambos implementam, com a linguagem de programação Java, a comunicação entre processos através de _sockets_ UDP.

- **Sistema de Backup de Arquivos:** O processo cliente envia os dados de um arquivo a ser transmitido para um servidor remoto. Os dados são enviados em blocos de 1024 bytes. O servidor verifica a integridade por meio de um _checksum_ `SHA-1` e salva o arquivo.

- **Chat Peer-to-Peer:** Permite com que os processos _peers_ troquem mensagens entre si. Ao serem transmitidas, as mensagens seguem um protocolo e podem ser de diferentes tipos: normal, emoji, URL ou ECHO.

## Google Protocol Buffers (protobuf)
A fim de colocar em prática os conceitos de Representação Externa de Dados, este projeto utiliza [Google Protocol Buffers](https://developers.google.com/protocol-buffers/) para gerar as estruturas e APIs que viabilizam a comunicação em um sistema distribuído heterogêneo de arquitetura cliente-servidor.

Nesse sentido, o servidor e o cliente, respectivamente, são implementados nas linguagens de programação Python e Java.

O objetivo do serviço é implementar as funcionalidades para o gerenciamento de filmes usando os dados providos pela coleção _movies_ da base de dados 
[Mflix](https://www.mongodb.com/docs/atlas/sample-data/sample-mflix/) do MongoDB.