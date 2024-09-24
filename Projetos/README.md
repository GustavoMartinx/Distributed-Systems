# Projetos Práticos

Este diretório contém alguns dos projetos práticos de codificação desenvolvidos durante a disciplina de Sistemas Distribuídos (OPT021) ministrada pelo professor [Dr. Rodrigo Campiolo](http://paginapessoal.utfpr.edu.br/rcampiolo).

Neste documento, é apresentada a lista de projetos e uma visão geral sobre cada um deles. Nesse sentido, dentro de cada pasta respectiva a um projeto, encontra-se não só a especificação daquele trabalho, mas também um arquivo `README.md` com mais detalhes a respeito dele.

## Sockets TCP
Este projeto implementa dois protocolos de comunicação TCP, a saber, Protocolo Binário e Protocolo Textual. Ambos, por meio da linguagem de programação Java, suportam a conexão de múltiplos processos clientes ao processo servidor com a utilização de _threads_.

- **Protocolo Binário:** A transmição dos dados entre os processos cliente e servidor é realizada byte a byte. O servidor implementa a gerência de um conjunto de arquivos remotos entre múltiplos processos clientes.
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

## Remote Procedure Call (RPC) - com gRPC
Com o objetivo de praticar os conceitos acerca de _Remote Procedure Call_ (RPC), este projeto utiliza a biblioteca Google RPC ([gRPC](https://grpc.io/)) para definir uma interface de serviço que proporciona a integração em um sistema distribuído heterogêneo de arquitetura cliente-servidor.

Através da IDL Protocol Buffers, são definidas interfaces de serviço e as operações que podem ser chamadas remotamente. O servidor Python implementa essas operações e o cliente Java as utiliza como se fossem chamadas locais - mas na verdade são realizadas remotamente e de forma transparente para o programador.


## Comunicação Indireta
Este projeto utiliza um modelo de comunicação indireta com _publish-subscribe_ e fila de mensagens para implementar um serviço de notificação de _tweets_. Está disponível [neste repositório](https://github.com/DiogoRodriguees/messages-rabbitmq).

O componente _coletor_ coleta _tweets_ por meio de palavras-chave que descrevem os tópicos de interesse. Em seguida ele envia os _tweets_ para uma fila de um serviço de mensagens (neste caso, o [RabbitMQ](https://www.rabbitmq.com/)). Os classificadores consomem os _tweets_ da fila e processam as mensagens para identificar qual o tópico do _tweet_ e os publicam atavés do serviço de mensagens naquele tópico. Dessa forma, os assinantes são notificados com os _tweets_ de interesse.

## Projeto Final - Aplicação Distribuída
O trabalho final visa desenvolver uma aplicação que proveja ao menos uma característica de sistemas distribuídos. Optamos por criar um sistema que provê escalabilidade horizontal através de uma arquitetura de microserviços. Ou seja, para lidar com o aumento da demanda, o sistema seria capaz de adicionar mais instâncias de um serviço. A descrição arquitetural e o próprio projeto estão disponíveis [neste repositório](https://github.com/DiogoRodriguees/sd-projeto-final).

O sistema emprega o [Nginx](https://nginx.org/) como balanceador de carga. Os microserviços de usuário, autenticação e publicação foram desenvolvidos com o _framework_ [NestJS](https://nestjs.com/) (em Typescript) e o banco de dados com [PostgreSQL](https://www.postgresql.org/). Além disso, também foi utilizado o serviço de mensagens [RabbitMQ](https://www.rabbitmq.com/) e teve seu _front-end_ implementado com [React](https://reactjs.org/).