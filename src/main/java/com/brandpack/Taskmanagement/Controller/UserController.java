package com.brandpack.Taskmanagement.Controller;



import com.brandpack.Taskmanagement.dao.UserDao;
import com.brandpack.Taskmanagement.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDao udao;

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public String HomePage(){
        return "Welcome Page";
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userReg", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> UserRegistration(@RequestBody UserModel um){
        HashMap<String, String> hm = new HashMap<>();
        List<UserModel> result = (List<UserModel>) udao.FindUser(um.getUsername());
        if(result.size() !=0){
            hm.put("status","failed");
        }else{
            udao.save(um);
            hm.put("status","success");
        }
        return hm;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userLogin", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> UserLogin(@RequestBody UserModel um){
        List<UserModel> result = (List<UserModel>) udao.UserLogin(um.getUsername(), um.getPassword());
        HashMap<String, String> hm = new HashMap<>();
        if(result.size() == 0){
            hm.put("status","failed");
        }else{
            hm.put("status","success");
            hm.put("userId",String.valueOf(result.get(0).getId()));
        }
        return hm;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userById", consumes = "application/json", produces = "application/json")
    public List<UserModel> FindUserById(@RequestBody UserModel um){
        List<UserModel> result = (List<UserModel>) udao.FindUserById(String.valueOf(um.getId()));
        return result;
    }

}