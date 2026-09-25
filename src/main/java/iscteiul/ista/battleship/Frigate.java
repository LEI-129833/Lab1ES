package iscteiul.ista.battleship;

/**
 * Representa uma Fragata no jogo Batalha Naval.
 * <p>
 * A Fragata é um navio de porte médio, ocupando 4 posições no tabuleiro no sentido vertical ou horizontal.
 * Deriva da classe abstrata {@link Ship}.
 * </p>
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
public class Frigate extends Ship {

    /**
     * Tamanho ocupado pela Fragata no tabuleiro (4 células).
     */
    private static final Integer SIZE = 4;

    /**
     * Nome identificador do tipo de navio.
     */
    private static final String NAME = "Fragata";

    /**
     * Constrói uma nova instância de Fragata com a orientação e posição inicial especificadas.
     * Calcula e adiciona automaticamente as 4 posições ocupadas pelo navio a partir do ponto de referência.
     *
     * @param bearing A orientação/direção do navio (ex: NORTE, SUL, ESTE, OESTE)
     * @param pos     A posição de referência (linha, coluna) onde o navio começa a ser colocado
     * @throws IllegalArgumentException Se a orientação fornecida for inválida para a colocação do navio
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /**
     * Obtém o tamanho da Fragata.
     *
     * @return O número de posições ocupadas pelo navio (sempre 4).
     */
    public Integer getSize() {
        return Frigate.SIZE;
    }

}
