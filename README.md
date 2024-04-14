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

### Passo 2 - Incluindo itens: criando classe

Nosso jogo por enquanto não permite fazer muita coisa.
Mas, e se quiséssemos acrescentar itens que os jogadores pudessem coletar e usar?

Neste passo, vamos começar implementando a primeira etapa dessa alteração que é criar itens que estejam em algum ambiente do jogo. 

O primeiro passo para isso é ter uma classe para representar os itens.

- Os itens que podem existir no seu jogo vão depender muito da temática que você escolheu. 

  - Logo, a classe não precisa se chamar `Item`; ela deve ter o nome que faça sentido pra você (ex.: arma, alimento, artefato, etc.).

- A classe deve ter, pelo menos, os atributos: nome e descrição.

  - Crie um construtor que define os atributos e métodos de acesso para cada um.

### Passo 3 - Incluindo itens nos ambientes

Altere a classe `Ambiente` de forma que ela agora tenha:

- Um atributo item.
- Um segundo construtor que receba um item.
- Um método que retorna um booleano indicando se o ambiente tem um item.

Por fim, altere o método `getDescricaoLonga` para incluir a informação sobre o item do ambiente, caso exista.

- Por exemplo, se houver um item a mensagem poderia ser, por exemplo:
  
  - `Há um livro antigo e misterioso por aqui`.
  - Obs.: o trecho `um livro antigo e misterioso` seria a descrição do item.

- Se não houver item, a mensagem poderia ser:

  - `Não há nada aqui`.

### Passo 4 - Incluindo itens no jogo

Para que a inclusão dos itens fique completa no jogo, precisamos criar itens que sejam acrescentados nos ambientes.

Altere então a classe `Jogo` da seguinte forma::

- Altere o método `criarAmbientes` criando pelo menos dois objetos itens.

  - *Dica 1*: use descrições criativas para os itens. Por ex.: “um livro misterioso, com folhas amareladas e cheirando a mofo” deixa o jogo muito mais interessante do que simplesmente “um livro”.

  - *Dica 2*: use dois itens diferentes para deixar o jogo mais interessante. Por ex.: um livro e uma chave é mais interessante que dois livros.

Altere a criação dos ambientes para que os itens criados sejam colocados nos ambientes onde devem estar (use o segundo construtor que criou na classe `Ambiente`).

*Sugestão*: 

- o jogo pode ficar mais interessante para o jogador se o método `irParaAmbiente` agora exibir apenas a descrição simples do novo ambiente (e não a descrição longa).
- Isso fará com que o comando *observar* se torne mais útil, pois ele representaria a ação do jogador estar ativamente procurando alguma coisa.
- Assim, um jogador pode passar por um ambiente e não perceber que há um item lá, por exemplo.

Não se esqueça de testar o seu jogo com as alterações feitas!

### Passo 5 - Coletando itens

Vamos agora alterar nosso jogo para que o jogador consiga pegar um item do ambiente.
Para isso:

- Crie uma palavra de comando chamada *pegar* (na classe `PalavrasComando`).

- Crie dois métodos na classe `Ambiente`:

  - Um para consultar o item existente (retorna `null` se não houver item).
  - Outro para coletar o item do ambiente, ou seja, ele deve deixar o atributo item do ambiente com valor `null` e retornar o item (*dica*: use uma variável auxiliar).

- Trate a palavra de comando **`pegar`** na classe `Jogo`.

  - Se o usuário digitar apenas `pegar`, dê uma mensagem apropriada (ex: `Pegar o que?`).
  - Se o usuário digitar o nome do item que está no ambiente:
  
    - O item deve ser coletado do ambiente, e uma mensagem deve ser exibida dizendo que o usuário pegou tal item.
    - Obs: por enquanto não é necessário guardar o item que o jogador pegou.
  
  - Se o item não estiver no ambiente (ou for uma palavra que não existe):
  
    - Deve ser exibida uma mensagem indicando que não há esse item no ambiente.

Teste suas alterações!

### Passo 6 - Carregando itens: refatorando - classe para Jogador

Vamos agora permitir que o jogador carregue itens que depois possam ser usados em outros lugares  para realizar ações (tais como: abrir portas, desbloquear passagens, destruir monstros, etc).

Veja que até agora o jogador não tem uma representação no jogo.
Ele apenas se move entre os ambientes e pega itens e, por isso, a classe `Jogo` é que guarda a localização atual do jogador.

Mas agora, além da localização atual, teremos uma coleção de itens que o jogador está carregando.
Logo, incluir essa informação na classe `Jogo` diminui sua coesão, pois ela representará a lógica do jogo **e** o jogador.

Vamos então **refatorar** nosso código, criando uma classe para representar o jogador.

- Dê um nome para a classe que tenha a ver o tema do seu jogo.

