import java.util.*; 
import java.io.*;

class Main {
  public static boolean[][] visited;
  public static String[][] mat;
  public static Integer[][] dist;
  public static TreeSet<Pair> pq;
  
  public static String ClosestEnemyII(String[] strArr) { 
    mat = new String[strArr.length][strArr[0].length()];
    int i = 0;
    for (String s : strArr) {
        mat[i] = s.split("");
        i++;
    }
    
    visited = new boolean[mat.length][mat[0].length];
    dist = new Integer[mat.length][mat[0].length];
    
    Pair startPos = null;
    List<Pair> twos = new ArrayList<>();
    for (int j = 0; j < mat.length; j++) {
        for (int k = 0; k < mat[j].length; k++) {
            if (mat[j][k].equals("1")) {
                startPos = new Pair(j, k);
            } else if (mat[j][k].equals("2")) {
                twos.add(new Pair(j, k));
            }
        }
    }
    
    // run Dijkstra from startPos to find the 2
    pq = new TreeSet<>((pair1, pair2) -> {
        if (getDist(pair1) == null && getDist(pair2) != null) {
            return -1;
        } else if (getDist(pair2) == null && getDist(pair1) != null) {
            return 1;
        } else if (getDist(pair1) == null && getDist(pair2) == null) {
            return 0;
        } else {
            return getDist(pair1) - getDist(pair2);
        }
    });
	
    setDist(startPos, 0);
    pq.add(startPos);
    
    while (!pq.isEmpty()) {
        Pair currPair = pq.pollFirst();
        visited[mod(currPair.i)][mod(currPair.j)] = true;
        
        Pair left, right, up, down;
        left = new Pair(currPair.i, currPair.j - 1);
        right = new Pair(currPair.i, currPair.j + 1);
        up = new Pair(currPair.i - 1, currPair.j);
        down = new Pair(currPair.i + 1, currPair.j);
        
        Pair[] pairs = new Pair[] {left, right, up, down};
        for (Pair p : pairs) {
            if (!isVisited(p)) {
				if (pq.contains(p)) { pq.remove(p); }
                if (getDist(p) == null) {
                    setDist(p, getDist(currPair) + 1);
                } else if (getDist(p) > getDist(currPair) + 1) {
                    setDist(p, getDist(currPair) + 1);
                }
				pq.add(p);
            }
        }
    }
    
    Integer shortestDist = null;
    for (Pair p : twos) {
        if (shortestDist == null || getDist(p) < shortestDist) {
            shortestDist = getDist(p);
        }
    }
    
    if (shortestDist == null) {
        return "0";
    }
    return Integer.toString(shortestDist);
    
  }
  
  public static Integer getDist(Pair p) {
      return dist[mod(p.i)][mod(p.j)];
  }
  
  public static void setDist(Pair p, int distance) {
		dist[mod(p.i)][mod(p.j)] = distance;
  }
  
  public static boolean isVisited(Pair p) {
      return visited[mod(p.i)][mod(p.j)];
  }
  
  public static int mod(int i) {
      while (i < 0) {
          i += mat.length;
      }
      return i % mat.length;
  }
  
  public static void main (String[] args) {  
    // keep this function call here     
    Scanner s = new Scanner(System.in);
    System.out.print(ClosestEnemyII(s.nextLine()));
  }   
  
}

class Pair {
      public int i;
      public int j;
      
      Pair(int i, int j) {
          this.i = i;
          this.j = j;
      }
      
      public String toString() {
          return "[" + i + ":" + j + "]";
      }

      public boolean equals(Object o) {
          if (!(o instanceof Pair)) {
              return false;
          }
          Pair p = (Pair) o;
          return ((this.i == p.i) && (this.j == p.j));
      }
  }
