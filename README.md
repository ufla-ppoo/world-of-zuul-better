## Projeto World of Zuul Better

**World of Zuul** é um jogo de aventura muito simples, baseado em texto.
Este jogo foi criado por Michael Kölling e David J. Barnes, no livro *Programação Orientada a Objetos com Java: Uma Introdução Prática Usando o BlueJ*.

O código deste projeto foi traduzido e adaptado para uso nas aulas de Programação Orientada a Objetos da UFLA.

Este é um projeto melhorado, utilizado nas aulas de Design de Classe. Ele é resultado de alterações no projeto original [world-of-zuul-bad](https://github.com/ufla-ppoo/world-of-zuul-bad).

## Evolução do jogo

### Passo 1 - Definindo um tema para seu jogo

O código do **World of Zuul** é usado para entendermos os conceitos de design de classes.
Mas o jogo fica mais interessante se for criado por você mesmo!
Neste passo você deve mudar o tema do jogo para um criado por você mesmo.
Pode ser qualquer jogo que tenha como estrutura de base um jogador que se move por locais diferentes (nas direções norte, sul, leste e oeste).

No papel, você deverá fazer o seguinte:

- Defina um tema para seu jogo.
- Defina um nome para o jogo.
- Planeje um mapa (com pelo menos cinco ambientes).
- Defina uma forma do jogador ganhar (ex: coletar um item mágico ou salvar uma pessoa, etc.).

Depois, no computador você deve alterar o código do **World of Zuul** da seguinte forma:

- Altere os métodos `imprimirBoasVindas` e `imprimirAjuda` para se adequar à temática do seu jogo.
- Alterar o método `criarAmbientes` para criar o mapa que você planejou para seu jogo.
- Teste seu jogo (por enquanto o jogador conseguirá apenas se movimentar no mapa).

### Passo 2 - Criando itens nos ambientes

Nosso jogo por enquanto não permite fazer muita coisa.
Mas, e se quiséssemos acrescentar itens que os jogadores pudessem coletar e usar?

Vamos neste passo permitir que nossos ambientes possuam itens.
Para isso, faça o seguinte:

- Crie uma classe para representar itens:

  - Com os atributos: nome e descrição.
  - Os ítens que podem existir no seu jogo vão depender muito da temática que você escolheu. Logo, a classe não precisa se chamar `Item`; ela deve ter o nome que faça sentido pra você.

- Altere a classe `Ambiente` para que ela tenha:

  - Um atributo item.
  - Um segundo construtor que receba um item.
  - Um método que retorna um booleano indicando se o ambiente tem um item.
  - E altere o método `getDescricaoLonga` para incluir a informação sobre o item do ambiente, caso exista.

- Altere a classe `Jogo`:

  - Altere a criação de ambientes para que pelo menos dois deles tenham algum item.
  - Os itens precisam ser diferentes em cada ambiente.
  - O jogo pode ficar mais interessante se o método `irParaAmbiente` exibir apenas a descrição do novo ambiente (e não a descrição longa). Isso fará com que o comando *observar* seja mais útil.

Não se esqueça de testar o seu jogo com as alterações feitas!

### Passo 3 - Coletando itens

Vamos agora alterar nosso jogo para que o jogador consiga pegar um item do ambiente.
Para isso:

- Crie uma palavra de comando chamada *pegar* (na classe `PalavrasComando`).

- Crie dois métodos na classe `Ambiente`:

  - Um para consultar o item existente (retorna `null` se não houver item).
  - Outro para coletar o item do ambiente, ou seja, ele deve deixar o atributo item do ambiente com valor `null` e retornar o item (dica: use uma variável auxiliar).

- Trate a palavra de comando *pegar* na classe Jogo.

  - Se o usuário digitar apenas *pegar*, dê uma mensagem apropriada (ex: *Pegar o que?*).
  - Se o usuário digitar o nome do item que está no ambiente:
    - O item deve ser coletado do ambiente, e uma mensagem deve ser exibida dizendo que o usuário pegou tal item.
    - Obs: por enquanto não é necessário guardar o item que o jogador pegou.
  - Se o item não estiver no ambiente (ou for uma palavra que não existe):
    - Deve ser exibida uma mensagem indicando que não há esse item no ambiente.

Teste suas alterações!

### Passo 4 - Carregando itens

Vamos agora permitir que o jogador carregue itens que depois possam ser usados em outros lugares  para realizar ações (tais como: abrir portas, desbloquear passagens, destruir monstros, etc).
Nosso código fica melhor se o **refatorarmos**, criando uma classe para representar o jogador.

Para isso, faça o seguinte:

- Crie uma classe para representar o jogador:

  - Dê um nome para a classe que tenha a ver o tema do seu jogo.
  - Um jogador deve ter um nome e uma lista de itens que estão sendo carregados.
    - Poderia ser uma mochila ou algo que faça sentido com o tema do seu jogo.
  - Crie o construtor que recebe o nome do jogador e cria a lista de itens vazia.
  - Crie um método para retornar o nome do jogador.
  - Crie um método para adicionar um item na lista do jogador.
  - Crie um método que, a partir do nome do ítem, remove-o da lista do jogador e o retorne.
  - Crie um método que retorne uma única string informando todos os itens que o jogador está carregando.

- Crie um atributo jogador na classe `Jogo` e o inicialize adequadamente.

- Altere o tratamento do comando *pegar* na classe `Jogo`.

  - Quando o jogador pegar um item que está no ambiente, esse item deve ser adicionado na lista de itens do jogador.

- Crie e trate um novo comando *inventario* exiba os itens que estão sendo carregados pelo jogador.

Jogue e teste suas alterações!

### Passo 5 - Bloqueando ambientes

Vamos tratar neste passo o bloqueio dos ambientes de uma forma bem simples.
A ideia é que apenas uma das saídas de um ambiente possa estar bloqueada (ou nenhuma delas).

Para isso faça as seguintes alterações na classe `Ambiente`:

- Crie um atributo string que indique qual saída (direção) está bloqueada.

  - Se o atributo tiver valor `null`, indicará que não há nenhuma saída bloqueada.

- Crie um atributo string que indique o nome do item que desbloqueia a saída bloqueada.

  - O atributo terá valor `null` se o ambiente não tiver nenhuma saída bloqueada.

- Crie um método chamado `ajustarSaidaBloqueada` que recebe uma direção, um ambiente e um nome de item de desbloqueio.

  - Tal método chamará o `ajustarSaida` para a direção e o ambiente e, em seguida, guardará a direção passada como a saída bloqueada e o nome do item passado como o item para desbloqueio.

- O método `getSaida` deve ser alterado de forma que:

  - Se a direção passada for de uma saída bloqueada, retorne `null` (isso impedirá que o jogador passe por aquela saída).
  - Se for para uma saída não bloqueada, retorne o ambiente como já fazia antes.

Em seguida, na criação dos ambientes na classe `Jogo`, bloqueie uma das saídas.

- Ou seja, substitua alguma chamada ao método `ajustarSaida` de algum ambiente por uma chamada ao método `ajustarSaidaBloqueada`, indicando o nome do item de desbloqueio.

Se tudo estiver certo você não conseguirá passar pela saída bloqueada.
No próximo passo veremos como usar um item para desbloquear a saída.

Teste seu jogo!

### Passo 6 - Usando os itens

Agora vamos realmente tornar os itens úteis, usando-os para desbloquear as saídas dos ambientes.
A ideia é que o jogador possa digitar um comando *usar algo*, onde *algo* é o nome de um item que ele está carregando e que será usado no ambiente.
Se o item for usado em um ambiente com saída bloqueada, e for o item que desbloqueia aquela saída, a mesma será desbloqueada.

Para isso faça as seguintes alterações no código:

- Crie o comando *usar* na classe `PalavrasComando`.

- Na classe `Ambiente`, crie um método que permita ao jogador usar um item.

  - Tal método deve receber, por parâmetro, **o objeto** item a ser utilizado (atenção: deve receber um objeto item, e não apenas o nome do item).
  - Se o item recebido tem o mesmo nome do item para desbloqueio, o atributo de saída bloqueada recebe o valor `null`.
  - O método deve retornar um booleano indicando se uma saída foi desbloqueada ou não.

- Na classe que representa o jogador, crie um método que recebe uma string com o nome de um item e retorne se o jogador tem aquele item.

- Faça o tratamento do comando *usar* na classe `Jogo`:

  - Se o usuário não digitar o nome do item a ser usado dê uma mensagem apropriada.
  - Se o usuário digitar o nome de um item:
    - Que o jogador não tem: informe o usuário.
    - Que o jogador tem: chame o método de usar o item da classe `Ambiente` e, caso uma saída tenha sido desbloqueada, remova o item da lista de itens do jogador.
    - Dê uma mensagem apropriada para o usuário caso ele tenha desbloqueado a passagem.

- Na criação dos ambientes na classe `Jogo`, configure os ambientes de forma que o jogador tenha que ir em um ambiente pegar um item, para desbloquear a saída de algum outro ambiente.

Teste seu jogo!
Você deve agora conseguir desbloquear a passagem que o jogador não tinha acesso :)

