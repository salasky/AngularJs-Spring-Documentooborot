package com.example.testproject1;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.model.TaskDocument;
import com.example.testproject1.service.Docfactory.DocFactory;
import com.example.testproject1.service.Docfactory.IncomingDocumentFactory;
import com.example.testproject1.service.Docfactory.OutgoingDocumentFactory;
import com.example.testproject1.service.Docfactory.TaskDocumentFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TestProject1Application {


	public static void main(String[] args) {
		var context=SpringApplication.run(TestProject1Application.class, args);


/*		var taskFactory=context.getBean(TaskDocumentFactory.class);
		for (int i=0;i<10;i++){
			BaseDocument taskDoc = taskFactory.createDocument();
				if(taskDoc!=null)
					System.out.println(taskDoc);
		}

		var incomingDocFactory=context.getBean(IncomingDocumentFactory.class);
		for (int i=0;i<10;i++){
			BaseDocument incomingDoc = incomingDocFactory.createDocument();
			if(incomingDoc!=null)
				System.out.println(incomingDoc);
		}*/

		var outgoingFactory=context.getBean(OutgoingDocumentFactory.class);
		for (int i=0;i<10;i++){
			BaseDocument outgoingDoc = outgoingFactory.createDocument();
			if(outgoingDoc!=null)
				System.out.println(outgoingDoc);
		}



	}

}
