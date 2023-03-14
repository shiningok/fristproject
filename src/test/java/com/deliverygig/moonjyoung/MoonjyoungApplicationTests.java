package com.deliverygig.moonjyoung;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.deliverygig.moonjyoung.entity.account.CustomerInfoEntity;
import com.deliverygig.moonjyoung.repository.account.CustomerRepository;
import com.deliverygig.moonjyoung.repository.delivery.PickUpAreaRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivInfoRepository;
import com.deliverygig.moonjyoung.repository.image.PickUpAreaImageRepository;
import com.deliverygig.moonjyoung.repository.review.ReviewRepository;
import com.deliverygig.moonjyoung.service.BasketService;
import com.deliverygig.moonjyoung.service.MasterService;
import com.deliverygig.moonjyoung.vo.delivery.PickUpAreaVO;
import com.deliverygig.moonjyoung.vo.mycart.AddBasketMenuOptionVO;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MoonjyoungApplicationTests {
	@Autowired PickUpAreaRepository pickUpAreaRepository;
	@Autowired UnivInfoRepository univInfoRepository;
	@Autowired CustomerRepository cRepo;
	@Autowired PickUpAreaImageRepository pickUpAreaImageRepository;

	@Transactional
	@Test
	void customerAccountAdd() {
		CustomerInfoEntity account = CustomerInfoEntity.builder().ciId("wrko613").ciPwd("1234").ciName("이영은")
				.ciNickName("영니").ciPhone("010-4944-5209").ciJoinDt(LocalDate.now()).ciEmail("duddms613@naver.com")
				.ciUiSeq(1L).ciBirthday(LocalDate.of(1997, 6, 13)).ciStatus(1).build();
		cRepo.save(account);
		log.info("accout={}", account);
	}
	@Test
		void testLogin() {
			String id = "wrk613";
			String pwd = "duddms4944";
			CustomerInfoEntity loginUser = cRepo.findByCiIdAndCiPwd(id, pwd);
			assertNotEquals(loginUser, null); // 로그인 유저가 null과 같지 않으면 통과
		}
		@Test
		void testLogOut() {

		}

		
	@Test
	public void addUniv() { // 새로운 대학 추가 test
		PickUpAreaVO vo = new PickUpAreaVO();
		vo.setUiName("testname");
		univInfoRepository.save(vo.toUnivInfoEntity());
	}
	@Autowired MasterService masterService;
	@Test
	public void addPickUpArea() { // 새로운 수령장소 추가 test
		masterService.addPickUpArea("연세대학교", "testpickupareaname");
	}
	@Test
	public void checkClosedDay() {
		LocalDate now = LocalDate.now();
        int dayOfWeekValue = now.getDayOfWeek().getValue();
		System.out.println(dayOfWeekValue);
	}
	@Test
	public void PickUpAreaImageEntity() {
		 System.out.println(pickUpAreaImageRepository.findAll());
		
	}

	// 구매내역 정보 변동여부 테스트
	@Autowired BasketService basketService;
	@Test
	public void checkBasketInfoUpdate() {
		AddBasketMenuOptionVO vo = new AddBasketMenuOptionVO();
		vo.setCiSeq(62L);
		vo.setStdSeq(3L);
		vo.setFmiSeq(83L);
		List<Long> list = new ArrayList<Long>();
		list.add(2L);
		list.add(8L);
		list.add(22L);
		list.add(24L);
		vo.setFdoSeqList(list);
		vo.setCount(5);
		basketService.getMenuOptions(vo);
	}

	@Test // 시간비교 테스트
	public void isAfterLocalTime() {
        LocalTime closeTime = LocalTime.of(16, 50, 00);
        LocalTime currentTime =  LocalTime.now();

		System.out.println(closeTime.isBefore(currentTime));
		System.out.println(closeTime.isAfter(currentTime));
	}

	@Test // localdatetime 변수 뽑기 테스트
	public void ldtTest() {
		LocalDate now = LocalDate.now();
		LocalDate lastDay = LocalDate.of(2022, 12, 31);
		System.out.println("===================================");
		System.out.println(now);
		System.out.println(now.getDayOfYear());
		System.out.println(now.getDayOfMonth());
		System.out.println(now.getYear());
		System.out.println(lastDay.isBefore(now));
		System.out.println("=========================");
		LocalDateTime nowTime = LocalDateTime.now();
		LocalDate nowDate = LocalDate.of(nowTime.getYear(), nowTime.getMonthValue(), nowTime.getDayOfMonth());
		System.out.println(lastDay.isBefore(nowDate));
		System.out.println(lastDay.isBefore(nowTime.toLocalDate()));
	}
	
	@Autowired ReviewRepository rRepo;
	@Test // reviewcount 테스트
	public void reviewCountTest() {
		System.out.println(rRepo.countBySiSeq(2L));
		System.out.println(rRepo.findAvgRiScoreBySiSeq(2L));
	}

	// @Test // 레코드 테스트
	// public record TestVO(String a, String b {}
	// public void recordTest() {
	// 	TestVO testVO = new TestVO("fsefse", "fsefse");
	// 	testVO.a();
	// 	testVO.b();
	// }

}
