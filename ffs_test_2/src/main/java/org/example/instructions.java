package org.example;

public class instructions {
    private fighting_man my_side;
    private fighting_man op_side;
    private fighting_hand_cards card;
    public instructions(fighting_man my_side,fighting_man op_side,fighting_hand_cards card)
    {
        this.my_side=my_side;
        this.op_side=op_side;
        this.card=card;
    }
    public fighting_man getMy_side(){return my_side;}
    public fighting_man getOp_side(){return op_side;}
    public fighting_hand_cards getCard(){return card;}
}
