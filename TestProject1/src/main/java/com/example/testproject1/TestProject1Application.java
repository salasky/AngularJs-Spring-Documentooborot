package com.example.testproject1;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.model.OutgoingDocument;
import com.example.testproject1.model.TaskDocument;
import com.example.testproject1.service.docfactory.IncomingDocumentFactory;
import com.example.testproject1.service.docfactory.OutgoingDocumentFactory;
import com.example.testproject1.service.docfactory.TaskDocumentFactory;
import com.example.testproject1.shell.TaskDocumentShell;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class TestProject1Application {


	public static void main(String[] args) {
		var context=SpringApplication.run(TestProject1Application.class, args);
	/*	System.out.println("----------------------------------------------------------------------");
		System.out.println("---------------------Сгенерированные документы---------------------");
		//Генерация поручений
		var taskFactory=context.getBean(TaskDocumentFactory.class);
		for (int i=0;i<10;i++){
			BaseDocument taskDoc = taskFactory.createDocument();
				if(taskDoc!=null)
					System.out.println(taskDoc);
		}
		//Генерация входящих сообщений
		var incomingDocFactory=context.getBean(IncomingDocumentFactory.class);
		for (int i=0;i<10;i++){
			BaseDocument incomingDoc = incomingDocFactory.createDocument();
			if(incomingDoc!=null)
				System.out.println(incomingDoc);
		}
		//Генерация исходящих сообщений
		var outgoingFactory=context.getBean(OutgoingDocumentFactory.class);
		for (int i=0;i<10;i++){
			BaseDocument outgoingDoc = outgoingFactory.createDocument();
			if(outgoingDoc!=null)
				System.out.println(outgoingDoc);

		}
		System.out.println("------------------------------------------------");
		System.out.println("---------------------Отчет---------------------");

		Map<String,List<String>> totalMap=new TreeMap<>();

		for (BaseDocument basedoc:TaskDocumentShell.documentList
			 ) {
			//Если не существует запись для данного автора
				if(!totalMap.containsKey(basedoc.getDocumentAuthor())){

					List<String> list= new ArrayList<>();
					//Если это поручение
					if(basedoc instanceof TaskDocument){
						list.add("Поручение "+basedoc.getId()+" от "+basedoc.getDocumentData()+". "+basedoc.getDocumentName()+"\n");
					}
					//Если документ входящий
					if(basedoc instanceof IncomingDocument){
						list.add("Входящий "+basedoc.getId()+" от "+basedoc.getDocumentData()+". "+basedoc.getDocumentName()+"\n");
					}
					//Если документ исходящий
					if(basedoc instanceof OutgoingDocument){
						list.add("Исходящий   "+basedoc.getId()+" от "+basedoc.getDocumentData()+". "+basedoc.getDocumentName()+"\n");
					}
					totalMap.put(basedoc.getDocumentAuthor(), list);
				}

				else {
					//Ecли существуют документы данного автора
					var oldlist = totalMap.get(basedoc.getDocumentAuthor());

					if (basedoc instanceof TaskDocument){
					oldlist.add("Поручение " + basedoc.getId() + " от " + basedoc.getDocumentData() + ". " + basedoc.getDocumentName()+"\n");
					}

					if (basedoc instanceof IncomingDocument){
						oldlist.add("Входящий " + basedoc.getId() + " от " + basedoc.getDocumentData() + ". " + basedoc.getDocumentName()+"\n");
					}

					if (basedoc instanceof OutgoingDocument){
						oldlist.add("Исходящий " + basedoc.getId() + " от " + basedoc.getDocumentData() + ". " + basedoc.getDocumentName()+"\n");
					}

					Collections.sort(oldlist);
					totalMap.put(basedoc.getDocumentAuthor(), oldlist);
				}
		}

		for (Map.Entry<String, List<String>> entry : totalMap.entrySet()) {
			System.out.println(entry.getKey() + ":\n" + entry.getValue());
		}*/



	}

}
