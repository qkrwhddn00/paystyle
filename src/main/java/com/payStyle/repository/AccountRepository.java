package com.payStyle.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.payStyle.model.Account;
import com.payStyle.model.Users;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	@Query(value="select sum(balance) as balance from Accounts where userid=:user group by userid", nativeQuery = true)
	int findBalanceByUsers(@Param("user") Users users);
	@Query("select sum(balance)as balance " +
			"from Account " +
			"group by userId, inputdate " +
			"having userId=:user and inputdate=:date")
	int 자산값불러오기(@Param("user")Users user,@Param("date")Date date);
	@Query("select payMethod from Account group by userId , payMethod having userId=:user")
	List<Object[]> payMethod호출 (@Param("user") Users user);
	Account findAllByUsersAndInputdate(Users user,Date date);
	Optional<Account> findByUsersAndPayMethodAndInputdate(@Param("user")Users user,@Param("method")String method,@Param("day")Date day);
	Optional<Account> findByUsersAndInputdate(Users user,Date date);
	List<Account> findByUsers(Users user);
	
}
