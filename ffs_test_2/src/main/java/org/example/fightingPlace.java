package org.example;

import javax.swing.*;
import java.util.*;

public class fightingPlace {
    Window mainFightWindow=null;
    private int red_num;
    private int[] red_side=new int[5];
    private int blue_num;
    private int[] blue_side=new int[5];
    private fighting_man[] red_man=new fighting_man[5];
    private fighting_man[] blue_man=new fighting_man[5];
    private boolean isAliveRed[]=new boolean[5];
    private boolean isAliveBlue[]=new boolean[5];
    boolean is_finished=false;
    int redCardsPools[]=new int[100];
    int redCardsPoolsSize=0;
    int blueCardsPools[]=new int[100];
    int blueCardsPoolsSize=0;
    private int now_state=0;//0代表等待操作，1代表合成操作，2代表选择目标操作，3代表选择卡牌操作,4代表选择目标使用

    /*_________界面功能按键_________*/
    Button compose=new Button(580,600,120,70,"合成","/imagin/composeTest1.png","/imagin/composingTest1.png");
    Button myActionClose=new Button(800,600,120,70,"结束回合", "/imagin/role_small_card/3.png", "/imagin/role_small_card/5.png");

    /*_________界面功能按键_________*/
    Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    };

    int pools[]=new int[100];
    int Idx=0;
    fighting_hand_cards nowCardsPools[]=new fighting_hand_cards[10];
    int nowCardsPoolsSize=0;//最大为8
    boolean hasCard[]=new boolean[10];
    boolean readyCard[]=new boolean[4];
    fighting_hand_cards ready_CardsPool[]=new fighting_hand_cards[4];
    private int now_operater=-1;
    private int now_cards=-1;
    private int now_object=-1;
    private int now_side=-1;
    List<Integer> composeQueue=new ArrayList<>();
    HashSet<List<Integer>> allCompose =new HashSet<List<Integer>>();
    Map<List<Integer>, Integer> composeIdx = new HashMap<>();
    List<Integer> allComposeIdx=new ArrayList<>();

    instructions Instruction[];

    /*_________逻辑功能设置_________*/


    /*_________逻辑功能设置_________*/

    public void fighting_unit()
    {
        for(int i=1;i<=red_num;i++)
        {
            isAliveRed[i]=true;
            System.out.println(i);
            red_man[i]=new fighting_man(1070+(i-1)*170,150,150,100,String.format("red %d",i-1),1,red_side[i],i,3,String.format("/imagin/role_small_card/%d.png",red_side[i]),String.format("/imagin/role_small_card/%d.png",red_side[i]));
            mainFightWindow.insert_Button(red_man[i].Get_Button(),1070+(i-1)*170,150,150+12,100);
        }
        for(int i=1;i<=blue_num;i++)
        {
            isAliveBlue[i]=true;
            System.out.println(i);
            blue_man[i]=new fighting_man(1070+(i-1)*170,590,150,100,String.format("blue %d",i-1),2,blue_side[i],i,0,String.format("/imagin/role_small_card/%d.png",blue_side[i]),String.format("/imagin/role_small_card/%d.png",blue_side[i]));
            mainFightWindow.insert_Button(blue_man[i].Get_Button(),1070+(i-1)*170,590+150,150+12,100);
        }
        createCardpool();//创建卡池
        //System.out.printf("________________________");
        DrawCards(5);//初始化手牌

    }

    public fightingPlace(int red_num,int[] red_side,int blue_num,int[] blue_side)
    {
        this.red_num=red_num;this.blue_num=blue_num;
        this.red_side=red_side.clone();
        this.blue_side=blue_side.clone();
        mainFightWindow=new Window(1800, 1000,0);
        fighting_unit();
        /*_________界面功能按键_________*/
        mainFightWindow.insert_Button(compose.Get_Button(),580,600,120,70);
        mainFightWindow.insert_Button(myActionClose.Get_Button(),800,600,120,70);
        /*_________界面功能按键_________*/
        mainFightWindow.draw_fight_back_ground();
        /*_________合成配方导入_________*/
        composeUnit();
        mainFightWindow.show();
        /*_________合成配方导入_________*/
        start();

    }
    /*_________具体交互逻辑_________*/
    public int start()
    {
        int win=-1;
        change_to_0();
        while(true)
        {
            if(is_finished)break;
            System.out.printf("Running_________%d\n",now_state);
            switch (now_state)
            {
                case 0:
                    check();
                    break;
                case 1:
                    compose.flashClick();
                    change_to_compose();
                    while(now_state==1)
                    {
                        compose_check();
                    }
                    //change_to_0();
                    break;
                case 2:
                    change_to_0();
                    break;
                case 3:
                    while(now_state==3)
                    {
                        choose_cards_check();
                    }
                    //change_to_0();
                    break;
                case 4:
                    while(now_state==4)
                    {
                        choose_object_check();
                    }
                    //change_to_0();
                    break;
                case 5:
                    is_finished=true;
                    System.out.printf("回合结束");
                    break;
            }




        }
        return win;
    }
    /*_________具体交互逻辑_________*/

    public void createCardpool()
    {
        for(int i=1;i<=red_num;i++)
        {
            for(int x:red_man[i].getCards())
            {
                System.out.println(x);
                redCardsPools[++redCardsPoolsSize]=x;
            }
        }
        for(int i=1;i<=blue_num;i++)
        {
            for(int x:blue_man[i].getCards())
            {
                System.out.println(x);
                blueCardsPools[++blueCardsPoolsSize]=x;
            }
        }
        shuffleArray(redCardsPools,redCardsPoolsSize);
        shuffleArray(blueCardsPools,blueCardsPoolsSize);
        pools=blueCardsPools.clone();
        shuffleArray(pools,blueCardsPoolsSize);
    }

    public void DrawCards(int x)
    {
        while(x>0&&nowCardsPoolsSize<8)
        {
            if(Idx>=blueCardsPoolsSize)
            {
                pools=blueCardsPools.clone();
                shuffleArray(pools,blueCardsPoolsSize);
                Idx=0;
            }
            if(nowCardsPoolsSize<8)
            {
                for(int i=1;i<=8;i++)
                {
                    if(!hasCard[i])
                    {
                        hasCard[i]=true;
                        x--;
                        nowCardsPools[i]=new fighting_hand_cards(7+(i-1)*(125), 730, 125, 200,1,pools[++Idx],"1",String.format("/imagin/fight_card/%d.png",pools[Idx]),String.format("/imagin/fight_card/4.png"));
                        mainFightWindow.insert_Button(nowCardsPools[i].Get_Button(),7+(i-1)*(125), 730, 125, 200);
                        nowCardsPoolsSize++;

                        break;
                    }
                }
            }

        }
    }


    public void shuffleArray(int[] array,int size) {//随机打乱数组(卡池程序)
        Random random = new Random();
        for (int i =size; i >=1; i--) {
            int index = random.nextInt(i)+1; // 随机选择一个数作为交换位置
            // 交换当前位置和随机位置的元素
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }

    public boolean checkFinishRed()//判断红是否结束，蓝赢
    {
        boolean flag=true;
        for(int i=1;i<=red_num;i++)if(isAliveRed[i]==true)flag=false;
        return flag;
    }

    public boolean checkFinishBlue()//判断蓝是否结束，红赢
    {
        boolean flag=true;
        for(int i=1;i<=blue_num;i++)if(isAliveBlue[i]==true)flag=false;
        return flag;
    }



    public void show()
    {
        mainFightWindow.show();
    }

    /*__________具体逻辑检测函数__________*/
    void check()
    {
        if(now_state!=0)return;
        for(int i=1;i<=blue_num;i++)
        {
            if(blue_man[i].IsClick())//有角色被选中
            {
                now_operater=i;
                for(int j=1;j<=blue_num;j++)//其他角色不可选
                {
                    if(j==i)continue;
                    blue_man[j].setUnClickable();
                }
                now_state=3;//开始选卡牌
                change_to_choose_card(i);//开始选卡牌
                return;
            }
        }
        if(compose.IsClick())
        {
            compose.flashClick();
            change_to_compose();//开始合成
            now_state=1;
            return;
        }
        if(myActionClose.IsClick())//结束自己的回合
        {
            now_state=5;
            return;
        }
    }

    public void change_to_0()//初始化为等待页面，部分按键可用
    {
        if(now_state!=0)return;
        now_operater=-1;
        now_object=-1;
        now_cards=-1;
        now_side=-1;
        for (int i=1;i<=blue_num;i++)if(isAliveBlue[i])
        {
            blue_man[i].setClickable();
            blue_man[i].unitState();
            blue_man[i].flashClick();
        }
        for(int i=1;i<=red_num;i++)if(isAliveRed[i])
        {
            red_man[i].setUnClickable();
            red_man[i].unitState();
            red_man[i].flashClick();
        }
        for(int i=1;i<=8;i++)
        {
            if(hasCard[i])
            {
                nowCardsPools[i].setUnClickable();
                nowCardsPools[i].flashClick();
            }
        }
        compose.setClickable();
        compose.flashClick();
        myActionClose.setClickable();
        myActionClose.flashClick();
        for(int i=1;i<=8;i++)if(hasCard[i])nowCardsPools[i].unitState();
    }

    public void change_to_choose_card(int x)//初始化为选手牌页面
    {
        if(now_state!=3)return;
        for(int i=1;i<=8;i++)
        {
            if(hasCard[i])//筛出能选择的手牌
            {
                if(nowCardsPools[i].getBelongType()==blue_man[x].getType()&&(nowCardsPools[i].getLimit_role()==-1||nowCardsPools[i].getLimit_role()==blue_man[i].getRole_idex()))
                {
                    nowCardsPools[i].setClickable();
                }
                else nowCardsPools[i].setUnClickable();
            }
        }
        compose.setUnClickable();//不可合成
        myActionClose.setUnClickable();//不可结束回合
        for(int i=1;i<=red_num;i++)red_man[i].setUnClickable();//不可选择其他角色
        for(int i=1;i<=blue_num;i++)if(x!=i)blue_man[i].setUnClickable();
        //while (true){}
    }

    public void choose_cards_check()
    {
        if(now_state!=3)return;
        for(int i=1;i<=8;i++)
        {
            if(hasCard[i])
            {
                if(nowCardsPools[i].IsClick())
                {

                            //ready_CardsPool[j]=nowCardsPools[i];
                            //mainFightWindow.remove_Button(nowCardsPools[i]);
                            //nowCardsPools[i]=null;
                            //hasCard[i]=false;
                            //readyCard[j]=true;
                            now_cards=i;
                            //ready_CardsPool[j].setBonous(100+(j-1)*(170), 300, 120, 200);
                            now_state=4;
                            change_to_choose_object();
                            //ready_CardsPool[j].flashClick();
                            return;
                }
            }
        }
    }

    public void change_to_choose_object()
    {
        if(now_state!=4)return;
        for(int i=1;i<=blue_num;i++)blue_man[i].setClickable();
        for(int i=1;i<=red_num;i++)red_man[i].setClickable();
        for(int i=1;i<=8;i++)if(hasCard[i])nowCardsPools[i].setUnClickable();
        compose.setUnClickable();
        myActionClose.setUnClickable();
        for(int i=1;i<=3;i++)if(readyCard[i])ready_CardsPool[i].setUnClickable();
    }

    private void choose_object_check() //转化为选择攻击对象状态
    {      
        if(now_state!=4)return;
        for(int i=1;i<=red_num;i++)
        {
            if(isAliveRed[i]&&red_man[i].IsClick())
            {
                now_object=i;
                now_side=1;
                dealWithTask();
                now_state=0;
                change_to_0();
                return;
            }
        }
        for(int i=1;i<=blue_num;i++)
        {
            if(isAliveRed[i]&&red_man[i].IsClick())
            {
                now_object=i;
                now_side=2;
                dealWithTask();
                now_state=0;
                change_to_0();
                return;
            }
        }
    }

    private void dealWithTask()
    {
        System.out.println(now_operater+"______"+now_object+"___________"+now_cards);

        //now_operater=-1;
        //now_object=-1;
        //now_cards=-1;
        boolean has_place=false;
        for(int i=1;i<=3;i++)
        {
            if(readyCard[i]==false)
            {
                has_place=true;
                ready_CardsPool[i]=nowCardsPools[now_cards];
                readyCard[i]=true;
                hasCard[now_cards]=false;
                ready_CardsPool[i].getButton().setBounds(100 + (i-1) * (170), 300, 120, 200);
                ready_CardsPool[i].unitState();
                ready_CardsPool[i].flashClick();
                break;
            }
        }
        if(!has_place)
        {
            JOptionPane.showMessageDialog(null, "出牌区已满", "警告", JOptionPane.WARNING_MESSAGE);
        }
        now_state=0;
        change_to_0();

    }

    public void compose_check()
    {
        if(now_state!=1)return;
        //System.out.printf("checking\n");
        if(compose.IsClick())
        {
            compose.flashClick();
            dealWithCompose();
            now_state=0;
            compose.flashClick();
        }
    }

    public void change_to_compose()
    {
        if(now_state!=1)return;
        for(int i=1;i<=8;i++)
        {
            if(hasCard[i])
            {
                nowCardsPools[i].setClickable();
            }
        }
        for(int i=1;i<=red_num;i++)
        {
              if(isAliveRed[i])red_man[i].setUnClickable();
        }
        for(int i=1;i<=blue_num;i++)
        {
            if(isAliveBlue[i])blue_man[i].setUnClickable();
        }
        compose.flashClick();
        myActionClose.flashClick();
    }

    public void dealWithCompose()
    {

        for(int i=1;i<=8;i++)
        {
            if(hasCard[i]&&nowCardsPools[i].getState()==2)
            {
                allComposeIdx.add(i);
                composeQueue.add(nowCardsPools[i].getIdentifier());
            }
            //System.out.printf("*\n");
        }
        Collections.sort(composeQueue);
          System.out.println(composeQueue);
          System.out.println(allComposeIdx);
          if(allCompose.contains(composeQueue))
          {
              int needCard=composeIdx.get(composeQueue);
              System.out.println(allComposeIdx);

              for(int x:allComposeIdx)
              {
                  //System.out.printf("%d______________________%d\n",x,nowCardsPools[x].getLimit_role());
                  hasCard[x]=false;
                  mainFightWindow.remove_Button(nowCardsPools[x].getButton());
                  nowCardsPools[x]=null;
              }
              for(int i=1;i<=8;i++)
              {
                  if(!hasCard[i])
                  {
                        nowCardsPools[i]=new fighting_hand_cards(7+(i-1)*(125), 730, 125, 200,1,needCard,"1",String.format("/imagin/fight_card/%d.png",needCard),String.format("/imagin/fight_card/4.png"));
                        mainFightWindow.mid_insert_Button(nowCardsPools[i].getButton(),7+(i-1)*(125), 730, 125, 200);
                        hasCard[i]=true;
                        System.out.printf("adding OK__________%d",nowCardsPools[i].getIdentifier());
                        break;
                  }
              }
              //mainFightWindow.windowFlash();

          }
          else//配方不符合，提示错误
          {
              JOptionPane.showMessageDialog(null, "合成失败，请重试", "警告", JOptionPane.WARNING_MESSAGE);
              for(int i=1;i<=8;i++)if(hasCard[i])nowCardsPools[i].unitState();
          }
          now_state=0;
          change_to_0();
          allComposeIdx.clear();
          composeQueue.clear();

        // while(true){}

    }
    /*__________具体逻辑检测函数__________*/

    public void composeUnit()//合成配方在这里导入
    {
        /*___________________________*/
        int[] tmp= new int[]{3,5,1};
        createCompose(tmp,6);
        /*___________________________*/
        tmp= new int[]{3, 5};
        createCompose(tmp,7);
        /*___________________________*/

    }

    public void createCompose(int[] ans,int to)
    {
        List<Integer> composeTmp = new ArrayList<>();
        for(int x:ans)
        {
            composeTmp.add(x);
        }
        allCompose.add(composeTmp);
        composeIdx.put(composeTmp,to);
    }


}


