package hand_card;

import base_card.*;

public class cardFactory {
    public static base_hand_cards_Data getcard(int type) {
        switch (type) {
            case 1:
                return new Rabbi_common_attact();
            case 2:
                return new Bowman_common_attact();
            case 3:
                return new Fighter_common_attact();
            case 4:
                return new Shield_common_attact();
            case 5:
                return new Assassin_common_attact();
            case 6:
                return new composed_1_test();
            case 7:
                return new composed_2_test();
            default:
                return null;
        }
    }
}
