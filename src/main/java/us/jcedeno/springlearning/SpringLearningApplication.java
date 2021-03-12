package us.jcedeno.springlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLearningApplication.class, args);

	}

	@GetMapping
	public String landingPage() {
		return Page
				.create("Hynix.",
						"A quick example of how springboot works." + 
						HTML_ELEMENT.LINE_BREAK +
						 "The current time is " + System.currentTimeMillis() + ".")
				.toHtmlWebsite();
	}


	/**
	 * Quick enum to represent html elements in java 
	 */
	enum HTML_ELEMENT{
		LINE_BREAK("<br>");

		String elementName;

		HTML_ELEMENT(String elementName){
			this.elementName = elementName;
		}

		@Override
		public String toString(){
			return elementName; 
		}
	}

}
