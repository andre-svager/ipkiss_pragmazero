package com.ebanx.account;

import com.ebanx.account.domain.aggregate.Account;
import com.ebanx.account.domain.EventType;
import com.ebanx.account.domain.aggregate.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class EbanxApplicationTests {

	/*@Autowired
	private AccountService service;*/

	@Test
	void contextLoads() {

	}

	/**
	 * --
	 * # Reset state before starting tests
	 *
	 * POST /reset
	 * 200 OK
	 *
	 **/
	void shouldResetStateBeforeStart(){

	}


	 @Test
	 void shouldGetBalanceForNonExitingAccount(){
		Account account = new Account(999);
		//service.getAccount(account.getId());
		 //Assertions.assertEquals();
		// return 404 0
	  }


	  /**
	 * # Create account with initial balance
	 *
	 * POST /event {"type":"deposit", "destination":"100", "amount":10}
	 *
	 * 201 {"destination": {"id":"100", "balance":10}}
	 *
	 *
	  **/

	@Test
	void shouldCreateAccountWithInitialBalance(){
		Account account = new Account(100);
		Event operation = new Event(EventType.DEPOSIT, null, account, new BigDecimal("10"));
	//	operation.applyEventToAccountOperations(100, 200);
		Assertions.assertEquals(new BigDecimal("10"), account.getBalance());
	}


	 /**
	 * --
	 * # Deposit into existing account
	 *
	 * POST /event {"type":"deposit", "destination":"100", "amount":10}
	 *
	 * 201 {"destination": {"id":"100", "balance":20}}

	 **/
	 @Test
	 void shouldDepositIntoExistingAccount(){

	 }



	 /**
	 * --
	 * # Get balance for existing account
	 *
	 * GET /balance?account_id=100
	 *
	 * 200 20
	  * */

	@Test
	void shouldGetBalanceForExistingAccount(){

	 }

	 /**
	 * # Withdraw from non-existing account
	 *
	 * POST /event {"type":"withdraw", "origin":"200", "amount":10}
	 *
	 * 404 0
	 *
	 **/
	 @Test
	 void shouldWithdrawFromNonExistingAccount(){

	}

	  /**
	  * # Withdraw from existing account
	 *
	 * POST /event {"type":"withdraw", "origin":"100", "amount":5}
	 *
	 * 201 {"origin": {"id":"100", "balance":15}}
	 **/
	@Test
	void shouldWithdrawFromExistingAccount(){

	}



	/** --	 * # Transfer from existing account
	 *
	 * POST /event {"type":"transfer", "origin":"100", "amount":15, "destination":"300"}
	 *
	 * 201 {"origin": {"id":"100", "balance":0}, "destination": {"id":"300", "balance":15}}
	 *
	 *
	 **/
	@Test
	void shouldTransferFromExistingAccount(){

	}

	/**
	 * # Transfer from non-existing account
	 *
	 * POST /event {"type":"transfer", "origin":"200", "amount":15, "destination":"300"}
	 *
	 * 404 0
	 */
	@Test
	void shouldTransferFromNonExistingAccount(){

	}

}
