package com.payStyle.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.payStyle.model.Deposit;
import com.payStyle.model.Users;
import com.sun.istack.Nullable;

public interface DepositRepository extends JpaRepository<Deposit, Integer>, JpaSpecificationExecutor<Deposit>{

	@Query("select sum(profit) from Deposit  group by payMethod having payMethod=:nBankName")
	int findProfitByBank(@Param("nBankName") String bankName);
	Deposit findAllById(int id);
	@Query("select sum(profit) from Deposit group by  category having category=:nSortuserId")
	int findProfitByCategory(@Param("nSortuserId") String sort);
	@Query("select sum(profit) as profit, TO_CHAR(inputdate,'YYYY-MM') as YMdate " +
			"from Deposit group by users,inputdate " +
			"having users=:user and inputdate between :start and :end " +
			"order by inputdate desc")
	List<Object[]> 월별수익(@Param("user")Users user,@Param("start")Date startYear, @Param("end")Date endYear);
	@Query("select sum(profit) as big , category from Deposit GROUP BY userId , category having userId = :user order by big desc")
	List<Object[]> 수익카테고리(@Param("user")Users user);
	@Query(value="select sum(profit) "
			+ "from Deposits "
			+ "where userid=:user and to_char(inputdate, 'YYYY-MM')=to_char(:start,'YYYY-MM')" +
			"group by userid,to_char(inputdate, 'YYYY-MM')" , nativeQuery = true )
	int 이번달총수익(@Param("start") Date start, @Param("user")Users user);
	@Nullable
	@Query("select sum(profit) from Deposit where createDate between TO_DATE(:startDate1,'yyyy/mm/dd') and TO_DATE(:endDate1,'yyyy/mm/dd')")
	Integer findValueBycreateDate(@Param("startDate1") String startDate1,@Param("endDate1") String endDate1);
	
	
	@Query("SELECT sum(profit) FROM Deposit WHERE to_char(createDate,'MM')=:addMonth AND to_char(createDate,'yyyy')=:startDate1 AND userid=:userid")
	Integer findProfitMonthBycreateDate(@Param("startDate1") String startDate1,@Param("addMonth") String addMonth,@Param("userid") int userid);

	@Query("SELECT sum(profit) FROM Deposit WHERE to_char(createDate,'MM')=:addMonth AND to_char(createDate,'yyyy')=:startDate1 AND userid=:userid AND category='월급'")
	Integer findProfitMonthSalaryBycreateDate(@Param("startDate1") String startDate1,@Param("addMonth") String addMonth,@Param("userid") int userid);

	@Query("SELECT sum(profit) FROM Deposit WHERE to_char(createDate,'MM')=:addMonth AND to_char(createDate,'yyyy')=:startDate1 AND userid=:userid AND category='주식/투자'")
	Integer findProfitMonthInvestdBycreateDate(@Param("startDate1") String startDate1,@Param("addMonth") String addMonth,@Param("userid") int userid);

	@Query("SELECT sum(profit) FROM Deposit WHERE to_char(createDate,'MM')=:addMonth AND to_char(createDate,'yyyy')=:startDate1 AND userid=:userid AND category='용돈'")
	Integer findProfitMonthPinmoneyBycreateDate(@Param("startDate1") String startDate1,@Param("addMonth") String addMonth,@Param("userid") int userid);

	@Query("SELECT sum(profit) FROM Deposit WHERE to_char(createDate,'MM')=:addMonth AND to_char(createDate,'yyyy')=:startDate1 AND userid=:userid AND category='기타'")
	Integer findProfitMonthEtcBycreateDate(@Param("startDate1") String startDate1,@Param("addMonth") String addMonth,@Param("userid") int userid);

	List<Deposit> findByCreateDateBetween(String 시작날짜,String 끝날짜);


	List<Deposit> findByUsers(Users user);


}
