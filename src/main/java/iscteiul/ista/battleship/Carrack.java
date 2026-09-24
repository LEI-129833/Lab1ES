package iscteiul.ista.battleship;

/**
 * Representa uma Nau (Carrack) no jogo Batalha Naval.
 * <p>
 * A Nau ocupa 3 posições contíguas no tabuleiro (vertical ou horizontalmente,
 * dependendo do {@link Compass} fornecido). Deriva da classe {@link Ship}.
 * </p>
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
public class Carrack extends Ship {

    /**
     * Tamanho ocupado pela Nau no tabuleiro (3 células).
     */
    private static final Integer SIZE = 3;

    /**
     * Nome identificador do tipo de navio.
     */
    private static final String NAME = "Nau";

    /**
     * Constrói uma nova instância de Nau com a orientação e posição inicial especificadas.
     * Calcula e adiciona as posições ocupadas pelo navio com base na orientação (bearing).
     *
     * @param bearing A orientação/direção para onde a Nau aponta (NORTH, SOUTH, EAST, WEST)
     * @param pos     A posição inicial de referência para colocar a Nau
     * @throws IllegalArgumentException Se a orientação {@code bearing} for inválida ou nula
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Obtém o tamanho da Nau.
     *
     * @return O número de posições ocupadas pelo navio (sempre 3).
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }
}