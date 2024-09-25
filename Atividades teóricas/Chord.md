# Chord: A Scalable Peer-to-peer Lookup Service for Internet Applications

Através dos princípios da arquitetura _peer-to-peer_, o protocolo _Chord_ explora como pode-se prover escalabilidade e tolerência a falhas para serviços de _lookup_ em sistemas distribuídos.

<!-- Antes de falarmos sobre o Chord propriamente dito, eu gostaria de contextualizar relembrando o que é uma arquitetura peer-to-peer. -->

## Contextualização: O que é _Peer-to-Peer_ (P2P)

<!-- slide 1: texto guia disso aqui -->
Sistemas ou aplicações _peer-to-peer_ (P2P) são sistemas distribuídos onde todos os nós da rede atuam como pares e possuem capacidades equivalentes. Nesse sentido, cada nó pode tanto fornecer quanto consumir recursos.

Ao contrário de arquiteturas tradicionais cliente-servidor, o P2P elimina a necessidade de entidades centrais e relações hierárquicas, tornando a rede descentralizada.

Desse modo, a arquitetura P2P tende a escalar melhor, pois, à medida que mais nós se juntam à rede, os recursos disponíveis também aumentam. Diferentemente de um servidor central que pode sobrecarregar conforme o número de clientes aumenta, em uma rede P2P, a carga é distribuída entre os nós.

Como não há um ponto único de falha, a rede P2P é naturalmente mais resistente a interrupções. Se um nó falhar, outros pares podem continuar a oferecer os serviços, o que aumenta a disponibilidade do sistema e o torna mais robusto.

<!-- slide 2: resumo de P2P -->
Em resumo, uma arquitetura P2P oferece:
- Descentralização;
- Distribuição de recursos;
- Escalabilidade;
- Tolerância a falhas.

<!-- nesse sentido... -->
## Contextualização: _Peer-to-peer Lookup Service_
<!-- slide 3: texto guia disso aqui -->
Um _peer-to-peer lookup_ é um serviço projetado para buscar recursos em uma rede distribuída P2P.

O objetivo é permitir que os pares encontrem dados ou outros pares de forma eficiente e escalável, sem depender de mecanismos centralizados de indexação.

Nesse sentido, o Chord é um protocolo de _lookup_ distribuído.


<!-- slide 4: A base do Chord -->
## A base do protocolo Chord

O protocolo Chord especifica como encontrar a localização de chaves, como novos nós entram no sistema e como recuperar-se da falha (ou saída esperada) de nós existentes. A base do protocolo descreve uma versão simplificada que não gerencia entradas ou falhas concorrentes de nós.

### Chord e Tabelas Hash Distribuídas

Ele utiliza um mecanismo baseado no conceito de tabela _hash_ distribuída (DHT), mapeando cada recurso (seja um nó ou um dado) na rede a uma chave usando uma função de _hash_ consistente, como o SHA-1. Cada nó da rede é responsável por um intervalo específico de chaves, e o Chord garante que qualquer recurso possa ser encontrado em tempo logarítmico, com base no número de nós.

- Função de _Hash_: Os identificadores que a função de _hash_ gera formam um espaço de chaves circular, onde os recursos são atribuídos aos nós mais próximos em termos de _hash_.


### Topologia em Anel

Os nós na rede Chord são organizados em um anel lógico circular, onde cada nó conhece apenas o seu sucessor imediato no anel.

- Sucessor e Antecessor: Cada nó mantém informações sobre o sucessor e, opcionalmente, sobre o nó antecessor. Isso permite que o sistema mantenha a estrutura circular da rede e ajuste-se dinamicamente a entrada e saída de nós.

### Lookup e Roteamento

A busca por um recurso no Chord é realizada através de um processo de roteamento distribuído. Quando um nó recebe uma solicitação para uma chave que não é de sua responsabilidade, ele encaminha a requisição para o nó mais próximo (em termos de identificador) do responsável pela chave.

