package com.lxht.emos;
import com.lxht.emos.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AuthcenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthcenterApplication.class,args);
	}
}
