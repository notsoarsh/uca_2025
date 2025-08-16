import java.util.Stack;
public class Matrioshkas{
  int capacity;
  int size;

  public Matrioshkas(int size){
	this.size = size ;
	this.capacity = 0 ;
  }

  public static void main(String[] args){
	int[] arr1 = {-9,-7,-2,-2,-3,-2,-1,1,2,3,7,-2,2,9};
	int[] arr2 = {-9,-7,-2,2,-3,-2,-1,1,2,3,7,9};
	// checking
	if( isMat(arr2) ){
	  System.out.println("Matrioshkas :)");
	}
	else{
	  System.out.println("Not Matrioshkas :(");
	}
  }



 public static boolean isMat(int arr[]) {
    Stack<Matrioshkas> stack = new Stack<>();

    for (int value : arr) {
        if (value < 0) { // ---- Opening a doll ----
            int size = -value;
            Matrioshkas newDoll = new Matrioshkas(size);

            if (!stack.isEmpty()) {
                Matrioshkas parent = stack.peek();
                // Check 1: The new doll must be smaller than its parent.
                if (newDoll.size >= parent.size) {
                    return false;
                }
                // Update parent's used capacity.
                parent.capacity += newDoll.size;
                // Check 2: The parent cannot be overfilled.
                if (parent.capacity >= parent.size) {
                    return false;
                }
            }
            // Add the new doll to the stack of open dolls.
            stack.push(newDoll);

        } else { // ---- Closing a doll ----
            int size = value;
            // Check 3: Cannot close a doll if none are open.
            if (stack.isEmpty()) {
                return false;
            }
            // Check 4: The doll being closed must match the last one opened.
            if (stack.peek().size != size) {
                return false;
            }
            // It's a match, so close it by popping it.
            stack.pop();
        }
    }

    // If the stack is empty, all dolls were opened and closed correctly.
    return stack.isEmpty();
}
}
 
