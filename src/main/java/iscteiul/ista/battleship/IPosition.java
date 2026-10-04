/**
 * Interface que define o contrato para uma posição no tabuleiro de batalha naval.
 * Uma posição é identificada por linha e coluna, e pode estar ocupada por um navio
 * e/ou ser alvo de um disparo.
 *
 * @author LETI-129803
 * @see Position
 */
package iscteiul.ista.battleship;

/**
 * Interface para representar uma posição no tabuleiro de jogo de batalha naval.
 */
public interface IPosition {
    /**
     * Retorna a linha desta posição.
     *
     * @return a linha da posição (0-9)
     */
    int getRow();

    /**
     * Retorna a coluna desta posição.
     *
     * @return a coluna da posição (0-9)
     */
    int getColumn();

    /**
     * Verifica se esta posição é igual a outro objeto.
     * Duas posições são iguais se têm a mesma linha e coluna.
     *
     * @param other o objeto a comparar
     * @return true se as posições são iguais, false caso contrário
     */
    boolean equals(Object other);

    /**
     * Verifica se esta posição é adjacente (até 1 casa de distância
     * em qualquer direção, incluindo diagonais) a outra posição.
     *
     * @param other a posição a comparar
     * @return true se as posições são adjacentes, false caso contrário
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marca esta posição como ocupada (por um navio).
     */
    void occupy();

    /**
     * Marca esta posição como tendo sido alvo de um disparo.
     */
    void shoot();

    /**
     * Verifica se esta posição está ocupada por um navio.
     *
     * @return true se a posição está ocupada, false caso contrário
     */
    boolean isOccupied();

    /**
     * Verifica se esta posição foi alvo de um disparo.
     *
     * @return true se a posição foi atingida, false caso contrário
     */
    boolean isHit();
}
