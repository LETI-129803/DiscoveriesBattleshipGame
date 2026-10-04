/**
 * Representa uma Fragata no jogo de batalha naval.
 * A Fragata é um navio de tamanho 4 que pode estar orientado
 * na vertical (NORTE/SUL) ou na horizontal (ESTE/OESTE).
 *
 * @author NeyrivanMSilva
 * @see Ship
 * @see IShip
 */
package iscteiul.ista.battleship;

public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Cria uma Fragata com o rumo e posição inicial especificados.
     * A Fragata ocupa 4 posições consecutivas no tabuleiro, dispostas
     * verticalmente se o rumo for NORTE/SUL, ou horizontalmente se for ESTE/OESTE.
     *
     * @param bearing o rumo (direção) da Fragata (NORTH, SOUTH, EAST ou WEST)
     * @param pos a posição inicial (superior esquerda) da Fragata
     * @throws IllegalArgumentException se o rumo não for uma direção válida
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /**
     * Retorna o tamanho da Fragata.
     *
     * @return o tamanho da Fragata, que é sempre 4
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}
