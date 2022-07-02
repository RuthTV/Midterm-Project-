package com.ironhack.Midterm.Project;

import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.accounts.StudentChecking;
import com.ironhack.Midterm.Project.model.address.Address;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.sql.Date;

@SpringBootApplication
@Profile("!test")
public class MidtermProjectApplication {


	public static void main(String[] args) {

		SpringApplication.run(MidtermProjectApplication.class, args);
//		Admin username Lorena Pardo, password 000000
//		AccountHolder username Ruth Telleria, password 123456
//		AccountHolder username Julen Telleria, password 654321
//		StudentChecking password abcdef
//		Checking password fghijk
//		CreditCard password fedcba
//		Saving password 098765

	}

}
