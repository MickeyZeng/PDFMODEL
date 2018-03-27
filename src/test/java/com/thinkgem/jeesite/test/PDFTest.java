package com.thinkgem.jeesite.test;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.*;
import java.util.Map;
import java.util.Set;

public class PDFTest {



    public static void main(String args[]) throws IOException, DocumentException {
        String filename = "/Users/mickey/document/PDFModel/PDFTest/yyzz_a4_copy.pdf";
        String filename1 = "/Users/mickey/document/PDFModel/PDFTest/Test.pdf";
        FileOutputStream out= null;
        PdfReader reader=null;
        PdfStamper pdfStamper=null;
        out = new FileOutputStream(new File(filename1));
        reader=new PdfReader(new FileInputStream(new File(filename)));
        pdfStamper=new PdfStamper(reader,out);
        AcroFields s = pdfStamper.getAcroFields();
        Map<String, AcroFields.Item> acroFieldMap=s.getFields();
        Set<String> keySet = acroFieldMap.keySet();
        Object[] keySetStr = keySet.toArray();
        for (Object s1 : keySetStr) {
            System.out.println(s1.toString());
        }
        pdfStamper.close();
        out.close();
    }

}
