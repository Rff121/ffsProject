package base_card;
/*具体角色初始属性*/
public class assassin_A extends base_role_data {
    private int type=5;//职业类型编号
    private int base_owner=5;//单个角色编号
    private double baseHP=120;//角色初始血量
    private int baseDebuff=0;//角色初始状态
    private double baseSpeed=2;//角色初始速度
    private double baseDefine=0.1;//角色初始防御
    private double baseAccuracy=1;//角色初始精度
    private double baseDamage=50;//角色初始攻击力
    private double baseDistance=10;//角色初始射程
    private double baseSneer=0.5;//角色初始嘲讽值
    private int cards[]={5,5,5,5};

    public assassin_A(){}

    public int get_type()
    {
        return type;
    }
    public int getBase_owner(){return base_owner;}
    public double getBaseHP(){return baseHP;}
    public int getBaseDebuff(){return baseDebuff;}
    public double getBaseSpeed(){return baseSpeed;}
    public double getBaseDefine(){return baseDefine;}
    public double getBaseAccuracy(){return baseAccuracy;}
    public double getBaseDamage(){return baseDamage;}
    public double getBaseDistance(){return baseDistance;}
    public double getBaseSneer(){return baseSneer;}
    public int[] getCards(){return cards;}
}