- Roteamento Logarítmico: O tempo para encontrar um recurso na rede é proporcional ao logaritmo do número de nós, ou seja, $O(log\ N)$, onde $N$ é o número de nós na rede. Esse desempenho é alcançado usando a estrutura de _finger table_.

### Finger Table

Cada nó no Chord mantém uma **tabela de atalho** chamada _finger table_. Essa tabela armazena informações sobre nós distantes, distribuídos ao longo da rede em intervalos de potências de dois ($2^{i\ saltos\ de\ distância}$), o que proporciona eficiência no roteamento das consultas.

Dessa forma, ao invés de simplesmente encaminhar as consultas de nó em nó no anel, ao usar a _finger table_, um nó pode encaminhar consultas saltando vários nós progressivamente em potências de dois em direção ao responsável pela chave.

### Escalabilidade

Um dos principais objetivos do Chord é ser **altamente escalável**. Ele é projetado para funcionar de maneira eficiente em grandes redes P2P, podendo suportar milhares ou até milhões de nós. Sua arquitetura descentralizada e o uso de uma DHT permitem que a carga seja distribuída entre todos os nós, de modo que à medida que novos nós entram na rede, a capacidade de armazenamento e processamento da rede como um todo aumenta.

Como cada nó precisa apenas de informações sobre um pequeno subconjunto dos outros nós (por conta da _finger table_), o Chord pode suportar grandes redes sem a necessidade de sobrecarregar os nós com muita informação.


## Limitações

### Desalinhamento entre topologia lógica e física (latência)
O Chord organiza os nós em uma topologia lógica de anel, sem levar em conta a proximidade física dos nós. Isso pode resultar em altas latências de comunicação quando mensagens são roteadas entre nós que, logicamente, estão próximos no anel, mas, geograficamente, podem estar muito distantes.

### Manutenção sob alta dinamicidade

Em redes altamente dinâmicas, onde muitos nós entram e saem com frequência, o Chord pode ter dificuldades para manter a consistência da _finger table_ e do anel. O custo de comunicação para atualizações constantes aumenta, e o processo de estabilização pode ser lento, afetando a eficiência do sistema.


## Conclusão

Embora o Chord tenha suas limitações, é capaz de resolver o problema de _lookup_ de maneira eficiente em grandes redes P2P. Com sua topologia em anel, uso de tabelas hash distribuídas (DHTs), e seu mecanismo de roteamento baseado em _finger tables_, o Chord é fornece um serviço de busca escalável e resiliente, lidando com dinamismo e falhas de maneira robusta.


## Referências

STOICA, Ion; MORRIS, Robert; KARGER, David; KAASHOEK, M. Frans; BALAKRISHNAN, Hari. **Chord: A scalable peer-to-peer lookup service for Internet applications.** _ACM SIGCOMM Computer Communication Review_, v. 31, n. 4, p. 149-160, 2001. Disponível em: https://pdos.csail.mit.edu/papers/chord:sigcomm01/chord_sigcomm.pdf. Acesso em: 10 de set. de 2024.




<br>
<br>
<br>
<br>

#### Curiosidade:

O substantivo "Chord" em inglês se refere a uma "corda" musical (um grupo de notas tocadas juntas) ou a uma linha que conecta dois pontos em uma curva. O nome foi escolhido para representar a maneira como os nós na rede estão conectados entre si, formando um anel lógico.

A ideia é que o nome capture a noção de interconexão entre os nós em uma topologia ordenada, assim como notas musicais se combinam em um acorde.






<!-- slide 0: Título | Seminário de artigo em tópico de SD - Gustavo -->
<!-- slide 1: contextualização: P2P -->
<!-- slide 2: resumo P2P -->
<!-- slide 3: contextualização: Lookup -->
<!-- slide 4: A base do Chord -->
<!-- slide: Fim. | Obrigado pela atenção! | Perguntas? -->
<!-- slide: curiosidade -->