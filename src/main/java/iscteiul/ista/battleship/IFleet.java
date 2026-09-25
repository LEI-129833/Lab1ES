package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface que define o contrato para a gestão de uma frota de navios no jogo Batalha Naval.
 * <p>
 * Uma frota agrupa o conjunto de navios em jogo, disponibilizando métodos para adicionar navios,
 * consultar o estado das embarcações, validar posições e obter estatísticas sobre a frota.
 * </p>
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
public interface IFleet {

    /**
     * Dimensão (largura e altura) do tabuleiro de jogo.
     */
    Integer BOARD_SIZE = 10;

    /**
     * Número máximo/total de navios que constituem uma frota completa.
     */
    Integer FLEET_SIZE = 10;

    /**
     * Obtém a lista de todos os navios pertencentes à frota.
     *
     * @return Lista contendo todas as instâncias de {@link IShip} da frota
     */
    List<IShip> getShips();

    /**
     * Tenta adicionar um novo navio à frota.
     *
     * @param s O navio {@link IShip} a ser adicionado
     * @return {@code true} se o navio foi adicionado com sucesso; {@code false} caso contrário (ex: posição inválida ou frota cheia)
     */
    boolean addShip(IShip s);

    /**
     * Obtém uma lista de navios pertencentes a uma determinada categoria/tipo.
     *
     * @param category O nome da categoria/tipo de navio a procurar (ex: "Fragata", "Galeao")
     * @return Lista contendo apenas os navios {@link IShip} da categoria especificada
     */
    List<IShip> getShipsLike(String category);

    /**
     * Obtém a lista de navios da frota que ainda se encontram a flutuar (que não foram afundados).
     *
     * @return Lista de navios {@link IShip} ainda em jogo
     */
    List<IShip> getFloatingShips();

    /**
     * Localiza o navio que ocupa a posição especificada no tabuleiro.
     *
     * @param pos A posição {@link IPosition} a verificar
     * @return O navio {@link IShip} presente na posição especificada, ou {@code null} se a posição estiver vaga
     */
    IShip shipAt(IPosition pos);

    /**
     * Imprime na consola o estado atual de cada navio pertencente à frota.
     */
    void printStatus();
}
