package customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

@EnableFeignClients
@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		
		SpringApplicationBuilder builder = configuration(new SpringApplicationBuilder());
		SpringApplication app = builder.build();
		Environment env = app.run(args).getEnvironment();
		System.out.println("appversion=" + env.getProperty("appversion"));
		System.out.println("port=" + env.getProperty("server.port"));
		System.out.println("context path=" + env.getProperty("server.servlet.contextPath"));
	}
	
	private static SpringApplicationBuilder configuration(SpringApplicationBuilder builder) {
        return builder.sources(CustomerServiceApplication.class);
    }
}
