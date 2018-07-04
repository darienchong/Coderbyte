import java.util.*; 
import java.io.*;

class Main {  
  public static String EightQueens(String[] strArr) { 
    List<Pair> queens = new ArrayList<>();
	List<Set<Pair>> illegalPositionsOrdered = new ArrayList<>();
	
    for (String s : strArr) {
        queens.add(new Pair(Integer.parseInt(Character.toString(s.charAt(1))), Integer.parseInt(Character.toString(s.charAt(3)))));
    }
    
    // Process each queen sequentially, note all illegal positions
    // If an illegal position is occupied, break and return the queen
    // Else, at the end return true
    
    for (Pair queen : queens) {
        Set<Pair> currIllegals = generateIllegalPos(queen);
		illegalPositionsOrdered.add(currIllegals);
    }
	
    for (int i = 0; i < queens.size(); i++) {
       for (int j = 0; j < i; j++) {
			if (illegalPositionsOrdered.get(j).contains(queens.get(i))) {
				return queens.get(j).toString();
			}
		}
    }
    return "true";
  }
  
  public static Set<Pair> generateIllegalPos(Pair queen) {
      Set<Pair> illegals = new HashSet<>();
      
      Set<Pair> left = new HashSet<>();
      Set<Pair> right = new HashSet<>();
      Set<Pair> up = new HashSet<>();
      Set<Pair> down = new HashSet<>();
      Set<Pair> upleft = new HashSet<>();
      Set<Pair> upright = new HashSet<>();
      Set<Pair> downleft = new HashSet<>();
      Set<Pair> downright = new HashSet<>();
      
      int i = queen.i;
      int j = queen.j;
      
      while (i < 8) {
          right.add(new Pair(++i, j));
      }
      
      i = queen.i;
      
      while (i > 1) {
          left.add(new Pair(--i, j));
      }
      
      i = queen.i;
      
      while (j > 1) {
          down.add(new Pair(i, --j));
      }
      
      j = queen.j;
      
      while (j < 8) {
          up.add(new Pair(i, ++j));
      }
      
      j = queen.j;
      
      while (i > 1 && j < 8) {
          upleft.add(new Pair(--i, ++j));
      }
      
      i = queen.i;
      j = queen.j;
      
      while (i < 8 && j < 8) {
          upright.add(new Pair(++i, ++j));
      }
      
      i = queen.i;
      j = queen.j;
      
      while (i > 1 && j > 1) {
          downleft.add(new Pair(--i, --j));
      }
      
      i = queen.i;
      j = queen.j;
      
      while (i < 8 && j > 1) {
          downright.add(new Pair(++i, --j));
      }
      
      illegals.addAll(left);
      illegals.addAll(right);
      illegals.addAll(up);
      illegals.addAll(down);
      illegals.addAll(upleft);
      illegals.addAll(upright);
      illegals.addAll(downleft);
      illegals.addAll(downright);
      
      return illegals;
  }
  public static void main (String[] args) {  
    // keep this function call here    
    Scanner s = new Scanner(System.in);
    System.out.print(EightQueens(s.nextLine()));
  }   
  
}

class Pair {
    Integer i, j;
    
    Pair(Integer i, Integer j) {
        this.i = i;
        this.j = j;
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair p = (Pair) o;
        return ((this.i.equals(p.i)) && (this.j.equals(p.j)));
    }
    
    public int hashCode() {
        return this.i.hashCode() ^ this.j.hashCode();
    }
	
	public String toString() {
		return "(" + this.i + "," + this.j + ")";
	}
}