A classe deve ter:

- Uma coleção (ArrayList ou HashMap, por ex.) de itens que estão sendo carregados.

  - Poderia ser uma mochila ou algo que faça sentido com o tema do seu jogo.

- O ambiente (localização) atual do jogador.

  - Além disso, pode ter atributos adicionais que façam sentido no seu jogo (ex.: nome).

- Um construtor que cria a coleção de itens vazia (e recebe parâmetros adicionais, se necessário, como o nome).

- Método de acesso para atributos como o ambiente atual (mas não para os itens).

- Método para adicionar um item na coleção do jogador.

- Método para remover um item da coleção a partir de seu nome.

- Método que monte e retorne uma string informando todos os itens que o jogador está carregando no momento.

### Passo 7 - Carregando itens no jogo

Faça as alterações necessárias na classe `Jogo` para utilizar a classe que representa o jogador.
Dentre as alterações você precisará:

- Declarar um atributo para representar o jogador na classe `Jogo` e criá-lo no construtor da classe.

- Remover o atributo `ambienteAtual` e fazer as alterações necessárias para usá-lo a partir da classe do jogador.

- Alterar o tratamento do comando `pegar` para que, quando o jogador pegar um item que estava em um ambiente, esse item seja adicionado na coleção de itens do jogador.

Teste o seu jogo!

### Passo 8 - Listando itens

Para que depois o jogador consiga usar os itens que está carregando, ele precisará ter uma forma de ver e lembrar os nomes dos itens que estão com ele.

Neste passo, crie e faça os tratamentos necessários para que o jogo tenha uma nova palavra de comando que permita ao jogador ver os itens que ele está carregando.

O nome da palavra de comando deve ter a ver com o tema do seu jogo (poderia ser, por exemplo, `inventario` ou  `listar_itens`).

Jogue e teste suas alterações!

### Passo 9 - Bloqueando ambientes: classe Saida

Vamos tratar neste passo o bloqueio dos ambientes.
Um bloqueio poderia ser, por exemplo, uma porta fechada que precisa de uma chave para ser aberta ou saída da caverna cheia de pedras que precisará de explosivos para ser liberada.

Repare que agora, a saída de um ambiente não deve ter apenas a informação do próximo ambiente, mas também a indicação se a saída está bloqueada ou não e a identificação do item capaz de desbloquear a saída.
Portanto, o ideal é que façamos uma nova refatoração.

Vamos então refatorar nosso projeto criando uma classe para representar uma saída de um ambiente. 
A classe `Saida` deve ter:

- Os atributos: ambiente para onde a saída leva, booleano indicando se ela está bloqueada ou não, e String com o nome do item que desbloqueia a saída (caso ela esteja bloqueada).

- Construtor que recebe os valores dos atributos e métodos de acesso para cada um.

A classe `Ambiente` deve então ser alterada de forma que a coleção de saídas seja agora de objetos da classe `Saida` (em vez de ser uma coleção de ambientes como estava até então).

- O método `ajustarSaida` será usado para definir saídas que não estão bloqueadas. Ele deve ser alterado para criar uma um objeto para saída desbloqueada (com valor `null` para o item que desbloqueia a saída) e deve colocá-la na coleção de saídas.

- Crie um novo método `ajustarSaidaBloqueada`, que receba, além da direção e do ambiente, o nome do item que desbloqueia a saída. O método deve então criar um objeto para saída bloqueada com o nome do item recebido e colocá-lo na coleção de saídas.

- Por fim, o método `getSaida` deve ser alterado de forma que ele retorne o ambiente da saída, caso ela exista e esteja desbloqueada. Caso contrário, deve retornar `null`.

### Passo 10 - Bloqueando ambientes no jogo

Agora que a classe `Ambiente` permite a definição de saídas bloqueadas, altere a criação de ambientes na classe `Jogo` de forma que pelo menos um ambiente tenha uma saída bloqueada.

- Veja que, para isso, basta substituir alguma chamada ao método `ajustarSaida` de algum ambiente por uma chamada ao método `ajustarSaidaBloqueada`, indicando o nome do item que desbloqueia a saída.

Teste seu jogo!

- Se tudo estiver certo, quando você tentar passar pela saída bloqueada, o jogo informará que não há passagem.

### Passo 11 - Desbloqueando saídas

Nós estamos quase no ponto de ter o jogo com algum tipo de missão que o jogador possa cumprir para ganhar o jogo.

- Veja que agora nosso jogo tem itens que o jogador consegue carregar e tem ambientes com saídas bloqueadas.

- Vamos então permitir que o jogador use algum item para desbloquear as saídas.

A ideia é que o jogador possa digitar um comando `usar algo`, onde `algo` é o nome de um item que ele está carregando e que será usado no ambiente.

