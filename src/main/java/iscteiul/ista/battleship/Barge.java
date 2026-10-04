/**
 * Representa uma Barca no jogo de batalha naval.
 * A Barca é o navio mais pequeno, ocupando apenas uma posição no tabuleiro.
 *
 * @author LETI-129788
 * @see Ship
 * @see IShip
 */
package iscteiul.ista.battleship;

public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Cria uma Barca com o rumo e posição inicial especificados.
     * A Barca ocupa apenas uma posição no tabuleiro.
     *
     * @param bearing o rumo (direção) da Barca
     * @param pos a posição inicial (e única) da Barca no tabuleiro
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Retorna o tamanho da Barca.
     *
     * @return o tamanho da Barca, que é sempre 1
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
