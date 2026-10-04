/**
 * Classe utilitária que contém tarefas de teste para o jogo de batalha naval.
 * Fornece quatro tarefas incrementais que testam diferentes aspetos do jogo:
 * criação de navios, construção de frotas, deteção de trapaças e simulação de combate.
 *
 * @author LETI-129788
 * @see Ship
 * @see Fleet
 * @see Game
 */
package iscteiul.ista.battleship;

import java.util.Scanner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tasks {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final int NUMBER_SHOTS = 3;

    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    /**
     * Constantes de comandos utilizadas pela interface com o utilizador.
     */
    private static final String NOVAFROTA = "nova";
    private static final String DESISTIR = "desisto";
    private static final String RAJADA = "rajada";
    private static final String VERTIROS = "ver";
    private static final String BATOTA = "mapa";
    private static final String STATUS = "estado";


    /////////////////////////////////////////////////////////////////////////////
    // hereafter one may find some code that can be converted to automatic tests,
    // as long as appropriate changes are made. It also shows that we should
    // develop our code incrementally e.g. first the ships, then the fleet,
    // then some rule checking, then dealing with firing and so on
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Tarefa A: Testa a construção de navios individuais.
     * Lê dados de navios e verifica se ocupam posições específicas.
     * Continua até não haver mais entrada disponível.
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * Tarefa B: Testa a construção de frotas.
     * Permite criar uma nova frota e consultar o seu estado.
     * Comandos disponíveis: "nova" (criar frota), "estado" (ver estado), "desisto" (sair).
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Tarefa C: Testa a construção de frotas com possibilidade de deteção de trapaças.
     * Além dos comandos da Tarefa B, permite visualizar o mapa secreto com todos os navios.
     * Comandos disponíveis: "nova", "estado", "mapa" (ver posição dos navios), "desisto".
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Tarefa D: Testa a simulação completa do jogo de batalha naval.
     * Permite criar uma frota, disparar contra ela em rondas de três disparos,
     * e visualizar o estado do jogo (acertos, disparos inválidos, repetidos, navios restantes).
     * Comandos disponíveis: "nova" (criar frota), "estado", "mapa", "rajada" (disparar),
     * "ver" (ver disparos válidos), "desisto".
     */
    public static void taskD() {

        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.", game.getHits(), game.getInvalidShots(),
                                game.getRepeatedShots(), game.getRemainingShips());
                        if (game.getRemainingShips() == 0)
                            LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Constrói uma frota lendo dados do utilizador através do scanner.
     * Tenta adicionar navios até atingir o limite da frota ou até o utilizador
     * deixar de fornecer dados válidos.
     *
     * @param in o scanner para ler dados do utilizador
     * @return a frota construída com os navios adicionados com sucesso
     * @throws AssertionError se o scanner for null
     */
    static Fleet buildFleet(Scanner in) {
        assert in != null;

        Fleet fleet = new Fleet();
        int i = 0; // i represents the total of successfully created ships

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);
            if (s != null) {
                boolean success = fleet.addShip(s);
                if (success)
                    i++;
                else
                    LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * Lê dados de um navio a partir do scanner e constrói uma instância do navio apropriado.
     * Espera receber: tipo de navio (string), linha, coluna e rumo (caractere).
     *
     * @param in o scanner para ler os dados
     * @return o navio construído, ou null se o tipo for desconhecido
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * Lê uma posição no tabuleiro a partir do scanner.
     * Espera receber dois inteiros: linha e coluna.
     *
     * @param in o scanner para ler os dados
     * @return a posição lida
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();
        return new Position(row, column);
    }

    /**
     * Executa uma ronda de disparos (três disparos) contra a frota do jogo.
     * Para cada disparo, verifica se acertou num navio e regista o resultado.
     *
     * @param in o scanner para ler as posições dos disparos
     * @param game o contexto do jogo onde os disparos serão efetuados
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);
            if (sh != null)
                LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
        }

    }

}
