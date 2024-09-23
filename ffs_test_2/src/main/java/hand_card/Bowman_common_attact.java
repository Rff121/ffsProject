package hand_card;

public class Bowman_common_attact extends base_hand_cards_Data{
    private int belong_type=0;
    private int limit_role=-1;
    private int identifier=0;
    public Bowman_common_attact()
    {
        super();
        this.belong_type=2;
        this.limit_role=-1;
        this.identifier=2;
    }

    /*————————————————————————卡牌属性————————————————————————*/
    /*___________基础判定属性___________*/
    private double distance=0;//射程
    private double range=0;//范围
    /*___________基础判定属性___________*/

    /*___________对目标状态修改___________*/
    private double damage=0;//对目标伤害
    private double percentageDamage=0;//对目标百分比伤害(百分比)
    private double cure=0;//对目标治疗
    private double percentageCure=0;//对目标百分比治疗(百分比)
    private int makeStateToObject=0;//是否对目标造成眩晕

    private double changingDistance=0;//修改目标射程(百分比）
    private double changingdamage=0;//修改目标伤害(百分比)
    private double changingDefine=0;//修改目标防御(百分比)
    private double changingAccuracy=0;//修改目标精度(百分比)
    private double changingSpeed=0;//修改目标速度(百分比)
    private double changingSneer=0;//修改目标嘲讽值(百分比)
    /*___________对目标状态修改___________*/

    /*___________对使用者状态修改___________*/
    private int makeStateToUser=0;//对使用者是否造成眩晕
    private double damageToUser=0;//对使用者造成伤害
    private double percentageDamageToUser=0;//对使用者造成百分比伤害(百分比)
    private double cureToUser=0;//对使用者使用治疗
    private double percentageCureToUser=0;//对使用者使用百分比治疗(百分比)

    private double changingDistanceToUser=0;//修改使用者射程(百分比)
    private double changingdamageToUser=0;//修改使用者伤害(百分比)
    private double changingDefineToUser=0;//修改使用者防御(百分比)
    private double changingAccuracyToUser=0;//修改使用者精度(百分比)
    private double changingSpeedToSpeed=0;//修改使用者速度(百分比)
    private double changingSneerToUser=0;//修改使用者嘲讽值(百分比)
    /*___________对使用者状态修改___________*/

    /*————————————————————————卡牌属性————————————————————————*/

    public int getBelong_type(){return this.belong_type;}
    public int getLimit_role(){return this.limit_role;}
    public int getIdentifier(){return this.identifier;}
}
