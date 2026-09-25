package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor principal da partida no jogo Batalha Naval.
 * <p>
 * Implementa a interface {@link IGame}, sendo responsável por gerir o estado do jogo,
 * processar os tiros efetuados, registar estatísticas (acertos, afundamentos, tiros repetidos e inválidos)
 * e imprimir o estado do tabuleiro.
 * </p>
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
public class Game implements IGame {

    /**
     * A frota de navios associada a esta partida.
     */
    private IFleet fleet;

    /**
     * Lista com as posições de todos os tiros válidos já efetuados.
     */
    private List<IPosition> shots;

    /**
     * Contador de tiros efetuados fora dos limites do tabuleiro.
     */
    private Integer countInvalidShots;

    /**
     * Contador de tiros efetuados em posições que já tinham sido atingidas.
     */
    private Integer countRepeatedShots;

    /**
     * Contador de tiros que atingiram com sucesso um navio.
     */
    private Integer countHits;

    /**
     * Contador de navios que foram totalmente afundados.
     */
    private Integer countSinks;

    /**
     * Constrói uma nova instância de jogo associada a uma frota.
     * Inicializa a lista de tiros efetuados e os contadores de controlo de jogadas.
     *
     * @param fleet A frota de navios que será utilizada no jogo
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * Processa o disparo de um tiro na posição especificada.
     * <p>
     * Atualiza os contadores de tiros inválidos ou repetidos caso a jogada não seja válida.
     * Se o tiro for válido e inédito, regista o disparo, verifica se atingiu algum navio da frota
     * e, caso esse navio se afunde com o tiro, retorna a sua instância.
     * </p>
     *
     * @param pos A posição do tabuleiro onde o tiro é efetuado
     * @return O navio {@link IShip} que foi afundado por este tiro, ou {@code null} se nenhum navio tiver sido afundado
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Obtém a lista de posições onde foram efetuados tiros válidos.
     *
     * @return Lista contendo as posições {@link IPosition} dos tiros disparados
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Obtém o número total de tiros repetidos disparados na partida.
     *
     * @return O número de tiros efetuados em posições já atingidas anteriormente
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Obtém o número total de tiros inválidos disparados na partida.
     *
     * @return O número de tiros efetuados fora das coordenadas do tabuleiro
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Obtém o número total de tiros que atingiram navios.
     *
     * @return O número de acertos bem-sucedidos
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Obtém o número total de navios da frota que já foram afundados.
     *
     * @return A quantidade de navios afundados até ao momento
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Obtém o número de navios da frota que ainda se encontram a flutuar.
     *
     * @return A quantidade de navios restantes em jogo
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Verifica se uma dada posição se encontra dentro dos limites válidos do tabuleiro.
     *
     * @param pos A posição a validar
     * @return {@code true} se a posição estiver dentro das dimensões do tabuleiro; {@code false} caso contrário
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Verifica se a posição especificada já recebeu um tiro anteriormente nesta partida.
     *
     * @param pos A posição a verificar
     * @return {@code true} se o tiro for repetido; {@code false} caso contrário
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Imprime na consola uma representação gráfica em texto do tabuleiro do jogo,
     * preenchendo as posições da lista dada com o caráter marcador especificado.
     *
     * @param positions Lista de posições a assinalar no tabuleiro
     * @param marker    Caráter a utilizar para marcar as posições especificadas (ex: 'X', '#')
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }

    }

    /**
     * Imprime no consola o tabuleiro mostrando a localização de todos os tiros válidos efetuados,
     * marcando-os com o caráter 'X'.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Imprime no consola o tabuleiro mostrando a disposição de todos os navios pertencentes à frota,
     * marcando as suas posições com o caráter '#'.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }

}
