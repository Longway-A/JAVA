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
    int length;//�߳���
    int []snakeX = new int[600];//�ߵ�����x
    int []snakeY = new int[500];//�ߵ�����y
    String direction;//�ߵķ��� �� R:��  L:��  U:��  D:��
    int foodX;
    int foodY;
    Random random = new Random();
    int score;//��Ϸ������
    boolean isStart = false;
    boolean isFail = false;//��Ϸ�Ƿ����
    Timer timer =new Timer(100,this);
    //���췽��
public Gameplay(){
    init();//��ʼ��
    this.setFocusable(true);//��ȡ�����¼�
    this.addKeyListener(this);//���̼����¼�
    timer.start();
}
    //��ʼ������
    public  void init(){//��ʼС��������,����С�Դ�
        //��ʼ����ʼ����,���߶�λ,
        length=3;
        snakeX[0]=100;snakeY[0]=100;
        snakeX[1]=75; snakeY[1]=100;
        snakeX[2]=50; snakeY[2]=100;
        direction="R";
        //��ʼ��ʳ������
        foodX = 25 +25*random.nextInt(34);
        foodY = 75 +25*random.nextInt(24);
        score = 0;////��ʼ������

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//����
        this.setBackground(Color.WHITE);//�������ı���ɫ
        Data.header.paintIcon(this,g,25,11);//����ͷ����Ϣ����
        g.fillRect(25,75,850,600); //������Ϸ����
        g.setColor(Color.white);
        g.setFont(new Font("΢���ź�",Font.BOLD,15));
        g.drawString("����"+length,750,35);
        g.drawString("����"+score,750,50);
        //�ж��ߵķ���
        switch (direction ) {
            case "R" -> Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
            case "L" -> Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
            case "U" -> Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
            case "D" -> Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        //�ߵ����峤�ȸ���lenth������
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        //��ʳ��
        Data.food.paintIcon(this,g,foodX,foodY);
        //��Ϸ��ʾ
        if(!isStart) {
            g.setColor(Color.white);
            g.setFont(new Font("΢���ź�", Font.BOLD, 40));
            g.drawString("���ո�ʼ��Ϸ", 300, 300);
        }
        //��Ϸʧ���ж�
        if (isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("΢���ź�",Font.BOLD,40));
            g.drawString("���¿ո����¿�ʼ��Ϸ",300,300);
        }
        }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //���̼����¼�
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();//��ȡ���µļ���
        if (keyCode == KeyEvent.VK_SPACE){//����ǿո�
            if (isFail){//�����Ϸʧ��,��ͷ������
                isFail=false;
                init();//���³�ʼ��
            }
            else{//������ͣ��Ϸ
                isStart = !isStart;
            }
            repaint();//�ػ�
        }
        //���̿�������
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
    //��ʱִ�еĲ���
    @Override
    public void actionPerformed(ActionEvent e) {
        //�����Ϸ���ڿ�ʼ״̬������û�н�������С�߿����ƶ�
        if (isStart && isFail==false){
            //����:���ú�һ���Ƶ�ǰһ����λ�ü��� !
            if (snakeX[0]==foodX && snakeY[0]==foodY){//��ʳ��:���ߵ�ͷ��ʳ��һ��ʱ,��Ե�ʳ��!
                length++;//1.���ȼ�һ
                score =score+10;//ÿ��һ��ʳ����ӻ���
                //2.��������ʳ��
                foodX = 25 +25*random.nextInt(34);
                foodY = 75 +25*random.nextInt(24);
            }
            for(int i = length-1; i > 0; i--) {//�����Դ�����ǰ�ƣ������ƶ�
                snakeX[i] = snakeX[i-1];//����i��(��һ��)��λ�ñ�Ϊ(i-1��ǰһ��)�ڵ�λ�ã�
                snakeY[i] = snakeY[i-1];
            }
            //ͨ��������ƣ�ͷ���ƶ�
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
            //�����жϣ�ͷ������ײ����
            for (int i =1; i < length; i++) {
                //���ͷ��������ײ���Ǿ�˵����Ϸʧ��
                if (snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]){
                    isFail = true;
                }

            }


            repaint(); //��Ҫ���ϵĸ���ҳ��ʵ�ֶ���
        }
        timer.start();//��ʱ�䶯����!
    }
}
