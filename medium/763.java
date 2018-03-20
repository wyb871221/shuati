class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> ret = new LinkedList<Integer>();
        int[][] map = new int[26][2];
        for (int[] arr : map) {
        	Arrays.fill(arr, -1);
        }

        for (int i = 0; i < S.length(); ++i) {
        	char curr = S.charAt(i);
        	int index = curr - 97;

        	if (map[index][0] == -1) { // setting the start position for current character
        		map[index][0] = i;
        	}

        	map[index][1] = i;
        }

        int index = S.charAt(0) - 97;
        int start = map[index][0];
        int end = map[index][1];
       	boolean[] visited = new boolean[26];
       	visited[index] = true;

        for (int i = 1; i < S.length(); ++i) {
        	if (!visited[S.charAt(i) - 97]) {
                visited[S.charAt(i) - 97] = true;
        		int currStart = map[S.charAt(i) - 97][0];
        		int currEnd = map[S.charAt(i) - 97][1];
        		if (currStart > end) {
        			ret.add(end - start + 1);
                    start = currStart;
                    end = currEnd;
        		} else {
        			end = Math.max(end, currEnd);
        		}
        	}
        }

        ret.add(end - start + 1);
        return ret;
    }
}