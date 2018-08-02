package cn.com.jnpc;

import cn.com.jnpc.dao.*;
import cn.com.jnpc.entity.*;
import cn.com.jnpc.service.SubmitAndApprovalRecordService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FireInspectionApplicationTests {

	@Autowired
	private EquipmentDao equipmentDao;
	@Autowired
	private LocationDao locationDao;
	@Autowired
	private ImportantPartRecordDao importantPartRecordDao;
	@Autowired
	private SubmitAndApprovalRecordService submitAndApprovalRecordService;
	@Autowired
	private IPRDefectDao iprDefectDao;
	@Test
	@Transactional
	public void contextLoads() throws IOException, DocumentException {
		Document document=new Document(PageSize.A4,50,50,30,20);
		String filePath="src/main/resources/static/test.pdf";
		PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream(filePath));
		document.open();
		BaseFont bf=BaseFont.createFont("C:\\Windows\\Fonts\\simkai.ttf", BaseFont.IDENTITY_H, false);
		Font font=new Font(bf,13,Font.NORMAL);
		Font font1=new Font(bf,15,Font.BOLD);
		PdfPTable table=new PdfPTable(7);
		table.setTotalWidth(new float[]{100,100,100,100,100,100,80});
		table.addCell(getPDFCell("姓名",font));
		table.addCell("");
		table.addCell(getPDFCell("性别",font));
		table.addCell("");
		table.addCell(getPDFCell("出生年月",font));
		table.addCell("");
		Image image=Image.getInstance("src/main/resources/static/assets/images/avatar-1.jpg");
		image.scaleAbsolute(57,75);
		PdfPCell cell = new PdfPCell(image);
		cell.setRowspan(3);
		table.addCell(cell);
		table.addCell(getPDFCell("学历",font));
		table.addCell("");
		table.addCell(getPDFCell("婚否",font));
		table.addCell("");
		table.addCell(getPDFCell("民族",font));
		table.addCell("");
		table.addCell(getPDFCell("专业",font));
		cell=mergeCol("", font, 2);
		table.addCell(cell);

		table.addCell(getPDFCell("毕业学校",font));
		cell=mergeCol("", font, 2);
		table.addCell(cell);

		table.addCell(getPDFCell("健康状况",font));
		cell=mergeCol("", font, 2);
		table.addCell(cell);

		table.addCell(getPDFCell("户籍所在地",font));
		cell=mergeCol("", font, 3);
		table.addCell(cell);

		table.addCell(getPDFCell("政治面貌",font));
		cell=mergeCol("", font, 2);
		table.addCell(cell);
		table.addCell(getPDFCell("身份证号码",font));
		cell=mergeCol("", font, 3);
		table.addCell(cell);

		table.addCell(getPDFCell("参加工作时间",font));
		cell=mergeCol("", font, 2);
		table.addCell(cell);

		table.addCell(getPDFCell("有无住房",font));
		table.addCell("");

		table.addCell(getPDFCell("要求待遇",font));
		table.addCell("");

		table.addCell(getPDFCell("联系电话",font));
		cell=mergeCol("", font, 2);
		table.addCell(cell);

		table.addCell(getPDFCell("电子邮件",font));
		table.addCell("");

		table.addCell(getPDFCell("手机",font));
		table.addCell("");

		table.addCell(getPDFCell("联系地址",font));
		cell=mergeCol("", font, 6);
		table.addCell(cell);

		table.addCell(getPDFCell("现工作所在地",font));
		cell=mergeCol("", font, 6);
		table.addCell(cell);

		table.addCell(getPDFCell("离职原因",font));
		cell=mergeCol("", font, 6);
		table.addCell(cell);

		cell=mergeRow("简历", font, 6);
		table.addCell(cell);

		table.addCell(getPDFCell("起止时间",font));

		cell=mergeCol("学习/工作单位", font, 3);
		table.addCell(cell);

		cell=mergeCol("专业/职位", font, 2);
		table.addCell(cell);

		for(int i=0;i<5;i++)
		{

			table.addCell(getPDFCell(" ",font));

			cell=mergeCol(" ", font, 3);
			table.addCell(cell);

			cell=mergeCol(" ", font, 2);
			table.addCell(cell);

		}


		cell=mergeRow("家庭情况", font, 6);
		table.addCell(cell);

		table.addCell(getPDFCell("姓名",font));
		table.addCell(getPDFCell("关系",font));
		table.addCell(getPDFCell("年龄",font));
		table.addCell(getPDFCell("文化程度",font));
		cell=mergeCol("现工作单位", font, 2);
		table.addCell(cell);

		for(int i=0;i<5;i++)
		{

			table.addCell(getPDFCell(" ",font));
			table.addCell(getPDFCell(" ",font));
			table.addCell(getPDFCell(" ",font));
			table.addCell(getPDFCell(" ",font));
			cell=mergeCol(" ", font, 2);
			table.addCell(cell);

		}


		table.addCell(getPDFCell("特别提示",font));

		cell=mergeCol("1. 本人承诺保证所填写资料真实。 \n2. 保证遵守公司招聘有关规程和国家有关法规。  \n3. 请填写好招聘登记表，带齐照片、学历、职称证书的有效证件及相关复印件。\n", font, 6);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		Paragraph title=new Paragraph("招聘人员登记表\n\n\n",font1);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);
		document.add(table);
		document.close();
		writer.close();

	}
	public static PdfPCell getPDFCell(String string, Font font)
	{
		//创建单元格对象，将内容与字体放入段落中作为单元格内容
		PdfPCell cell=new PdfPCell(new Paragraph(string,font));

		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		//设置最小单元格高度
		cell.setMinimumHeight(25);
		return cell;
	}
	//合并行的静态函数
	public static PdfPCell mergeRow(String str,Font font,int i) {

		//创建单元格对象，将内容及字体传入
		PdfPCell cell=new PdfPCell(new Paragraph(str,font));
		//设置单元格内容居中
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		//将该单元格所在列包括该单元格在内的i行单元格合并为一个单元格
		cell.setRowspan(i);

		return cell;
	}

	//合并列的静态函数
	public static PdfPCell mergeCol(String str,Font font,int i) {

		PdfPCell cell=new PdfPCell(new Paragraph(str,font));
		cell.setMinimumHeight(25);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		//将该单元格所在行包括该单元格在内的i列单元格合并为一个单元格
		cell.setColspan(i);

		return cell;
	}

	@Autowired
	private FireworkRecordDao fireworkRecordDao;
	@Autowired
	private FWDefectDao fwDefectDao;
	@Test
	public void test(){
		FWDefect defect=new FWDefect();
		List<FireworkRecord> list = fireworkRecordDao.findAllByDateAndName("2018-07-01", "尼古拉斯赵四", "1");
		FireworkRecord record1 = list.get(0);
		defect.setFireworkRecord(record1);
		defect.setMethod("zhuangtai");
		fwDefectDao.save(defect);
	}
}
