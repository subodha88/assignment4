package crownandanchor.tests.bugs;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;


import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import crownandanchor.Dice;
import crownandanchor.DiceValue;
import crownandanchor.Game;
import crownandanchor.Player;

public class TestBug1Payout {

	private static final String NAME = "John";
	private static final int STARTING_BALANCE = 100;

	private static final DiceValue PICK_CROWN = DiceValue.CROWN;
	private static final int BET_AMOUNT = 5;
	
	private Player player;
	private Dice d1;
	private Dice d2;
	private Dice d3;
	private Game game;
	
	@Before
	public void setUp() throws Exception {
		//Create player
		player = new Player(NAME, STARTING_BALANCE);
		//Create dice
		d1 = EasyMock.createMock(Dice.class);
		d2 = EasyMock.createMock(Dice.class);
		d3 = EasyMock.createMock(Dice.class);
		
	}

	@After
	public void tearDown() throws Exception {
		player = null;
		d1 = null;
		d2 = null;
		d3 = null;
		game = null;
	}

	@Test
	public void testOneMatch() {
		//Create result under test (one match) with mock dice
		expect(d1.roll()).andReturn(PICK_CROWN).once();
		expect(d1.getValue()).andReturn(PICK_CROWN).atLeastOnce();
		
		expect(d2.roll()).andReturn(DiceValue.ANCHOR).once();
		expect(d2.getValue()).andReturn(DiceValue.ANCHOR).atLeastOnce();
		
		expect(d3.roll()).andReturn(DiceValue.ANCHOR).once();
		expect(d3.getValue()).andReturn(DiceValue.ANCHOR).atLeastOnce();
		
		replay(d1);
		replay(d2);
		replay(d3);
		
		//Create game
		game = new Game(d1, d2, d3);
		
		//Play round
		int result = game.playRound(player, PICK_CROWN, BET_AMOUNT);
		
		//Check result
		verify(d1);
		verify(d2);
		verify(d3);
		
		int expectedResult = BET_AMOUNT;
		assertEquals(expectedResult, result);
		
		//Check player balance
		int expectedBalance = STARTING_BALANCE + expectedResult;

		assertEquals(expectedBalance, player.getBalance());
	}
	
	@Test
	public void testNoMatches() {
		//Create result under test (one match) with mock dice
		expect(d1.roll()).andReturn(DiceValue.ANCHOR).once();
		expect(d1.getValue()).andReturn(DiceValue.ANCHOR).atLeastOnce();
		
		expect(d2.roll()).andReturn(DiceValue.ANCHOR).once();
		expect(d2.getValue()).andReturn(DiceValue.ANCHOR).atLeastOnce();
		
		expect(d3.roll()).andReturn(DiceValue.ANCHOR).once();
		expect(d3.getValue()).andReturn(DiceValue.ANCHOR).atLeastOnce();
		
		replay(d1);
		replay(d2);
		replay(d3);
		
		//Create game
		game = new Game(d1, d2, d3);
		
		//Play round
		int result = game.playRound(player, PICK_CROWN, BET_AMOUNT);
		
		//Check result
		verify(d1);
		verify(d2);
		verify(d3);
		
		int expectedResult = 0;
		assertEquals(expectedResult, result);
		
		//Check player balance
		int expectedBalance = STARTING_BALANCE - BET_AMOUNT;

		assertEquals(expectedBalance, player.getBalance());
	}
	
	@Test
	public void testTwoMatches() {
		//Create result under test (one match) with mock dice
		expect(d1.roll()).andReturn(PICK_CROWN).once();
		expect(d1.getValue()).andReturn(PICK_CROWN).atLeastOnce();
		
		expect(d2.roll()).andReturn(PICK_CROWN).once();
		expect(d2.getValue()).andReturn(PICK_CROWN).atLeastOnce();
		
		expect(d3.roll()).andReturn(DiceValue.ANCHOR).once();
		expect(d3.getValue()).andReturn(DiceValue.ANCHOR).atLeastOnce();
		
		replay(d1);
		replay(d2);
		replay(d3);
		
		//Create game
		game = new Game(d1, d2, d3);
		
		//Play round
		int result = game.playRound(player, PICK_CROWN, BET_AMOUNT);
		
		//Check result
		verify(d1);
		verify(d2);
		verify(d3);
		
		int expectedResult = BET_AMOUNT * 2;
		assertEquals(expectedResult, result);
		
		//Check player balance
		int expectedBalance = STARTING_BALANCE + expectedResult;

		assertEquals(expectedBalance, player.getBalance());
	}
	
	@Test
	public void testThreeMatches() {
		//Create result under test (one match) with mock dice
		expect(d1.roll()).andReturn(PICK_CROWN).once();
		expect(d1.getValue()).andReturn(PICK_CROWN).atLeastOnce();
		
		expect(d2.roll()).andReturn(PICK_CROWN).once();
		expect(d2.getValue()).andReturn(PICK_CROWN).atLeastOnce();
		
		expect(d3.roll()).andReturn(PICK_CROWN).once();
		expect(d3.getValue()).andReturn(PICK_CROWN).atLeastOnce();
		
		replay(d1);
		replay(d2);
		replay(d3);
		
		//Create game
		game = new Game(d1, d2, d3);
		
		//Play round
		int result = game.playRound(player, PICK_CROWN, BET_AMOUNT);
		
		//Check result
		verify(d1);
		verify(d2);
		verify(d3);
		
		int expectedResult = BET_AMOUNT * 3;
		assertEquals(expectedResult, result);
		
		//Check player balance
		int expectedBalance = STARTING_BALANCE + expectedResult;

		assertEquals(expectedBalance, player.getBalance());
	}
	

}
