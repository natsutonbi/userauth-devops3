// package com.example.demo.security.filter;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.lang.Nullable;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.example.demo.user.dao.loginUserMapper;
// import com.example.demo.user.dao.entity.userAccountInfo;

// import jakarta.servlet.http.HttpServletRequest;


// public class addSaltFilter extends UsernamePasswordAuthenticationFilter{

//     @Autowired
//     loginUserMapper infoMapper;

//     @Override
//     @Nullable
//     protected String obtainPassword(HttpServletRequest request) {
//         String username=obtainUsername(request);
//         List<userAccountInfo> infos=infoMapper.getByUsername(username);
//         userAccountInfo info=null;
//         if(infos!=null) info=infos.get(0);
//         String pwd_salt=super.obtainPassword(request);
//         if(info!=null) pwd_salt+=info.salt;
//         return pwd_salt;
//     }
    
// }
