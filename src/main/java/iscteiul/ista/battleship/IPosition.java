/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Define as operações que uma posição do tabuleiro deve disponibilizar.
 *
 * @author fba
 */
public interface IPosition {

    /**
     * Obtém a linha da posição.
     *
     * @return o número da linha
     */
    int getRow();

    /**
     * Obtém a coluna da posição.
     *
     * @return o número da coluna
     */
    int getColumn();

    /**
     * Compara esta posição com outro objeto.
     *
     * @param other objeto a comparar
     * @return true se representar a mesma posição; false caso contrário
     */
    boolean equals(Object other);

    /**
     * Verifica se outra posição é adjacente a esta.
     *
     * @param other posição a verificar
     * @return true se a posição for adjacente; false caso contrário
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marca esta posição como ocupada.
     */
    void occupy();

    /**
     * Marca esta posição como atingida por um tiro.
     */
    void shoot();

    /**
     * Indica se esta posição está ocupada.
     *
     * @return true se estiver ocupada; false caso contrário
     */
    boolean isOccupied();

    /**
     * Indica se esta posição já foi atingida.
     *
     * @return true se já tiver sido atingida; false caso contrário
     */
    boolean isHit();
}
