package com.tmt.TaskManagementTool.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.UserService;

import org.springframework.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.tmt.TaskManagementTool.security.SecurityConstants.HEADER_STRING;
import static com.tmt.TaskManagementTool.security.SecurityConstants.TOKEN_PREFIX;;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired 
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

                    String jwt = getJWTFromRequest(request);
        
                    if(StringUtils.hasText(jwt)&& jwtTokenProvider.validateToken(jwt)){
                        ObjectId userId = jwtTokenProvider.getUserIdFromJWT(jwt);
                        Optional<User> userDetails = userService.getUserById(userId);
        
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, Collections.emptyList());
        
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
        
                    }
        
                }catch (Exception ex){
                    logger.error("Could not set user authentication in security context", ex);
                }
        
        
                filterChain.doFilter(request, response);
        
            
    }

    private String getJWTFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(HEADER_STRING);

        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }
    
}
