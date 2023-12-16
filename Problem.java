import java.util.ArrayList;
import java.util.List;

enum Color {Orange, Cyan__, Blue__, Red___}

class BoardState {Color[][] board;}

public class Problem {
	private static int BOARD_SIZE = 5, counter = 1;
    public static int getBOARD_SIZE() {return BOARD_SIZE;}
	public static void setBOARD_SIZE(int bOARD_SIZE) {BOARD_SIZE = bOARD_SIZE;}


	public BoardState getInitialState() {
		BoardState initialState = new BoardState();
        initialState.board = getRandomBoard();
        return initialState;
    }

    
    private Color[][] getRandomBoard() {
        Color[][] board = new Color[BOARD_SIZE][BOARD_SIZE];
        
        for (int i = 0; i < BOARD_SIZE; i++) 
            for (int j = 0; j < BOARD_SIZE; j++)           
            	board[i][j] = getRandomColor();  	                 
        
        return board;
    }

    
    private Color getRandomColor() {
        Color[] colors = Color.values();
        return colors[(int) (Math.random() * colors.length)];
    }
    

    public List<BoardState> getNeighbors(BoardState currentState) {
        List<BoardState> neighbors = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                for (Color color : Color.values()) {             	
                    if (color != currentState.board[i][j]) {
                    	BoardState neighbor = cloneState(currentState);
                        neighbor.board[i][j] = color;
                        neighbors.add(neighbor);
                    }
                }
            }
        }
        return neighbors;
    }
    

    private BoardState cloneState(BoardState state) {
    	BoardState clone = new BoardState();
        clone.board = new Color[BOARD_SIZE][BOARD_SIZE];
        
        for (int i = 0; i < BOARD_SIZE; i++) 
            System.arraycopy(state.board[i], 0, clone.board[i], 0, BOARD_SIZE);       
        return clone;
    }
    

    public int getScore(BoardState state) {
        int score = 0; 
        
        for (int i = 0; i < BOARD_SIZE - 1; i++) {
            for (int j = 0; j < BOARD_SIZE - 1; j++)        	
                if ((state.board[i][j] == state.board[i + 1][j] || state.board[i][j] == state.board[i][j + 1]))  		
                	score++;                         
        }
        return -score;
    }

    
    public BoardState chooseBestNeighbor(List<BoardState> neighbors) {
    	BoardState bestNeighbor = null;
        int bestValue = Integer.MIN_VALUE;

        for (BoardState neighbor : neighbors) {
            int neighborValue = getScore(neighbor);
            if (neighborValue > bestValue) {
                bestValue = neighborValue;
                bestNeighbor = neighbor;
            }
        }
        return bestNeighbor;
    }


    public static void printBoard(BoardState state) {
    	
    	System.out.println(String.valueOf(counter) + ")");
    	
    	for(int i = 0 ; i < BOARD_SIZE; i++)
    		System.out.print("---------");
    	System.out.println();
    	for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(state.board[i][j] + " | ");        
            }
            System.out.println();           
        }
        counter++;
        
        for(int i = 0 ; i < BOARD_SIZE; i++)
    		System.out.print("---------");
        System.out.println("\n");
    }
}