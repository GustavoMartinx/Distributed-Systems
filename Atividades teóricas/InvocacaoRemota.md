# Invocação Remota
Comunicação entre processos remotos por meio de invocação remota.

Os parâmetros de entrada são passados para o servidor remoto pelo envio dos valores dos argumentos na mensagem de requisição e, então, repassados como argumentos para a operação a ser executada no servidor. Os parâmetros de saída são retornados na mensagem de resposta e são usados como resultado da chamada ou para substituir os valores das variáveis correspondentes no ambiente de chamada.

- Para o processo que invoca o método, a implementação é transparente.
- A implementação do método está no processo remoto.
- O uso de sockets para o programador é transparente (mas ocorre internamente).

>A remote procedure call occurs in the following steps:
>1. The client procedure calls the client stub in the normal way.
>2. The client stub builds a message and calls the local operating system.
>3. The client’s OS sends the message to the remote OS.
>4. The remote OS gives the message to the server stub.
>5. The server stub unpacks the parameter(s) and calls the server.
>6. The server does the work and returns the result to the stub.
>7. The server stub packs the result in a message and calls its local OS.
>8. The server’s OS sends the message to the client’s OS.
>9. The client’s OS gives the message to the client stub.
>10. The stub unpacks the result and returns it to the client.

## Remote Procedure Call (RPC)
Permite aos programas clientes chamarem procedimentos de forma transparente em programas servidores que estejam sendo executados em processos separados e, geralmente, em computadores diferentes do cliente.

O objetivo é tornar a programação de sistemas distribuídos semelhante à programação convencional, ou seja, obter transparência de distribuição de alto nível.

