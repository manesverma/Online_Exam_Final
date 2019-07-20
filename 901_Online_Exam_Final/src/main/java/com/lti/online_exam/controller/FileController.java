package com.lti.online_exam.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lti.online_exam.exception.ExamException;
import com.lti.online_exam.model.Document;
import com.lti.online_exam.model.Question;
import com.lti.online_exam.service.IFileStorageService;
import com.lti.online_exam.service.IQuestionService;

@Controller
@RequestMapping(value = "/file")
public class FileController {

	@Autowired
	private IFileStorageService fileStorageService;
	
	@Autowired
	private IQuestionService questionService;

	/* private static String UPLOADED_FOLDER = "/static/documents/"; */
	@RequestMapping("/")
	public String homePage(Model model) {
		model.addAttribute("msg", "HomePage");
		return "homePage";
	}

	@GetMapping(value = "/uploadDocument")
	public String documentUpload(Model model) {
		// return model and view
		model.addAttribute("msg", "Document upload page");
		model.addAttribute("today", LocalDate.now());
		// model object associated with name 'msg'
		return "uploadPage";// view name which will be returned to dispacherServlet
	}

	@PostMapping("/upload") // //new annotation since 4.3
	public String singleFileUpload(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request)
			throws IOException, ExamException {
		System.out.println(String.format("File name %s", file.getName()));
		System.out.println(String.format("File original name %s", file.getOriginalFilename()));
		System.out.println(String.format("File size %s", file.getSize()));
		File fileDest = new File("d:/backup/" + file.getOriginalFilename());
		System.out.println("fileDest :" + fileDest);
		String questionDesc = "";
		String questionSubject = "";
		String optionFirst = "";
		String optionSecond = "";
		String optionThird = "";
		String optionFourth = "";
		String correctOption = "";
		Integer questionLevel = null;
		Question question = null;
		// do whatever you want with the MultipartFile
		if (file.isEmpty()) {
			model.addAttribute("msg", "Document name empty ");
			return "redirect:/uploadDocument";
		}
		// read byte by byte using buffer
		System.out.println("\nReading byte from the file using buffer : " + file.getOriginalFilename()
				+ " \n Writing byte to the file using buffer : " + fileDest.getAbsolutePath());
		try (// read byte by byte using buffer
				BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileDest));) {
				int b = 0;
				while ((b = bis.read()) != -1) {// read from buffer byte byte till EOF (-1)
				// write to buffer byte by byte
				// System.out.println(b);
				bos.write(b);
				bos.flush();// clear the cache
				}
				// file= this.fileStorageService.addCustomerDoc(document);

				} catch (IOException e) {
				e.printStackTrace();
				}
		try {

            FileInputStream excelFile = new FileInputStream(fileDest);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                Cell currentCell = null;

                /*while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellType() == CellType.STRING)
                    {
                        System.out.print(currentCell.getStringCellValue() + "\n\n");
                    } 
                    else if (currentCell.getCellType() == CellType.NUMERIC) 
                    {
                    	
                        System.out.print(currentCell.getNumericCellValue() + "\n\n");
                    }
                    System.out.println("****************************************************************************\n\n");
                }*/
                if(cellIterator.hasNext()) {
                	currentCell = cellIterator.next();
                	questionDesc = currentCell.getStringCellValue();
                	currentCell = cellIterator.next();
                	correctOption = currentCell.getStringCellValue();
                	currentCell = cellIterator.next();
                	optionFirst = currentCell.getStringCellValue();
                	currentCell = cellIterator.next();
                	optionSecond = currentCell.getStringCellValue();
                	currentCell = cellIterator.next();
                	optionThird = currentCell.getStringCellValue();
                	currentCell = cellIterator.next();
                	optionFourth = currentCell.getStringCellValue();
                	currentCell = cellIterator.next();
                	questionSubject = currentCell.getStringCellValue();
                	currentCell = cellIterator.next();
                	questionLevel = (int) currentCell.getNumericCellValue();
                	question = new Question(questionDesc, questionSubject, optionFirst, optionSecond, optionThird, optionFourth, correctOption, questionLevel);
                	questionService.addQuestion(question);
                }
                System.out.println("****************************************************************************");
                System.out.println("**************************************************************************** 	");

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		Document doc = new Document();
		doc.setDocName(file.getOriginalFilename());
		doc.setDocUri("d:/backup/" + file.getOriginalFilename());
		// Document document = fileStorageService.addQuestionDoc(doc);
		// System.out.println("document at controller : " + document);
		model.addAttribute("msg", "Document upload success");
		return "displayExcelQuestionPage";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "displayExcelQuestionPage";
	}

}