/**
 * Interface que define o contrato para o controlo de uma partida de batalha naval.
 * Especifica as operações para executar disparos, consultar estatísticas da partida
 * e visualizar o estado do tabuleiro.
 *
 * @author LETI-129803
 * @see Game
 * @see IShip
 * @see IPosition
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IGame {
    /**
     * Executa um disparo contra a posição especificada.
     * Verifica validade, repetição e acertos, e retorna o navio se for afundado.
     *
     * @param pos a posição alvo do disparo
     * @return o navio se for totalmente afundado por este disparo, null caso contrário
     */
    IShip fire(IPosition pos);

    /**
     * Retorna a lista de todas as posições onde foram efetuados disparos válidos e únicos.
     *
     * @return lista de posições disparadas
     */
    List<IPosition> getShots();

    /**
     * Retorna o número de disparos repetidos (posições já atacadas anteriormente).
     *
     * @return número de disparos repetidos
     */
    int getRepeatedShots();

    /**
     * Retorna o número de disparos inválidos (fora dos limites do tabuleiro).
     *
     * @return número de disparos inválidos
     */
    int getInvalidShots();

    /**
     * Retorna o número total de disparos que acertaram num navio.
     *
     * @return número de acertos
     */
    int getHits();

    /**
     * Retorna o número de navios que foram totalmente afundados.
     *
     * @return número de navios afundados
     */
    int getSunkShips();

    /**
     * Retorna o número de navios da frota que ainda estão flutuando (não foram totalmente afundados).
     *
     * @return número de navios restantes
     */
    int getRemainingShips();

    /**
     * Imprime o tabuleiro mostrando as posições onde foram efetuados disparos válidos.
     */
    void printValidShots();

    /**
     * Imprime o tabuleiro mostrando a posição de todos os navios da frota.
     */
    void printFleet();
}
