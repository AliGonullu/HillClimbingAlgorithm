import java.util.List;
import java.util.Scanner;


public class HillClimbing {
	
	public static void main(String[] args) {
		Problem problem = new Problem();
		Scanner scanner = new Scanner(System.in);
		
        System.out.println("Determine The Size of The Table : ");
		int size = scanner.nextInt();
				
		Problem.setBOARD_SIZE(size);
		
		BoardState currState = problem.getInitialState();
        System.out.println("\nInitial Board:");
        Problem.printBoard(currState);

        while(true) {
            List<BoardState> neighbors = problem.getNeighbors(currState);
            BoardState nextState = problem.chooseBestNeighbor(neighbors);
            
            if (problem.getScore(nextState) > problem.getScore(currState)) {
            	currState = nextState;
            	Problem.printBoard(currState);
            }
            else break;              
        }
        System.out.println("\nFinal Board:");
        Problem.printBoard(currState);
        scanner.close();
    }
}
