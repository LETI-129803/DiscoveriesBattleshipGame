/**
 * Representa um Galeão no jogo de batalha naval.
 * O Galeão é o navio mais grande, ocupando 5 posições no tabuleiro
 * num padrão em forma de cruz que varia consoante o rumo (NORTE, SUL, ESTE, OESTE).
 *
 * @author LETI-129788
 * @see Ship
 * @see IShip
 */
package iscteiul.ista.battleship;

public class Galleon extends Ship {
    private static final Integer SIZE = 5;
    private static final String NAME = "Galeao";

    /**
     * Cria um Galeão com o rumo e posição inicial especificados.
     * O Galeão ocupa 5 posições no tabuleiro em padrão em forma de cruz:
     * - NORTE: 3 posições na horizontal (linha 0) e 2 posições na vertical (coluna central)
     * - SUL: 2 posições na vertical (coluna 0) e 3 posições na horizontal (linha 2)
     * - ESTE: posição topo, 3 posições na horizontal (linha 1), posição fundo
     * - OESTE: posição topo, 3 posições na horizontal (linha 1), posição fundo
     *
     * @param bearing o rumo (direção) do Galeão (NORTH, SOUTH, EAST ou WEST)
     * @param pos a posição inicial (superior esquerda) do Galeão
     * @throws NullPointerException se o rumo for null
     * @throws IllegalArgumentException se o rumo não for uma direção válida
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /**
     * Retorna o tamanho do Galeão.
     *
     * @return o tamanho do Galeão, que é sempre 5
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Preenche as posições do Galeão quando orientado para o NORTE.
     * Cria um padrão em forma de T invertido:
     * - 3 posições na linha inicial (horizontal)
     * - 2 posições para baixo na coluna central
     *
     * @param pos a posição inicial (superior esquerda) do Galeão
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Preenche as posições do Galeão quando orientado para o SUL.
     * Cria um padrão em forma de T direito:
     * - 2 posições para baixo na coluna esquerda
     * - 3 posições na horizontal na linha inferior
     *
     * @param pos a posição inicial (superior esquerda) do Galeão
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Preenche as posições do Galeão quando orientado para o ESTE.
     * Cria um padrão em forma de T para a direita:
     * - 1 posição no topo
     * - 3 posições na horizontal na linha central
     * - 1 posição no fundo
     *
     * @param pos a posição inicial (superior esquerda) do Galeão
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Preenche as posições do Galeão quando orientado para o OESTE.
     * Cria um padrão em forma de T para a esquerda:
     * - 1 posição no topo
     * - 3 posições na horizontal na linha central
     * - 1 posição no fundo
     *
     * @param pos a posição inicial (superior esquerda) do Galeão
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
