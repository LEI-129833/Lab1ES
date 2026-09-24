package iscteiul.ista.battleship;

/**
 * Representa os pontos cardeais e direções da rosa dos ventos para os navios no jogo Batalha Naval.
 * <p>
 * Cada direção está associada a um caráter representativo ('n', 's', 'e', 'o'/'w', 'u').
 * </p>
 *
 * @author fba
 * @version 1.0
 */
public enum Compass {

    /**
     * Direção Norte ('n').
     */
    NORTH('n'),

    /**
     * Direção Sul ('s').
     */
    SOUTH('s'),

    /**
     * Direção Este/Leste ('e').
     */
    EAST('e'),

    /**
     * Direção Oeste ('o').
     */
    WEST('o'),

    /**
     * Direção desconhecida ou inválida ('u').
     */
    UNKNOWN('u');

    /**
     * O caráter que representa a direção.
     */
    private final char c;

    /**
     * Constrói uma constante da enumeração com o caráter associado à direção.
     *
     * @param c O caráter correspondente à direção
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Obtém o caráter associado à direção da agulha/bússola.
     *
     * @return O caráter representativo da direção ('n', 's', 'e', 'o', 'u')
     */
    public char getDirection() {
        return c;
    }

    /**
     * Retorna a representação em {@link String} da direção.
     *
     * @return Uma String contendo apenas o caráter da direção
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um caráter na respetiva constante do enum {@link Compass}.
     *
     * @param ch O caráter a converter ('n', 's', 'e', 'o')
     * @return A constante {@link Compass} correspondente ao caráter, ou {@link Compass#UNKNOWN} se o caráter for inválido
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