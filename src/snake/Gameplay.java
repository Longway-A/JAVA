package snake;
import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    int length;//蛇长度
    int []snakeX = new int[600];//蛇的坐标x
    int []snakeY = new int[500];//蛇的坐标y
    String direction;//蛇的方向 ： R:右  L:左  U:上  D:下
    int foodX;
    int foodY;
    Random random = new Random();
    int score;//游戏分数！
    boolean isStart = false;
    boolean isFail = false;//游戏是否结束
    Timer timer =new Timer(100,this);
    //构造方法
public Gameplay(){
    init();//初始化
    this.setFocusable(true);//获取焦点事件
    this.addKeyListener(this);//键盘监听事件
    timer.start();
}
    //初始化方法
    public  void init(){//初始小蛇有三节,包括小脑袋
        //初始化开始的蛇,给蛇定位,
        length=3;
        snakeX[0]=100;snakeY[0]=100;
        snakeX[1]=75; snakeY[1]=100;
        snakeX[2]=50; snakeY[2]=100;
        direction="R";
        //初始化食物数据
        foodX = 25 +25*random.nextInt(34);
        foodY = 75 +25*random.nextInt(24);
        score = 0;////初始化分数

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        this.setBackground(Color.WHITE);//设置面板的背景色
        Data.header.paintIcon(this,g,25,11);//绘制头部信息区域
        g.fillRect(25,75,850,600); //绘制游戏区域
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD,15));
        g.drawString("长度"+length,750,35);
        g.drawString("分数"+score,750,50);
        //判断蛇的方向
        switch (direction ) {
            case "R" -> Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
            case "L" -> Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
            case "U" -> Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
            case "D" -> Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        //蛇的身体长度根据lenth来控制
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        //画食物
        Data.food.paintIcon(this,g,foodX,foodY);
        //游戏提示
        if(!isStart) {
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按空格开始游戏", 300, 300);
        }
        //游戏失败判断
        if (isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格重新开始游戏",300,300);
        }
        }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //键盘监听事件
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();//获取按下的键盘
        if (keyCode == KeyEvent.VK_SPACE){//如果是空格
            if (isFail){//如果游戏失败,从头再来！
                isFail=false;
                init();//重新初始化
            }
            else{//否则，暂停游戏
                isStart = !isStart;
            }
            repaint();//重绘
        }
        //键盘控制走向
        if (keyCode==KeyEvent.VK_UP){
            direction = "U";

        }else if(keyCode==KeyEvent.VK_DOWN){
            direction = "D";
        }
        else if (keyCode==KeyEvent.VK_LEFT){
            direction = "L";
        }
        else if(keyCode==KeyEvent.VK_RIGHT){
            direction = "R";
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    //定时执行的操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态，并且没有结束，则小蛇可以移动
        if (isStart && isFail==false){
            //右移:即让后一个移到前一个的位置即可 !
            if (snakeX[0]==foodX && snakeY[0]==foodY){//吃食物:当蛇的头和食物一样时,算吃到食物!
                length++;//1.长度加一
                score =score+10;//每吃一个食物，增加积分
                //2.重新生成食物
                foodX = 25 +25*random.nextInt(34);
                foodY = 75 +25*random.nextInt(24);
            }
            for(int i = length-1; i > 0; i--) {//除了脑袋都往前移：身体移动
                snakeX[i] = snakeX[i-1];//即第i节(后一节)的位置变为(i-1：前一节)节的位置！
                snakeY[i] = snakeY[i-1];
            }
            //通过方向控制，头部移动
            if (direction.equals("R")) {
                snakeX[0] = snakeX[0]+25;
                if (snakeX[0]>850){
                    snakeX[0] =25;
                }
            }
            else if (direction.equals("L")){
                snakeX[0] = snakeX[0]-25;
                if (snakeX[0]<25){
                    snakeX[0] =850;
                }
            }
           else if (direction.equals("U")){
               snakeY[0] = snakeY[0]-25;
                if (snakeY[0]<75){
                    snakeY[0] =650;
                }
           }
            else if (direction.equals("D")){
                snakeY[0] = snakeY[0]+25;
                if (snakeY[0]>650){
                    snakeY[0] =75;
                }
            }
            //结束判断，头和身体撞到了
            for (int i =1; i < length; i++) {
                //如果头和身体碰撞，那就说明游戏失败
                if (snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]){
                    isFail = true;
                }

            }


            repaint(); //需要不断的更新页面实现动画
        }
        timer.start();//让时间动起来!
    }
}
