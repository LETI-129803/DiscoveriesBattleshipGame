/**
 * Representa uma Caravela no jogo de batalha naval.
 * A Caravela é um navio de tamanho 2 que pode estar orientado
 * na vertical (NORTE/SUL) ou na horizontal (ESTE/OESTE).
 *
 * @author LETI-129803
 * @see Ship
 * @see IShip
 */
package iscteiul.ista.battleship;

public class Caravel extends Ship {
    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Cria uma Caravela com o rumo e posição inicial especificados.
     * A Caravela ocupa 2 posições consecutivas no tabuleiro, dispostas
     * verticalmente se o rumo for NORTE/SUL, ou horizontalmente se for ESTE/OESTE.
     *
     * @param bearing o rumo (direção) da Caravela (NORTH, SOUTH, EAST ou WEST)
     * @param pos a posição inicial (superior esquerda) da Caravela
     * @throws NullPointerException se o rumo for null
     * @throws IllegalArgumentException se o rumo não for uma direção válida
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /**
     * Retorna o tamanho da Caravela.
     *
     * @return o tamanho da Caravela, que é sempre 2
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
