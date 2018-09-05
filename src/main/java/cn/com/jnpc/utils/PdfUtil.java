package cn.com.jnpc.utils;

import cn.com.jnpc.entity.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by cc on 2018/7/18.
 */
public class PdfUtil {


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

    public static void createPDF(List<ImportantPartRecord> importantPartRecords, List<FireworkRecord> fireworkRecords, List<DepositaryRecord> depositaryRecords, List<FireproofdoorRecord> fireproofdoorRecords, List<PersonnelviolationRecord> personnelviolationRecords, List<OtherRecord> otherRecords,SubmitAndApprovalRecord srecord) throws IOException, DocumentException {
        //创建Document对象
        Document doc=new Document(PageSize.A4,0,0,50,0);
        //获得PdfWriter实例，将文档放到输出流上
        String filePath="src/main/resources/static/PDF/"+srecord.getId()+".pdf";
        PdfWriter writer=PdfWriter.getInstance(doc, new FileOutputStream(filePath));
        doc.open();
        //字体设置
        BaseFont bf=BaseFont.createFont("C:\\Windows\\Fonts\\simkai.ttf", BaseFont.IDENTITY_H, false);
        //创建Font对象，将基础字体对象，字体大小，字体风格
        Font font=new Font(bf,13,Font.NORMAL);
        Font font1=new Font(bf,15,Font.BOLD);

        Paragraph title=new Paragraph(srecord.getUnit()+"号机组消防监督巡查记录表("+srecord.getApprovaldate()+")\n\n",font1);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(title);
        Paragraph line=new Paragraph("        检查人："+srecord.getSubmiter()+"/"+srecord.getCheckdate()+"             "+"批准人："+srecord.getApprovaler()+"/"+srecord.getApprovaldate()+"\n\n",font);
        doc.add(line);
        PdfPCell cell=new PdfPCell();
        PdfPTable table;
        //创建PdfTable1对象
        table=new PdfPTable(12);
        cell=mergeCol("消防重点位置",font,12);
        table.addCell(cell);
        table.addCell(getPDFCell("序号",font));
        table.addCell(getPDFCell("机组",font));
        table.addCell(getPDFCell("厂房",font));
        table.addCell(getPDFCell("位置",font));
        table.addCell(getPDFCell("状态",font));
        table.addCell(getPDFCell("测量值",font));
        table.addCell(getPDFCell("检查时间",font));
        table.addCell(getPDFCell("检查人",font));
        table.addCell(getPDFCell("备注",font));
        table.addCell(getPDFCell("缺陷描述",font));
        table.addCell(getPDFCell("整改方式",font));
        table.addCell(getPDFCell("跟踪序号",font));
        for (int i = 0; i <importantPartRecords.size() ; i++) {
            ImportantPartRecord record=importantPartRecords.get(i);
            int size = record.getIPRdefects().size();
            cell=mergeRow(i+1+"",font,size);
            table.addCell(cell);
            cell=mergeRow(record.getUnit(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getFactoryBuilding(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getLocation(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getState()==1?"√":"×",font,size);
            table.addCell(cell);
            cell=mergeRow(record.getMeasurements(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getChecktime(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getChecker(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getAttachment()==1?"有附件":"无附件",font,size);
            table.addCell(cell);
            for(IPRDefect defect:record.getIPRdefects()){
                table.addCell(getPDFCell(defect.getDefectdesc(),font));
                table.addCell(getPDFCell(defect.getMethod(),font));
                table.addCell(getPDFCell(defect.getTracenumber(),font));
            }
        }
        doc.add(table);
        //创建PdfTable1对象
        table=new PdfPTable(13);
        cell=mergeCol("动火作业",font,13);
        table.addCell(cell);
        table.addCell(getPDFCell("序号",font));
        table.addCell(getPDFCell("机组",font));
        table.addCell(getPDFCell("厂房",font));
        table.addCell(getPDFCell("位置",font));
        table.addCell(getPDFCell("动火证号",font));
        table.addCell(getPDFCell("工作负责人",font));
        table.addCell(getPDFCell("状态",font));
        table.addCell(getPDFCell("检查时间",font));
        table.addCell(getPDFCell("检查人",font));
        table.addCell(getPDFCell("备注",font));
        table.addCell(getPDFCell("缺陷描述",font));
        table.addCell(getPDFCell("整改方式",font));
        table.addCell(getPDFCell("跟踪序号",font));

        for (int i = 0; i <fireworkRecords.size() ; i++) {
            FireworkRecord record=fireworkRecords.get(i);
            int size = record.getFWDefects().size();
            cell=mergeRow(i+1+"",font,size);
            table.addCell(cell);
            cell=mergeRow(record.getUnit(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getFactoryBuilding(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getLocation(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getFireworkNumber(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getJobmanager(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getState()==1?"√":"×",font,size);
            table.addCell(cell);
            cell=mergeRow(record.getChecktime(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getChecker(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getAttachment()==1?"有附件":"无附件",font,size);
            table.addCell(cell);
            for(FWDefect defect:record.getFWDefects()){
                table.addCell(getPDFCell(defect.getDefectdesc(),font));
                table.addCell(getPDFCell(defect.getMethod(),font));
                table.addCell(getPDFCell(defect.getTracenumber(),font));
            }
        }
        doc.add(table);

        //创建PdfTable1对象
        table=new PdfPTable(10);
        cell=mergeCol("物品物料/易燃易爆危险化学品存放",font,10);
        table.addCell(cell);
        table.addCell(getPDFCell("序号",font));
        table.addCell(getPDFCell("机组",font));
        table.addCell(getPDFCell("厂房",font));
        table.addCell(getPDFCell("位置",font));
        table.addCell(getPDFCell("检查时间",font));
        table.addCell(getPDFCell("检查人",font));
        table.addCell(getPDFCell("备注",font));
        table.addCell(getPDFCell("缺陷描述",font));
        table.addCell(getPDFCell("整改方式",font));
        table.addCell(getPDFCell("跟踪序号",font));

        for (int i = 0; i <depositaryRecords.size() ; i++) {
            DepositaryRecord record=depositaryRecords.get(i);
            int size = record.getDpDefects().size();
            cell=mergeRow(i+1+"",font,size);
            table.addCell(cell);
            cell=mergeRow(record.getUnit(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getFactoryBuilding(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getLocation(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getChecktime(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getChecker(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getAttachment()==1?"有附件":"无附件",font,size);
            table.addCell(cell);
            for(DPDefect defect:record.getDpDefects()){
                table.addCell(getPDFCell(defect.getDefectdesc(),font));
                table.addCell(getPDFCell(defect.getMethod(),font));
                table.addCell(getPDFCell(defect.getTracenumber(),font));
            }
        }
        doc.add(table);

        //创建PdfTable1对象
        table=new PdfPTable(13);
        cell=mergeCol("防火门",font,13);
        table.addCell(cell);
        table.addCell(getPDFCell("序号",font));
        table.addCell(getPDFCell("机组",font));
        table.addCell(getPDFCell("厂房",font));
        table.addCell(getPDFCell("位置",font));
        table.addCell(getPDFCell("kks码",font));
        table.addCell(getPDFCell("名称",font));
        table.addCell(getPDFCell("状态",font));
        table.addCell(getPDFCell("检查时间",font));
        table.addCell(getPDFCell("检查人",font));
        table.addCell(getPDFCell("备注",font));
        table.addCell(getPDFCell("缺陷描述",font));
        table.addCell(getPDFCell("整改方式",font));
        table.addCell(getPDFCell("跟踪序号",font));

        for (int i = 0; i <fireproofdoorRecords.size() ; i++) {
            FireproofdoorRecord record=fireproofdoorRecords.get(i);
            int size = record.getFdDefects().size();
            cell=mergeRow(i+1+"",font,size);
            table.addCell(cell);
            cell=mergeRow(record.getUnit(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getFactoryBuilding(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getLocation(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getKks(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getName(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getState()==1?"√":"×",font,size);
            table.addCell(cell);
            cell=mergeRow(record.getChecktime(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getChecker(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getAttachment()==1?"有附件":"无附件",font,size);
            table.addCell(cell);
            for(FDDefect defect:record.getFdDefects()){
                table.addCell(getPDFCell(defect.getDefectdesc(),font));
                table.addCell(getPDFCell(defect.getMethod(),font));
                table.addCell(getPDFCell(defect.getTracenumber(),font));
            }
        }
        doc.add(table);

        //创建PdfTable1对象
        table=new PdfPTable(13);
        cell=mergeCol("人员违章",font,13);
        table.addCell(cell);
        table.addCell(getPDFCell("序号",font));
        table.addCell(getPDFCell("机组",font));
        table.addCell(getPDFCell("厂房",font));
        table.addCell(getPDFCell("位置",font));
        table.addCell(getPDFCell("姓名",font));
        table.addCell(getPDFCell("单位",font));
        table.addCell(getPDFCell("通行卡号",font));
        table.addCell(getPDFCell("检查时间",font));
        table.addCell(getPDFCell("检查人",font));
        table.addCell(getPDFCell("备注",font));
        table.addCell(getPDFCell("缺陷描述",font));
        table.addCell(getPDFCell("整改方式",font));
        table.addCell(getPDFCell("跟踪序号",font));

        for (int i = 0; i <personnelviolationRecords.size() ; i++) {
            PersonnelviolationRecord record=personnelviolationRecords.get(i);
            int size = record.getPvDefects().size();
            cell=mergeRow(i+1+"",font,size);
            table.addCell(cell);
            cell=mergeRow(record.getUnit(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getFactoryBuilding(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getLocation(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getPersonname(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getCompany(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getPassnumber(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getChecktime(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getChecker(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getAttachment()==1?"有附件":"无附件",font,size);
            table.addCell(cell);
            for(PVDefect defect:record.getPvDefects()){
                table.addCell(getPDFCell(defect.getDefectdesc(),font));
                table.addCell(getPDFCell(defect.getMethod(),font));
                table.addCell(getPDFCell(defect.getTracenumber(),font));
            }
        }
        doc.add(table);
        //创建PdfTable1对象
        table=new PdfPTable(10);
        cell=mergeCol("其他问题",font,10);
        table.addCell(cell);
        table.addCell(getPDFCell("序号",font));
        table.addCell(getPDFCell("机组",font));
        table.addCell(getPDFCell("厂房",font));
        table.addCell(getPDFCell("位置",font));
        table.addCell(getPDFCell("检查时间",font));
        table.addCell(getPDFCell("检查人",font));
        table.addCell(getPDFCell("备注",font));
        table.addCell(getPDFCell("缺陷描述",font));
        table.addCell(getPDFCell("整改方式",font));
        table.addCell(getPDFCell("跟踪序号",font));

        for (int i = 0; i <otherRecords.size() ; i++) {
            OtherRecord record=otherRecords.get(i);
            int size = record.getoDefects().size();
            cell=mergeRow(i+1+"",font,size);
            table.addCell(cell);
            cell=mergeRow(record.getUnit(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getFactoryBuilding(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getLocation(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getChecktime(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getChecker(),font,size);
            table.addCell(cell);
            cell=mergeRow(record.getAttachment()==1?"有附件":"无附件",font,size);
            table.addCell(cell);
            for(ODefect defect:record.getoDefects()){
                table.addCell(getPDFCell(defect.getDefectdesc(),font));
                table.addCell(getPDFCell(defect.getMethod(),font));
                table.addCell(getPDFCell(defect.getTracenumber(),font));
            }
        }
        doc.add(table);
        doc.close();
        writer.close();
    }
}
