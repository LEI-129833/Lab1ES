package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface que define o contrato para a gestão e controlo de uma partida no jogo Batalha Naval.
 * <p>
 * Disponibiliza métodos para processar disparos, obter estatísticas do jogo
 * (tiros efetuados, acertos, afundamentos e jogadas inválidas) e visualizar o estado do tabuleiro.
 * </p>
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
public interface IGame {

    /**
     * Processa a jogada de um disparo na posição especificada.
     *
     * @param pos A posição {@link IPosition} do tabuleiro onde o tiro é efetuado
     * @return O navio {@link IShip} que foi afundado por este tiro, ou {@code null} se nenhum navio tiver sido afundado
     */
    IShip fire(IPosition pos);

    /**
     * Obtém a lista de posições onde foram efetuados tiros válidos durante a partida.
     *
     * @return Lista contendo as posições {@link IPosition} dos tiros efetuados
     */
    List<IPosition> getShots();

    /**
     * Obtém o número total de tiros repetidos disparados na partida.
     *
     * @return O número de tiros efetuados em posições previamente atingidas
     */
    int getRepeatedShots();

    /**
     * Obtém o número total de tiros inválidos disparados na partida.
     *
     * @return O número de tiros efetuados fora das coordenadas válidas do tabuleiro
     */
    int getInvalidShots();

    /**
     * Obtém o número total de tiros que atingiram navios com sucesso.
     *
     * @return O número de acertos registados
     */
    int getHits();

    /**
     * Obtém o número de navios pertencentes à frota que já foram totalmente afundados.
     *
     * @return A quantidade de navios afundados
     */
    int getSunkShips();

    /**
     * Obtém o número de navios pertencentes à frota que ainda se encontram a flutuar.
     *
     * @return A quantidade de navios restantes em jogo
     */
    int getRemainingShips();

    /**
     * Imprime na consola a representação do tabuleiro mostrando a localização de todos os tiros válidos efetuados.
     */
    void printValidShots();

    /**
     * Imprime na consola a representação do tabuleiro mostrando a disposição de todos os navios pertencentes à frota.
     */
    void printFleet();
}
