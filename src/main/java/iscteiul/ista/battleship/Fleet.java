package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a frota de navios no jogo Batalha Naval.
 * <p>
 * Gere o conjunto de navios presentes no jogo, garantindo as regras de validação
 * de posicionamento (limites do tabuleiro e colisão/proximidade entre navios).
 * Implementa a interface {@link IFleet}.
 * </p>
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
public class Fleet implements IFleet {

    /**
     * Imprime no consola a lista de navios fornecida.
     *
     * @param ships A lista de navios ({@link IShip}) a imprimir
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    /**
     * Lista de navios pertencentes à frota.
     */
    private List<IShip> ships;

    /**
     * Constrói uma nova frota vazia.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Obtém a lista completa de navios que constituem a frota.
     *
     * @return Lista contendo os navios ({@link IShip}) da frota
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Adiciona um navio à frota caso este cumpra todas as regras do jogo:
     * não exceder o limite de navios da frota, estar totalmente dentro do tabuleiro
     * e não estar em risco de colisão/proximidade com navios já existentes.
     *
     * @param s O navio ({@link IShip}) a adicionar à frota
     * @return {@code true} se o navio foi adicionado com sucesso; {@code false} caso contrário
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Retorna uma lista com todos os navios da frota pertencentes a uma determinada categoria.
     *
     * @param category A categoria/tipo de navio pretendido (ex: "Barca", "Caravela")
     * @return Lista contendo apenas os navios correspondentes à categoria fornecida
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Retorna uma lista contendo apenas os navios da frota que ainda se encontram a flutuar (não afundados).
     *
     * @return Lista de navios ({@link IShip}) ainda a flutuar
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Procura e retorna o navio que ocupa a posição especificada no tabuleiro.
     *
     * @param pos A posição ({@link IPosition}) a verificar no tabuleiro
     * @return O navio ({@link IShip}) presente na posição dada, ou {@code null} se a posição estiver livre
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Verifica se o navio fornecido está totalmente contido dentro dos limites do tabuleiro.
     *
     * @param s O navio a validar
     * @return {@code true} se as extremidades do navio estiverem dentro do tabuleiro; {@code false} caso contrário
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Verifica se a colocação do navio fornecido colide ou fica demasiado próximo de algum navio já existente na frota.
     *
     * @param s O navio a testar
     * @return {@code true} se existir risco de colisão/proximidade com algum navio; {@code false} caso contrário
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }

    /**
     * Exibe no consola o estado atual da frota, imprimindo todos os navios,
     * os que ainda flutuam e a listagem por cada uma das categorias.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Imprime no consola todos os navios da frota pertencentes a uma categoria específica.
     *
     * @param category A categoria dos navios a imprimir
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * Imprime no consola todos os navios da frota que ainda se encontram a flutuar.
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Imprime no consola a totalidade dos navios pertencentes à frota.
     */
    void printAllShips() {
        printShips(ships);
    }
}