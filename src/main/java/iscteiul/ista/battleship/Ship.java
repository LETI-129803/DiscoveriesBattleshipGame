/**
 * Classe abstrata que representa uma embarcação no jogo de batalha naval.
 * Cada navio conhece a sua categoria (tipo), direção (rumo) e as posições
 * que ocupa no tabuleiro. Fornece funcionalidade comum a todos os tipos de navios.
 *
 * @author LETI-129788
 * @see IShip
 * @see Barge
 * @see Caravel
 * @see Carrack
 * @see Frigate
 * @see Galleon
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Ship implements IShip {

    private static final String GALEAO = "galeao";
    private static final String FRAGATA = "fragata";
    private static final String NAU = "nau";
    private static final String CARAVELA = "caravela";
    private static final String BARCA = "barca";

    /**
     * Constrói um navio do tipo especificado com o rumo e posição dados.
     * Este método é uma fábrica que cria instâncias das subclasses apropriadas.
     *
     * @param shipKind o tipo de navio ("barca", "caravela", "nau", "fragata" ou "galeao")
     * @param bearing o rumo (direção) do navio
     * @param pos a posição inicial do navio no tabuleiro
     * @return uma instância da subclasse apropriada, ou null se o tipo for desconhecido
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }


    private String category;
    private Compass bearing;
    private IPosition pos;
    protected List<IPosition> positions;


    /**
     * Cria um navio com a categoria, rumo e posição inicial especificados.
     *
     * @param category o tipo/categoria do navio (ex.: "Galeao", "Fragata")
     * @param bearing o rumo (direção) do navio
     * @param pos a posição inicial (superior esquerda) do navio
     * @throws AssertionError se o rumo ou a posição forem null
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * Retorna a categoria (tipo) deste navio.
     *
     * @return a categoria do navio (ex.: "Galeao", "Fragata", "Nau", "Caravela", "Barca")
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Retorna a lista de todas as posições que este navio ocupa no tabuleiro.
     *
     * @return lista de posições do navio
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Retorna a posição inicial (superior esquerda) deste navio.
     *
     * @return a posição inicial do navio
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Retorna o rumo (direção) deste navio.
     *
     * @return o rumo do navio (NORTH, SOUTH, EAST ou WEST)
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Verifica se este navio ainda está a flutuar (não foi totalmente afundado).
     * Um navio flutua enquanto tiver pelo menos uma posição não atingida.
     *
     * @return true se o navio está a flutuar, false se foi completamente afundado
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Retorna o valor da linha mais pequena (topo) que o navio ocupa.
     *
     * @return a linha do ponto mais alto do navio
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Retorna o valor da linha mais grande (fundo) que o navio ocupa.
     *
     * @return a linha do ponto mais baixo do navio
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * Retorna o valor da coluna mais pequena (esquerda) que o navio ocupa.
     *
     * @return a coluna do ponto mais à esquerda do navio
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * Retorna o valor da coluna mais grande (direita) que o navio ocupa.
     *
     * @return a coluna do ponto mais à direita do navio
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Verifica se este navio ocupa a posição especificada.
     *
     * @param pos a posição a verificar
     * @return true se o navio ocupa essa posição, false caso contrário
     * @throws AssertionError se a posição for null
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Verifica se este navio está demasiado perto de outro navio.
     * Dois navios estão demasiado perto se qualquer posição de um deles
     * é adjacente a qualquer posição do outro.
     *
     * @param other o outro navio a comparar
     * @return true se os navios estão demasiado perto, false caso contrário
     * @throws AssertionError se o outro navio for null
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Verifica se este navio está demasiado perto de uma posição específica.
     * Um navio está demasiado perto de uma posição se qualquer uma das suas
     * posições é adjacente à posição dada.
     *
     * @param pos a posição a verificar
     * @return true se o navio está demasiado perto da posição, false caso contrário
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }

    /**
     * Marca uma posição deste navio como tendo sido atingida por um disparo.
     * Se a posição pertencer ao navio, é marcada como atingida.
     *
     * @param pos a posição que foi atingida
     * @throws AssertionError se a posição for null
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Retorna uma representação em texto deste navio.
     *
     * @return string no formato "[categoria rumo posição]"
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
