/**
 * Representa uma posição no tabuleiro de jogo de batalha naval.
 * Cada posição é identificada por uma linha e coluna, e pode estar
 * ocupada por um navio e/ou ter sido alvo de um disparo.
 *
 * @author LETI-129788
 * @see IPosition
 */
package iscteiul.ista.battleship;

import java.util.Objects;

public class Position implements IPosition {
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    /**
     * Cria uma posição no tabuleiro com a linha e coluna especificadas.
     * Inicialmente, a posição não está ocupada nem foi alvo de disparo.
     *
     * @param row a linha da posição (0-9)
     * @param column a coluna da posição (0-9)
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * Retorna a linha desta posição.
     *
     * @return a linha da posição
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Retorna a coluna desta posição.
     *
     * @return a coluna da posição
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Calcula o código hash desta posição com base na linha,
     * coluna, estado de ocupação e estado de disparo.
     *
     * @return código hash da posição
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Verifica se esta posição é igual a outro objeto.
     * Duas posições são iguais se têm a mesma linha e coluna.
     *
     * @param otherPosition o objeto a comparar
     * @return true se as posições são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Verifica se esta posição é adjacente (até 1 casa de distância
     * em qualquer direção, incluindo diagonais) a outra posição.
     *
     * @param other a posição a comparar
     * @return true se as posições são adjacentes, false caso contrário
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * Marca esta posição como ocupada (por um navio).
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * Marca esta posição como tendo sido alvo de um disparo.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * Verifica se esta posição está ocupada por um navio.
     *
     * @return true se a posição está ocupada, false caso contrário
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Verifica se esta posição foi alvo de um disparo.
     *
     * @return true se a posição foi atingida, false caso contrário
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Retorna uma representação em texto desta posição.
     *
     * @return string no formato "Linha = X Coluna = Y"
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }

}
