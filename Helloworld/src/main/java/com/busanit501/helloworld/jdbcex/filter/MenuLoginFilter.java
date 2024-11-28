package com.busanit501.helloworld.jdbcex.filter;

import com.busanit501.helloworld.jdbcex.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/menu/*"})
@Log4j2
public class MenuLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter, /menu/ 하위로 들어오는 모든 url에 대해서 로그인 체크 함.");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if(session.isNew()){
            log.info("최초로 서버에 요청 함");
            response.sendRedirect("/menuLogin");
            return;
        }

        if(session.getAttribute("MenuloginInfo") == null){
            log.info("2번째 이후로 서버로 요청, 하지만 로그인 정보 없는 경우");
            response.sendRedirect("/menuLogin");
            return;
        }

        if(session.getAttribute("MenuloginInfo") != null){
            MemberDTO memberDTO = (MemberDTO) session.getAttribute("MenuloginInfo");
            log.info("result: "+memberDTO);
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
