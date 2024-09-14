import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.Scanner;
import java.util.*;

public class Maze {
    private char [][] tab;
    private int [][] odl;
    private int n;
    private int m;
    private Pair pocz;
    private Pair kon;
    private boolean loaded;
    public Maze()
    {
        tab=new char[2055][2055];
        odl=new int[2055][2055];
        for(int i=0;i<2055;i++)
        {
            for(int j=0;j<2055;j++)
            {
                odl[i][j]=-1;
            }
        }
        n=0;
        m=0;
    }
    public void wczytaj(File plik)
    {
        try {
            n=0;
            m=0;
            Scanner scanner = new Scanner(plik);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                m=line.length();

                char[] charArray = line.toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    tab[n][i] = charArray[i];
                    if(tab[n][i]=='P') {
                        pocz = new Pair(n, i);
                        tab[n][i] = 'X';
                    }
                    if(tab[n][i]=='K')
                    {
                        kon = new Pair(n, i);
                        tab[n][i] = 'X';
                    }
                }
                n++;
                loaded=true;
            }


            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public boolean isLoaded()
    {
        return loaded;
    }
    public int get_n()
    {
        return n;
    }
    public int get_m()
    {
        return m;
    }
    public char get_tab(int wie, int kol)
    {
        return tab[wie][kol];
    }
    public void ustaw_pocz(int wie, int kol)
    {
        pocz=new Pair(wie,kol);
    }
    public void ustaw_kon(int wie, int kol)
    {
        kon=new Pair(wie,kol);
    }
    public Pair get_pocz()
    {
        return pocz;
    }
    public Pair get_kon()
    {
        return kon;
    }
    public void zeruj()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(tab[i][j]=='S') tab[i][j]=' ';
                odl[i][j]=-1;
            }
        }
    }
    public void najkrotsza(int w, int k)
    {
        zeruj();
        Pair start=new Pair(w,k);
        odl[w][k]=0;
        Queue<Pair> kolejka=new LinkedList<>();
        kolejka.offer(start);
        while(!kolejka.isEmpty())
        {
            Pair teraz=kolejka.poll();
            int x = teraz.first();
            int y = teraz.second();

            if(kon.first()==x+1 && kon.second()==y) odl[x+1][y]=odl[x][y]+1;
            else if(kon.first()==x && kon.second()==y+1) odl[x][y+1]=odl[x][y]+1;
            else if(kon.first()==x && kon.second()==y-1) odl[x][y-1]=odl[x][y]+1;
            else if(kon.first()==x-1 && kon.second()==y) odl[x-1][y]=odl[x][y]+1;
            if((kon.first()==x+1 && kon.second()==y) || (kon.first()==x && kon.second()==y+1) || (kon.first()==x && kon.second()==y-1) || (kon.first()==x-1 && kon.second()==y)) return;
            if (x - 1 >= 0 && tab[x - 1][y] != 'X' && odl[x - 1][y]==-1) {
                kolejka.offer(new Pair(x - 1, y));
                odl[x-1][y]=odl[x][y]+1;
            }

            if (x + 1 < tab.length && tab[x + 1][y] != 'X' && odl[x + 1][y]==-1) {
                kolejka.offer(new Pair(x + 1, y));
                odl[x+1][y]=odl[x][y]+1;
            }

            if (y - 1 >= 0 && tab[x][y - 1] != 'X' && odl[x][y - 1]==-1) {
                kolejka.offer(new Pair(x, y - 1));
                odl[x][y-1]=odl[x][y]+1;
            }

            if (y + 1 < tab[0].length && tab[x][y + 1] != 'X' && odl[x][y + 1]==-1) {
                kolejka.offer(new Pair(x, y + 1));
                odl[x][y + 1] = odl[x][y] + 1;
            }
        }
    }
    public int odzyskaj()
    {
        int x=kon.first();
        int y=kon.second();

        int w=odl[x][y];
        int pom=w;
        while(w>0)
        {
            if(!(x==kon.first() && y==kon.second()))tab[x][y]='S';
            if (x - 1 >= 0 && odl[x - 1][y] ==odl[x][y]-1) {
                x--;
                w--;
                continue;
            }
            if (x + 1 < n && odl[x + 1][y] ==odl[x][y]-1) {
                x++;
                w--;
                continue;
            }

            if (y - 1 >= 0 && odl[x][y - 1] ==odl[x][y]-1) {
                y--;
                w--;
                continue;
            }

            if (y + 1 < m && odl[x][y + 1] ==odl[x][y]-1) {
                y++;
                w--;
                continue;
            }
        }
        return pom;
    }
}
