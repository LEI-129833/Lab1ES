package iscteiul.ista.battleship;

/**
 * Representa uma Caravela no jogo Batalha Naval.
 * <p>
 * A Caravela ocupa 2 posições contíguas no tabuleiro (vertical ou horizontalmente,
 * dependendo do {@link Compass} fornecido). Deriva da classe {@link Ship}.
 * </p>
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
public class Caravel extends Ship {

    /**
     * Tamanho ocupado pela Caravela no tabuleiro (2 células).
     */
    private static final Integer SIZE = 2;

    /**
     * Nome identificador do tipo de navio.
     */
    private static final String NAME = "Caravela";

    /**
     * Constrói uma nova instância de Caravela com a orientação e posição inicial especificadas.
     * Calcula e adiciona as posições ocupadas pelo navio com base na orientação (bearing).
     *
     * @param bearing A orientação/direção para onde a Caravela aponta (NORTH, SOUTH, EAST, WEST)
     * @param pos     A posição inicial de referência para colocar a Caravela
     * @throws NullPointerException     Se a orientação {@code bearing} for nula
     * @throws IllegalArgumentException Se a orientação {@code bearing} for inválida ou não suportada
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }
    }

    /**
     * Obtém o tamanho da Caravela.
     *
     * @return O número de posições ocupadas pelo navio (sempre 2).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}