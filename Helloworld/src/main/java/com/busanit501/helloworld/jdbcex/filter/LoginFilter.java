package com.busanit501.helloworld.jdbcex.filter;


import com.busanit501.helloworld.jdbcex.dto.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//톰캣 서버에서 한글로 입력된 내용 UTF8로 변환해서 보내기.
@WebFilter(urlPatterns = {"/todo/*"})
@Log4j2
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter, /todo/ 하위로 들어오는 모든 url에 대해서 로그인 체크 함.");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if(session.isNew()){
            log.info("최초로 서버에 요청 함");
            response.sendRedirect("/login");
            return;
        }

        if(session.getAttribute("loginInfo") != null){
            log.info("2번째 이후로 서버로 요청, 하지만 로그인 정보 없는 경우");
//            response.sendRedirect("/login");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Cookie findCookie = findCookie(request.getCookies(), "rememberMe");
        if(findCookie == null) {
            response.sendRedirect("/login");
            return;
        }
        String getUuid = findCookie.getValue();

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.getMemberWithUuidService(getUuid);
            log.info("memberDTO : ", memberDTO);

            if(memberDTO == null) {
                throw new Exception("쿠키값이 유효하지 않습니다.");
            }
            session.setAttribute("loginInfo", memberDTO);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/login");
        }


        if(session.getAttribute("loginInfo") != null){
            MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginInfo");
            log.info("result: "+memberDTO);
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    private Cookie findCookie(Cookie[] cookies, String name) {
        Cookie findCookie = null;
        if(cookies != null && cookies.length > 0) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals(name)) {
                    findCookie = cookie;
                    break;
                }
            }
        }
        return  findCookie;
    }


}
