package com.example.testproject1.shell;


import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.model.OutgoingDocument;
import com.example.testproject1.model.TaskDocument;
import com.example.testproject1.service.DocSave.DocSave;
import com.example.testproject1.service.docfactory.IncomingDocumentFactory;
import com.example.testproject1.service.docfactory.OutgoingDocumentFactory;
import com.example.testproject1.service.docfactory.TaskDocumentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.*;

@ShellComponent
public class TaskDocumentShell {
    //Хранятся все документы.Сохранение происходят в Builder-ax (.build())
    public static List<BaseDocument> documentList=new ArrayList<>();

    @Autowired
    private TaskDocumentFactory taskFactory;
    @Autowired
    private IncomingDocumentFactory incomingDocFactory;
    @Autowired
    private OutgoingDocumentFactory outgoingFactory;
    @Autowired
    private DocSave docSave;
    @ShellMethod(value = "generate Param(Int taskDocCount(default=10),Int incomingDocCount(default=10),Int outgoingDocCount(default=10)", key = "generate")
    public void generate(@ShellOption(defaultValue="10") String task, @ShellOption(defaultValue="10") String incoming, @ShellOption(defaultValue="10") String outgoing) {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("---------------------Сгенерированные документы---------------------");
        //Генерация поручений

        for (int i=0;i<Integer.valueOf(task);i++){
            BaseDocument taskDoc = taskFactory.createDocument();

            if(taskDoc!=null){
                docSave.docSave(taskDoc);
                System.out.println(taskDoc);
            }
        }
        //Генерация входящих сообщений
        for (int i=0;i<Integer.valueOf(incoming);i++){
            BaseDocument incomingDoc = incomingDocFactory.createDocument();
            if(incomingDoc!=null) {
                docSave.docSave(incomingDoc);
                System.out.println(incomingDoc);
            }
        }
        //Генерация исходящих сообщений
        for (int i=0;i<Integer.valueOf(outgoing);i++){
            BaseDocument outgoingDoc = outgoingFactory.createDocument();
            if(outgoingDoc!=null){
                docSave.docSave(outgoingDoc);
                System.out.println(outgoingDoc);}

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
        }
    }



}