> Se você procura um exemplo prático de sistema que utiliza RPC, confira [este projeto](https://github.com/GustavoMartinx/Distributed-Systems/tree/main/Projetos/rpc).

#### Ocultamento de aspectos importantes de distribuição:
- Codificação e decodificação de parâmetros e resultados;
- Passagem de mensagens;
- Preservação da semântica exigida para a chamada de procedimento.

### Programação com interfaces

_Interface de serviço_ refere-se à especificação dos procedimentos oferecidos por um servidor, definindo os nomes de cada procedimento, os tipos dos argumentos, bem como seu tipo de retorno, se houver.

As interfaces definem as estruturas a serem usadas na comunicação.

Vantagens da programação com interfaces em sistemas distribuídos (resultantes da separação entre interface e implementação):
- Modularização: os programadores não precisam compreender os detalhes de implementação, mas somente a interface de serviço.
- Sistemas heterogêneos: os programadores não precisam conhecer a linguagem de programação utilizada para implementar o serviço.
- Evolução de software: fornecimento de suporte natural à evolução de software, pois as implementações podem mudar, desde que a interface (mudando ou não) permaneça compatível com a original.

Interface Definition Language (IDLs) são usadas para especificar interfaces e possibilitar a invocação entre programas desenvolvidos em linguagens diferentes.

### Semânticas de invocação

#### Garantias de entrega:
- Retransmit request message: para retransmitir a mensagem de requisição até que uma resposta seja recebida ou que se presuma que o servidor falhou.
- Duplicate filtering: quando são usadas retransmissões para eliminar requisições duplicadas no servidor.
- Re-execute procedure or retransmit reply: para manter um histórico das mensagens de respostas a fim de permitir que os resultados perdidos sejam retransmitidos sem uma nova
execução das operações no servidor.

#### Escolhas de Semântica de RPC:
- **Semântica talvez:** a RPC pode ser executada uma vez ou não ser executada. Surge quando nenhuma das medidas de tolerância a falhas é aplicada e pode sofrer os tipos de falha:
    - falhas por omissão: se a mensagem de requisição ou de resultado for perdida;
    - falhas por colapso: quando o servidor que contém a operação remota falha.
- **Semântica pelo menos uma vez:** o invocador recebe um resultado ou uma exceção. Pode ser obtida pela retransmissão das mensagens de requisição, o que mascara as falhas por omissão da mensagem de requisição ou de resultado. Pode sofrer os tipos de falhas:
    - falhas por colapso: quando o servidor que contém o procedimento remoto falha;
    - falhas arbitrárias: nos casos em que a mensagem de requisição é retransmitida, o
servidor remoto pode recebê-la e executar o procedimento mais de uma vez, possivelmente causando o armazenamento ou o retorno de valores errados.
- **Semântica no máximo uma vez:**  ou o chamador recebe um resultado – no caso em que o chamador sabe que o procedimento foi executado exatamente uma vez – ou recebe uma exceção informando-o de que nenhum resultado foi recebido – no caso em que o procedimento terá sido executado uma vez ou não terá sido executado. Pode ser obtida pelo uso de todas as medidas de tolerância a falhas.

### Transparência
Todas as chamadas necessárias para procedimentos de serialização e trocas de mensagens foram ocultadas do programador que faz a chamada.

A RPC tenta oferecer pelo menos transparência de localização e de acesso, ocultando o local físico do procedimento remoto.

O consenso atual é o de que as chamadas remotas devem se tornar transparentes, no
sentido de a sintaxe de uma chamada remota ser a mesma de uma chamada local, mas a
diferença entre chamadas locais e remotas deve ser expressa em suas interfaces.


## Remote Method Invocation (RMI)

Permite que objetos de diferentes processos, possivelmente distribuídos em máquinas distintas, se comuniquem por meio da invocação de métodos remotos. Assim como o RPC, o RMI busca fornecer uma abstração que oculta a complexidade da comunicação remota, tornando o processo de invocação remota o mais transparente possível para o programador. No entanto, ao contrário do RPC, que se concentra em funções ou procedimentos, o RMI é orientado a objetos, o que significa que ele envolve a comunicação entre objetos distribuídos e a invocação de métodos desses objetos.

A principal diferença entre o RPC e o RMI é que no RMI o foco está em objetos e na invocação de métodos, enquanto o RPC lida com funções/procedimentos em um nível mais básico. O RMI permite que os métodos sejam chamados em objetos que residem em máquinas diferentes, e a interface do método chamado é definida em uma Interface Remota. Quando um método é invocado remotamente, os argumentos são serializados e enviados através da rede, e o valor de retorno (se houver) é também serializado e retornado ao chamador. Isso proporciona aos sistemas distribuídos que utilizam a orientação a objetos uma maneira mais intuitiva de realizar operações remotas.

### Características

- **Suporte a objetos distribuídos**: Objetos que residem em diferentes máquinas podem ser acessados e manipulados por meio de métodos remotos, mantendo o paradigma de orientação a objetos.
- **Transparência na invocação**: A sintaxe da chamada remota é a mesma da chamada local, oferecendo transparência de acesso e de localização, semelhante ao RPC.
- **Serialização**: Argumentos e valores de retorno precisam ser serializados/deserializados para serem transmitidos corretamente entre diferentes máquinas.
  
### Vantagens

- **Facilidade de programação distribuída**: Oferece uma abstração poderosa para sistemas distribuídos orientados a objetos, permitindo invocações remotas de maneira intuitiva.
- **Integração com Java**: Como RMI foi projetado com a linguagem Java em mente, ele se integra naturalmente com outras ferramentas e bibliotecas da plataforma, simplificando o desenvolvimento de sistemas distribuídos.

### Limitações

- **Dependência de Java**: RMI está fortemente vinculado ao ecossistema Java, o que limita seu uso em ambientes heterogêneos que envolvem múltiplas linguagens de programação.
- **Desempenho**: A serialização de objetos pode aumentar a latência das operações, especialmente em redes com alta latência ou quando o volume de dados transmitidos é significativo.


## Referências

COULOURIS, George F; DOLLIMORE, Jean; KINDBERG, Tim; BLAIR, Gordon. **Sistemas distribuídos: conceitos e projeto**. 5. ed. Porto Alegre: Bookman, 2013.

STEEN, Maarten van; TANENBAUM, Andrew S.. **_Distributed Systems_**, 4. ed., distributed-systems.net, 2023. Disponível em: https://www.distributed-systems.net/index.php/books/ds4/. Acesso em: 10/03/2024.