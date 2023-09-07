package com.backy;


import com.backy.constant.ItemSellStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
		/*ApplicationContext context = GetContext.getAppContext();
		System.out.println();
		for(String con : context.getBeanDefinitionNames()){
			System.out.println(con);
		}*/

	ItemSellStatus sale = ItemSellStatus.SELL;
	}


}
