import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


public class Main {

	static Map<Integer, Problem> problems;
	static Map<Integer, Set<Integer>> levels;
	static Map<Integer, Set<Integer>> groups;
	
	static int n,m;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		
		problems=new HashMap<Integer, Problem>();
		levels=new HashMap<Integer, Set<Integer>>();
		groups=new HashMap<Integer, Set<Integer>>();
		
		n=Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine()," ");
			add(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		String function;
		m=Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			st=new StringTokenizer(br.readLine()," ");
			function=st.nextToken();
			
			switch(function) {
			case "add":
				add(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				break;
			case "solved":
				solved(Integer.parseInt(st.nextToken()));
				break;
			case "recommend":
				sb.append(recommend(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
				sb.append("\n");
				break;
			case "recommend2":
				sb.append(recommend2(Integer.parseInt(st.nextToken())));
				sb.append("\n");
				break;
			case "recommend3":
				sb.append(recommend3(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
				sb.append("\n");
				break;
			}
		}
		System.out.println(sb);
	}

	private static void add(int p, int l, int g) {
		problems.put(p, new Problem(p,l,g));
		
		Set<Integer> temp=levels.getOrDefault(l, new HashSet<Integer>());
		temp.add(p);
		levels.put(l, temp);
		temp=groups.getOrDefault(g, new HashSet<Integer>());
		temp.add(p);
		groups.put(g, temp);
	}
	private static void solved(int p) {
		int level=problems.get(p).l;
		int group=problems.get(p).g;
		levels.get(level).remove(p);
		if(levels.get(level).size()==0) levels.remove(level);
		groups.get(group).remove(p);
		if(groups.get(group).size()==0) groups.remove(group);
		problems.remove(p);
	}
	private static int recommend(int g, int x) { 
		int resl=x==1?0:101;
		int resp=-1;
		int p;
		
		Iterator it=groups.get(g).iterator();
		while(it.hasNext()) {
			p=(int) it.next();
			if(x==1 && problems.get(p).l>=resl) {
				if(problems.get(p).l==resl && p<resp) continue;
				resl=problems.get(p).l;
				resp=p;
			}else if(x==-1 && problems.get(p).l<=resl) {
				if(problems.get(p).l==resl && p>resp) continue;
				resl=problems.get(p).l;
				resp=p;
			}
		}
		return resp;
	}
	private static int recommend2(int x) { 
		int level;
		
		if(x==1) {
			level=Collections.max(levels.keySet());
			return (int) Collections.max(levels.get(level));
		}
		else {
			level=Collections.min(levels.keySet());
			return (int) Collections.min(levels.get(level));
		}
	}
	private static int recommend3(int x, int l) { 
		Iterator it=levels.keySet().iterator();
		int templevel;
		int level;
		
		if(x==1) {
			level=101;
			while(it.hasNext()) {
				templevel=(int) it.next();
				if(templevel<level && templevel>=l) level=templevel;
			}
			
			if(levels.get(level)==null) return -1;
			return (Integer) Collections.min(levels.get(level));
		}
		else {
			level=0;
			while(it.hasNext()) {
				templevel=(int) it.next();
				if(templevel>level && templevel<l) level=templevel;
			}
			
			if(levels.get(level)==null) return -1;
			return (Integer) Collections.max(levels.get(level));
		}
	}
	
	static class Problem{
		int p,l,g;
		public Problem(int p, int l, int g) {
			this.p=p;
			this.l=l;
			this.g=g;
		}
	}
}