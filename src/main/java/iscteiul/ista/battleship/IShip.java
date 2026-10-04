/**
 * Interface que define o contrato para uma embarcação no jogo de batalha naval.
 * Especifica as operações para obter informações sobre o navio, verificar ocupação
 * e posicionamento no tabuleiro, e executar disparos contra o navio.
 *
 * @author LETI-129788
 * @see Ship
 * @see IPosition
 * @see Compass
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IShip {
    /**
     * Retorna a categoria (tipo) deste navio.
     *
     * @return a categoria do navio (ex.: "Galeao", "Fragata", "Nau", "Caravela", "Barca")
     */
    String getCategory();

    /**
     * Retorna o tamanho deste navio (número de posições que ocupa).
     *
     * @return o tamanho do navio
     */
    Integer getSize();

    /**
     * Retorna a lista de todas as posições que este navio ocupa no tabuleiro.
     *
     * @return lista de posições do navio
     */
    List<IPosition> getPositions();

    /**
     * Retorna a posição inicial (superior esquerda) deste navio.
     *
     * @return a posição inicial do navio
     */
    IPosition getPosition();

    /**
     * Retorna o rumo (direção) deste navio.
     *
     * @return o rumo do navio (NORTH, SOUTH, EAST ou WEST)
     */
    Compass getBearing();

    /**
     * Verifica se este navio ainda está a flutuar (não foi totalmente afundado).
     * Um navio flutua enquanto tiver pelo menos uma posição não atingida.
     *
     * @return true se o navio está a flutuar, false se foi completamente afundado
     */
    boolean stillFloating();

    /**
     * Retorna o valor da linha mais pequena (topo) que o navio ocupa.
     *
     * @return a linha do ponto mais alto do navio
     */
    int getTopMostPos();

    /**
     * Retorna o valor da linha mais grande (fundo) que o navio ocupa.
     *
     * @return a linha do ponto mais baixo do navio
     */
    int getBottomMostPos();

    /**
     * Retorna o valor da coluna mais pequena (esquerda) que o navio ocupa.
     *
     * @return a coluna do ponto mais à esquerda do navio
     */
    int getLeftMostPos();

    /**
     * Retorna o valor da coluna mais grande (direita) que o navio ocupa.
     *
     * @return a coluna do ponto mais à direita do navio
     */
    int getRightMostPos();

    /**
     * Verifica se este navio ocupa a posição especificada.
     *
     * @param pos a posição a verificar
     * @return true se o navio ocupa essa posição, false caso contrário
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se este navio está demasiado perto de outro navio.
     * Dois navios estão demasiado perto se qualquer posição de um deles
     * é adjacente a qualquer posição do outro.
     *
     * @param other o outro navio a comparar
     * @return true se os navios estão demasiado perto, false caso contrário
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se este navio está demasiado perto de uma posição específica.
     * Um navio está demasiado perto de uma posição se qualquer uma das suas
     * posições é adjacente à posição dada.
     *
     * @param pos a posição a verificar
     * @return true se o navio está demasiado perto da posição, false caso contrário
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Marca uma posição deste navio como tendo sido atingida por um disparo.
     * Se a posição pertencer ao navio, é marcada como atingida.
     *
     * @param pos a posição que foi atingida
     */
    void shoot(IPosition pos);
}
