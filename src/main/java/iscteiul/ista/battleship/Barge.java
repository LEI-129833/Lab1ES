package iscteiul.ista.battleship;

/**
 * Representa uma Barca no jogo Batalha Naval.
 * <p>
 * A Barca é o menor navio do jogo, ocupando apenas 1 posição no tabuleiro.
 * Deriva da classe abstrata {@link Ship}.
 * </p>
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
public class Barge extends Ship {

    /**
     * Tamanho ocupado pela Barca no tabuleiro (1 célula).
     */
    private static final Integer SIZE = 1;

    /**
     * Nome identificador do tipo de navio.
     */
    private static final String NAME = "Barca";

    /**
     * Constrói uma nova instância de Barca com a orientação e posição inicial especificadas.
     * Adiciona automaticamente a posição inicial à lista de posições do navio.
     *
     * @param bearing A orientação/direção do navio (ex: NORTE, SUL, ESTE, OESTE)
     * @param pos     A posição de referência (linha, coluna) onde o navio é colocado
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Obtém o tamanho da Barca.
     *
     * @return O número de posições ocupadas pelo navio (sempre 1).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}