package cn.com.jnpc.service;


import cn.com.jnpc.dao.EquipmentDao;
import cn.com.jnpc.entity.Equipment;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;


import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cc on 2018/7/6.
 */
@Service
public class EquipService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Transactional
    public Page<Equipment> findAll(Pageable pageable){
        return equipmentDao.findAll(pageable);
    }

    /*public Page<Equipment> findByCondition(String unit, String factoryBuilding, String location, String kks, String name, String category, String enteringman, String updatetime, PageRequest pageRequest) {
        return equipmentDao.findByCondition(unit,factoryBuilding,location,kks,name,category,enteringman,updatetime,pageRequest);
    }*/
    @Transactional
    public Page<Equipment> findByCondition(String unit,String factoryBuilding,String location,String KKS,String name,String category,String enteringman,String updatetimestart,String updatetimeend,PageRequest pageRequest){
        return equipmentDao.findAll(new Specification<Equipment>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Equipment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list =new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(unit)&&unit!=""){
                    list.add(criteriaBuilder.like(root.get("unit").as(String.class),"%"+unit+"%"));
                }
                if (!StringUtils.isEmpty(factoryBuilding)&&factoryBuilding!=""){
                    list.add(criteriaBuilder.like(root.get("factoryBuilding").as(String.class),"%"+factoryBuilding+"%"));
                }
                if (!StringUtils.isEmpty(location)&&location!=""){
                    list.add(criteriaBuilder.like(root.get("location").as(String.class),"%"+location+"%"));
                }
                if (!StringUtils.isEmpty(KKS)&&KKS!=""){
                    list.add(criteriaBuilder.like(root.get("kks").as(String.class),"%"+KKS+"%"));
                }
                if (!StringUtils.isEmpty(name)&&name!=""){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+name+"%"));
                }
                if (!StringUtils.isEmpty(category)&&category!=""){
                    list.add(criteriaBuilder.like(root.get("category").as(String.class),"%"+category+"%"));
                }
                if (!StringUtils.isEmpty(enteringman)&&enteringman!=""){
                    list.add(criteriaBuilder.like(root.get("enteringman").as(String.class),"%"+enteringman+"%"));
                }
                if (!StringUtils.isEmpty(updatetimeend)&&updatetimeend!=""&&!StringUtils.isEmpty(updatetimestart)&&updatetimestart!=""){
                    list.add(criteriaBuilder.between(root.get("configurationtime").as(String .class),updatetimestart,updatetimeend));
                }

                Predicate[] p=new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageRequest);
    }

    public void add(Equipment equipment) {
        equipmentDao.save(equipment);
    }

    @Transactional
    public void deleteEquipByID(String id) {
        equipmentDao.deleteById(id);
    }
    @Transactional
    public boolean importFile(String filename, MultipartFile file) {
        if (!filename.matches("^.+\\.(?i)(xls)$") && !filename.matches("^.+\\.(?i)(xlsx)$")){
            return false;
        }
        boolean isExcel2003 = true;
        if (filename.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream stream = null;
        try {
            stream = file.getInputStream();
            Workbook wb=null;
            if (isExcel2003){
                wb=new HSSFWorkbook(stream);
            }else {
                wb=new XSSFWorkbook(stream);
            }
            Sheet sheet=wb.getSheetAt(0);
            Equipment equipment;
            for (int r=1;r<=sheet.getLastRowNum();r++){
                Row row=sheet.getRow(r);
                if (row==null){
                    continue;
                }
                equipment=new Equipment();
                equipment.setUnit(row.getCell(0).getStringCellValue());
                equipment.setFactoryBuilding(row.getCell(1).getStringCellValue());
                equipment.setLocation(row.getCell(2).getStringCellValue());
                equipment.setKks(row.getCell(3).getStringCellValue());
                equipment.setName(row.getCell(4).getStringCellValue());
                equipment.setCategory(row.getCell(5).getStringCellValue());
                Date date = row.getCell(6).getDateCellValue();
                DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                String s = format.format(date);
                equipment.setConfigurationtime(s);
                equipment.setCheckcycle(row.getCell(7).getStringCellValue());
                equipment.setTestcycle(row.getCell(8).getStringCellValue());
                equipment.setReplacecycle(row.getCell(9).getStringCellValue());
                equipment.setEnteringman(row.getCell(10).getStringCellValue());
                String s1 = format.format(row.getCell(11).getDateCellValue());
                equipment.setUpdatetime(s1);
                equipmentDao.save(equipment);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Equipment> findAllByCondition(String unit, String checkproject) {
        return equipmentDao.findAllByCondition(unit,checkproject);
    }
}
