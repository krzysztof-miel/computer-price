package com.krzysztof.computerprice;

import com.krzysztof.computerprice.entity.Computer;
import com.krzysztof.computerprice.entity.ComputerDTO;
import com.krzysztof.computerprice.entity.Invoice;
import com.krzysztof.computerprice.entity.InvoiceDTO;
import com.krzysztof.computerprice.service.ComputerService;
import com.krzysztof.computerprice.service.InvoiceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.time.LocalDate;

@SpringBootApplication
public class ComputerPriceApplication {


	public static void main(String[] args){

		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(ComputerPriceApplication.class, args);
		ComputerService computerService =
				configurableApplicationContext.getBean(ComputerService.class);
		InvoiceService invoiceService =
				configurableApplicationContext.getBean(InvoiceService.class);

		Invoice invoice1 = invoiceService.createInvoice();
		Invoice invoice2 = invoiceService.createInvoice();

		Computer computer1 = new Computer("dell", LocalDate.of(2022,01,03), 345, invoice1);
		computerService.createComputer(computer1);

		Computer computer2 = new Computer("hp", LocalDate.of(2022,01,03), 543, invoice1);
		computerService.createComputer(computer2);

		Computer computer3 = new Computer("apple", LocalDate.of(2022,01,03), 346,invoice1);
		computerService.createComputer(computer3);

		Computer computer4 = new Computer("dell", LocalDate.of(2022,01,10), 345, invoice2);
		computerService.createComputer(computer4);

		Computer computer5 = new Computer("hp", LocalDate.of(2022,01,10), 543, invoice2);
		computerService.createComputer(computer5);

		Computer computer6 = new Computer("apple", LocalDate.of(2022,01,10), 346, invoice2);
		computerService.createComputer(computer6);


		InvoiceDTO invoiceDTO1 = new InvoiceDTO();
		invoiceDTO1.addToList(new ComputerDTO(computer1));
		invoiceDTO1.addToList(new ComputerDTO(computer2));
		invoiceDTO1.addToList(new ComputerDTO(computer3));

		InvoiceDTO invoiceDTO2 = new InvoiceDTO();
		invoiceDTO2.addToList(new ComputerDTO(computer4));
		invoiceDTO2.addToList(new ComputerDTO(computer5));
		invoiceDTO2.addToList(new ComputerDTO(computer6));


		try {
			JAXBContext context = JAXBContext.newInstance(InvoiceDTO.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			File file1 = new File("C:\\dev\\projects\\computer-price\\computer-price\\src\\main\\resources\\invoice1.xml");
			File file2 = new File("C:\\dev\\projects\\computer-price\\computer-price\\src\\main\\resources\\invoice2.xml");

			marshaller.marshal(invoiceDTO1, file1);
			marshaller.marshal(invoiceDTO2, file2);
			marshaller.marshal(invoiceDTO1, System.out);
			marshaller.marshal(invoiceDTO2, System.out);

		} catch (JAXBException e) {
			System.out.println(e);
		}

		/*
		W celu rozwinięca aplikacji, można by przenieść te dane pozpośrednio do bazy danych wykorzystując migracje
		Dynamiczne tworzenie plików XML z fakturami
		 */
	}
}





