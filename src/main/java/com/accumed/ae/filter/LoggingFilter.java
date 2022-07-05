package com.accumed.ae.filter;
import com.accumed.ae.history.HistoryServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Component
@ComponentScan("com.accumed.ae")
public class LoggingFilter extends OncePerRequestFilter {
    private final static Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    private final HistoryServices historyServices;
    @Autowired
    public LoggingFilter(HistoryServices historyServices) {
        this.historyServices = historyServices;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);

        Long startTime = System.currentTimeMillis();
        Long timeTaken = System.currentTimeMillis() - startTime;
        filterChain.doFilter(contentCachingRequestWrapper,contentCachingResponseWrapper);
        String requestBody = getStringValue(contentCachingRequestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        String responseBody = getStringValue(contentCachingResponseWrapper.getContentAsByteArray(), response.getCharacterEncoding());
//        String remoteAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//                .getRequest().getRemoteAddr();
        String clientID;
        logger.info(
                "filter logs :Params = {};",
                request.getParameter("clientID")

        );
        String responseBod = contentCachingResponseWrapper.getResponse().toString();
        contentCachingResponseWrapper.copyBodyToResponse();
        LocalDateTime lt = LocalDateTime.now();
        historyServices.submitRequest(
                request.getParameter("actionType"),
                request.getParameter("clientID"),
                request.getRequestURI(),
                request.getMethod(),
                request.getRemoteAddr(),
                response.getStatus(),
                requestBody,
                lt,
                responseBody


        );
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray,0,contentAsByteArray.length,characterEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
