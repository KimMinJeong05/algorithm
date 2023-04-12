import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class Main {

	static int p,m;
	static List<Integer> roomLevel=new ArrayList<Integer>();
	static List<TreeSet> rooms=new ArrayList<TreeSet>();
	
	public static void main(String[] args) throws IOException{
		StringBuilder sb=new StringBuilder();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		p=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		int l; String n;
		for(int k=0; k<p; k++) {
			st=new StringTokenizer(br.readLine(), " ");
			l=Integer.parseInt(st.nextToken());
			n=st.nextToken();
			
			add(l,n);
		}
		
		for(TreeSet room:rooms) {
			sb.append(room.size()==m?"Started!":"Waiting!").append("\n");
			
			Iterator iter=room.iterator();
			Player p;
			while(iter.hasNext()) {
				p=(Player) iter.next();
				sb.append(p.l).append(" ").append(p.n).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void add(int l, String n) {
		for(int i=0; i<roomLevel.size(); i++) {
			int curLevel=roomLevel.get(i);
			if(curLevel-10<=l && l<=curLevel+10 && rooms.get(i).size()<m) {
				rooms.get(i).add(new Player(l,n));
				return;
			}
		}
		
		roomLevel.add(l);
		TreeSet<Player> temp=new TreeSet<Player>();
		temp.add(new Player(l,n));
		rooms.add(temp);
	}
	static class Player implements Comparable{
		int l; String n;
		public Player(int l, String n) {
			this.l=l;
			this.n=n;
		}
		
		@Override
		public int compareTo(Object o) {
			return this.n.compareTo(((Player)o).n);
		}
	}
}