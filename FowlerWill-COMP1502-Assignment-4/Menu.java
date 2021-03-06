/**
 * Menu class for terminal chess game
 * @author Will Fowler
 * @date 2013 04 10
 */

import java.util.*;
import java.io.*;
public class Menu
{
    private ChessBoard theGame;
    private ChessPiece verifiedOriginPiece, verifiedDestinationPiece;
    private boolean wantToQuit;
    /**
     * Constructor for class
     * @params ChessBoard
     */
    public Menu(ChessBoard aGame)
    {
        theGame = aGame;        
    }
    
    /**
     * selectOrigin sets a verifiedOriginPiece with a reference to a ChessPiece that has
     * passed muster.
     * @param Player integer, 0 for white, 1 for black
     */
    public void selectOrigin(int player)
    {
        int originX;
        int originY;
        Scanner keyboard = new Scanner(System.in);
        boolean moveGood = true;
        wantToQuit = false;
        verifiedOriginPiece = null;
        verifiedDestinationPiece = null;
        
        do
        {
            //Prompt user for position
            System.out.println("Enter Start point x followed by y (e.g. \"1 2\"):");
            originX = keyboard.nextInt();
            originY = keyboard.nextInt();
            
            //see if they want to quit
            if( originX == -1 && originY == -1 )
            {
                wantToQuit = true;
                moveGood = false;
            }
            else
            {
                //ok, they don't want to quit.
                //is the move inbounds?
                if( originX < 0 || originX > 7 ||
                    originY < 0 || originY > 7 )
                {
                    System.out.println("That move is out of bounds");
                    moveGood = false; //outa bounds
                }
                else
                {
                    //is there a piece there?
                    ChessPiece originPiece = theGame.selectPiece( originX, originY );
                    if( originPiece == null )
                    {
                        System.out.println("There's no piece there");
                        moveGood = false; //no piece means that nothing can be done
                    }
                    else
                    {
                        //there is a piece there, is it friendly?
                        if( originPiece.getPlayer() != player )
                        {
                            System.out.println("That's not your piece");
                            moveGood = false; //it is an enemy piece!
                        }
                        else
                        {
                            System.out.println(originPiece + originPiece.getPos() +" Selected");
                            verifiedOriginPiece = originPiece;
                        }
                    }
                }
            }
        }
        while(!moveGood && !wantToQuit);  
    }
    
    /**
     * selectDestination sets a verifiedDestinationPiece with a reference to a ChessPiece that has
     * passed muster.
     * @param Player integer, 0 for white, 1 for black
     */
    public void selectDestination(int player)
    {
        int destinationX, destinationY;
        Scanner keyboard = new Scanner(System.in);
        boolean moveGood = true;

        if(!wantToQuit)
        {
            do
            {
                //Prompt user for position
                System.out.println("Enter end point x followed by y (e.g. \"1 2\"):");
                destinationX = keyboard.nextInt();
                destinationY = keyboard.nextInt();
                
                //see if they want to quit
                if( destinationX == -1 && destinationY == -1 )
                {
                    wantToQuit = true;
                    moveGood = false;
                }
                else
                {
                    //ok, they don't want to quit.
                    //is the move inbounds?
                    if( destinationX < 0 || destinationX > 7 ||
                        destinationY < 0 || destinationY > 7 )
                    {
                        System.out.println("That move is out of bounds");
                        moveGood = false; //outa bounds
                    }
                    else
                    {
                        //is there a piece there
                        ChessPiece destinationPiece = theGame.selectPiece( destinationX, destinationY );
                        if( destinationPiece == null )
                        {
                            if(verifiedOriginPiece.validMove( destinationX, destinationY ))
                            {
                                //here, the move is valid, move the piece and update the board.
                                theGame.removePieceAtLocation(  verifiedOriginPiece.getCol(), 
                                                                verifiedOriginPiece.getRow());
                                verifiedOriginPiece.setCol(destinationX);
                                verifiedOriginPiece.setRow(destinationY);
                                verifiedOriginPiece.updatePieceLocation();
                                moveGood = true;

                            }
                            else
                            {
                                System.out.println("Sorry, that move doesn't work.");
                                moveGood = false; //either piece cannot move that way, or way is blocked
                            }
                        }
                        else
                        {
                            if( destinationPiece.getPlayer() == player )
                            {
                                System.out.println("You already have a piece there");
                                moveGood = false; //friendly piece means that nothing can be done
                            }
                            else
                            {
                                //Here, I'm going to have to validate the move, to see if the piece is
                                //capable of getting there. I should pass in all the data that I have
                                if(verifiedOriginPiece.validMove( destinationX, destinationY ))
                                {
                                    //here, the move is valid, move the piece and update the board.
                                    theGame.removePieceAtLocation(  verifiedOriginPiece.getCol(), 
                                                                    verifiedOriginPiece.getRow());
                                    verifiedOriginPiece.setCol(destinationX);
                                    verifiedOriginPiece.setRow(destinationY);
                                    verifiedOriginPiece.updatePieceLocation();
                                    moveGood = true;
                                    
                                }
                                else
                                {
                                    System.out.println("Sorry, that move doesn't work.");
                                    moveGood = false; //either piece cannot move that way, or way is blocked
                                }
                            }
                        }
                    }
                }
            }
            while(!moveGood && !wantToQuit); 
        }
    }
    
    /**
     * quit - checks the col, row int's for -1 values, indicating the user wishes to quit.
     */
    public boolean quit()
    {
        if( wantToQuit )
        {
            return true;
        }
        else
            return false;
    }
}
