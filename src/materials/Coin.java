package materials;

import java.util.Random;

public class Coin {

    private CoinState coinState;
    private static Coin instance = null;

    public static Coin getCoin() {
        if (instance == null)
            instance = new Coin();
        return instance;
    }

    private Coin() {
    }

    /**
     * Change l'état de la pièce.
     * 50% de probabilité d'obtenir HEADS, 50% de probabilité d'obtenir TAILS
     */
    public void throwCoin() {
        // TODO : Votre code ici
        if (new Random().nextBoolean())
            coinState = CoinState.HEADS;
        else
            coinState = CoinState.TAILS;
    }

    public CoinState getState() {
        return coinState;
    }


}
