package Tank;

import java.io.*;
import java.util.Vector;

public  class Recorder {
    private static int enemy_tank_num = 0;
    private static Vector<Enemy_tank> enemy_tanks = null;

    private static Vector<Node> nodes = new Vector<>();
    public static Integer killed = 0;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    //private FileWriter fw = null;
    static String path = "src/score.txt";


    public static void setEnemy_tanks(Vector<Enemy_tank> enemy_tanks) {
        Recorder.enemy_tanks = enemy_tanks;
    }

    public static Vector<Node> load(){
        try {
            br = new BufferedReader(new FileReader(path));
            enemy_tank_num = Integer.parseInt(br.readLine());
            String line = "";
            while((line = br.readLine())!=null){
                String[] str = line.split(" ");
                Node node = new Node(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[1]));
                nodes.add(node);
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }finally {
            try {
                if(br == null) {
                    br.close();
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
        return nodes;
    }
    public static void save()  {
        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(killed.toString());
            bw.newLine();
            for (int i = 0; i < enemy_tanks.size(); i++) {
                Enemy_tank et = enemy_tanks.get(i);
                bw.write(et.getX()+" "+et.getY()+" "+et.getDirection());
                bw.newLine();
            }

        }catch (IOException ioe){
            ioe.printStackTrace();
        }finally {
            try {
                bw.close();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }
}
