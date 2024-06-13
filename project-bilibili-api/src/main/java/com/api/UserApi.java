package com.api;

import com.domain.JsonResponse;
import com.domain.User;
import com.domain.UserInfo;
import com.service.UserService;
import com.support.UserSupport;
import com.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSupport userSupport;

    /**
     * 查询用户信息
     * @return
     */
    @GetMapping("/Searchusers")
    public JsonResponse<User> getUserInfo(){
        Long userId = userSupport.getCurrentUserId();
        User user = userService.getUserInfo(userId);
        return new JsonResponse<>(user);
    }


    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPublicKey(){
        //获取公钥
        String pk = RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(pk);
    }

    /**
     * 新建用户
     */
    @PostMapping("/Addusers")
    public JsonResponse<String> addUser(@RequestBody User user){
        userService.addUser(user);
        return JsonResponse.success();
    }
    /**
     * 用户登录
     */
    @PostMapping("/user-login")
    public JsonResponse<String> login(@RequestBody User user) throws Exception{
        String token = userService.login(user);
        return new JsonResponse<>(token);
    }

    /**
     * 更新用户
     * @param user
     * @return
     * @throws Exception
     */
    @PutMapping("/UpdateUsers")
    public JsonResponse<String> updateUsers(@RequestBody User user) throws Exception{
        Long userId = userSupport.getCurrentUserId();
        user.setId(userId);
        userService.updateUsers(user);
        return JsonResponse.success();
    }

    @PutMapping("/UpdateUserinfo")
    public JsonResponse<String> updateUserInfos(@RequestBody UserInfo userInfo){
        //从token中获取userId 防止被恶意攻击
        Long userId = userSupport.getCurrentUserId();
        userInfo.setUserId(userId);
        userService.updateUserInfos(userInfo);
        return JsonResponse.success();
    }

}
