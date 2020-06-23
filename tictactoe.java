import java.awt.BorderLayout;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

class tictactoe {
    public static void main(final String args[]){
        Display disp = new Display();
        disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        disp.setSize(400,400);
        disp.setVisible(true);
        disp.setResizable(false);
        disp.setLocationRelativeTo(null);  
        
    }
}


class Display extends JFrame{
    private String tur;
    private final JLabel bar;
    public boolean enabled=true;

    int lx[]={40,155,270};
    int ly[]={40,155,270};

    private final BufferedImage xi;
    private final BufferedImage oi;
    BufferedImage now,old;

    Color myRed = new Color(105, 22, 22);
    Color myGreen = new Color(15, 78, 15);

    ImageIcon icon = new ImageIcon("icon.png");

    public int positions[]= {0,0,0,0,0,0,0,0,0};

    public Display(){
        super("TicTacToe");
        bar = new JLabel("");
        
        setBackground(Color.LIGHT_GRAY);

        xi=Images.loadImage("X.png");
        oi=Images.loadImage("O.png");
        now=xi;
        
        bar.setFont(new Font("", Font.PLAIN, 18));
        add(bar,BorderLayout.SOUTH);
        setIconImage(icon.getImage());
        addMouseListener(new MouseManager());
    }

    public void paint(final Graphics g){
        g.clearRect(0, 0, 400, 400);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(130, 40, 10, 320);
        g.fillRect(250, 40, 10, 320);
        g.fillRect(40, 130, 320, 10);
        g.fillRect(40, 250, 320, 10);

        bar.setText(" "+"Turn: X");

        g.dispose();
    }

    private class MouseManager extends MouseAdapter{
        int x=0,y=0,i;
        public char winner;

        public void mouseReleased(MouseEvent event) {
            if(!enabled){
                x=event.getX();
                y=event.getY();
                clearEverything();
                }
            if (!enabled) {
                return;
              }
            x=event.getX();
            y=event.getY();
            position();
            if(checkGame()==true){
                if(winner == 'T')
                tur = String.format("  Tie                      Click On The Box To Restart");
                else
                tur = String.format(" "+ winner +" Won                  Click On The Box To Restart");

                bar.setText(tur);
                drawBox();
            }
         }

        

        public void drawBox(){
            Graphics g = getGraphics();
            g.drawRect(360, 330, 30, 30);
        }

        public void clearEverything(){
            int i,c;
            Graphics g = getGraphics();
            if(((x >= 360) && (y >= 330) && (x < 390) && (y < 360)) && !enabled){
            for(i=0;i<3;i++){
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(40+i*115,40,80,80);
                g.fillRect(40+i*115,155,80,80);
                g.fillRect(40+i*115,270,80,80);
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(360, 330, 30, 30);
            }
            for(c=0;c<9;c++){
                positions[c]= 0;
                }
            bar.setText(" "+"Turn: X");
            now=xi;
            enabled = true;
            }
        }

        public boolean checkGame(){
            int i;
            Graphics g = getGraphics();
            for(i=0;i<9;i=i+3){
                if(((positions[i]==positions[i+1])&&(positions[i+1]==positions[i+2]))&& positions[i]!=0 &&positions[i+1]!=0&&positions[i+2]!=0){
                    if(positions[i]==1)
                    winner = 'X';
                    else
                    winner = 'O';
                    enabled=false;
                    if(winner== 'X')
                    g.setColor(myRed);
                    else
                    g.setColor(myGreen);
                    g.fillRect(40,40+(i/3)*115,80,80);
                    g.fillRect(155,40+(i/3)*115,80,80);
                    g.fillRect(270,40+(i/3)*115,80,80);
                    g.drawImage(old, 38, 40+(i/3)*115, null);
                    g.drawImage(old, 153, 40+(i/3)*115, null);
                    g.drawImage(old, 268, 40+(i/3)*115, null);
                    return true;
                }  
            }
            for(i=0;i<3;i++){
                if(((positions[i]==positions[i+3])&&(positions[i+3]==positions[i+6]))&& positions[i]!=0 &&positions[i+3]!=0&&positions[i+6]!=0){
                    if(positions[i]==1)
                    winner = 'X';
                    else
                    winner = 'O';
                    enabled=false;
                    if(winner== 'X')
                    g.setColor(myRed);
                    else
                    g.setColor(myGreen);
                    g.fillRect(40+i*115,40,80,80);
                    g.fillRect(40+i*115,155,80,80);
                    g.fillRect(40+i*115,270,80,80);
                    g.drawImage(old, 38+i*115, 40, null);
                    g.drawImage(old, 38+i*115, 155, null);
                    g.drawImage(old, 38+i*115, 270, null);
                    return true;
                }  
            }
            if(((positions[0]==positions[4])&&(positions[4]==positions[8]))&& positions[0]!=0 &&positions[4]!=0&&positions[8]!=0){
                if(positions[0]==1)
                    winner = 'X';
                    else
                    winner = 'O';
                    enabled=false;
                    if(winner== 'X')
                    g.setColor(myRed);
                    else
                    g.setColor(myGreen);
                    g.fillRect(40,40,80,80);
                    g.fillRect(155,155,80,80);
                    g.fillRect(270,270,80,80);
                    g.drawImage(old, 38, 40, null);
                    g.drawImage(old, 153, 155, null);
                    g.drawImage(old, 268, 270, null);
                return true;
            }
            if(((positions[2]==positions[4])&&(positions[4]==positions[6]))&& positions[2]!=0 &&positions[4]!=0&&positions[6]!=0){
                if(positions[2]==1)
                    winner = 'X';
                    else
                    winner = 'O';
                    enabled=false;
                    if(winner== 'X')
                    g.setColor(myRed);
                    else
                    g.setColor(myGreen);
                    g.fillRect(270,40,80,80);
                    g.fillRect(155,155,80,80);
                    g.fillRect(40,270,80,80);
                    g.drawImage(old, 268, 40, null);
                    g.drawImage(old, 153, 155, null);
                    g.drawImage(old, 38, 270, null);
                return true;
            }
                if(positions[0]!=0 && positions[1]!=0 &&positions[2]!=0 &&positions[3]!=0 &&positions[4]!=0 &&positions[5]!=0 &&positions[6]!=0 &&positions[7]!=0 &&positions[8]!=0){
                    winner = 'T';
                    enabled=false;
                    return true;
                }
            
            
            return false;
        }
        public void position(){
            int j,k;
            Graphics g = getGraphics();
            for(i=0,j=0,k=0;i<9;i++,j++){
                if(i==3 || i==6){
                    j=0;
                    k++;
                }              
                if(((x >= 40+j*115) && (y >= 40+k*115) && (x < 120+j*115) && (y < 120+k*115)) && positions[i]==0) {
                    g.drawImage(now, 40+j*115, 40+k*115, null);
                    old=now;
                    if(now==xi){
                    now=oi;
                    positions[i]=1;
                    tur = String.format(" "+"Turn: O");
                    }
                    else{
                    now=xi;
                    positions[i]=2;
                    tur = String.format(" "+"Turn: X");
                    }
                    bar.setText(tur);
                    break;
                }
            }
        }
    }
}

class Images{
    public static BufferedImage loadImage(final String path){
        try{
        return ImageIO.read(Images.class.getResource(path));
        }catch(final Exception e){
            System.exit(1);
        }
        return null;
    }
}