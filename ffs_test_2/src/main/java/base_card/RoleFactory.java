package base_card;

public class RoleFactory {
    public static base_role_data getData(int type) {
        switch (type) {
            case 1:
                return new assassin_A();
            case 2:
                return new Bowman_A();
            case 3:
                return new Fighter_A();
            case 4:
                return new Rabbi_A();
            case 5:
                return new Shield_A();
            default:
                return null;
        }
    }
}
