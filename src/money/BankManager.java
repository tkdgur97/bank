package money;

import java.util.ArrayList;
import java.util.Scanner;

public class BankManager {
	Scanner sc = new Scanner(System.in);
	String id;
	BankDTO dto;

	public BankManager() throws Exception {

		System.out.println("은행 고객 관리 프로그램");
		while (true) {
			System.out.println("--------------------------------------------------------------");
			System.out.println("1. 고객 정보 입력\t2.고객 정보 수정\t3.고객 탈퇴\t4.전체고객리스트");
			System.out.print("번호 입력(0번 종료) >>");
			int num = sc.nextInt();
			System.out.println("--------------------------------------------------------------");

			// 고객 정보 입력
			if (num == 1) {
				insertMember();

				// 고객 전화번호 수정
			} else if (num == 2) {
				updateMember();

				// 고객 탈퇴
			} else if (num == 3) {
				deleteMember();

				// 전체 고객 리스트
			} else if (num == 4) {
				allMemberList();

			} else if (num == 0) {
				break;
			} else {
				System.out.println("메뉴에 있는 번호를 입력하세요");
			}

		} // while end
		System.out.println("은행 고객 관리 프로그램 종료");
	}

	// 전체 고객 정보 리스트
	public void allMemberList() throws Exception {
		ArrayList<BankDTO> list = new BankDAO().allMemberInfo();
		System.out.println("ID\tNAME\tAGE\tTEL");
		for (int i = 0; i < list.size(); i++) {
			dto = list.get(i);
			System.out.println(dto.getId() + "\t" + dto.getName() + "\t" + dto.getAge() + "\t" + dto.getTel());
		}
	}

	// 회웥 탈퇴
	public void deleteMember() throws Exception {
		System.out.print("삭제할 아이디 >>");
		id = sc.next();
		if (new BankDAO().idCheck(id)) {
			new BankDAO().memberDelete(id);
		} else {
			System.out.println("해당 아이디가 없습니다.");
		}

	}

	// 고객 정보 수정
	public void updateMember() throws Exception {
		System.out.print("전화번호 수정할 아이디 입력>>");
		id = sc.next();

		// 해당 아이디 여부 확인
		if (new BankDAO().idCheck(id)) {

			// 전화번호 수정
			System.out.print("바꾸실 전화번호 >>");
			String tel = sc.next();
			new BankDAO().updateMember(id, tel);
		} else {
			System.out.println("해당 아이디가 없습니다.");
		}
	}

	// 고객 정보 입력
	public void insertMember() throws Exception {
		System.out.print("아이디 입력 >>");
		id = sc.next();

		// 아이디 중복 확인
		if (!(new BankDAO().idCheck(id))) {
			dto = new BankDTO();
			dto.setId(id);
			System.out.print("이름 입력 >>");
			dto.setName(sc.next());
			System.out.print("나이 입력 >>");
			dto.setAge(sc.nextInt());
			System.out.print("전화번호 입력 >>");
			dto.setTel(sc.next());
			new BankDAO().insertMember(dto);
		} else {
			System.out.println("해당 아이디가 존재합니다.");
		}

	}

	public static void main(String[] args) throws Exception {
		new BankManager();
	}

}
