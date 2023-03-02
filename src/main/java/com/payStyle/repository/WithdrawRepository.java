package com.payStyle.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.payStyle.model.Deposit;
import com.payStyle.model.Users;
import com.payStyle.model.Withdraw;
import com.sun.istack.Nullable;

public interface WithdrawRepository extends JpaRepository<Withdraw, Integer>, JpaSpecificationExecutor<Withdraw>{

	Withdraw findAllById(int id);
	@Query("select sum(value) from Withdraw  group by payMethod having payMethod=:sBankName")
	int findValueByBank(@Param("sBankName") String bankName);

	@Query("select sum(value) from Withdraw group by  category having category=:sCate")
	int findValueByCategory(@Param("sCate") String cate);
	
	@Nullable
	@Query("select sum(value) from Withdraw where createDate between TO_DATE(:startDate1,'yyyy/mm/dd') and TO_DATE(:endDate1,'yyyy/mm/dd')")
	Integer findValueBycreateDate(@Param("startDate1") String startDate1,@Param("endDate1") String endDate1);
	
	@Query(value="select sum(value) as value " +
			"from Withdraws "+ 
			"where userid=:user and to_char(inputdate, 'YYYY-MM')=to_char(:start,'YYYY-MM')" +
			"group by userid,to_char(inputdate, 'YYYY-MM')" , nativeQuery = true )
	int 이번달총지출(@Param("start")Date start,@Param("user")Users user);
	@Query(value="select sum(value) as value, TO_CHAR(inputdate,'YYYY-MM') as YMdate " +
			"from Withdraws "+ 
			"where userid=:user and to_char(inputdate, 'YYYY')=to_char(:start,'YYYY')" +
			"group by userid,to_char(inputdate, 'YYYY-MM') " +
			"order by to_char(inputdate, 'YYYY-MM') desc" , nativeQuery = true)
	List<Object[]> 월별지출(@Param("user")Users user,@Param("start")Date startYear);
	@Query("select sum(value) as big , category from Withdraw GROUP BY users , category having users = :user order by big desc")
	List<Object[]> 최다지출(@Param("user")Users user);
	//월별로 로그인한사람 구분한 value합계
	@Query("SELECT sum(value) FROM Withdraw WHERE to_char(createDate,'MM')=:addMonth AND to_char(createDate,'yyyy')=:startDate1 AND userid=:userid")
	Integer findValueMonthBycreateDate(@Param("startDate1") String startDate1,@Param("addMonth") String addMonth,@Param("userid") int userid);
	
	@Query("SELECT sum(value) FROM Withdraw WHERE to_char(createDate,'MM')=:addMonth AND to_char(createDate,'yyyy')=:startDate1 AND userid=:userid AND category='식비'")
	Integer findValueMonthFoodBycreateDate(@Param("startDate1") String startDate1,@Param("addMonth") String addMonth,@Param("userid") int userid);
	
	@Query("SELECT sum(value) FROM Withdraw WHERE to_char(createDate,'MM')=:addMonth AND to_char(createDate,'yyyy')=:startDate1 AND userid=:userid AND category='주거'")
	Integer findValueMonthHouseBycreateDate(@Param("startDate1") String startDate1,@Param("addMonth") String addMonth,@Param("userid") int userid);

	@Query("SELECT sum(value) FROM Withdraw WHERE to_char(createDate,'MM')=:addMonth AND to_char(createDate,'yyyy')=:startDate1 AND userid=:userid AND category='교통'")
	Integer findValueMonthTransportBycreateDate(@Param("startDate1") String startDate1,@Param("addMonth") String addMonth,@Param("userid") int userid);
	
	@Query("SELECT sum(value) FROM Withdraw WHERE to_char(createDate,'MM')=:addMonth AND to_char(createDate,'yyyy')=:startDate1 AND userid=:userid AND category='교육'")
	Integer findValueMonthEduBycreateDate(@Param("startDate1") String startDate1,@Param("addMonth") String addMonth,@Param("userid") int userid);

	@Query("SELECT sum(value) FROM Withdraw WHERE to_char(createDate,'MM')=:addMonth AND to_char(createDate,'yyyy')=:startDate1 AND userid=:userid AND category='세금'")
	Integer findValueMonthTaxBycreateDate(@Param("startDate1") String startDate1,@Param("addMonth") String addMonth,@Param("userid") int userid);

	@Query("SELECT sum(value) FROM Withdraw WHERE to_char(createDate,'MM')=:addMonth AND to_char(createDate,'yyyy')=:startDate1 AND userid=:userid AND category='회비'")
	Integer findValueMonthDuesBycreateDate(@Param("startDate1") String startDate1,@Param("addMonth") String addMonth,@Param("userid") int userid);

	@Query("SELECT sum(value) FROM Withdraw WHERE to_char(createDate,'MM')=:addMonth AND to_char(createDate,'yyyy')=:startDate1 AND userid=:userid AND category='의류'")
	Integer findValueMonthClothesBycreateDate(@Param("startDate1") String startDate1,@Param("addMonth") String addMonth,@Param("userid") int userid);
	
	List<Withdraw> findByCreateDateBetween(String 시작날짜,String 끝날짜);


	List<Withdraw> findByUsers(Users user);

	

	 //createdate like '____-'||:monthly||'%'

	//,@Param("monthly") String monthly,@Param("checkDate") String checkDate

}
