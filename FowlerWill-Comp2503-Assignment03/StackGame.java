import java.util.ArrayList;
import java.util.Stack;



/**
 * StackGame by
 * @author wfowl594
 * ...it sorts your stacks!
 */
public class StackGame {
	
	public ConsoleCom com;
	public Board board;
	
	public StackGame() {
		this.com = new ConsoleCom();
	}
	
	public void newBoard(int n) {
		board = new Board(n);
	}
	
	public boolean hasItBeenDone(ArrayList<Board> a, ArrayList<Board> b) {
		boolean foundUnique = true;
		for(int i = 0; i < b.size(); i++) {
			for(int y = 0; y < a.size(); y++) {
				foundUnique = true;
				if(board.compareBoards(a.get(y), b.get(i))) {
					foundUnique = false;
				}
			}
		}
		return foundUnique;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StackGame game = new StackGame();
		int n = game.com.getInput_Int("How many tiles per side? ");
		game.newBoard(n);
		System.out.println(game.board);
		
		Stack<Board> 		usedMoves = new Stack<Board>();
		ArrayList<Board> 	doneMoves = new ArrayList<Board>();
		ArrayList<Board> 	currentMovesPossible = new ArrayList<Board>();
		boolean foundUnique, needToBackTrack;
		
		//seed the stack
		usedMoves.push(game.board);
		doneMoves.add(game.board);
		//seenMoves.add(game.board);
		
		while(!usedMoves.peek().checkWin()) {
			
			//find possible moves
			needToBackTrack = false;
			do {
				currentMovesPossible.clear();
				currentMovesPossible.addAll(usedMoves.peek().findAllPossibleMoves());
				if(currentMovesPossible.size() == 0 || !game.hasItBeenDone(doneMoves, currentMovesPossible) ) {
					usedMoves.pop();
					needToBackTrack = true;
				} else {
					needToBackTrack = false;
				}
			} while(needToBackTrack);
			
			//Choose Move we haven't used
			//tried every way I can think of to use .contains - will not work for me :(
			foundUnique = false;
			for(int i = 0; i < currentMovesPossible.size(); i++) {
				for(int y = 0; y < doneMoves.size(); y++) {
					foundUnique = true;
					if(game.board.compareBoards(doneMoves.get(y), currentMovesPossible.get(i))) {
						foundUnique = false;
					}
				}
				
				if(foundUnique) {
					usedMoves.push(currentMovesPossible.get(i));
					doneMoves.add(currentMovesPossible.get(i));
				}
					
			}
			
			game.board = usedMoves.peek();
			System.out.println(game.board);
			
		}
		System.out.println("final board: " + game.board);
		System.out.println("Final Move List: " + usedMoves);
		System.out.println("Done?");
		
		

	}

}
