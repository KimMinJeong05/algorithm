import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] energy = new int[N + 1][N + 1];
		int[][] map = new int[N + 1][N + 1]; 
		Deque<int[]> tree_list = new LinkedList<>();

		// A[r][c] 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				energy[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}

		// 나무 리스트에 추가
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int tree_age = Integer.parseInt(st.nextToken());
			tree_list.add(new int[] {x, y, tree_age});
		}

		while (K > 0) {
			Queue<int[]> die_tree_list = new LinkedList<>();

			// 봄
			for (int i = 0; i < tree_list.size();) {
				int[] cur = tree_list.poll();
				if (map[cur[0]][cur[1]] >= cur[2]) {
					map[cur[0]][cur[1]] -= cur[2];
					cur[2]++;
					i++;
					tree_list.add(cur);
				} else {
					die_tree_list.add(cur);
				}
			}

			// 여름
			for (int[] t : die_tree_list) {
				map[t[0]][t[1]] += t[2] / 2;
			}

			// 가을
			Queue<int[]> temp_list = new LinkedList<>();
			for (int[] t : tree_list) {
				if (t[2] % 5 == 0) {
					temp_list.add(t);
				}
			}
			while (!temp_list.isEmpty()) {
				int[] t = temp_list.poll();

				for (int i = 0; i < 8; i++) {
					int nr = t[0] + dr[i];
					int nc = t[1] + dc[i];
					if (nr >= 1 && nr <= N && nc >= 1 && nc <= N) {
						tree_list.addFirst(new int[] {nr, nc, 1});
					}
				}
			}

			// 겨울
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] += energy[i][j];
				}
			}

			K--;
		}

		bw.write(tree_list.size() + "\n");
		bw.flush();
		bw.close();
	}
}