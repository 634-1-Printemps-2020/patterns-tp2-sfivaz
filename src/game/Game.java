package game;

import materials.Coin;
import materials.CoinState;
import player.Player;
import utils.Statistics;

import java.util.*;

public class Game {

    private Rules rules;
    private Coin coin;
    private Map<Player, List<CoinState>> history;

    public Game() {
        history = new HashMap<>();
    }

    /**
     * Ajouter un nouveau joueur au jeu
     *
     * @param player le nouveau joueur
     */
    public void addPlayer(Player player) {
        history.put(player, new ArrayList<>());
    }

    /**
     * Faire joueur tous les joueurs et stocker chaque partie dans history
     */
    public void play() {
        for (Player player : history.keySet())
            play(player);
    }

    private void play(Player player) {
        Rules rules = Rules.getRules();
        while (!rules.checkWin(history.get(player)))
            playOnce(player);
    }

    private void playOnce(Player player) {
        Coin coin = Coin.getCoin();
        player.play(coin);
        CoinState coinState = coin.getState();
        history.get(player).add(coinState);
    }

    /**
     * Calculer des statistiques de la partie précédente
     *
     * @return Statistics
     */
    public Statistics getStatistics() {
        int averageToWin,
                fewerMovesToWin = Integer.MAX_VALUE,
                mostMovesToWin = 0,
                totalNumberMoves = 0,
                counter = 0;

        for (Player player : history.keySet()) {
            List<CoinState> coinStates = history.get(player);
            int nbOfMoves = coinStates.size();
            if (nbOfMoves > mostMovesToWin)
                mostMovesToWin = nbOfMoves;
            if (nbOfMoves < fewerMovesToWin)
                fewerMovesToWin = nbOfMoves;
            totalNumberMoves += nbOfMoves;
            counter++;
        }
        averageToWin = totalNumberMoves / counter;
        return new Statistics(averageToWin, fewerMovesToWin, mostMovesToWin, totalNumberMoves);
    }

    /**
     * Obtenir l'historique de tous les joueurs de la partie précédente
     *
     * @return Map contenant chaque joueur et la liste des ses lancers
     */
    public Map<Player, List<CoinState>> getHistory() {
        return history;
    }


    /**
     * Obtenir l'historique d'un joueur spécifique
     *
     * @param player instance du joueur pour lequel on veut avoir l'historique
     * @return la liste des lancers d'un joueur
     */
    public List<CoinState> getSpecificHistory(Player player) {
        return history.get(player);
    }

}
