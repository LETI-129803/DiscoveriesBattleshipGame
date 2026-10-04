/**
 * Interface que define o contrato para uma frota no jogo de batalha naval.
 * Especifica as operações disponíveis para gerenciar navios, consultar estado da frota
 * e obter informações sobre navios específicos.
 *
 * @author NeyrivanMSilva
 * @see Fleet
 * @see IShip
 * @see IPosition
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IFleet {
    /**
     * Tamanho do tabuleiro (10x10).
     */
    Integer BOARD_SIZE = 10;
    
    /**
     * Número máximo de navios permitidos numa frota.
     */
    Integer FLEET_SIZE = 10;

    /**
     * Retorna a lista de todos os navios da frota.
     *
     * @return lista contendo todos os navios
     */
    List<IShip> getShips();

    /**
     * Tenta adicionar um navio à frota.
     * O navio é adicionado apenas se respeitar as restrições:
     * - A frota não atingiu o limite máximo de navios
     * - O navio está completamente dentro do tabuleiro
     * - O navio não colide com nenhum outro navio presente na frota
     *
     * @param s o navio a adicionar
     * @return true se o navio foi adicionado com sucesso, false caso contrário
     */
    boolean addShip(IShip s);

    /**
     * Retorna a lista de navios da frota que pertencem à categoria especificada.
     *
     * @param category a categoria de navios a filtrar (ex.: "Galeao", "Fragata")
     * @return lista de navios da categoria indicada
     */
    List<IShip> getShipsLike(String category);

    /**
     * Retorna a lista de navios da frota que ainda estão a flutuar (não foram totalmente afundados).
     *
     * @return lista de navios ainda flutuando
     */
    List<IShip> getFloatingShips();

    /**
     * Retorna o navio que ocupa a posição especificada, se houver algum.
     *
     * @param pos a posição a verificar
     * @return o navio que ocupa a posição, ou null se nenhum navio a ocupa
     */
    IShip shipAt(IPosition pos);

    /**
     * Mostra o estado completo da frota, incluindo:
     * - Todos os navios
     * - Navios ainda flutuando
     * - Navios agrupados por categoria
     */
    void printStatus();
}
