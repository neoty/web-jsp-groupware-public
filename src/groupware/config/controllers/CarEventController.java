package groupware.config.controllers;

import groupware.commons.Controller;
import groupware.dao.CarDAO;
import groupware.dto.CarListDTO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarEventController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		

		/* 차량 추가 일경우 */
		String mode = request.getParameter("mode");
		if (mode.equals("CREATE")) {
			/* 기본 파라메터 검사 */
			String number = request.getParameter("number");
			if (number == null || number.equals("")) {
				return "js:back:차량 번호를 입력해주세요. ";
			}

			String model = request.getParameter("model");
			if (model == null || model.equals("")) {
				return "js:back:차량 모델을 입력해주세요. ";
			}
			String mileage = request.getParameter("mileage");
			String use = request.getParameter("use");
			
			CarListDTO DTO = new CarListDTO();
			DTO.setCl_number(number);
			DTO.setCl_model(model);
			DTO.setCl_mileage(Integer.parseInt(mileage));
			DTO.setCl_use(Integer.parseInt(use));
			int af = CarDAO.getInstance().checkCarDup(DTO);
			if (af > 0) {
				return "js:back:이미 존재하는 차량 번호 입니다.";
			} else {
				af = CarDAO.getInstance().carAdd(DTO);
				if (af > 0) {
					return "redirect:/Config/Car.config";
				}
			}
		}
		
		
		if (mode.equals("MODIFY")) {
			String number = request.getParameter("number");
			if (number == null || number.equals("")) {
				return "js:back:차량 번호는 공백일수 없습니다. ";
			}
			
			String model = request.getParameter("model");
			if (model == null || mode.equals("")) {
				return "js:back:차량 모델은 공백일수 없습니다. ";
			}
			
			String mileage = request.getParameter("mileage");
			if (mileage == null || mileage.equals("")) {
				return "js:back:주행거리를 입력해주세요. ";
			} else {
				int tem = Integer.parseInt(mileage);
				int length = (int) (Math.log10(tem)+1);
				if (length > 9) {
					return "js:back:주행거리의 범위가 너무 큽니다. ";
				}
			}

			String use = request.getParameter("use");
			String cl_no = request.getParameter("cl_no");
			
			
			CarListDTO DTO = new CarListDTO();
			DTO.setCl_no(Integer.parseInt(cl_no));
			DTO.setCl_number(number);
			DTO.setCl_model(model);
			DTO.setCl_use(Integer.parseInt(use));
			DTO.setCl_mileage(Integer.parseInt(mileage));
			int af = CarDAO.getInstance().updateCar(DTO);
			if (af > 0) {
				return "js:back:차량 정보가 수정 되었습니다. ";
			}
		}
		
		
		if (mode.equals("DELETE")) {
			String cl_no = request.getParameter("cl_no");
			if (cl_no.equals("") || cl_no == null) {
				return "js:back:잘못된 접근입니다. ";
			}
			int af = CarDAO.getInstance().deleteCar(Integer.parseInt(cl_no));
			if (af > 0) {
				return "js:url:차량 정보가 삭제 되었습니다. :/Config/Car.config";
			}
		}
		
		return null;
	}

}
