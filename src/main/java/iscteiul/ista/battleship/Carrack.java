/**
 * Representa uma Nau (Carrack) no jogo de batalha naval.
 * A Nau é um navio de tamanho 3 que pode estar orientado
 * na vertical (NORTE/SUL) ou na horizontal (ESTE/OESTE).
 *
 * @author LETI-129803
 * @see Ship
 * @see IShip
 */
package iscteiul.ista.battleship;

public class Carrack extends Ship {
    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Cria uma Nau com o rumo e posição inicial especificados.
     * A Nau ocupa 3 posições consecutivas no tabuleiro, dispostas
     * verticalmente se o rumo for NORTE/SUL, ou horizontalmente se for ESTE/OESTE.
     *
     * @param bearing o rumo (direção) da Nau (NORTH, SOUTH, EAST ou WEST)
     * @param pos a posição inicial (superior esquerda) da Nau
     * @throws IllegalArgumentException se o rumo não for uma direção válida
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Retorna o tamanho da Nau.
     *
     * @return o tamanho da Nau, que é sempre 3
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
