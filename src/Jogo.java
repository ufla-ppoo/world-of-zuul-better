/**
 * Essa é a classe principal da aplicacao "World of Zull".
 * "World of Zuul" é um jogo de aventura muito simples, baseado em texto.
 * 
 * Usuários podem caminhar em um cenário. E é tudo! Ele realmente precisa ser 
 * estendido para fazer algo interessante!
 * 
 * Para jogar esse jogo, crie uma instancia dessa classe e chame o método "jogar".
 * 
 * Essa classe principal cria e inicializa todas as outras: ela cria os ambientes, 
 * cria o analisador e começa o jogo. Ela também avalia e  executa os comandos que 
 * o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido e adaptado por Julio César Alves)
 */

public class Jogo {
    // analisador de comandos do jogo
    private Analisador analisador;
    // ambiente onde se encontra o jogador
    private Ambiente ambienteAtual;
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo()  {
        criarAmbientes();
        analisador = new Analisador();
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes() {
        Ambiente reitoria, pavilhao, cantina, departamento, laboratorio, porao;
      
        // cria os ambientes
        reitoria = new Ambiente("em um espaço aberto, gramado, em frente à reitoria");
        pavilhao = new Ambiente("no pavilhao de aulas");
        cantina = new Ambiente("na cantina da universidade");
        departamento = new Ambiente("no departamento de computacao");
        laboratorio = new Ambiente("na laboratorio de aulas de programacao");
        porao = new Ambiente("no porão do laboratório de programação");
        
        // inicializa as saidas dos ambientes
        reitoria.ajustarSaida("leste", pavilhao);
        reitoria.ajustarSaida("sul", departamento);
        reitoria.ajustarSaida("oeste", cantina);
        
        pavilhao.ajustarSaida("oeste", reitoria);
        
        cantina.ajustarSaida("leste", reitoria);        
        
        departamento.ajustarSaida("norte", reitoria);
        departamento.ajustarSaida("leste", laboratorio);
        
        laboratorio.ajustarSaida("oeste", departamento);
        laboratorio.ajustarSaida("baixo", porao);

        porao.ajustarSaida("cima", laboratorio);

        ambienteAtual = reitoria;  // o jogo comeca em frente à reitoria
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar()  {
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nós repetidamente lemos comandos e 
        // os executamos até o jogo terminar.
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Até mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas() {
        System.out.println();
        System.out.println("Bem-vindo ao World of Zuul!");
        System.out.println("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();
        
        imprimirLocalizacaoAtual();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando)  {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("observar")) {
            observar(comando);
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }

        return querSair;
    }

    /**
     * Exibe informações de ajuda.
     * Aqui nós imprimimos algo bobo e enigmático e a lista de  palavras de comando
     */
    private void imprimirAjuda()  {
        System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println("   " + analisador.getComandosValidos());
    }

    /**
     * Trata o comando "observar", exibindo as informações da localização atual do jogador
     * 
     * @param comando Objeto comando a ser tratado
     */
    private void observar(Comando comando) {
        // se há segunda palavra, explica para o usuário que não pode...
        if(comando.temSegundaPalavra()) {            
            System.out.println("Não é possível observar detalhes de alguma coisa");
            return;
        }

        imprimirLocalizacaoAtual();
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saída para lá entra no novo ambiente, 
     * caso contrário imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando)  {
        // se não há segunda palavra, não sabemos pra onde ir...
        if(!comando.temSegundaPalavra()) {            
            System.out.println("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);

        if (proximoAmbiente == null) {
            System.out.println("Nao ha passagem!");
        }
        else {
            ambienteAtual = proximoAmbiente;
            
            imprimirLocalizacaoAtual();
        }
    }

    /**
     * Exibe as informações da localização atual para o jogador
     */
    private void imprimirLocalizacaoAtual() {
    	System.out.println(ambienteAtual.getDescricaoLonga());
        System.out.println();
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver se nós queremos 
     * realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrário.
     */
    private boolean sair(Comando comando)  {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nós realmente queremos sair
        }
    }
}
