/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Define as operações comuns a todos os navios do jogo Batalha Naval.
 */
public interface IShip {

    /**
     * Obtém a categoria do navio.
     *
     * @return a categoria do navio
     */
    String getCategory();

    /**
     * Obtém o tamanho do navio.
     *
     * @return o número de posições ocupadas pelo navio
     */
    Integer getSize();

    /**
     * Obtém as posições ocupadas pelo navio.
     *
     * @return lista das posições ocupadas
     */
    List<IPosition> getPositions();

    /**
     * Obtém a posição de referência do navio.
     *
     * @return a posição de referência
     */
    IPosition getPosition();

    /**
     * Obtém a orientação do navio.
     *
     * @return a orientação do navio
     */
    Compass getBearing();

    /**
     * Verifica se o navio ainda está a flutuar.
     *
     * @return true se existir pelo menos uma posição não atingida; false caso contrário
     */
    boolean stillFloating();

    /**
     * Obtém a linha mais acima ocupada pelo navio.
     *
     * @return índice da linha superior
     */
    int getTopMostPos();

    /**
     * Obtém a linha mais abaixo ocupada pelo navio.
     *
     * @return índice da linha inferior
     */
    int getBottomMostPos();

    /**
     * Obtém a coluna mais à esquerda ocupada pelo navio.
     *
     * @return índice da coluna mais à esquerda
     */
    int getLeftMostPos();

    /**
     * Obtém a coluna mais à direita ocupada pelo navio.
     *
     * @return índice da coluna mais à direita
     */
    int getRightMostPos();

    /**
     * Verifica se o navio ocupa uma determinada posição.
     *
     * @param pos posição a verificar
     * @return true se o navio ocupar a posição; false caso contrário
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se outro navio está demasiado próximo deste.
     *
     * @param other navio a verificar
     * @return true se os navios estiverem demasiado próximos; false caso contrário
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se uma posição está demasiado próxima deste navio.
     *
     * @param pos posição a verificar
     * @return true se a posição estiver demasiado próxima; false caso contrário
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Regista um tiro numa determinada posição do navio.
     *
     * @param pos posição atingida pelo tiro
     */
    void shoot(IPosition pos);
}
