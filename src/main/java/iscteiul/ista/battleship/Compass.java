/**
 * Enumeração que representa os rumos (direções) possíveis de um navio no jogo.
 * Cada rumo é associado a um caractere para facilitar a entrada de dados.
 * NORTE, SUL, ESTE e OESTE são as direções válidas, e UNKNOWN representa um rumo inválido.
 *
 * @author 129803
 */
package iscteiul.ista.battleship;

/**
 * Enumeração dos rumos (direções cardeais) disponíveis no jogo de batalha naval.
 */
public enum Compass {
    /**
     * Rumo para o Norte, representado pelo caractere 'n'.
     */
    NORTH('n'),
    
    /**
     * Rumo para o Sul, representado pelo caractere 's'.
     */
    SOUTH('s'),
    
    /**
     * Rumo para o Este, representado pelo caractere 'e'.
     */
    EAST('e'),
    
    /**
     * Rumo para o Oeste, representado pelo caractere 'o'.
     */
    WEST('o'),
    
    /**
     * Rumo desconhecido ou inválido, representado pelo caractere 'u'.
     */
    UNKNOWN('u');

    private final char c;

    /**
     * Cria um rumo com o caractere de representação especificado.
     *
     * @param c o caractere que representa este rumo
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Retorna o caractere que representa este rumo.
     *
     * @return o caractere de representação do rumo
     */
    public char getDirection() {
        return c;
    }

    /**
     * Retorna a representação em texto deste rumo (o seu caractere).
     *
     * @return uma string contendo o caractere do rumo
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um caractere num rumo (Compass) correspondente.
     * Aceita 'n' (NORTE), 's' (SUL), 'e' (ESTE), 'o' (OESTE).
     * Se o caractere não corresponder a nenhum rumo válido, retorna UNKNOWN.
     *
     * @param ch o caractere a converter
     * @return o rumo correspondente ao caractere, ou UNKNOWN se inválido
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}
