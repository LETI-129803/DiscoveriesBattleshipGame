/**
 * Representa a frota de um jogador no jogo de batalha naval.
 * Gerencia a coleção de navios, permitindo adicionar navios, consultar
 * navios por categoria, verificar navios ainda flutuando e obter informações
 * sobre a disposição da frota no tabuleiro.
 *
 * @author LETI-129803
 * @see IFleet
 * @see Ship
 * @see IShip
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

public class Fleet implements IFleet {
    /**
     * Imprime todos os navios da lista fornecida na consola.
     *
     * @param ships a lista de navios a imprimir
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    private List<IShip> ships;

    /**
     * Cria uma frota vazia.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Retorna a lista de todos os navios da frota.
     *
     * @return a lista de navios
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Tenta adicionar um navio à frota.
     * O navio é adicionado apenas se:
     * - A frota não atingiu o limite máximo de navios
     * - O navio está completamente dentro do tabuleiro
     * - O navio não colide com nenhum outro navio (não está demasiado perto)
     *
     * @param s o navio a adicionar
     * @return true se o navio foi adicionado com sucesso, false caso contrário
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Retorna a lista de navios da frota que pertencem à categoria especificada.
     *
     * @param category a categoria de navios a filtrar
     * @return lista de navios da categoria indicada
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Retorna a lista de navios da frota que ainda estão a flutuar (não foram totalmente afundados).
     *
     * @return lista de navios ainda flutuando
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Retorna o navio que ocupa a posição especificada, se houver algum.
     *
     * @param pos a posição a verificar
     * @return o navio que ocupa a posição, ou null se nenhum navio a ocupa
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Verifica se um navio está completamente dentro dos limites do tabuleiro.
     *
     * @param s o navio a verificar
     * @return true se o navio está completamente dentro do tabuleiro, false caso contrário
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Verifica se um navio entra em colisão (está demasiado perto) de algum navio já presente na frota.
     *
     * @param s o navio a verificar
     * @return true se existe risco de colisão, false caso contrário
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }


    /**
     * Mostra o estado completo da frota.
     * Imprime todos os navios, navios ainda flutuando e navios agrupados por categoria.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Imprime todos os navios da frota que pertencem à categoria especificada.
     *
     * @param category a categoria de navios a imprimir
     * @throws AssertionError se a categoria for null
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * Imprime todos os navios da frota que ainda estão a flutuar (não foram totalmente afundados).
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Imprime todos os navios da frota.
     */
    void printAllShips() {
        printShips(ships);
    }

}
