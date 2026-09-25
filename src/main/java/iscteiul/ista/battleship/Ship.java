/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe abstrata que representa as características e comportamentos comuns
 * aos diferentes tipos de navio do jogo Batalha Naval.
 */
public abstract class Ship implements IShip {

    /** Identificador usado para criar um Galeão. */
    private static final String GALEAO = "galeao";

    /** Identificador usado para criar uma Fragata. */
    private static final String FRAGATA = "fragata";

    /** Identificador usado para criar uma Nau. */
    private static final String NAU = "nau";

    /** Identificador usado para criar uma Caravela. */
    private static final String CARAVELA = "caravela";

    /** Identificador usado para criar uma Barca. */
    private static final String BARCA = "barca";

    /**
     * Cria um navio do tipo indicado, utilizando a orientação e posição fornecidas.
     *
     * @param shipKind tipo de navio a criar
     * @param bearing orientação do navio
     * @param pos posição de referência do navio
     * @return o navio criado ou null se o tipo não for reconhecido
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }


    /** Categoria do navio. */
    private String category;

    /** Orientação do navio. */
    private Compass bearing;

    /** Posição de referência utilizada na criação do navio. */
    private IPosition pos;

    /** Lista das posições ocupadas pelo navio no tabuleiro. */
    protected List<IPosition> positions;


    /**
     * Constrói um navio com a categoria, orientação e posição indicadas.
     *
     * @param category categoria do navio
     * @param bearing orientação do navio
     * @param pos posição de referência do navio
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * Obtém a categoria do navio.
     *
     * @return a categoria do navio
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Obtém a lista das posições ocupadas pelo navio.
     *
     * @return lista das posições ocupadas
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Obtém a posição de referência do navio.
     *
     * @return a posição de referência
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Obtém a orientação do navio.
     *
     * @return a orientação do navio
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Verifica se o navio ainda está a flutuar.
     * Basta existir uma posição do navio que ainda não tenha sido atingida.
     *
     * @return true se o navio ainda estiver a flutuar; false se estiver afundado
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Determina a linha mais acima ocupada pelo navio.
     *
     * @return índice da linha superior
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Determina a linha mais abaixo ocupada pelo navio.
     *
     * @return índice da linha inferior
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * Determina a coluna mais à esquerda ocupada pelo navio.
     *
     * @return índice da coluna mais à esquerda
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * Determina a coluna mais à direita ocupada pelo navio.
     *
     * @return índice da coluna mais à direita
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Verifica se o navio ocupa uma determinada posição.
     *
     * @param pos posição a procurar
     * @return true se a posição fizer parte do navio; false caso contrário
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Verifica se outro navio está demasiado próximo deste.
     * Para isso, testa individualmente cada posição ocupada pelo outro navio.
     *
     * @param other navio a verificar
     * @return true se existir proximidade entre os navios; false caso contrário
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Verifica se uma posição está demasiado próxima de alguma das posições do navio.
     *
     * @param pos posição a verificar
     * @return true se a posição for adjacente a uma posição do navio; false caso contrário
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }


    /**
     * Regista um tiro na posição indicada, caso essa posição pertença ao navio.
     *
     * @param pos posição atingida pelo tiro
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }


    /**
     * Devolve uma representação textual do navio.
     *
     * @return texto com a categoria, orientação e posição de referência
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
