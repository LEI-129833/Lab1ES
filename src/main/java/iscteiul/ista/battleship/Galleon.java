package iscteiul.ista.battleship;

/**
 * Representa um Galeão no jogo Batalha Naval.
 * <p>
 * O Galeão é um navio de grande porte que ocupa 5 posições no tabuleiro, com uma disposição geométrica específica
 * baseada na sua orientação. Deriva da classe abstrata {@link Ship}.
 * </p>
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
public class Galleon extends Ship {

    /**
     * Tamanho ocupado pelo Galeão no tabuleiro (5 células).
     */
    private static final Integer SIZE = 5;

    /**
     * Nome identificador do tipo de navio.
     */
    private static final String NAME = "Galeao";

    /**
     * Constrói uma nova instância de Galeão com a orientação e posição inicial especificadas.
     * Preenche a lista de posições ocupadas pelo navio de acordo com a sua orientação.
     *
     * @param bearing A orientação/direção do navio (ex: NORTE, SUL, ESTE, OESTE)
     * @param pos     A posição de referência (linha, coluna) onde o navio começa a ser colocado
     * @throws NullPointerException     Se a orientação fornecida for nula
     * @throws IllegalArgumentException Se a orientação fornecida for inválida
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
     * Obtém o tamanho do Galeão.
     *
     * @return O número de posições ocupadas pelo navio (sempre 5).
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Preenche a lista de posições do Galeão quando orientado para o NORTE.
     *
     * @param pos A posição de referência inicial
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Preenche a lista de posições do Galeão quando orientado para o SUL.
     *
     * @param pos A posição de referência inicial
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
     * Preenche a lista de posições do Galeão quando orientado para o ESTE.
     *
     * @param pos A posição de referência inicial
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Preenche a lista de posições do Galeão quando orientado para o OESTE.
     *
     * @param pos A posição de referência inicial
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
