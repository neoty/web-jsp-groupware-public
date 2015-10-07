package groupware.commons;

import groupware.community.controllers.MenuList;
import groupware.dao.CheckDAO;
import groupware.dao.CommunityDAO;
import groupware.dao.MemberDao;
import groupware.dto.BoardDTO;
import groupware.dto.LogDTO;
import groupware.dto.MemberDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Dispatcher extends HttpServlet {

	private Map<String, Object> Maps = new Hashtable<String, Object>();

	/* 톰켓에 올라갈때 한번만 실행됨 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext ctx = getServletContext();
		/* web.xml 읽기 */
		String PathOfUrlMaps = config.getInitParameter("path");
		if (PathOfUrlMaps == null) {
			if (true)
				return;
		}

		/* 콘스트명까지의 실제 물리경로 ex) c:\tomcat\webapps\Elite */
		PathOfUrlMaps = ctx.getRealPath(PathOfUrlMaps);
		System.out.println(PathOfUrlMaps);
		try {
			Properties prop = new Properties();
			FileInputStream fis = null;
			fis = new FileInputStream(PathOfUrlMaps);
			prop.load(fis);
			fis.close();

			Iterator<Object> it = prop.keySet().iterator();
//			System.out
//					.println("========================================== 맵핑 정보 시작 "
//							+ "==========================================");
			int i = 1;
			while (it.hasNext()) {
				String key = (String) it.next();
				String className = prop.getProperty(key);

				/* 컨텐스트 초기화 중 확인된 클래스 목록 */
//				System.out.println(i + "번째   " + key + "	      " + className);

				Class<?> cls = Class.forName(className);
				Object ob = cls.newInstance();

				/* Map 에다가 꽃아넣기 */
				Maps.put(key, ob);
				i++;
			}
//			System.out
//					.println("========================================== 맵핑 정보 끝 "
//							+ "============================================");
		} catch (Exception e) {
			// TODO: handle exception
			/* Error 페이지로 Dispatcher 하기 */
//			System.out.println("패키지 경로 설정 문제");
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
		service(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
		service(request, response);
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long start = System.nanoTime();
		Controller controller = null;
		RequestDispatcher rd = null;
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			String uri = request.getRequestURI();

			/* 혹시 모를 컨텐스트 명 붙는거 대비해서 */
			String ctx = request.getContextPath();

			uri = uri.replaceAll(ctx, "");

			controller = (Controller) Maps.get(uri);

			/* 공통 세션 체크 부분 */
			boolean CheckResult = SessionCheck.getInstance().CommonCheck(
					request, uri);
			if (!CheckResult) {
				JavaScript.getIntance().MessageBackToUrl(request, response,
						"로그인을 먼저해주세요. ", "/index.jsp");
				return;
			} else {

				String[] CheckUrl = uri.split("/");
				if (CheckUrl[1].equals("Config")) {
					/* Config 로 들어오는 디렉토리에 대한 보안 설정 */
					CheckResult = SessionCheck.getInstance()
							.AdminCheck(request);
					if (CheckResult) {
						JavaScript.getIntance().HistoryBack(request, response,
								"관리자만 접근 가능합니다. ");
						return;
					}
				}

				// switch는 1.7 이상부터 문자열 지원 하므로 사용하기 이르다고 판단함 enum 으로 찾아보길
				String dir = CheckUrl[1];
				if (dir.equals("Community")) {
					Common.getIntance().GetCommunityMenu(request);
					request.setAttribute("NAVIGATION_VALUE", "COMMUNITY");
				} else if (dir.equals("Chat")) {
					Common.getIntance().GetUnreadMessage(request);
					request.setAttribute("NAVIGATION_VALUE", "CHAT");
				} else if (dir.equals("Schedule")) {
					List<MemberDTO> list = new ArrayList<MemberDTO>(MemberDao
							.getInstance().GetSameDepMb(
									Integer.parseInt(request.getSession()
											.getAttribute("MB_NO").toString())));
					int my_dep_no = MemberDao.getInstance().GetDepNo(
							Integer.parseInt(request.getSession()
									.getAttribute("MB_NO").toString()));
					request.setAttribute("MEM_LIST", list);
					request.setAttribute("MY_DEP_NO", my_dep_no);
					request.setAttribute("NAVIGATION_VALUE", "SCHEDULE");
				} else if (dir.equals("Config")) {
					request.setAttribute("NAVIGATION_VALUE", "CONFIG");
				} else if (dir.equals("People")) {
					request.setAttribute("NAVIGATION_VALUE", "PEOPLE");
				} else if (dir.equals("Work")) {

					request.setAttribute("NAVIGATION_VALUE", "WORK");

					/*
					 * 아이피 체크
					 */
					if (CheckUrl[2].equals("WorkTime.work")
							|| CheckUrl[2].equals("WorkTimeEventProc.do")) {
						/*
						 * 배포 하면 165 번째줄 주석 해제 할것
						 */
						// System.out.println(request.getRemoteAddr());
						// IPCheck.getIntance().checkIp(request.getRemoteAddr());
						Boolean ipCheck = IPCheck.getIntance().checkIp(
								"211.227.151.174");
						if (!ipCheck) {
							JavaScript.getIntance().HistoryBack(request,
									response, "출퇴근을 할수 없는 아이피 입니다. ");
							return;
						}
					}

				} else if (dir.equals("Main")) {
					request.setAttribute("NAVIGATION_VALUE", "MAIN");
				}
				/* 만약 키값에 해당하는 컨트롤러클래스를 찾지 못했을경우 */
				if (controller == null) {
					/* 에러 페이지로 리다이렉트 */
					response.sendRedirect(ctx + "/error.jsp");
					System.out.println("오류 urlmaps 볼것");
					if (true)
						return;
				}

				/* do 요청에 대한 보안 검사 모든 요청은 post 기준으로 해석함 get 요청은 검증 안됨 */
				String[] refer = uri.split("\\.");
				if (refer[1].equals("do")) {
					String referer = request.getHeader("referer");
					if (referer == null) {
						JavaScript.getIntance().HistoryBack(request, response,
								"정상적으로 접근 해주세요. ");
						return;
					}
				}
				String goingUrl = controller.execute(request, response);
				
				/* 로그기록 */
				HttpSession session = request.getSession();
				String logId = "no id";
				if (session.getAttribute("MB_NO") != null) {
					logId = session.getAttribute("MB_NO").toString();
				}
				LogDTO LogDto = new LogDTO();
				LogDto.setLog_ip(request.getRemoteAddr()); 					// 아이피
				LogDto.setMember_id(logId);									// 멤버 아이디
				LogDto.setLog_subject("After controller execute");			// 제목
				Long end = System.nanoTime();
		        String procTime = (end-start)/1000000000.0+"초";
		        LogDto.setLog_content(goingUrl+"\n걸린시간"+procTime);
		        CheckDAO.getInstance().LOG(LogDto);
		        /* 로그 기록 끝 */
		        
		        
				System.out.println("컨트롤러후 URL" + goingUrl);
				/* 개별 컨트롤러 실행후 전송방식 결정 */
				String[] gotoUrl = goingUrl.split(":");
				if (!(gotoUrl[0].equals("redirect"))
						&& !(gotoUrl[0].equals("void"))
						&& !(gotoUrl[0].equals("js"))) {
					request.getRequestDispatcher(goingUrl).forward(request,
							response);
					return; // tiles 이동
				} else if (gotoUrl[0].equals("void")) {
					return;
				} else if (gotoUrl[0].equals("js")) {
					if (gotoUrl[1].equals("back")) {
						JavaScript.getIntance().HistoryBack(request, response,
								gotoUrl[2]);
						return;
					} else if (gotoUrl[1].equals("url")) {
						JavaScript.getIntance().MessageBackToUrl(request,
								response, gotoUrl[2], gotoUrl[3]);
						return;
					}
				} else {
					response.sendRedirect(ctx + gotoUrl[1]);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("서비스 메서드 에러");
		}

	}

}