### (Opcional) Passo 7 - Zerando o jogo

Agora nosso jogo permite que o jogador não só caminhe entre os ambientes, como também pegue itens e desbloqueie passagens.
Podemos então definir uma forma do jogador *zerar* o jogo.

Neste passo você deve:

- Fazer um mapa maior, com mais ambientes, itens e passagens bloqueadas.
- Criar um objetivo no jogo, que faça sentido com seu tema.

Algumas ideias de objetivo são:

- Definir um ambiente final que é onde o jogador precisa chegar.
- Criar um item especial a ser conquistado pelo jogador.
- Enfim, alguma coisa que represente que o jogador conseguiu *finalizar* o jogo.

### (Opcional) Passo 8 - Ideias para melhorar seu jogo

Nós agora temos um jogo mais divertido que o **World of Zuul**, o que é muito bacana :)

Mas com criatividade você pode fazer muito mais!
Veja alguns exemplos abaixo *(obs.: se você fizer algum desses passos, coloque um comentário no cabeçalho da classe Jogo, indicando o que você fez)*.

- Adicione alguma forma de limite de tempo ao seu jogo, e se o jogador não chegar no objetivo dentro do tempo, ele perde. O tempo não precisa ser real, pode ser o número de movimentos ou de comandos inseridos, por exemplo.
- Implemente uma porta secreta em algum lugar (ou alguma outra forma de porta que você só possa cruzar uma vez).
- Adicione um *beamer* ao seu jogo. O beamer memoriza o ambiente onde ele é iniciado e teletransporta o jogador para aquele ambiente quando ele é disparado. Para isso, você precisará tratar os comandos para *iniciar* e *disparar* o beamer. E o beamer em si poderia ser um item a ser encontrado pelo jogador.
- Adicione personagens ao jogo. Eles são parecidos com os itens, mas quando o jogador encontra um personagem pela primeira vez ele diz alguma coisa (pode ser uma dica que ajude o jogador, por exemplo).
- Adicione personagens que se movem. Eles são como os personagens comuns, mas toda vez que o jogador digita um comando, o personagem se move para um dos ambientes vizinhos.
- Crie um sistema de pontuação que motive o jogador. Ele poderia ganhar pontos ao pegar itens especiais (estrelas ou moedas, por exemplo), ou ao desbloquear passagens, etc.
- Crie inimigos e coloque-os nos ambientes. Crie então armas que podem ser coletadas e usadas para derrotar inimigos.
- Crie um limite de peso para a mochila jogador de forma que ele não consiga carregar todos os itens. Para isso, permita que os ambientes tenham uma lista de itens e não apenas um. Além disso, será necessário permitir que o usuário largue itens nos ambientes.
- Permita que os ambientes tenham mais saídas bloqueadas e não apenas uma. Uma boa solução seria criar uma classe para representar as saídas, assim você mantém a coesão no código.

Enfim, agora sua criatividade é o limite :)