- Se o item for usado em um ambiente com saída bloqueada, e for o item que desbloqueia aquela saída, a mesma será desbloqueada.


Como primeiro passa para tratarmos isso, altere a classe `Saida`, acrescentando um método modificador chamado `desbloquear`, que altera o atributo booleano para false.

Na classe `Ambiente`, crie um método `usarItem` (ou nome similar de acordo com o tema do seu jogo).

- Ele deve receber, por parâmetro, o objeto item a ser utilizado (**atenção**: deve receber um objeto item, e não apenas o nome do item).

- Se a coleção de saídas tiver alguma saída cujo nome do item de desbloqueio seja o do item passado, a saída deve ser desbloqueada.

- O método deve retornar um booleano indicando se alguma saída foi desbloqueada ou não.

### Passo 12 - Usando itens para desbloquear saídas


Na classe que representa o jogador, crie um método que recebe uma string com o nome de um item e retorne se o jogador tem aquele item.

Crie uma nova palavra de comando **`usar`** e faça o tratamento do comando na classe Jogo:

- Se o usuário digitar apenas `usar` avise-o que é necessário informar o que quer usar.

- Se o usuário digitar o nome de um item (ex: `usar algo`):
  
  - Se o jogador não tiver o item: informe o usuário.

  - Se o jogador tiver o item: chame o método de usar o item da classe `Ambiente`.

    - Se uma saída tiver sido desbloqueada: remova o item da coleção de itens do jogador e avise o usuário que uma saída foi desbloqueada.

    - Se nenhuma saída tiver sido desbloqueada: avise ao usuário que isso aconteceu e não remova o item da coleção do jogador.

Na criação dos ambientes na classe `Jogo`, configure os ambientes de forma que o jogador tenha que ir em um ambiente pegar um item, para desbloquear a saída de algum outro ambiente.

Teste seu jogo! Agora há uma missão a cumprir!

### (Opcional) Passo 13 - Zerando o jogo

Agora nosso jogo permite que o jogador não só caminhe entre os ambientes, como também pegue itens e desbloqueie passagens.
Podemos então definir uma forma do jogador *zerar* o jogo.

Neste passo você deve:

- Fazer um mapa maior, com mais ambientes, itens e passagens bloqueadas.
- Criar um objetivo no jogo, que faça sentido com seu tema.

Algumas ideias de objetivo são:

- Definir um ambiente final que é onde o jogador precisa chegar.
- Criar um item especial a ser conquistado pelo jogador.
- Enfim, alguma coisa que represente que o jogador conseguiu *finalizar* o jogo.

### (Opcional) Passo 14 - Ideias para melhorar seu jogo

Nós agora temos um jogo mais divertido que o **World of Zuul**, o que é muito bacana :)

Mas com criatividade você pode fazer muito mais!
Veja alguns exemplos abaixo *(obs.: se você fizer algum desses passos, coloque um comentário no cabeçalho da classe Jogo, indicando o que você fez)*.

- Adicione **personagens** ao jogo. Eles são parecidos com os itens, mas quando o jogador encontra um personagem pela primeira vez ele diz alguma coisa (pode ser uma dica que ajude o jogador, por exemplo).

- Adicione alguma forma de **limite de tempo** ao seu jogo, e se o jogador não chegar no objetivo dentro do tempo, ele perde. O tempo não precisa ser real, pode ser o número de movimentos ou de comandos inseridos, por exemplo.

- Crie um **sistema de pontuação** que motive o jogador. Ele poderia ganhar pontos ao pegar itens especiais (estrelas ou moedas, por exemplo), ou ao desbloquear passagens, etc.

- Crie **inimigos** e coloque-os nos ambientes. 

  - Inimigos podem atacar o jogador que pode sofrer dano e até morrer.
  
  - Crie então armas que podem ser coletadas e usadas para derrotar inimigos.

  - Crie também itens que possam ser usados para melhorar a saúde do jogador.

- Adicione **personagens que se movem**. Eles são como os personagens comuns, mas toda vez que o jogador digita um comando, o personagem se move para um dos ambientes vizinhos.

- Implemente uma **porta secreta** em algum lugar (ou alguma outra forma de porta que você só possa cruzar uma vez).

- Adicione um ***beamer*** ao seu jogo. O beamer memoriza o ambiente onde ele é iniciado e teletransporta o jogador para aquele ambiente quando ele é disparado. Para isso, você precisará tratar os comandos para *iniciar* e *disparar* o beamer. E o beamer em si poderia ser um item a ser encontrado pelo jogador.

- Crie um **limite de peso** para a mochila jogador de forma que ele não consiga carregar todos os itens. Para isso, permita que os ambientes tenham uma lista de itens e não apenas um. Além disso, será necessário permitir que o usuário largue itens nos ambientes.

Enfim, agora sua criatividade é o limite :)
