/**
 * Representa uma partida do jogo de batalha naval.
 * Gerencia o controlo de disparos, contabiliza acertos, falhas e navios afundados,
 * e fornece informações sobre o estado do jogo.
 *
 * @author NeyrivanMSilva
 * @see IGame
 * @see Fleet
 * @see IShip
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

public class Game implements IGame {
    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;


    /**
     * Cria uma nova partida com a frota especificada.
     * Inicializa os contadores de disparos a zero.
     *
     * @param fleet a frota que será alvo dos disparos
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * Executa um disparo contra a posição especificada.
     * Verifica se o disparo é válido, se já foi feito anteriormente,
     * e se acertou num navio. Se um navio for totalmente afundado, retorna-o.
     *
     * @param pos a posição alvo do disparo
     * @return o navio se for totalmente afundado por este disparo, null caso contrário
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Retorna a lista de todas as posições onde foram efetuados disparos válidos e únicos.
     *
     * @return lista de posições disparadas
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Retorna o número de disparos repetidos (posições já atacadas anteriormente).
     *
     * @return número de disparos repetidos
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Retorna o número de disparos inválidos (fora dos limites do tabuleiro).
     *
     * @return número de disparos inválidos
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Retorna o número total de disparos que acertaram num navio.
     *
     * @return número de acertos
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Retorna o número de navios que foram totalmente afundados.
     *
     * @return número de navios afundados
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Retorna o número de navios da frota que ainda estão flutuando (não foram totalmente afundados).
     *
     * @return número de navios restantes
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Verifica se uma posição é um disparo válido (dentro dos limites do tabuleiro).
     *
     * @param pos a posição a validar
     * @return true se a posição está dentro do tabuleiro, false caso contrário
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Verifica se um disparo já foi efetuado anteriormente nesta posição.
     *
     * @param pos a posição a verificar
     * @return true se a posição já foi alvo de um disparo, false caso contrário
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }


    /**
     * Imprime na consola um tabuleiro 10x10 com as posições marcadas com o caractere especificado.
     * Posições não marcadas aparecem com um ponto ('.').
     *
     * @param positions lista de posições a marcar no tabuleiro
     * @param marker o caractere a usar para marcar as posições
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }

    }


    /**
     * Imprime o tabuleiro mostrando as posições onde foram efetuados disparos válidos (marcadas com 'X').
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }


    /**
     * Imprime o tabuleiro mostrando a posição de todos os navios da frota (marcados com '#').
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }

}
