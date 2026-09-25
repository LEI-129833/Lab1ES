/**
 *
 */
package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Representa uma posição do tabuleiro através de uma linha e de uma coluna.
 * Guarda também informação sobre a ocupação e sobre a ocorrência de um tiro.
 */
public class Position implements IPosition {

    /** Linha da posição no tabuleiro. */
    private int row;

    /** Coluna da posição no tabuleiro. */
    private int column;

    /** Indica se a posição se encontra ocupada. */
    private boolean isOccupied;

    /** Indica se a posição já foi atingida por um tiro. */
    private boolean isHit;

    /**
     * Cria uma posição com a linha e coluna indicadas.
     * Inicialmente, a posição não está ocupada nem atingida.
     *
     * @param row linha da posição
     * @param column coluna da posição
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * Obtém a linha desta posição.
     *
     * @return o número da linha
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Obtém a coluna desta posição.
     *
     * @return o número da coluna
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Calcula o código hash desta posição.
     *
     * @return o código hash da posição
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compara esta posição com outra através das respetivas coordenadas.
     *
     * @param otherPosition objeto a comparar
     * @return true se ambos representarem a mesma linha e coluna; false caso contrário
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Verifica se outra posição está na mesma posição ou numa das posições vizinhas,
     * considerando diferenças máximas de uma linha e uma coluna.
     *
     * @param other posição a verificar
     * @return true se a posição for considerada adjacente; false caso contrário
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * Marca esta posição como ocupada.
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * Marca esta posição como atingida.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * Indica se esta posição está ocupada.
     *
     * @return true se estiver ocupada; false caso contrário
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Indica se esta posição já foi atingida.
     *
     * @return true se tiver sido atingida; false caso contrário
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Devolve uma representação textual das coordenadas da posição.
     *
     * @return texto com a linha e a coluna
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }

}
