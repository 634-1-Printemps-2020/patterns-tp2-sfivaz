package game;

import materials.CoinState;

import java.util.List;

public class Rules {

    private static Rules instance;

    private Rules() {
    }

    public static Rules getRules() {
        if (instance == null)
            instance = new Rules();
        return instance;
    }

    /**
     * Cette méthode permet de déterminer si une suite d'états de pièce permet de gagner à une partie
     *
     * @param states liste d'états pour un joueur
     * @return true si un joueur a gagné, false sinon
     */
    public boolean checkWin(List<CoinState> states) {
        int nbOfHeads = 0;
        for (CoinState state : states)
            if (state == CoinState.HEADS) {
                nbOfHeads++;
                if (nbOfHeads > 2)
                    return true;
            }
        return false;
    }
}
